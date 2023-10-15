
package Model;

import java.sql.Date;

/**
 * The BillingInvoiceEntity class represents a billing invoice entity in the system.
 */
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

    /**
     * Constructs a BillingInvoiceEntity with the specified attributes and calculates the invoice amount based on the service provided.
     *
     * @param invoiceID The unique ID of the invoice.
     * @param invoiceDate The date of the invoice.
     * @param serviceProvided The name of the service provided.
     * @param appointmentID The unique ID of the associated appointment.
     * @param patientID The unique ID of the patient associated with the invoice.
     */
    public BillingInvoiceEntity(long invoiceID, java.sql.Date invoiceDate, String serviceProvided, long appointmentID, long patientID) {
        this.ID = invoiceID;
        this.date = invoiceDate;
        this.serviceName = serviceProvided;
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        calculatePrice();
    }
    
    /**
     * Gets the unique ID of the invoice.
     *
     * @return The invoice's unique ID.
     */
    public long getInvoiceID() {
        return ID;
    }

    /**
     * Sets the unique ID of the invoice.
     *
     * @param invoiceID The invoice's unique ID to set.
     */
    public void setInvoiceID(long invoiceID) {
        this.ID = invoiceID;
    }

     /**
     * Gets the invoice amount due.
     *
     * @return The amount due on the invoice.
     */
    public double getAmountDue() {
        return invoiceAmount;
    }

    /**
     * Sets the invoice amount due.
     *
     * @param amountDue The amount due on the invoice to set.
     */
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
    // The calculatePrice method is private and not part of the public API.

    /**
     * Gets the date of the invoice.
     *
     * @return The invoice date.
     */
    public java.sql.Date getInvoiceDate() {
        return date;
    }
    
    /**
     * Sets the date of the invoice.
     *
     * @param invoiceDate The invoice date to set.
     */
    public void setInvoiceDate(java.sql.Date invoiceDate) {
        this.date = invoiceDate;
    }

    /**
     * Gets the name of the service provided on the invoice.
     *
     * @return The name of the service provided.
     */
    public String getServiceProvided() {
        return serviceName;
    }

    /**
     * Sets the name of the service provided on the invoice.
     *
     * @param serviceProvided The name of the service provided to set.
     */
    public void setServiceProvided(String serviceProvided) {
        this.serviceName = serviceProvided;
    }

    /**
     * Gets the unique ID of the associated appointment.
     *
     * @return The unique ID of the associated appointment.
     */
    public long getAppointmentID() {
        return appointmentID;
    }

    /**
     * Sets the unique ID of the associated appointment.
     *
     * @param appointmentID The unique ID of the associated appointment to set.
     */
    public void setAppointmentID(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Gets the unique ID of the patient associated with the invoice.
     *
     * @return The unique ID of the patient associated with the invoice.
     */
    public long getPatientID() {
        return patientID;
    }

    /**
     * Sets the unique ID of the patient associated with the invoice.
     *
     * @param patientID The unique ID of the patient associated with the invoice to set.
     */
    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }

    /**
     * Calculates and returns the GST (Goods and Services Tax) for the invoice amount.
     *
     * @return The GST amount formatted as a string.
     */
    public String getGST()
    {
        double gst = (0.1 * getAmountDue());
        return String.format("%.2f", gst);
    }

    /**
     * Calculates and returns the total amount including GST for the invoice.
     *
     * @return The total amount including GST formatted as a string.
     */
    public String getTotal()
    {
        double total = ((0.1) * getAmountDue())+ getAmountDue();
        return String.format("%.2f", total);
    }
    
}
