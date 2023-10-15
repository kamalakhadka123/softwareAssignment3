
package Model;

import java.sql.Date;

/**
 * The AppointmentEntity class represents an appointment entity in the system.
 */

public class AppointmentEntity {
    private long ID;
    private java.sql.Date scheduleDate;
    private String serviceName;
    private String scheduleTime;
    private long patientID;
    /**
     * Constructs an AppointmentEntity with the specified attributes.
     *
     * @param appointmentID The unique ID of the appointment.
     * @param appointmentDate The date of the appointment.
     * @param bookingService The name of the service booked for the appointment.
     * @param bookingTime The scheduled time of the appointment.
     * @param patientID The unique ID of the patient associated with the appointment.
     */
    public AppointmentEntity(long appointmentID, java.sql.Date appointmentDate, String bookingService, String bookingTime, long patientID) {
        this.ID = appointmentID;
        this.scheduleDate = appointmentDate;
        this.serviceName = bookingService;
        this.scheduleTime = bookingTime;
        this.patientID = patientID;
    }
    
    /**
     * Gets the unique ID of the appointment.
     *
     * @return The appointment's unique ID.
     */
    public long getAppointmentID() {
        return ID;
    }
    
    /**
     * Sets the unique ID of the appointment.
     *
     * @param appointmentID The appointment's unique ID to set.
     */
    public void setAppointmentID(long appointmentID) {
        this.ID = appointmentID;
    }
    
    /**
     * Gets the date of the appointment.
     *
     * @return The appointment date.
     */
    public java.sql.Date getAppointmentDate() {
        return scheduleDate;
    }
    
    /**
     * Sets the date of the appointment.
     *
     * @param appointmentDate The appointment date to set.
     */
    public void setAppointmentDate(java.sql.Date appointmentDate) {
        this.scheduleDate = appointmentDate;
    }
    
    /**
     * Gets the name of the service booked for the appointment.
     *
     * @return The name of the booked service.
     */
    public String getBookingService() {
        return serviceName;
    }
    
    /**
     * Sets the name of the service booked for the appointment.
     *
     * @param bookingService The name of the booked service to set.
     */
    public void setBookingService(String bookingService) {
        this.serviceName = bookingService;
    }
    
    /**
     * Gets the scheduled time of the appointment.
     *
     * @return The scheduled time of the appointment.
     */
    public String getBookingTime() {
        return scheduleTime;
    }

    /**
     * Sets the scheduled time of the appointment.
     *
     * @param bookingTime The scheduled time to set.
     */
    public void setBookingTime(String bookingTime) {
        this.scheduleTime = bookingTime;
    }

    /**
     * Gets the unique ID of the patient associated with the appointment.
     *
     * @return The patient's unique ID.
     */
    public long getPatientID() {
        return patientID;
    }

    /**
     * Sets the unique ID of the patient associated with the appointment.
     *
     * @param patientID The patient's unique ID to set.
     */
    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }
    
    
    
    
}
