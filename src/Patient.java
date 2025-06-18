/*
    Description: Midterm Sprint - Semester 3 - Patient Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

import java.util.ArrayList;
import java.util.Date;

public class Patient extends Person {
    // Private attributes
    private ArrayList<Medication> medications;
    private ArrayList<Prescription> prescriptions;

    // Constructors
    // Parameterized
    public Patient(String firstName, String lastName, Date dateOfBirth, String phone,char gender, ArrayList<Medication> medications,
                   ArrayList<Prescription> prescriptions) {
        super(firstName, lastName, dateOfBirth, phone, gender);

        this.medications = new ArrayList<>(medications);
        this.prescriptions = new ArrayList<>(prescriptions);
    }

    // Add a medication to the list of medications for the patient
    public void addMedication(Medication medication){
        this.medications.add(medication);
    }

    // Add a prescription to the list of prescriptions for the patient
    public void addPrescription(Prescription prescription){
        this.prescriptions.add(prescription);
    }
}
