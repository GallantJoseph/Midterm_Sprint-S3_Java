/*
    Description: Midterm Sprint - Semester 3 - MedicationTrackingSystem Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MedicationTrackingSystem {
    // Private attributes
    private ArrayList<Patient> patients;
    private static ArrayList<Doctor> doctors = new ArrayList<>(); // had to make it static so my static methods could access the list of doctors 
    private ArrayList<Medication> medications;

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit){
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n***** Welcome To The Pharmacy Medication Tracking System *****");
            System.out.println("\nPlease make a selection:\n");
            System.out.println("1. Manage a patient");
            System.out.println("2. Manage a medication");
            System.out.println("3. Manage a doctor");
            System.out.println("4. Accept a prescription");
            System.out.println("5. Generate a report");
            System.out.println("6. Exit");
            System.out.println("*************************************************************\n");
            
//            Sub menus template
//            Patient Management
//            1.1 Add Patient
//            1.2 Search Patient
//            1.3 Edit Patient
//            1.4 Delete Patient
//            1.5 Assign Patient to a Doctor

//            Doctor Management
//            2.1 Add Doctor
//            2.2 Search Doctor
//            2.3 Edit Doctor
//            2.4 Delete Doctor

//            Medication Management
//            3.1 Add Medication
//            3.2 Search Medication
//            3.3 Edit Medication
//            3.4 Delete Medication
//            3.5 Restock Medication

//            Reports
//            5.1 Generate a General Report
//            5.2 Generate a Report for Expired Medication
//            5.3 Generate a Prescriptions Report by Doctor

            // Validation for the menu selection
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        managePatient(scanner);
                        break;
                    case 2:
                        manageMedication(scanner);
                        break;
                    case 3:
                        manageDoctor(scanner);
                        break;
                    case 4:
                        accPresc();
                        break;
                    case 5:
                        generateReport(scanner);
                        break;
                    case 6:
                        System.out.println("\n***** Thank you for using our system. Have a good day. *****");
                        exit = true;
                        break;
                    default:
                        System.out.println("Please enter a value between 1-6.");
                }
            } else {
                System.out.println("Invalid input. Must be a numeric value.");
            }
        }
    }

    // Main program functions
    private static void managePatient(Scanner scanner) {

    }

    private static void manageMedication(Scanner scanner) {

    }

    private static void manageDoctor(Scanner scanner) {
        boolean exit = false;
        while (!exit) {

            System.out.println("\n***** Doctor Management *****");
            System.out.println("\nPlease make a selection:\n");
            System.out.println("1. Add Doctor");
            System.out.println("2. Search Doctor");
            System.out.println("3. Edit Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Back to main menu");
            System.out.println("*************************************************************\n");

            //            Doctor Management
            //            2.1 Add Doctor
            //            2.2 Search Doctor
            //            2.3 Edit Doctor
            //            2.4 Delete Doctor

            // Validation for the Doctor management menu selection
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        addDoctor(scanner);
                        break;
                    case 2:
                        searchDoctor(scanner);
                        break;
                    case 3:
                        editDoctor(scanner);
                        break;
                    case 4:
                        deleteDoctor(scanner);
                        break;
                    case 5:
                        System.out.println("\n***** Thank you for using our system. Have a good day. *****");
                        exit = true;
                        break;
                    default:
                        System.out.println("Please enter a value between 1-5.");
                }
            } else {
                System.out.println("Invalid input. Must be a numeric value.");
            }
        }

    }

    public static void addDoctor(Scanner scanner) {
        try {
            // getting all details to create new doctor.
            System.out.println("Enter Doctor ID (number): ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter First Name:");
            String firstName = scanner.nextLine();

            System.out.println("Enter Last Name: ");
            String lastName = scanner.nextLine();

            System.out.println("Enter Date Of Birth (YYYY-MM-DD): ");
            String dateOfBirth = scanner.nextLine();
            Date dob = java.sql.Date.valueOf(dateOfBirth); // NOTE: Replace Date with LocalDate if group decides to switch.

            System.out.println("Enter Phone Number: ");
            String phone = scanner.nextLine();

            System.out.println("Enter Gender (M/F/O)");
            char gender = scanner.nextLine().toUpperCase().charAt(0);

            System.out.println("Enter Specialization Of Doctor: ");
            String specialization = scanner.nextLine();

            Doctor newDoctor = new Doctor(id, firstName, lastName, dob, phone, gender, specialization,
                    new ArrayList<>());

            doctors.add(newDoctor);
            System.out.println("Doctor added successfully");

//Test to be deleted to test if doctor was added 
            System.out.println("Current list of doctors:");
            for (Doctor doctor : doctors) {
                System.out.println(", Name: " + doctor.getFirstName() + " " + doctor.getLastName()
                        +
                ", Specialization: " + doctor.getSpecialization());
}
        } catch (Exception e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
    }

    public static void searchDoctor(Scanner scanner) {
        System.out.println("Search doctor not created yet");};

    public static void editDoctor(Scanner scanner) {
        System.out.println("Edit doctor not created yet");};

    public static void deleteDoctor(Scanner scanner) {
        System.out.println("Delete doctor not created yet");};
    
    
    
    
    private static void accPresc() {

    }

    private static void generateReport(Scanner scanner) {

    }
}
