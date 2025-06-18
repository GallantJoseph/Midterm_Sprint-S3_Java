/*
    Description: Midterm Sprint - Semester 3 - Doctor Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

import java.util.ArrayList;
import java.util.Date;

public class Doctor extends Person {
    // Private attributes
    private ArrayList<Patient> patients;
    private String specialization;

    // Constructors
    // Parameterized
    public Doctor(String firstName, String lastName, Date dateOfBirth, String phone, char gender, String specialization, ArrayList<Patient> patients) {
        super(firstName, lastName, dateOfBirth, phone, gender);
        this.patients = new ArrayList<>(patients);
        this.specialization = specialization;
    }

    // Getters and setters
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Add a patient to a doctor
    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }
}
