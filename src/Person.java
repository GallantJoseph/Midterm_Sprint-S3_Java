/*
    Description: Midterm Sprint - Semester 3 - Person Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

import java.time.LocalDate;
import java.time.Period;

/**
 * An abstract class representing a person in the system.
 * Used as a base class for Doctors and Patients.
 * Stores basic personal information like name, birthdate, phone, and gender.
 */
public abstract class Person {
    // Private attributes
    private static int idCounter = 1;
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private char gender; // (M)ale, (F)emale or (O)ther

    // Constructors
    /**
     * Constructs a new Person with the provided information.
     * This constructor is used by subclasses like Doctor or Patient.
     *
     * @param firstName    The person's first name.
     * @param lastName     The person's last name.
     * @param dateOfBirth  The person's date of birth.
     * @param phone        The person's phone number.
     * @param gender       The person's gender (M for Male, F for Female, O for Other).
     */
    public Person(String firstName, String lastName, LocalDate dateOfBirth, String phone, char gender) {
        this.id = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.gender = gender;
    }

    // Getters and Setters
    /**
     * Returns the ID of the person.
     *
     * @return the ID of the person.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the first name of the person.
     *
     * @return the first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName the first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the person.
     *
     * @return the last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName the last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the date of birth of the person.
     *
     * @return the date of birth of the person.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Calculates and returns the age of the person based on the date of birth.
     *
     * @return the age of the person in years.
     */
    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Returns the phone number of the person.
     *
     * @return the phone number of the person.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phone the phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the gender of the person.
     *
     * @return the gender of the person as a char.
     */
    public char getGender() {
        return gender;
    }

    /**
     * Sets the gender of the person.
     *
     * @param gender the gender to set as a char (e.g., 'M' for male, 'F' for female).
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Returns a string representation of the person in the format "last name, first name".
     *
     * @return a string representation of the person's name.
     */
    @Override
    public String toString() {
        // Last name, first name
        return String.format("%s, %s", this.lastName, this.firstName);
    }
}