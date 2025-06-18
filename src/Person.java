/*
    Description: Midterm Sprint - Semester 3 - Person Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

import java.time.LocalDate;
import java.time.Period;

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
    public Person(String firstName, String lastName, LocalDate dateOfBirth, String phone, char gender) {
        this.id = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.gender = gender;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    // Calculate and return the person's age
    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    // toString method override
    @Override
    public String toString() {
        // Last name, first name, gender, and age
        return String.format("%s, %s", this.lastName, this.firstName);
    }
}