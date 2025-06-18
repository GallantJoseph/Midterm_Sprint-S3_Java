/*
    Description: Midterm Sprint - Semester 3 - MedicationTrackingSystem Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MedicationTrackingSystem {
    // Private attributes
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Medication> medications = new ArrayList<>();

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
//            5.4 Generate a Report of Patients Prescriptions (past year)

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
    // Manage patient Sub Menu
    private static void managePatient(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n***** Patient Management *****");
            System.out.println("\nPlease make a selection:\n");
            System.out.println("1. Add a Patient");
            System.out.println("2. Search a Patient");
            System.out.println("3. Edit a Patient");
            System.out.println("4. Delete a Patient");
            System.out.println("5. Assign a Patient to a Doctor");
            System.out.println("6. Exit");
            System.out.println("*************************************************************\n");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        addPatient(scanner);
                        break;
                    case 2:
                        searchPatient(scanner);
                        break;
                    case 3:
                        //editPatient(scanner);
                        break;
                    case 4:
                        //deletePatient(scanner);
                        break;
                    case 5:
                        //assignPatientDoc(scanner);
                        break;
                    case 6:
                        System.out.println("\n***** Back to the main menu *****");
                        exit = true;
                        break;
                    default:
                        System.out.println("Please enter a value between 1-6.");
                }
            } else {
                System.out.println("Invalid input. Must be a numeric value.");
            }
        }

        System.out.println("\nReturning to the main menu...\n");
    }

    private static void addPatient(Scanner scanner) {
        // Variables declaration
        String firstName = null;
        String lastName = null;
        LocalDate dateOfBirth = null;
        String phone = null;
        char gender = 0;

        // Header
        System.out.println("\nAdd New Patient Details (q to quit):");

        boolean exit = false;

        while (!exit) {
            // First name input
            System.out.print("First Name: ");
            if (scanner.hasNext()) {
                firstName = scanner.next();

                // Quit if the user has entered "q"
                if (firstName.equals("q")) {
                    break;
                }
            }

            // Last name input
            System.out.print("Last Name: ");
            if (scanner.hasNext()) {
                lastName = scanner.next();
            }

            // Date of birth input
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            if (scanner.hasNext()) {
                while (true) {
                    String dateOfBirthStr = scanner.next();

                    // Validate that the format is correct
                    if (dateOfBirthStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        try {
                            // Try to parse the date entry into a LocalDate
                            dateOfBirth = LocalDate.parse(dateOfBirthStr);
                            break;
                        } catch (Exception exception) {
                            System.out.println("Invalid date. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid date format. Please try again.");
                    }
                }
            }

            // Phone number input
            System.out.print("Phone Number (10 digits): ");
            if (scanner.hasNext()) {
                while (true) {
                    phone = scanner.next();

                    // Validate that the format is correct
                    if (phone.matches("\\d{10}")) {
                        break;
                    } else {
                        System.out.println("Invalid phone number. Please try again.");
                    }
                }
            }

            // Gender input
            System.out.print("Gender (M, F, O): ");
            if (scanner.hasNext()) {
                while (true) {
                    String genderStr = scanner.next();

                    // Validate that the format is correct
                    if (genderStr.matches("[mfoMFO]")) {
                        gender = genderStr.toUpperCase().charAt(0);
                        break;
                    } else {
                        System.out.println("Invalid gender. Please try again.");
                    }
                }
            }

            // Try to create and add the new patient to the list of patients
            try {
                Patient patient = new Patient(firstName, lastName, dateOfBirth, phone, gender, new ArrayList<>(), new ArrayList<>());
                patients.add(patient);

                System.out.println("Patient added successfully.");
            } catch (Exception e) {
                System.out.println("Error while adding the new patient: \n" + e.getMessage());
            }

            System.out.print("\nWould you like to add another one (Y/N)? ");
            while (true) {
                String anotherPatientOpt;

                if (scanner.hasNext()) {
                    anotherPatientOpt = scanner.next().toUpperCase();

                    if (!anotherPatientOpt.equals("Y") && !anotherPatientOpt.equals("N")) {
                        System.out.println("Invalid input. Please try again.");
                    } else if (anotherPatientOpt.equals("N")) {
                        exit = true;
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
        System.out.println("\nReturning to the main menu...\n");
    }

    private static void searchPatient(Scanner scanner) {
        // TODO Scanner inputs
        Patient patient = null;

        String firstName = "First";
        String lastName = "Last";
        System.out.print("First Name: ");
        System.out.print("Last Name: ");

        for (Patient curPatient : patients) {
            if (curPatient.getFirstName().equals(firstName) && curPatient.getLastName().equals(lastName)) {
                patient = curPatient;
                break;
            }
        }

        if (patient != null) {
            Doctor doctor = new Doctor("DocFirst", "DocLast",
                    LocalDate.of(1975,3,12), "709-987-6543", 'M',
                    "Java Debugging", new ArrayList<>());
            doctor.addPatient(patient);

            Medication medication = new Medication("Meds", 100, 1000,LocalDate.now());

            patient.addMedication(medication);
            patient.addPrescription(new Prescription(doctor, patient, medication, LocalDate.now(),LocalDate.now()));

            System.out.println("Patient Details:");
            System.out.println("Name: " + patient);
            System.out.println("Age: " + patient.getAge());
            System.out.println("Date of Birth: " + patient.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            System.out.println("Phone Number: " + patient.getPhone());
            System.out.println("Gender: " + patient.getGender());

            System.out.println("\nList of medications:");
            for (Medication med : patient.getMedications()) {
                System.out.println("Name: " + med.getName());
                System.out.println("Dose: " + med.getDose());
                System.out.println("Expiry Date: " + med.getExpiryDate());
            }

            System.out.println("\nList of prescriptions:");
            for (Prescription prescription : patient.getPrescriptions()) {
                System.out.println("Medication Name: " + prescription.getName());
                System.out.println("Issued by: " + prescription.getDoctor());
                System.out.println("Issued on: " + prescription.getIssueDate());
                System.out.println("Expires on: " + prescription.getPrescriptionExpiry());
                System.out.println("Medication Dose: " + prescription.getDose());
            }
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void editPatient(Scanner scanner){

    }

    private static void deletePatient(Scanner scanner){

    }

    private static void assignPatientDoc(Scanner scanner){

    }

    // Manage mediation Sub Menu
    private static void manageMedication(Scanner scanner) {

    }

    // Manage doctor Sub Menu
    private static void manageDoctor(Scanner scanner) {

    }

    // Accept a prescription
    private static void accPresc() {

    }

    // Generate a report Sub Menu
    private static void generateReport(Scanner scanner) {

    }
}
