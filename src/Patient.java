/*
    Description: Midterm Sprint - Semester 3 - Patient Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

import java.time.LocalDate;
import java.util.ArrayList;

public class Patient extends Person {
    // Private attributes
    private ArrayList<Medication> medications;
    private ArrayList<Prescription> prescriptions;

    // Constructors
    // Parameterized
    public Patient(String firstName, String lastName, LocalDate dateOfBirth, String phone, char gender, ArrayList<Medication> medications,
                   ArrayList<Prescription> prescriptions) {
        super(firstName, lastName, dateOfBirth, phone, gender);

        this.medications = new ArrayList<>(medications);
        this.prescriptions = new ArrayList<>(prescriptions);
    }

    // Add a medication to the list of medications for the patient
    public void addMedication(Medication medication){
        this.medications.add(medication);
    }

    // Remove a medication from the list of medications for the patient
    public void removeMedication(Medication medication) {
        this.medications.remove(medication);
    }

    // Add a prescription to the list of prescriptions for the patient
    public void addPrescription(Prescription prescription){
        this.prescriptions.add(prescription);
    }

    // Remove a prescription from the list of prescriptions for the patient
    public void removePrescription(Prescription prescription) {
        this.prescriptions.remove(prescription);
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }
}
