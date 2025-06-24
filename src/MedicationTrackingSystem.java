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
    private static ArrayList<Prescription> prescriptions = new ArrayList<>();

    public static void main(String[] args) {

        // loading my preloaded date here
        preloadData();

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
            System.out.println("**************************************************************\n");

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

            // TODO Remove tests
            patients.add(new Patient("First", "Last", LocalDate.of(1990,1,1), "1234567890", 'M', new ArrayList<>(), new ArrayList<>()));
            Patient patient = patients.getFirst();

            Doctor doctor = new Doctor("DocFirst", "DocLast",
                    LocalDate.of(1975,3,12), "709-987-6543", 'M',
                    "Java Debugging", new ArrayList<>());
            doctor.addPatient(patient);

            doctors.add(doctor);

            Medication medication = new Medication("Meds", 100, 1000,LocalDate.now());

            patient.addMedication(medication);
            patient.addMedication(medication);
            patient.addMedication(new Medication(medication));

            // Old issue date, not expired
            patient.addPrescription(new Prescription(doctor, patient, medication, LocalDate.of(2023,3,12),LocalDate.of(2026,7,12)));

            // Recent issue date, expired prescription
            patient.addPrescription(new Prescription(doctor, patient, medication, LocalDate.of(2025,3,12),LocalDate.of(2025,5,5)));

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
                        reportsMenu(scanner);
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
            System.out.println("2. Find a Patient");
            System.out.println("3. Edit a Patient");
            System.out.println("4. Delete a Patient");
            System.out.println("5. Assign a Patient to a Doctor");
            System.out.println("6. Exit");
            System.out.println("**************************************************************");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        addPatient(scanner);
                        break;
                    case 2:
                        findPatient(scanner);
                        break;
                    case 3:
                        editPatient(scanner);
                        break;
                    case 4:
                        deletePatient(scanner);
                        break;
                    case 5:
                        assignPatientDoc(scanner);
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
                // Clear the scanner to accept another value.
                scanner.nextLine();
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

            // Give the user the option to add another patient.
            System.out.print("\nWould you like to add another one (Y/N)? ");
            boolean isValidOpt = false;

            while (!isValidOpt) {
                String anotherPatientOpt;

                if (scanner.hasNext()) {
                    // Convert to uppercase for easier validation
                    anotherPatientOpt = scanner.next().toUpperCase();

                    if (!anotherPatientOpt.equals("Y") && !anotherPatientOpt.equals("N")) {
                        System.out.println("Invalid input. Please try again.");
                    } else if (anotherPatientOpt.equals("N")) {
                        isValidOpt = true;
                        exit = true;
                    } else { // Option "Y"
                        isValidOpt = true;
                    }
                }
            }
        }
        System.out.println("\nReturning to the main menu...\n");
    }

    private static void findPatient(Scanner scanner) {
        // Header
        System.out.println("\nSearch for Patient Details:");

        // Allow the user to search for a patient by id or name
        Patient patient = patientSearch(scanner);

        // Show the patient details, including their list of medications and prescriptions
        if (patient != null) {
            showPatientDetails(patient);
            showPatientMeds(patient);

            // Hide the patient's prescriptions that are expired
            showPatientPrescs(patient,true,false);
        } else {
            System.out.println("No patient found with the provided ID or name.");
        }
    }

    // Allows the user to search for a patient by id or first name and last name
    private static Patient patientSearch(Scanner scanner) {
        // Variables declaration
        int id = 0;
        String firstName = null;
        String lastName = null;

        String input;
        boolean isValidInput = false;

        Patient patient = null;

        // ID input
        System.out.print("Patient ID (leave blank for name search): ");

        // Clear the scanner before to accept a blank value
        // TODO make sure to only clear when necessary
        scanner.nextLine();
        while (!isValidInput) {
            input = scanner.nextLine();

            if (!input.isEmpty()) {
                try {
                    id = Integer.parseInt(input);

                    if (id > 0) {
                        isValidInput = true;
                    } else {
                        System.out.println("Invalid ID. It must be a positive number.");
                    }
                } catch (Exception exception) {
                    System.out.println("Invalid ID. It must be numeric.");
                }
            } else { // Blank input
                isValidInput = true;
            }
        }

        // Search by id
        if (id != 0) {
            for (Patient curPatient : patients) {
                if (curPatient.getId() == id) {
                    patient = curPatient;
                    break;
                }
            }
        } else {
            // Search by first name and last name

            // First name input
            System.out.print("First Name: ");
            if (scanner.hasNext()) {
                firstName = scanner.next();
            }

            // Last name input
            System.out.print("Last Name: ");
            if (scanner.hasNext()) {
                lastName = scanner.next();
            }

            // Search for a patient by its full name
            for (Patient curPatient : patients) {
                if (curPatient.getFirstName().equals(firstName) && curPatient.getLastName().equals(lastName)) {
                    patient = curPatient;
                    break;
                }
            }
        }

        return patient;
    }

    private static void showPatientDetails(Patient patient) {
        System.out.println("\nPatient Details:");
        System.out.println("--------------------");
        System.out.println("ID: " + patient.getId());
        System.out.println("First Name: " + patient.getFirstName());
        System.out.println("Last Name: " + patient.getLastName());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Date of Birth: " + patient.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println("Phone Number: " + patient.getPhone());
        System.out.println("Gender: " + patient.getGender());
    }

    private static void showPatientMeds(Patient patient) {
        System.out.println("\nMedications:");
        System.out.println("--------------------");
        for (Medication med : patient.getMedications()) {
            System.out.println("Name: " + med.getName());
            System.out.println("Dose: " + med.getDose());
            System.out.println("Expiry Date: " + med.getExpiryDate() + "\n");
        }
    }

    // Show the patient's prescriptions. Flag to show or hide expired medication.
    private static void showPatientPrescs(Patient patient, boolean showOldPrescs, boolean showExpired) {
        // Old prescription threshold value in years.
        final int YEARS_THRES = 1;
        int prescsCount = 0;

        System.out.println("\nPrescriptions:");
        System.out.println("--------------------");
        for (Prescription prescription : patient.getPrescriptions()) {
            // Hide the prescriptions that are old or expired, when necessary
            if ((showOldPrescs || Period.between(prescription.getIssueDate(),LocalDate.now()).getYears() <= YEARS_THRES)
                    && (showExpired || Period.between(prescription.getPrescriptionExpiry(), LocalDate.now()).toTotalMonths() <= 0)) {

                System.out.println("Medication Name: " + prescription.getName());
                System.out.println("Issued by: " + prescription.getDoctor());
                System.out.println("Issued on: " + prescription.getIssueDate());
                System.out.println("Expires on: " + prescription.getPrescriptionExpiry());
                System.out.println("Medication Dose: " + prescription.getDose() + "\n");
                prescsCount++;
            }
        }

        // Display a message if no prescriptions are found, given the criteria.
        if (prescsCount == 0) {
            System.out.println("No prescriptions.\n");
        }
    }

    // Show the patient's prescriptions for the past year
    private static void showPatientCurrentPrescs(Patient patient) {
        // Old prescription threshold value in years.
        final int YEARS_THRES = 1;
        int prescsCount = 0;

        System.out.println("\n\nPatient Name: " + patient);

        System.out.println("\nPrescriptions:");
        System.out.println("--------------------");
        for (Prescription prescription : patient.getPrescriptions()) {
            // Hide the prescriptions that are older than the YEARS_THRES
            if (Period.between(prescription.getIssueDate(),LocalDate.now()).getYears() <= YEARS_THRES) {

                System.out.println(prescription.getName());
                prescsCount++;
            }
        }

        // Display a message if no prescriptions are found, given the criteria.
        if (prescsCount == 0) {
            System.out.println("No prescriptions.\n");
        }
    }

    private static void editPatient(Scanner scanner){
        boolean exit = false;

        while (!exit) {
            // Header
            System.out.println("\nEdit a Patient's Details:");

            // Search for a patient to edit
            Patient patient = patientSearch(scanner);

            if (patient != null) {
                // Show the patient details and allow them to modify each attribute.
                showPatientDetails(patient);

                // TODO Fix when searching the patient by name, First name entry is skipped. Works when searching by ID
                editPatientDetails(patient, scanner);
            } else {
                System.out.println("No patient found with the provided ID or name.");
            }

            // Give the user the option to edit another patient.
            System.out.print("\nWould you like to edit another patient (Y/N)? ");
            boolean isValidOpt = false;

            while (!isValidOpt) {
                String anotherPatientOpt;

                if (scanner.hasNext()) {
                    // Convert to uppercase for easier validation
                    anotherPatientOpt = scanner.next().toUpperCase();

                    if (!anotherPatientOpt.equals("Y") && !anotherPatientOpt.equals("N")) {
                        System.out.println("Invalid input. Please try again.");
                    } else if (anotherPatientOpt.equals("N")) {
                        isValidOpt = true;
                        exit = true;
                    } else { // Option "Y"
                        isValidOpt = true;
                    }
                }
            }
        }
    }

    private static void editPatientDetails(Patient patient, Scanner scanner) {
        // Variables declaration
        String firstName = null;
        String lastName = null;
        String phone = null;

        String input;

        // First name input
        System.out.println("\nLeave value blank for no modifications.\n");
        System.out.print("First Name: ");

        input = scanner.nextLine();
        if (!input.isEmpty()) {
            firstName = input;
        }

        // Last name input
        System.out.print("Last Name: ");

        input = scanner.nextLine();
        if (!input.isEmpty()) {
            lastName = input;
        }

        // Phone number input
        System.out.print("Phone Number (10 digits): ");

        // Validate that the phone number is valid
        while (true) {
            input = scanner.nextLine();

            // Validate that the format is correct
            if (!input.isEmpty()) {
                if (input.matches("\\d{10}")) {
                    phone = input;
                    break;
                } else {
                    System.out.println("Invalid phone number. Please try again.");
                }
            } else {
                break;
            }
        }

        System.out.println("\nConfirm the patient details update:\n");

        System.out.print("First Name: ");
        if (firstName == null) {
            System.out.print(patient.getFirstName() + " (unchanged)");
        } else {
            System.out.print(firstName);
        }

        System.out.print("\nLast Name: ");
        if (lastName == null) {
            System.out.print(patient.getLastName() + " (unchanged)");
        } else {
            System.out.print(lastName);
        }

        System.out.print("\nPhone Number: ");
        if (phone == null) {
            System.out.print(patient.getPhone() + " (unchanged)");
        } else {
            System.out.print(phone);
        }

        // Give the user the option to add another patient.
        System.out.print("\nUpdate the patient details these information (Y/N)? ");
        boolean isValidOpt = false;
        boolean updatePatient = false;

        while (!isValidOpt) {
            String updatePatientOpt;

            // Convert to uppercase for easier validation
            updatePatientOpt = scanner.nextLine().toUpperCase();

            if (!updatePatientOpt.equals("Y") && !updatePatientOpt.equals("N")) {
                System.out.println("Invalid input. Please try again.");
            } else if (updatePatientOpt.equals("Y")) {
                isValidOpt = true;
                updatePatient = true;
            } else { // Option "N"
                isValidOpt = true;
            }
        }

        // Update the patient details if they chose "Y"
        if (updatePatient) {
            try {
                if (firstName != null) {
                    patient.setFirstName(firstName);
                }

                if (lastName != null) {
                    patient.setLastName(lastName);
                }

                if (phone != null) {
                    patient.setPhone(phone);
                }

                System.out.println("\nPatient details updated successfully.\n");
            } catch (Exception e) {
                System.out.println("Error while updating the patient details. " + e.getMessage());
            }
        }
    }

    // Search for a patient to delete.
    private static void deletePatient(Scanner scanner){
        boolean exit = false;

        while (!exit) {
            // Header
            System.out.println("\nSearch for Patient to Delete:");

            // Allow the user to search for a patient by id or name
            Patient patient = patientSearch(scanner);

            // Show the patient details, and ask for confirmation to delete
            if (patient != null) {
                showPatientDetails(patient);

                // Patient deletion confirmation message
                System.out.print("\nAre you sure you want to delete the patient (Y/N)? ");
                boolean isValidOpt = false;
                boolean deletePatient = false;

                while (!isValidOpt) {
                    String deletedPatientOpt;

                    // Convert to uppercase for easier validation
                    deletedPatientOpt = scanner.nextLine().toUpperCase();

                    if (!deletedPatientOpt.equals("Y") && !deletedPatientOpt.equals("N")) {
                        System.out.println("Invalid input. Please try again.");
                    } else if (deletedPatientOpt.equals("Y")) {
                        isValidOpt = true;
                        deletePatient = true;
                    } else { // Option "N"
                        isValidOpt = true;
                    }
                }

                // If the user has confirmed the deletion, otherwise, do nothing
                if (deletePatient) {
                    try {
                        // Remove the patient from the list of patients
                        patients.remove(patient);

                        System.out.println("\nPatient deleted successfully.\n");
                    } catch (Exception e) {
                        System.out.println("Error while deleting the patient. " + e.getMessage());
                    }
                }
            } else {
                System.out.println("No patient found with the provided ID or name.");
            }

            // Give the user the option to delete another patient.
            System.out.print("\nWould you like to delete another patient (Y/N)? ");
            boolean isValidOpt = false;

            while (!isValidOpt) {
                String anotherPatientOpt;

                if (scanner.hasNext()) {
                    // Convert to uppercase for easier validation
                    anotherPatientOpt = scanner.next().toUpperCase();

                    if (!anotherPatientOpt.equals("Y") && !anotherPatientOpt.equals("N")) {
                        System.out.println("Invalid input. Please try again.");
                    } else if (anotherPatientOpt.equals("N")) {
                        isValidOpt = true;
                        exit = true;
                    } else { // Option "Y"
                        isValidOpt = true;
                    }
                }
            }
        }

        // Return to the main menu
        System.out.println("\nReturning to the main menu...\n");
    }

    // Assign a patient to a doctor's list of patients
    private static void assignPatientDoc(Scanner scanner){
        // Header
        System.out.println("\nAssign a Patient to a Doctor:");
        System.out.println("\nDoctor Search:");

        // TODO Remove test values
        // Doctor for testing
        Doctor doctor = doctors.getFirst();

        // Search for a doctor function
        // Show doctor details function
        // Confirmation message

        System.out.println("\nPatient Search:");

        // Search for a patient to attach to the doctor
        Patient patient = patientSearch(scanner);

        if (patient != null) {
            // Show the patient details and allow them to modify each attribute.
            showPatientDetails(patient);

            // TODO Fix when searching the patient by name, First name entry is skipped. Works when searching by ID
            // Try to add the patient to the doctor's patients list
            try {
                doctor.addPatient(patient);

                // Message for successful addition
                System.out.println("\n" + patient.getFirstName() + " " + patient.getLastName() +
                        " added successfully to Dr. " + doctor.getLastName() + "'s patients.");
            } catch (Exception e) {
                System.out.println("Error while adding the patient to the doctor's patients list. " + e.getMessage());
            }
        } else {
            System.out.println("No patient found with the provided ID or name.");
        }
    }

    // Manage mediation Sub Menu
    private static void manageMedication(Scanner scanner) {
        System.out.println("\nPlease make a selection:\n");
        System.out.println("1. Add a medication");
        System.out.println("2. Search for a medication");
        System.out.println("3. Edit a medication");
        System.out.println("4. Delete a medication");
        System.out.println("5. Restock a medication");
        System.out.println("6. Back to main menu");

        if (scanner.hasNextInt()) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Medication name:");
                    String name = scanner.nextLine();

                    System.out.println("Dosage:");
                    double dose = scanner.nextDouble();

                    System.out.println("Quantity in stock:");
                    int quantity = scanner.nextInt();

                    System.out.println("Expiry date (yyyy-mm-dd):");
                    String input = scanner.nextLine();
                    LocalDate expiry = LocalDate.parse(input, dateFormat);


                    Medication medication = new Medication(name, dose, quantity, expiry);

                    medications.add(medication);

                    System.out.println("Medication created successfully");

                    break;
                case 2:
                    System.out.println("Medication name:");
                    String search1 = scanner.nextLine();
                    boolean found1 = false;

                    for (int i = 0; i < medications.size(); i++) {
                        if (medications.get(i).getName().toLowerCase() == search1.toLowerCase()) {
                            found1 = true;
                            System.out.println(medications.get(i));
                            break;
                        }
                    }

                    if (!found1) {
                        System.out.println("No medication with that name was found.");
                    }

                    break;
                case 3:
                    System.out.println("Medication name:");
                    String search2 = scanner.nextLine();
                    boolean found2 = false;

                    for (int i = 0; i < medications.size(); i++) {
                        if (medications.get(i).getName().toLowerCase() == search2.toLowerCase()) {
                            found2 = true;
                            Medication editMed = medications.get(i);

                            System.out.println("New medication name:");
                            editMed.setName(scanner.nextLine());

                            System.out.println("New dose:");
                            editMed.setDose(scanner.nextDouble());

                            System.out.println("New expiration date:");
                            String input2 = scanner.nextLine();
                            LocalDate date = LocalDate.parse(input2, dateFormat);
                            editMed.setExpiryDate(date);

                            System.out.println("Medication updated successfully");
                            break;
                        }
                    }

                    if (!found2) {
                        System.out.println("No medication with that name was found.");
                    }

                    break;
                case 4:
                    System.out.println("Medication name:");
                    String search3 = scanner.nextLine();
                    boolean found3 = false;

                    for (int i = 0; i < medications.size(); i++) {
                        if (medications.get(i).getName().toLowerCase() == search3.toLowerCase()) {
                            found3 = true;

                            medications.remove(i);
                            System.out.println("Medication removed successfully.");

                            break;
                        }
                    }

                    if (!found3) {
                        System.out.println("No medication with that name was found.");
                    }
                    break;
                case 5:
                    System.out.println("Medication name:");
                    String search4 = scanner.nextLine();
                    boolean found4 = false;

                    for (int i = 0; i < medications.size(); i++) {
                        if (medications.get(i).getName().toLowerCase() == search4.toLowerCase()) {
                            found4 = true;

                            System.out.println("Quantity to add to stock:");
                            int addition = scanner.nextInt();

                            medications.get(i).addQuantity(addition);
                            break;
                        }
                    }

                    if (!found4) {
                        System.out.println("No medication with that name was found.");
                    }


                    break;
                case 6:
                    return;
                default:
                    System.out.println("Please enter a value between 1-6.");
            }
        } else {
            System.out.println("Invalid input. Must be a numeric value.");
        }
    }

    // Manage doctor Sub Menu
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
                        System.out.println("\n***** Back to the main menu *****");
                        exit = true;
                        break;
                    default:
                        System.out.println("Please enter a value between 1-5.");
                }
            } else {
                System.out.println("Invalid input. Must be a numeric value.");
                scanner.nextLine();
            }
        }
        System.out.println("\nReturning to the main menu...\n");
    }


    private static void addDoctor(Scanner scanner) {
        //
        try {
            String firstName;
            while (true) {
                System.out.print("Enter First Name: ");
                firstName = scanner.nextLine().trim();
                if (firstName.matches("^[A-Za-z]+$")) {
                    break;
                } else {
                    System.out.println("Invalid first name. Please use letters only.");
                }
            }

            String lastName;
            while (true) {
                System.out.print("Enter Last Name: ");
                lastName = scanner.nextLine().trim();
                if (lastName.matches("^[A-Za-z]+$")) {
                    break;
                } else {
                    System.out.println("Invalid last name. Please use letters only.");
                }
            }

            String dateOfBirth;
            LocalDate dob;
            while (true) {
                System.out.print("Enter Date Of Birth (YYYY-MM-DD): ");
                dateOfBirth = scanner.nextLine().trim();
                if (dateOfBirth.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    try {
                        dob = LocalDate.parse(dateOfBirth);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid date. Please enter a valid date.");
                    }
                } else {
                    System.out.println("Invalid format. Use YYYY-MM-DD.");
                }
            }

            String phone;
            while (true) {
                System.out.print("Enter Phone Number (10 digits): ");
                phone = scanner.nextLine().trim();
                if (phone.matches("^\\d{10}$")) {
                    break;
                } else {
                    System.out.println("Invalid phone number. Must be 10 digits.");
                }
            }

            char gender;
            while (true) {
                System.out.print("Enter Gender (M/F/O): ");
                String genderInput = scanner.nextLine().trim().toUpperCase();
                if (genderInput.matches("^[MFO]$")) {
                    gender = genderInput.charAt(0);
                    break;
                } else {
                    System.out.println("Invalid input. Please enter M, F, or O.");
                }
            }

            System.out.println("Enter Specialization Of Doctor: ");
            String specialization = scanner.nextLine();

            Doctor newDoctor = new Doctor(firstName, lastName, dob, phone, gender, specialization,
                    new ArrayList<>());

            doctors.add(newDoctor);
            System.out.println("\nDoctor " + firstName + " " + lastName + " added successfully");

            // Pause to let user read result
            System.out.print("To return press enter: ");
            scanner.nextLine();



        } catch (Exception e) {
            System.out.println("Error adding doctor: " + e.getMessage());
            System.out.print("To return press enter: ");
            scanner.nextLine();
        }
    }

    private static void searchDoctor(Scanner scanner) {
        Doctor doctor = doctorSearch(scanner);
    
        if (doctor != null) {
            // Already displayed in doctorSearch()
        } else {
            System.out.println("No doctor found with that ID or name.");
        }
    
        System.out.print("To return, press Enter: ");
        scanner.nextLine();
    }
    
