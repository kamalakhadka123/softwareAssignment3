
package Model;

import java.sql.Date;

public class AppointmentEntity {
    private long ID;
    private java.sql.Date scheduleDate;
    private String serviceName;
    private String scheduleTime;
    private long patientID;

    public AppointmentEntity(long appointmentID, java.sql.Date appointmentDate, String bookingService, String bookingTime, long patientID) {
        this.ID = appointmentID;
        this.scheduleDate = appointmentDate;
        this.serviceName = bookingService;
        this.scheduleTime = bookingTime;
        this.patientID = patientID;
    }

    public long getAppointmentID() {
        return ID;
    }

    public void setAppointmentID(long appointmentID) {
        this.ID = appointmentID;
    }

    public java.sql.Date getAppointmentDate() {
        return scheduleDate;
    }

    public void setAppointmentDate(java.sql.Date appointmentDate) {
        this.scheduleDate = appointmentDate;
    }

    public String getBookingService() {
        return serviceName;
    }

    public void setBookingService(String bookingService) {
        this.serviceName = bookingService;
    }

    public String getBookingTime() {
        return scheduleTime;
    }

    public void setBookingTime(String bookingTime) {
        this.scheduleTime = bookingTime;
    }

    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }
    
    
    
    
}
