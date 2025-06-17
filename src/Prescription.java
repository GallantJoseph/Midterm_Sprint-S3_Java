/*
    Description: Midterm Sprint - Semester 3 - Prescription Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

 import java.util.Date;

 public class Prescription extends Medication {
     // Private attributes
     private static int idCounter = 1;
     private int id;
     private Doctor doctor;
     private Patient patient;
     private Date prescriptionExpiry;
 
     // Constructors
     // Parameterized
     public Prescription(Doctor doctor, Patient patient, String medName, double medDose, int medQuantity, Date medExpiry,
                         Date prescriptionExpiry) {
         super(medName, medDose, medQuantity, medExpiry);
         this.id = idCounter++;
         this.doctor = doctor;
         this.patient = patient;
         this.prescriptionExpiry = prescriptionExpiry;
     }
 
     // Parameterized (takes a medication and uses the copy constructor of the Medication class)
     public Prescription(Doctor doctor, Patient patient, Medication medication,
                         Date prescriptionExpiry) {
         super(medication);
         this.id = idCounter++;
         this.doctor = doctor;
         this.patient = patient;
         this.prescriptionExpiry = prescriptionExpiry;
     }
 
     // Getters and setters
     public Doctor getDoctor() {
         return doctor;
     }
 
     public Patient getPatient() {
         return patient;
     }
 
     public Date getPrescriptionExpiry() {
         return prescriptionExpiry;
     }
 }
  