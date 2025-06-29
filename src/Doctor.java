/*
    Description: Midterm Sprint - Semester 3 - Doctor Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

import java.time.LocalDate;
import java.util.ArrayList;

public class Doctor extends Person {
    // Private attributes
    private ArrayList<Patient> patients;
    private String specialization;

    // Constructors
    // Parameterized
    /**
    * Creates a new Doctor with the given details.
    *
    * @param firstName      The doctor's first name.
    * @param lastName       The doctor's last name.
    * @param dob            The doctor's date of birth.
    * @param phone          The doctor's 10-digit phone number.
    * @param gender         The doctor's gender (M/F/O).
    * @param specialization The doctor's area of specialization.
    * @param patients       The list of patients assigned to this doctor.
    */
    public Doctor(String firstName, String lastName, LocalDate dateOfBirth, String phone, char gender, String specialization, ArrayList<Patient> patients) {
        super(firstName, lastName, dateOfBirth, phone, gender);
        this.patients = new ArrayList<>(patients);
        this.specialization = specialization;
    }

    // Getters and setters
    /**
     * Get the list of patients of the doctor.
     *
     * @return The list of patients for the the doctor.
     */
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    /**
     * Get the specialization of the doctor.
     *
     * @return The specialization of the doctor.
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Set the specialization of the doctor.
     *
     * @param specialization The specialization of the doctor.
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Add a patient to a doctor.
     *
     * @param patient The patient to add to the doctor.
     */
    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

    /**
     * Remove a patient from a doctor
     *
     * @param patient The patient to remove from the doctor.
     */
    public void removePatient(Patient patient) {
        this.patients.remove(patient);
    }
}
