/*
    Description: Midterm Sprint - Semester 3 - Patient Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a patient, which extends the Person class, with additional attributes for medications and prescriptions.
 * Provides functionality to manage medications and prescriptions for the patient.
 */
public class Patient extends Person {
    // Private attributes
    private ArrayList<Medication> medications;
    private ArrayList<Prescription> prescriptions;

    // Constructors
    // Parameterized
    /**
     * Constructs a new Patient object with the specified attributes.
     * 
     * @param firstName the first name of the patient.
     * @param lastName the last name of the patient.
     * @param dateOfBirth the date of birth of the patient.
     * @param phone the phone number of the patient.
     * @param gender the gender of the patient 'M' for male, 'F' for female, 'O' for other).
     * @param medications a list of medications the patient is currently taking.
     * @param prescriptions a list of prescriptions associated with the patient.
     */
    public Patient(String firstName, String lastName, LocalDate dateOfBirth, String phone, char gender, ArrayList<Medication> medications,
                   ArrayList<Prescription> prescriptions) {
        super(firstName, lastName, dateOfBirth, phone, gender);
        this.medications = new ArrayList<>(medications);
        this.prescriptions = new ArrayList<>(prescriptions);
    }

    /**
     * Adds a medication to the patient's list of medications.
     * 
     * @param medication the medication to add.
     */
    public void addMedication(Medication medication){
        this.medications.add(medication);
    }

    /**
     * Removes a medication from the patient's list of medications.
     * 
     * @param medication the medication to remove.
     */
    public void removeMedication(Medication medication) {
        this.medications.remove(medication);
    }

    /**
     * Adds a prescription to the patient's list of prescriptions.
     * 
     * @param prescription the prescription to add.
     */
    public void addPrescription(Prescription prescription){
        this.prescriptions.add(prescription);
    }

    /**
     * Removes a prescription from the patient's list of prescriptions.
     * 
     * @param prescription the prescription to remove.
     */
    public void removePrescription(Prescription prescription) {
        this.prescriptions.remove(prescription);
    }

    /**
     * Returns the list of medications currently associated with the patient.
     * 
     * @return a list of Medication objects representing the patient's medications.
     */
    public ArrayList<Medication> getMedications() {
        return medications;
    }

    /**
     * Returns the list of prescriptions currently associated with the patient.
     * 
     * @return a list of Prescription objects representing the patient's prescriptions.
     */
    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }
}
