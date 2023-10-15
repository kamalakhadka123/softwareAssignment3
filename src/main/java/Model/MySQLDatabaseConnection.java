
package Model;
import com.mycompany.healthcaremanagementsystem.PatientUpdate;
import com.mycompany.healthcaremanagementsystem.ShowAnalytics;
import com.mycompany.healthcaremanagementsystem.ShowBills;
import com.mycompany.healthcaremanagementsystem.UpdateUser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class MySQLDatabaseConnection {
    
    private String url;
    private String username;
    private String password;
    private Connection c;
    private String printBill;
    private PatientEntity patient;

    public MySQLDatabaseConnection(String username, String password)
    {
            String dbUrl = "jdbc:mysql://localhost:3306";

       try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
                    Statement s = connection.createStatement();
                    s.addBatch(SQLQueries.DROP_DATABASE_IF_EXIST);
                    s.addBatch(SQLQueries.CREATE_NEW_DATABASE);
                    s.executeBatch();
                    this.url = dbUrl+"/healthcaredb";
                    this.username = username;
                    this.password = password;

                    s.close();
                }
         catch (Exception e) {
            e.printStackTrace();
        }
       generateTables();
       alterTableAutoIncrement();
       LocalDate date = LocalDate.now();
        
       UsersEntity admin = new UsersEntity(-1,"admin", "x", "Male", java.sql.Date.valueOf(date), "admin@123mail.com", "a", "Admin");
       UsersEntity medicalStaff = new UsersEntity(-1,"Medical", "Staff", "Male", java.sql.Date.valueOf(date), "medicalStaff@123mail.com", "a", "Medical Staff");
       PatientEntity p = new PatientEntity(-1, "Patient", "Patient", "Male", java.sql.Date.valueOf(date), "123 main Street", "123 049 0599", "9867 87362 4", "None");
       addUser(admin);
       addUser(medicalStaff);
        addPatient(p);
    }
    
    // secure a database connnection
    private Connection establishConnection()
    {
        Connection c = null;
        try {
            c = DriverManager.getConnection(url, username, password);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
    private void generateTables()
    {
        try {
            
            Connection c = establishConnection();
            Statement s = c.createStatement();
            String ut = SQLQueries.USER_ENTITY_TABLE;
            String pt = SQLQueries.CREATE_PATIENT_TABLE;
            String at = SQLQueries.CREATE_APPOINTMENT_TABLE;
            String bt = SQLQueries.CREATE_INVOICE_TABLE;
            
            s.addBatch(ut);
            s.addBatch(pt);
            s.addBatch(at);
            s.addBatch(bt);
            
            s.executeBatch();
      
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alterTableAutoIncrement()
    {
        try {
            Connection c = establishConnection();
            Statement s = c.createStatement();
            s.addBatch(SQLQueries.USER_ID_AUTO_INCREMENT);
            s.addBatch(SQLQueries.PATIENT_TABLE_AUTO_INCREMENT);
            s.addBatch(SQLQueries.INVOICE_TABLE_AUTO_INCREMENT);
            s.addBatch(SQLQueries.APPOINTMENT_TABLE_AUTO_INCREMENT);
            
            s.executeBatch();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public UsersEntity addUser(UsersEntity admin)
    {
        try {
            
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.INSERT_INTO_USER_TABLE);
            
            
            ps.setString(1,admin.getFirstName());
            ps.setString(2,admin.getLastName());
            ps.setString(3,admin.getGender());
            ps.setDate(4,admin.getDateOfBirth());
            ps.setString(5,admin.getEmail());
            ps.setString(6,admin.getPassword());
            ps.setString(7,admin.getRole());
            
            int ra = ps.executeUpdate();
            Statement s = c.createStatement();

            if (ra > 0) {
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    long id = rs.getInt(1);
                    admin.setId(id);
                }
            }
            
            ps.close();
            
                     
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
    
    
    public UsersEntity modifyUserByID(UsersEntity admin)
    {
        try {
            
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.MODIFY_USER_BY_ID);
            
            
            ps.setString(1,admin.getFirstName());
            ps.setString(2,admin.getLastName());
            ps.setString(3,admin.getGender());
            ps.setDate(4,admin.getDateOfBirth());
            ps.setString(5,admin.getEmail());
            ps.setString(6,admin.getPassword());
            ps.setString(7,admin.getRole());
            ps.setLong(8, admin.getId());
            
            ps.executeUpdate();
            ps.close();
            
                     
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
    
    
    public UsersEntity selectUserByID(long id)
    {
        UsersEntity userEntity = null;
        try {
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.GET_USER_BY_ID);
            ps.setLong(1, id);
            
            ResultSet rs= ps.executeQuery();
            if(rs.next())
            {
                userEntity = new UsersEntity(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
            else{
                return userEntity;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        return userEntity;
    }
    
    public void deleteUser(long userID)
    {
        
        try {
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.REMOVE_USER_BY_ID);
            ps.setLong(1, userID);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    }
    
    
    public PatientEntity addPatient(PatientEntity patient)
    {
        try {
            
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.ADD_PATIENT);
            
            
            ps.setString(1,patient.getFirstname());
            ps.setString(2,patient.getLastname());
            ps.setString(3,patient.getGender());
            ps.setDate(4,patient.getDateOfBirth());
            ps.setString(5,patient.getAddress());
            ps.setString(6,patient.getContactNum());
            ps.setString(7,patient.getMedicareNumber());
            ps.setString(8,patient.getMedicalHistory());

            
            int ra = ps.executeUpdate();
            Statement s = c.createStatement();

            if (ra > 0) {
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    long id = rs.getInt(1);
                    patient.setPatientId(id);
                }
            }
            
            ps.close();
            
                     
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
        
    }
    
    public PatientEntity selectPatient(long id)
    {
        PatientEntity patient = null;
        try {
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.SEARCH_PATIENT);
            ps.setLong(1, id);
            
            ResultSet rs= ps.executeQuery();
            if(rs.next())
            {
                patient = new PatientEntity(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
            }
            else{
                return patient;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        return patient;
    }
    
    
   public PatientEntity modifyPatient(PatientEntity patient)
    {
        try {
            
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.UPDATE_PATIENT);
            
            
            ps.setString(1,patient.getFirstname());
            ps.setString(2,patient.getLastname());
            ps.setString(3,patient.getGender());
            ps.setDate(4,patient.getDateOfBirth());
            ps.setString(5,patient.getAddress());
            ps.setString(6,patient.getContactNum());
            ps.setString(7,patient.getMedicareNumber());
            ps.setString(8, patient.getMedicalHistory());
            ps.setLong(9, patient.getPatientId());
            
            ps.executeUpdate();
            ps.close();
            
                     
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
    }
   
   public AppointmentEntity addAppointmentData(AppointmentEntity a)
    {
        try {
            
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.ADD_APPOINTMENT);
            
            
            ps.setDate(1,a.getAppointmentDate());
            ps.setString(2,a.getBookingService());
            ps.setString(3,a.getBookingTime());
            ps.setLong(4,a.getPatientID());


            int ra = ps.executeUpdate();
            Statement s = c.createStatement();

            if (ra > 0) {
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    long id = rs.getInt(1);
                    a.setAppointmentID(id);
                }
            }
            
            ps.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
        
    }
   
   
    public boolean searchAppointment(AppointmentEntity ae)
    {
        boolean result = false;
        try {
            
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.SEARCH_APPOINTMENT);
            ps.setString(1,ae.getBookingService());
            ps.setDate(2,ae.getAppointmentDate());
            ps.setString(3,ae.getBookingTime());
            ResultSet rs = ps.executeQuery();         

            result =  rs.next();
           ps.close();
  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public BillingInvoiceEntity addBillingInvoice(BillingInvoiceEntity bi)
    {
        try {
            
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.ADD_INVOICE);    
            ps.setDouble(1,bi.getAmountDue());
            ps.setDate(2,bi.getInvoiceDate());
            ps.setString(3,bi.getServiceProvided());
            ps.setLong(4,bi.getAppointmentID());
            ps.setLong(5,bi.getPatientID());


            int ra = ps.executeUpdate();
            Statement s = c.createStatement();

            if (ra > 0) {
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    long id = rs.getInt(1);
                    bi.setInvoiceID(id);
                }
            }
            
            ps.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bi;
        
    }
    
    
    public void updateUserViewSet(UpdateUser uu, UsersEntity u)
    {
        uu.getFirstnameField().setText(u.getFirstName());
        uu.getLastnameField().setText(u.getLastName());
        uu.getGenderField().setText(u.getGender());
        uu.getDateOfBirthField().setText(u.getDateOfBirth().toString());
        uu.getEmailField().setText(u.getEmail());
        uu.getPasswordField().setText(u.getPassword());
        uu.getConfirmPassField().setText(u.getPassword());
        uu.getRoleField().setText(u.getRole());
    }
    
    public void updatePatientViewSet(PatientUpdate upc, PatientEntity p)
    {
        upc.getFirstnameField().setText(p.getFirstname());
        upc.getLastnameField().setText(p.getLastname());
        upc.getGenderMenu().setText(p.getGender());
        upc.getDateOfBirthField().setValue(p.getDateOfBirth().toLocalDate());
        upc.getAddressField().setText(p.getAddress());
        upc.getContactNumberField().setText(p.getContactNum());
        upc.getMedicareCardField().setText(p.getMedicareNumber());
        upc.getMedicalHistoryField().setText(p.getMedicalHistory());
    }
    
    public List<BillingInvoiceEntity> getAllPatientInvoices(PatientEntity p)
    {
        List<BillingInvoiceEntity> list = new ArrayList<>();
        try {
            
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.GET_ALL_PATIENT_INVOICE);
            
            
            ps.setLong(1,p.getPatientId());


            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                BillingInvoiceEntity i = new BillingInvoiceEntity(rs.getLong(1), rs.getDate(3), rs.getString(4), rs.getLong(5), rs.getLong(6));
                list.add(i);
            }
            
            ps.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<BillingInvoiceEntity> getAllInvoices()
    {
        List<BillingInvoiceEntity> list = new ArrayList<>();
        try {
            
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.GET_ALL_INVOICE);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                BillingInvoiceEntity i = new BillingInvoiceEntity(rs.getLong(1), rs.getDate(3), rs.getString(4), rs.getLong(5), rs.getLong(6));
                list.add(i);
            }
            
            ps.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
    public List<PatientEntity> getAllPatients()
    {
        List<PatientEntity> list = new ArrayList<>();
        try {
            
            Connection c = establishConnection();
            PreparedStatement ps = c.prepareStatement(SQLQueries.GET_PATIENTS);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
              PatientEntity  p = new PatientEntity(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));

                list.add(p);
            }
            
            ps.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
        
        
    public void setBillView(ShowBills vbc, PatientEntity p)
    {
        String bill="";
        vbc.getPatientNameLabel().setText(p.getFirstname()+" "+p.getLastname());
        List<BillingInvoiceEntity> li = getAllPatientInvoices(p);
        vbc.getBillArea().appendText("\n");
        String billView = "";
        double grandTotal = 0.0;
        for(BillingInvoiceEntity i:li)
        {
            billView += "Invoice ID: "+i.getInvoiceID()+"		 Booking ID: "+i.getAppointmentID()+"\n" +
                        "\n" +
                        "Booking Date: "+i.getInvoiceDate()+"\n" +
                        "------------------------------------------------------\n" +
                        "Service Selected: "+i.getServiceProvided()+"\n" +
                        "Charges: $ "+i.getAmountDue()+"\n" +
                        "GST: $ "+i.getGST()+"\n" +
                        "Sub Total: $ "+i.getTotal()+"\n\n";
            grandTotal += Double.parseDouble(i.getTotal());
        }
        vbc.getBillArea().appendText(billView);
        vbc.getBillArea().appendText("\n");

        vbc.getBillArea().appendText("======================================================\n"+""
                + "Total: $ "+grandTotal);

        printBill = vbc.getBillArea().getText();
        patient = p;
    }
    
    public void printPatientBill()
    {
        try {
            String fileName = patient.getFirstname()+" "+patient.getLastname();
            Formatter f = new Formatter(fileName);
            f.format(printBill);
            f.close();
        } catch (Exception e) {
        }
    }
    
    public void setAnalyticsView(ShowAnalytics vac)
    {
        // All Patients
        List<PatientEntity> patientList = getAllPatients();
        List<BillingInvoiceEntity> invoiceList = MySQLDatabaseConnection.this.getAllInvoices();
        
        int totalPatient = patientList.size();
        int totalAppointments = invoiceList.size();
        double totalRevenue = 0;
        double totalGST = 0;
        double averageIncome = 0;
        double grossTotal = 0;
        
        int consultationNum = 0;
        int admittanceNum = 0;
        int operationNum = 0;
        int healthCheckupNum = 0;
        
        double consultation = 0;
        double admittance = 0;
        double operation = 0;
        double healthCheckup = 0;
        
        
        for(BillingInvoiceEntity i:invoiceList)
        {
            totalRevenue += i.getAmountDue();
            totalGST += Double.parseDouble(i.getGST());
            grossTotal += Double.parseDouble(i.getTotal());
            if(i.getServiceProvided().equalsIgnoreCase("Consultation"))
            {
                consultationNum++;
                consultation += i.getAmountDue();
            }
            else if(i.getServiceProvided().equalsIgnoreCase("Admittance"))
            {
                admittanceNum++;
                admittance += i.getAmountDue();
            }
            else if(i.getServiceProvided().equalsIgnoreCase("Operation"))
            {
                operationNum++;
                operation += i.getAmountDue();
            }
            else if(i.getServiceProvided().equalsIgnoreCase("Health Checkup"))
            {
                healthCheckupNum++;
                healthCheckup += i.getAmountDue();
            }
        }
        
        averageIncome = totalRevenue/totalPatient;
        
        LocalDate now = LocalDate.now();
        vac.getAnalyticsArea().appendText("1 ->	INCOME:\n" +
                                            "\n" +
                                            "\n" +
                                            "	Total Patients : "+totalPatient+"\n" +
                                            "\n" +
                                            "	Total Appointements : "+totalAppointments+"\n" +
                                            "\n" +
                                            "	Total Revenue : $ "+totalRevenue+"\n" +
                                            "\n" +
                                            "	GST : $ "+totalGST+"\n" +
                                            "\n" +
                                            "	Total : $ "+grossTotal+" \n" +
                                            "\n" +
                                            "\n" +
                                            "\n" +
                                            "2 ->    Services Used\n" +
                                            "\n" +
                                            "\n" +
                                            "	Consultation : "+consultationNum+"\n" +
                                            "\n" +
                                            "	Admittance : "+admittanceNum+"\n" +
                                            "\n" +
                                            "	Operation : "+operationNum+"\n" +
                                            "\n" +
                                            "	Health Checkup : "+healthCheckupNum+"\n" +
                                            "\n" +
                                            "\n" +
                                            "\n" +
                                            "3 ->    Distributed Income By Service\n" +
                                            "\n" +
                                            "\n" +
                                            "	Consultation : $ "+consultation+" \n" +
                                            "\n" +
                                            "	Admittance : $ "+admittance+" \n" +
                                            "\n" +
                                            "	Operation : $ "+operation+" \n" +
                                            "\n" +
                                            "	Health Checkup : $ "+healthCheckup+" \n" +
                                            "\n" +
                                            "	Total Income : $ "+(consultation+admittance+operation+healthCheckup)+" ");
        
    }
 
   
    
}
