/*
    Description: Midterm Sprint - Semester 3 - Prescription Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

 import java.time.LocalDate;

 public class Prescription extends Medication {
     // Private attributes
     private static int idCounter = 1;
     private int id;
     private Doctor doctor;
     private Patient patient;
     private LocalDate issueDate;
     private LocalDate prescriptionExpiry;
 
     // Constructors
 
     /**
      * Creates a new prescription using detailed medication info.
      *
      * @param doctor              The doctor issuing the prescription.
      * @param patient             The patient receiving the prescription.
      * @param medName             The name of the medication.
      * @param medDose             The dosage of the medication.
      * @param medQuantity         The quantity of medication prescribed.
      * @param issueDate           The date the prescription was issued.
      * @param medExpiry           The expiration date of the medication.
      * @param prescriptionExpiry  The date the prescription expires.
      */
     public Prescription(Doctor doctor, Patient patient, String medName, double medDose, int medQuantity, LocalDate issueDate, LocalDate medExpiry,
                         LocalDate prescriptionExpiry) {
         super(medName, medDose, medQuantity, medExpiry);
         this.id = idCounter++;
         this.doctor = doctor;
         this.patient = patient;
         this.issueDate = issueDate;
         this.prescriptionExpiry = prescriptionExpiry;
     }
 
     /**
      * Creates a new prescription using an existing Medication object.
      *
      * @param doctor              The doctor issuing the prescription.
      * @param patient             The patient receiving the prescription.
      * @param medication          The medication being prescribed.
      * @param issueDate           The date the prescription was issued.
      * @param prescriptionExpiry  The date the prescription expires.
      */
     public Prescription(Doctor doctor, Patient patient, Medication medication, LocalDate issueDate,
                         LocalDate prescriptionExpiry) {
         super(medication);
         this.id = idCounter++;
         this.doctor = doctor;
         this.patient = patient;
         this.issueDate = issueDate;
         this.prescriptionExpiry = prescriptionExpiry;
     }
 
     // Getters
 
     /**
      * Gets the unique ID of the prescription.
      *
      * @return The prescription ID.
      */
     public int getPrescriptionId() {
         return this.id;
     }
 
     /**
      * Gets the doctor who issued the prescription.
      *
      * @return The doctor object.
      */
     public Doctor getDoctor() {
         return doctor;
     }
 
     /**
      * Gets the patient the prescription is for.
      *
      * @return The patient object.
      */
     public Patient getPatient() {
         return patient;
     }
 
     /**
      * Gets the date the prescription was issued.
      *
      * @return The issue date.
      */
     public LocalDate getIssueDate() {
         return issueDate;
     }
 
     /**
      * Gets the expiration date of the prescription.
      *
      * @return The prescription expiry date.
      */
     public LocalDate getPrescriptionExpiry() {
         return prescriptionExpiry;
     }
 }
 
  