
package Model;

import java.sql.Date;


public class BillingInvoiceEntity {
    private long ID;
    private double invoiceAmount;
    private java.sql.Date date;
    private String serviceName;
    private long appointmentID;
    private long patientID;
    private final double CONSULTATION = 180.0;
    private final double ADMITTANCE = 3800.0;
    private final double OPERATION = 10000.0;
    private final double HEALTH_CHECKUP = 120.0;


    public BillingInvoiceEntity(long invoiceID, java.sql.Date invoiceDate, String serviceProvided, long appointmentID, long patientID) {
        this.ID = invoiceID;
        this.date = invoiceDate;
        this.serviceName = serviceProvided;
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        calculatePrice();
    }

    public long getInvoiceID() {
        return ID;
    }

    public void setInvoiceID(long invoiceID) {
        this.ID = invoiceID;
    }

    public double getAmountDue() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double amountDue) {
        this.invoiceAmount = amountDue;
    }
    
    private void calculatePrice()
    {
        if(getServiceProvided().equalsIgnoreCase("Consultation"))
        {
            setInvoiceAmount(CONSULTATION);
        }
        else if(getServiceProvided().equalsIgnoreCase("Admittance"))
        {
            setInvoiceAmount(ADMITTANCE);
        }
        else if(getServiceProvided().equalsIgnoreCase("Operation"))
        {
            setInvoiceAmount(OPERATION);
        }
        else if(getServiceProvided().equalsIgnoreCase("Health Checkup"))
        {
            setInvoiceAmount(HEALTH_CHECKUP);
        }
        else{
            setInvoiceAmount(100.0);
        }
    }

    public java.sql.Date getInvoiceDate() {
        return date;
    }

    public void setInvoiceDate(java.sql.Date invoiceDate) {
        this.date = invoiceDate;
    }

    public String getServiceProvided() {
        return serviceName;
    }

    public void setServiceProvided(String serviceProvided) {
        this.serviceName = serviceProvided;
    }

    public long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }
    
    public String getGST()
    {
        double gst = (0.1 * getAmountDue());
        return String.format("%.2f", gst);
    }
    
    public String getTotal()
    {
        double total = ((0.1) * getAmountDue())+ getAmountDue();
        return String.format("%.2f", total);
    }
    
}
