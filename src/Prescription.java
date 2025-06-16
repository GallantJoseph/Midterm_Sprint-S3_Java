import java.util.Date;

public class Prescription {
    private int id;
    private Doctor doctor;
    private Patient patient;
    private Medication medication;
    private Date prescriptionExpiry;

    public Prescription(int id, Doctor doctor, Patient patient, Medication medication, Date prescriptionExpiry) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.prescriptionExpiry = prescriptionExpiry;
    }
}