// Allows the user to search for a doctor by ID or by first and last name
private static Doctor doctorSearch(Scanner scanner) {
    int id = 0;
    String firstName = null;
    String lastName = null;

    String input;
    boolean isValidInput = false;
    Doctor doctor = null;

    // Prompt for ID (optional)
    System.out.print("Doctor ID (leave blank to search by name): ");

    while (!isValidInput) {
        input = scanner.nextLine().trim();

        if (!input.isEmpty()) {
            try {
                id = Integer.parseInt(input);
                if (id > 0) {
                    isValidInput = true;
                } else {
                    System.out.println("ID must be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter numeric digits.");
            }
        } else {
            // Leave blank for name search
            isValidInput = true;
        }
    }

    // Search by ID
    if (id != 0) {
        for (Doctor d : doctors) {
            if (d.getId() == id) {
                doctor = d;
                break;
            }
        }
    } else {
        // Prompt for first name
        while (true) {
            System.out.print("Enter Doctor First Name: ");
            firstName = scanner.nextLine().trim();
            if (firstName.matches("^[A-Za-z]+$")) break;
            else System.out.println("Invalid first name. Use letters only.");
        }

        // Prompt for last name
        while (true) {
            System.out.print("Enter Doctor Last Name: ");
            lastName = scanner.nextLine().trim();
            if (lastName.matches("^[A-Za-z]+$")) break;
            else System.out.println("Invalid last name. Use letters only.");
        }

        for (Doctor d : doctors) {
            if (d.getFirstName().equalsIgnoreCase(firstName)
                    && d.getLastName().equalsIgnoreCase(lastName)) {
                doctor = d;
                break;
            }
        }
    }

    // Display result
    if (doctor != null) {
        System.out.println("\nDoctor found:");
        System.out.println("ID: " + doctor.getId());
        System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
        System.out.println("Date of Birth: " + doctor.getDateOfBirth());
        System.out.println("Phone: " + doctor.getPhone());
        System.out.println("Gender: " + doctor.getGender());
        System.out.println("Specialization: " + doctor.getSpecialization());
    } else {
        System.out.println("No matching doctor found.");
    }

    return doctor;
}

private static void editDoctor(Scanner scanner) {
    Doctor doctor = doctorSearch(scanner);

    if (doctor == null) {
        System.out.println("Doctor not found. Cannot edit.");
        return;
    }

    System.out.println("Editing Doctor: " + doctor.getFirstName() + " " + doctor.getLastName());

    // Ask for new phone number
    System.out.print("New Phone (press Enter to keep current): ");
    String phone = scanner.nextLine().trim();
    if (!phone.isEmpty()) {
        if (phone.matches("^\\d{10}$")) {
            doctor.setPhone(phone);
        } else {
            System.out.println("Invalid phone number. Must be 10 digits. Keeping existing.");
        }
    }

    // Ask for new specialization
    System.out.print("New Specialization (press Enter to keep current): ");
    String specialization = scanner.nextLine();
    if (!specialization.isEmpty()) {
        doctor.setSpecialization(specialization);
    }

    System.out.println("Doctor info updated successfully.");
    System.out.print("To return, press Enter: ");
    scanner.nextLine();
}

    // removes a doctor from the list using their ID and full name
    private static void deleteDoctor(Scanner scanner) {
        Doctor doctor = doctorSearch(scanner);
    
        if (doctor == null) {
            System.out.println("Doctor not found. Cannot delete.");
            return;
        }
    
        doctors.remove(doctor);
        System.out.println("Doctor " + doctor.getFirstName() + " " + doctor.getLastName() + " deleted successfully.");
        System.out.print("To return, press Enter: ");
        scanner.nextLine();
    }
    
    // Accept a prescription
    private static void accPresc() {

    }

    // created some data to preload in until additional data can be created.
    private static void preloadData() {
        // Create sample doctors
        Doctor doc1 = new Doctor("Justin", "Greenslade",
                LocalDate.of(1992, 2, 19), "7091234567", 'M', "karaoke", new ArrayList<>());
        Doctor doc2 = new Doctor("Barba", "Brown",
                LocalDate.of(1975, 3, 15), "7091231111", 'F', "Cooking Jigs", new ArrayList<>());
        doctors.add(doc1);
        doctors.add(doc2);

        // Create empty medication and prescription lists for patients
        ArrayList<Medication> emptyMedList = new ArrayList<>();
        ArrayList<Prescription> emptyPrescList = new ArrayList<>();

        // Create sample patients with empty meds and prescriptions lists
        Patient pat1 = new Patient("Bob", "Ross",
                LocalDate.of(1990, 1, 10), "7093334444", 'M', emptyMedList, emptyPrescList);
        Patient pat2 = new Patient("Jim", "Roberts",
                LocalDate.of(1985, 11, 5), "7094443333", 'M', emptyMedList, emptyPrescList);
        patients.add(pat1);
        patients.add(pat2);

        // Create sample medications
        Medication med1 = new Medication("Aspirin", 500, 30, LocalDate.of(2024, 12, 31));
        Medication med2 = new Medication("Ibuprofen", 200, 20, LocalDate.of(2025, 6, 30));
        medications.add(med1);
        medications.add(med2);

        // Create sample prescriptions
        Prescription presc1 = new Prescription(doc1, pat1, med1, LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 12, 1));
        Prescription presc2 = new Prescription(doc2, pat2, med2, LocalDate.of(2025, 5, 15),
                LocalDate.of(2025, 11, 15));
        prescriptions.add(presc1);
        prescriptions.add(presc2);

        // Add the prescriptions and medications to the patients
        pat1.addMedication(med1);
        pat1.addPrescription(presc1);

        pat2.addMedication(med2);
        pat2.addPrescription(presc2);
    }

    // Reports Sub Menu
    private static void reportsMenu(Scanner scanner) {
        boolean exit = false;


    while (!exit) {
        System.out.println("\n***** Reports Menu *****");
        System.out.println("\nPlease make a selection:\n");
        System.out.println("1. Generate a General Report");
        System.out.println("2. Generate a Report for Expired Medication");
        System.out.println("3. Generate a Prescriptions Report by Doctor");
        System.out.println("4. Generate a Report of Patients Prescriptions (past year)");
        System.out.println("5. Back to main menu");
        System.out.println("*************************************************************\n");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("General Report Not created yet");
                        break;
                    case 2:
                        expiredMedicationReport(scanner);
                        break;
                    case 3:
                        generateDoctorReport(scanner);
                      break;
                    case 4:
                        generatePatientsPrescReport(patients, scanner);
                        break;
                    case 5:
                        System.out.println("\n***** Back to the main menu *****");
                        exit = true;
                        break;
                    default:
                        System.out.println("Please enter a value between 1-5.");
                }
            } else {
                System.out.println("Invalid input. Must be a numeric value.");
                scanner.nextLine(); // consume invalid input
            }
        }

        System.out.println("\nReturning to the main menu...\n");
    }

    // Generate a report for the prescriptions of the past years for all the patients
    private static void generatePatientsPrescReport(ArrayList<Patient> patients, Scanner scanner) {
        // Header
        System.out.println("\n\n***** Patients Prescriptions issued in the past year *****");

        // Generate a prescriptions report for each patient in the list of patients
        if (!patients.isEmpty()) {
            for (Patient patient : patients) {
                // Show prescriptions of the past year for each patient.
                showPatientCurrentPrescs(patient);
            }
        } else {
            System.out.println("\nNo patients found.\n");
        }

        System.out.print("Press Enter/Return to return to the main menu.");
        // Clear the scanner
        scanner.nextLine();

        // Accept a blank input
        scanner.nextLine();

        System.out.println("\n");
    }

    private static void generateDoctorReport(Scanner scanner) {
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter Doctor ID to generate report: ");
        String input = scanner.nextLine().trim();
        int doctorId;

        try {
            doctorId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Doctor ID. Please enter a numeric value.");
            return;
        }

        Doctor doctor = null;
        for (Doctor d : doctors) {
            if (d.getId() == doctorId) {
                doctor = d;
                break;
            }}

        if (doctor == null) {
            System.out.println("Doctor not found with ID: " + doctorId);
            return;
        }

        printPrescriptionReportForDoctor(doctor, scanner);
    }

    private static void expiredMedicationReport(Scanner scanner) {
        boolean foundExpired = false;
        LocalDate today = LocalDate.now();
        for (Medication m : medications) {
            if (m.getExpiryDate().isBefore(today)) {
                foundExpired = true;
                System.out.println("Medication name: " + m.getName());
                System.out.println("Dose: " + m.getDose());
                System.out.println("Quantity in stock: " + m.getQuantity());
                System.out.println("Expiration date: " + m.getExpiryDate());
            }
        }

        if (!foundExpired) {
            System.out.println("No expired medications found.");
        }

        System.out.println("To return press enter: ");
        scanner.nextLine();
}

private static void printPrescriptionReportForDoctor(Doctor doctor, Scanner scanner) {
    System.out.println("\nPrescription Report for Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
    System.out.println("-------------------------------------------");

    boolean foundAny = false;

    for (Prescription p : prescriptions) {
        if (p.getDoctor().getId() == doctor.getId()) {
            foundAny = true;

            System.out.println("Prescription ID: " + p.getId());
            System.out.println("Patient First Name: " + p.getPatient().getFirstName());
            System.out.println("Patient Last Name: " + p.getPatient().getLastName());
            System.out.println("Medication: " + p.getName());
            System.out.println("Dose: " + p.getDose());
            System.out.println("Quantity: " + p.getQuantity());
            System.out.println("Issue Date: " + p.getIssueDate());
            System.out.println("Prescription Expiry: " + p.getPrescriptionExpiry());
            System.out.println("-------------------------------------------");

            System.out.print("Press Enter to continue...");
            scanner.nextLine();
        }
    }

    if (!foundAny) {
        System.out.println("No prescriptions found for this doctor.");

    }

}

}
