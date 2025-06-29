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

/**
 * Main program. Includes the menus and required functions.
 */
public class MedicationTrackingSystem {
    // Private attributes
    private static final ArrayList<Patient> patients = new ArrayList<>();
    private static final ArrayList<Doctor> doctors = new ArrayList<>();
    private static final ArrayList<Medication> medications = new ArrayList<>();
    private static final ArrayList<Prescription> prescriptions = new ArrayList<>();

    private static boolean prescAccepted = false;

    /**
     * The main entry point of the Pharmacy Medication Tracking System.
     * Initializes the application by preloading data and displaying the main menu.
     * Allows the user to navigate to patient, medication, doctor management, prescription handling, or report generation.
     * The application runs until the user chooses to exit.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Loading my preloaded data here
        preloadData();

        boolean exit = false;

        while (!exit) {
            clearConsole();
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n***** Welcome To The Pharmacy Medication Tracking System *****");
            System.out.println("\nPlease make a selection:\n");
            System.out.println("1. Manage a patient");
            System.out.println("2. Manage a medication");
            System.out.println("3. Manage a doctor");
            System.out.println("4. Accept a prescription");
            System.out.println("5. Generate a report");
            System.out.println("6. Exit");
            System.out.println("**************************************************************");

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
                        accPresc(scanner);
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
    /**
     * Handles the patient management submenu including adding, updating, or removing patients.
     * @param scanner Scanner for input.
     */
    private static void managePatient(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            clearConsole();
            System.out.println("\n***** Patient Management *****");
            System.out.println("\nPlease make a selection:\n");
            System.out.println("1. Add a Patient");
            System.out.println("2. Find a Patient");
            System.out.println("3. Edit a Patient");
            System.out.println("4. Delete a Patient");
            System.out.println("5. Exit");
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
                        System.out.println("\n***** Back to the main menu *****");
                        exit = true;
                        break;
                    default:
                        System.out.println("Please enter a value between 1-5.");
                }
            } else {
                System.out.println("Invalid input. Must be a numeric value.");
                // Clear the scanner to accept another value.
                scanner.nextLine();
            }
        }


    }

    /**
     * Prompts the user to input details for a new patient, validates each entry,
     * creates a Patient object, and adds it to the system's patient list.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
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

            scanner.nextLine();

            // Date of birth input
            System.out.print("Date of Birth (YYYY-MM-DD): ");

            // Date validation
            while (true) {
                String dateOfBirthStr = scanner.nextLine();

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
            
            // Phone number input
            System.out.print("Phone Number (10 digits): ");
            while (true) {
                phone = scanner.nextLine();

                // Validate that the format is correct
                if (phone.matches("\\d{10}")) {
                    break;
                } else {
                    System.out.println("Invalid phone number. Please try again.");
                }
            }
            
            // Gender input
            System.out.print("Gender (M, F, O): ");
            while (true) {
                String genderStr = scanner.nextLine();

                // Validate that the format is correct
                if (genderStr.matches("[mfoMFO]")) {
                    gender = genderStr.toUpperCase().charAt(0);
                    break;
                } else {
                    System.out.println("Invalid gender. Please try again.");
                }
            }
            
            // Try to create and add the new patient to the list of patients
            try {
                Patient patient = new Patient(firstName, lastName, dateOfBirth, phone, gender, new ArrayList<>(), new ArrayList<>());
                patients.add(patient);

                System.out.println("\nPatient added successfully.");
            } catch (Exception e) {
                System.out.println("\nError while adding the new patient: \n" + e.getMessage());
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

    }

    /**
     * Searches for a patient in the system using their ID or name.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void findPatient(Scanner scanner) {
        // Header
        System.out.println("\nSearch for Patient Details:");

        // Allow the user to search for a patient by id or name
        scanner.nextLine();
        Patient patient = patientSearch(scanner);

        // Show the patient details, including their list of medications and prescriptions
        if (patient != null) {
            showPatientDetails(patient);
            showPatientMeds(patient);

            // Hide the patient's prescriptions that are expired
            showPatientPrescs(patient, true, false);
            System.out.print("\nPress Enter to continue.");
            scanner.nextLine();
        } else {
            System.out.println("No patient found with the provided ID or name.");
            System.out.print("\nPress Enter to continue.");
            scanner.nextLine();

        }
    }

    /**
     * Searches for a patient in the system either by patient ID or by first and last name.
     *
     * @param scanner The Scanner object used to read user input from the console.
     * @return The Patient object if a match is found; null otherwise.
     */
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
                firstName = scanner.nextLine().trim();
                
            }

            // Last name input
            System.out.print("Last Name: ");
            if (scanner.hasNext()) {
                lastName = scanner.nextLine().trim();
                ;
            }

            // Search for a patient by its full name
            for (Patient curPatient : patients) {
                if (curPatient.getFirstName().equalsIgnoreCase(firstName) && curPatient.getLastName().equalsIgnoreCase(lastName)) {
                    patient = curPatient;
                    break;
                }
            }
        }

        return patient;
    }

    /**
     * Searches for a doctor in the system either by doctor ID or by first and last name.
     *
     * @param scanner The Scanner object used to read user input from the console.
     * @return The Doctor object if a match is found; null otherwise.
     */
    private static Doctor docSearch(Scanner scanner) {
        // Variables declaration
        int id = 0;
        String firstName = null;
        String lastName = null;

        String input;
        boolean isValidInput = false;

        Doctor doctor = null;

        // ID input
        System.out.print("Doctor ID (leave blank for name search): ");

        // Clear the scanner before to accept a blank value
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
            for (Doctor curDoctor : doctors) {
                if (curDoctor.getId() == id) {
                    doctor = curDoctor;
                    break;
                }
            }
        } else {
            // Search by first name and last name

            // First name input
            System.out.print("First Name: ");
            if (scanner.hasNext()) {
                firstName = scanner.nextLine().trim();
            }

            // Last name input
            System.out.print("Last Name: ");
            if (scanner.hasNext()) {
                lastName = scanner.nextLine().trim();
            }

            // Search for a patient by its full name
            for (Doctor curDoctor : doctors) {
                if (curDoctor.getFirstName().equalsIgnoreCase(firstName) && curDoctor.getLastName().equalsIgnoreCase(lastName)) {
                    doctor = curDoctor;
                    break;
                }
            }
        }

        return doctor;
    }

    /**
     * Displays detailed information about a patient, including ID, name, age,
     * date of birth, phone number, and gender.
     *
     * @param patient The Patient object whose details are to be displayed.
     */
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

    /**
     * Displays detailed information about a doctor, including ID, name, age,
     * date of birth, phone number, and gender.
     *
     * @param doctor The Doctor object whose details are to be displayed.
     */
    private static void showDoctorDetails(Doctor doctor) {
        System.out.println("\nDoctor Details:");
        System.out.println("--------------------");
        System.out.println("ID: " + doctor.getId());
        System.out.println("First Name: " + doctor.getFirstName());
        System.out.println("Last Name: " + doctor.getLastName());
        System.out.println("Age: " + doctor.getAge());
        System.out.println("Date of Birth: " + doctor.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println("Phone Number: " + doctor.getPhone());
        System.out.println("Gender: " + doctor.getGender());
    }

    /**
     * Displays a list of medications prescribed to the specified patient,
     * including the name, dose, and expiry date of each medication.
     *
     * @param patient The Patient object whose medications are to be displayed.
     */
    private static void showPatientMeds(Patient patient) {
        System.out.println("\nMedications:");
        System.out.println("--------------------");
        for (Medication med : patient.getMedications()) {
            System.out.println("Name: " + med.getName());
            System.out.println("Dose: " + med.getDose());
            System.out.println("Expiry Date: " + med.getExpiryDate() + "\n");
        }
    }

    /**
     * Displays a list of prescriptions for the specified patient based on the provided filters.
     * Only prescriptions that meet the specified conditions for age and expiration are shown.
     *
     * @param patient        The Patient object whose prescriptions are to be displayed.
     * @param showOldPrescs  If true, includes prescriptions older than one year.
     * @param showExpired    If true, includes prescriptions that have expired.
     */
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

    /**
     * Displays the names of the patient's current prescriptions issued within the last year.
     *
     * @param patient The Patient object whose current prescriptions are to be displayed.
     */
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

    /**
     * Allows the user to search for a patient by ID or name and edit their details.
     * After displaying the patient's current information, the user can update each attribute.
     * The process can be repeated for multiple patients until the user chooses to exit.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void editPatient(Scanner scanner){
        boolean exit = false;

        while (!exit) {
            // Header
            System.out.println("\nEdit a Patient's Details:");

            // Search for a patient to edit
            scanner.nextLine();
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

    /**
     * Allows the user to modify a patient's first name, last name, and phone number.
     * Prompts the user for new values, validates phone number input, and confirms
     * changes before applying them. Fields can be left blank to remain unchanged.
     *
     * @param patient The Patient object whose details are to be edited.
     * @param scanner The Scanner object used to read user input from the console.
     */
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

    /**
     * Allows the user to search for a patient by ID or name and delete them from the system.
     * Displays patient details for confirmation before deletion. The user can delete
     * multiple patients in one session or return to the main menu.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void deletePatient(Scanner scanner){
        boolean exit = false;

        while (!exit) {
            // Header
            System.out.println("\nSearch for Patient to Delete:");

            // Allow the user to search for a patient by id or name
            scanner.nextLine();
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


    }

    /**
     * Allows the user to assign a patient to a doctor's list of patients.
     * The user searches for both the doctor and the patient by ID or name.
     * If both are found, the patient is added to the doctor's list and a confirmation message is displayed.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void assignPatientDoc(Scanner scanner){
        // Header
        System.out.println("\nAssign a Patient to a Doctor:");
        System.out.println("\nDoctor Search:");
        // Search for a doctor to add a patient to their list of patients

        Doctor doctor = docSearch(scanner);

        if (doctor != null) {
            // Show doctor details.
            showDoctorDetails(doctor);

            System.out.println("\nPatient Search:");

            // Search for a patient to attach to the doctor
            Patient patient = patientSearch(scanner);

            if (patient != null) {
                // Show the patient details.
                showPatientDetails(patient);

                // Try to add the patient to the doctor's patients list
                try {
                    doctor.addPatient(patient);

                    // Message for successful addition
                    System.out.println("\n" + patient.getFirstName() + " " + patient.getLastName() +
                            " added successfully to Dr. " + doctor.getLastName() + "'s list of patients.");
                } catch (Exception e) {
                    System.out.println("Error while adding the patient to the doctor's patients list. " + e.getMessage());
                }
            } else {
                System.out.println("No patient found with the provided ID or name.");
            }
        } else {
            System.out.println("No Doctor found with the provided ID or name.");
        }

        System.out.print("\nPress Enter/Return to return to the main menu.");
        // Accept a blank input
        scanner.nextLine();
    }

    /**
     * Displays the medication management menu and handles operations such as
     * adding, searching, editing, deleting, and restocking medications.
     * The user can select an option from the menu and interact with the medication list accordingly.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void manageMedication(Scanner scanner) {
        int option = 0;
        String input = "";

        while (option != 6) {
            clearConsole();
            System.out.println("\n***** Medication Management *****\n");
            System.out.println("Please make a selection:\n");
            System.out.println("1. Add a medication");
            System.out.println("2. Search for a medication");
            System.out.println("3. Edit a medication");
            System.out.println("4. Delete a medication");
            System.out.println("5. Restock a medication");
            System.out.println("6. Back to main menu");
            System.out.println("**************************************************************");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // Scanner buffer

                switch (option) {
                    case 1: // Add a medication
                        System.out.println("\nMedication name:");
                        String name = "";

                        // Validation for medication name
                        while (true) {
                            input = scanner.nextLine();

                            if (input.isEmpty()) {
                                System.out.println("Please enter a medication name.");
                            } else {
                                name = input;
                                break;
                            }
                        }

                        System.out.println("Dosage:");
                        double dose = 0d;

                        // Dose validation
                        while (true) {
                            String doseStr = scanner.nextLine();

                            // Validate that the number is valid.
                            if (doseStr.matches("\\d+")) {
                                dose = Double.parseDouble(doseStr);

                                if (dose <= 0) {
                                    System.out.println("The number must be be positive. Please try again.");
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println("Not a number or negative number entered. Please try again.");
                            }
                        }

                        System.out.println("Quantity in stock:");
                        int quantity = 0;

                        // Quantity validation
                        while (true) {
                            String quantityStr = scanner.nextLine();

                            // Validate that the number is valid.
                            if (quantityStr.matches("\\d+")) {
                                quantity = Integer.parseInt(quantityStr);

                                break;
                            } else {
                                System.out.println("Not a number or negative number entered. Please try again.");
                            }
                        }

                        System.out.println("Expiry date (yyyy-mm-dd):");
                        LocalDate expiry = null;

                        // Date validation
                        while (true) {
                            String expiryStr = scanner.nextLine();

                            // Validate that the format is correct
                            if (expiryStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                try {
                                    // Try to parse the date entry into a LocalDate
                                    expiry = LocalDate.parse(expiryStr);
                                    break;
                                } catch (Exception exception) {
                                    System.out.println("Invalid date. Please try again.");
                                }
                            } else {
                                System.out.println("Invalid date format. Please try again.");
                            }
                        }
                        
                        Medication medication = new Medication(name, dose, quantity, expiry);
                        medications.add(medication);

                        System.out.println("\nMedication created successfully!\nPress enter to return to menu...");
                        scanner.nextLine();
                        break;

                    case 2: // Search for a medication
                        System.out.println("\nMedication name:");
                        String search1 = scanner.nextLine();
                        boolean found1 = false;

                        for (int i = 0; i < medications.size(); i++) {
                            if (medications.get(i).getName().equalsIgnoreCase(search1)) {
                                found1 = true;
                                System.out.println(medications.get(i));
                                System.out.print("\nPress Enter to continue.");
                                scanner.nextLine();
                                break;
                            }
                        }

                        if (!found1) {
                            System.out.println("\nNo medication with that name was found.");
                            System.out.print("Press Enter to continue.");
                            scanner.nextLine();
                        }

                        break;

                    case 3: // Edit a medication
                        System.out.println("\nMedication name:");
                        String search2 = scanner.nextLine();
                        boolean found2 = false;

                        for (int i = 0; i < medications.size(); i++) {
                            if (medications.get(i).getName().equalsIgnoreCase(search2)) {
                                found2 = true;
                                Medication editMed = medications.get(i);

                                System.out.println("New medication name:");

                                // Validation for medication name
                                while (true) {
                                    input = scanner.nextLine();

                                    if (input.isEmpty()) {
                                        System.out.println("Please enter a new medication name.");
                                    } else {
                                        editMed.setName(input);
                                        break;
                                    }
                                }

                                System.out.println("New dose:");
                                double editDose = 0d;

                                // Dose validation
                                while (true) {
                                    String doseStr = scanner.nextLine();

                                    // Validate that the number is valid.
                                    if (doseStr.matches("\\d+")) {
                                        editDose = Double.parseDouble(doseStr);

                                        editMed.setDose(editDose);
                                        break;
                                    } else {
                                        System.out.println("Not a number or negative number entered. Please try again.");
                                    }
                                }

                                System.out.println("New expiration date:");

                                // Date validation
                                while (true) {
                                    String expiryStr = scanner.nextLine();

                                    // Validate that the format is correct
                                    if (expiryStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                        try {
                                            // Try to parse the date entry into a LocalDate
                                            editMed.setExpiryDate(LocalDate.parse(expiryStr));
                                            break;
                                        } catch (Exception exception) {
                                            System.out.println("Invalid date. Please try again.");
                                        }
                                    } else {
                                        System.out.println("Invalid date format. Please try again.");
                                    }
                                }

                                System.out.println("Medication updated successfully");
                                System.out.print("Press Enter to continue.");
                                scanner.nextLine();
                                break;
                            }
                        }

                        if (!found2) {
                            System.out.println("No medication with that name was found.");
                            System.out.print("Press Enter to continue.");
                            scanner.nextLine();
                        }

                        break;

                    case 4: // Delete a medication
                        System.out.println("\nMedication name:");
                        String search3 = scanner.nextLine();
                        boolean found3 = false;

                        for (int i = 0; i < medications.size(); i++) {
                            if (medications.get(i).getName().equalsIgnoreCase(search3)) {
                                found3 = true;
                                medications.remove(i);
                                System.out.println("Medication removed successfully.");
                                System.out.print("Press Enter to continue.");
                                scanner.nextLine();
                                break;
                            }
                        }

                        if (!found3) {
                            System.out.println("No medication with that name was found.");
                            System.out.print("Press Enter to continue.");
                            scanner.nextLine();
                        }
                        break;

                    case 5: // Restock a medication
                        System.out.println("\nMedication name:");
                        String search4 = scanner.nextLine();
                        boolean found4 = false;

                        for (int i = 0; i < medications.size(); i++) {
                            if (medications.get(i).getName().equalsIgnoreCase(search4)) {
                                found4 = true;

                                System.out.println("Quantity to add to stock:");
                                int addition = 0;

                                // Addition validation
                                while (true) {
                                    String additionStr = scanner.nextLine();

                                    // Validate that the number is valid.
                                    if (additionStr.matches("\\d+")) {
                                        addition = Integer.parseInt(additionStr);

                                        break;
                                    } else {
                                        System.out.println("Not a number or negative number entered. Please try again.");
                                    }
                                }

                                medications.get(i).addQuantity(addition);
                                System.out.println("Medication restocked successfully!");
                                System.out.print("Press Enter to continue.");
                                scanner.nextLine();
                                break;
                            }
                        }

                        if (!found4) {
                            System.out.println("\nNo medication with that name was found.");
                            System.out.print("Press Enter to continue.");
                            scanner.nextLine();
                        }

                        break;

                    case 6:

                        break;

                    default:
                        System.out.println("Please enter a value between 1-6.");
                }
            } else {
                System.out.println("Invalid input. Must be a numeric value.");
                scanner.nextLine(); // clear invalid input
            }

            System.out.println(); // spacing
        }
    }

    /**
     * Displays the doctor management menu and handles operations such as
     * adding, searching, editing, deleting doctors, and assigning a patient to a doctor.
     * Continues to prompt the user until they choose to return to the main menu.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void manageDoctor(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            clearConsole();
            System.out.println("\n***** Doctor Management *****");
            System.out.println("\nPlease make a selection:\n");
            System.out.println("1. Add Doctor");
            System.out.println("2. Search Doctor");
            System.out.println("3. Edit Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Assign a Patient to a Doctor");
            System.out.println("6. Back to main menu");
            System.out.println("*************************************************************");

            // Validation for the Doctor management menu selection
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
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
            }
        }

    }

    /**
     * Prompts the user to enter details for a new doctor, validates the input fields,
     * and adds the doctor to the system. The details include first name, last name,
     * date of birth, phone number, gender, and specialization.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void addDoctor(Scanner scanner) {
        //
        try {
            scanner.nextLine();
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
            System.out.print("\nPress Enter to return.");
            scanner.nextLine();



        } catch (Exception e) {
            System.out.println("Error adding doctor: " + e.getMessage());
            System.out.print("\nPress Enter to return.");
            scanner.nextLine();
        }
    }

    /**
     * Searches for a doctor by ID or name and displays the result.
     * If no matching doctor is found, a message is shown to the user.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void searchDoctor(Scanner scanner) {
        scanner.nextLine();
        Doctor doctor = doctorSearch(scanner);
    
        if (doctor != null) {
            // Already displayed in doctorSearch()
        } else {
            System.out.println("No doctor found with that ID or name.");
        }
    
        System.out.print("\nPress Enter to return.");
        scanner.nextLine();
    }
    
    /**
     * Searches for a doctor in the system either by doctor ID or by first and last name.
     * If a matching doctor is found, their details are displayed in the console.
     *
     * @param scanner The Scanner object used to read user input from the console.
     * @return The Doctor object if a match is found; null otherwise.
     */
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

    /**
     * Allows the user to edit a doctor's phone number and specialization.
     * The user is prompted to enter new values or press Enter to keep existing ones.
     * Validation is performed on the phone number input.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void editDoctor(Scanner scanner) {
        scanner.nextLine();
        Doctor doctor = doctorSearch(scanner);

        if (doctor == null) {
            System.out.println("Doctor not found. Cannot edit.");
            System.out.print("Press Enter to return.");
            scanner.nextLine();
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
        System.out.print("\nPress Enter to return.");
        scanner.nextLine();
    }

    /**
     * Removes a doctor from the system by searching with their ID or full name.
     * If the doctor is found, they are removed from the list of doctors.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void deleteDoctor(Scanner scanner) {
        scanner.nextLine();
        Doctor doctor = doctorSearch(scanner);
    
        if (doctor == null) {
            System.out.println("Doctor not found. Cannot delete.");
            System.out.print("\nPress Enter to return.");
            scanner.nextLine();
            return;
        }
    
        doctors.remove(doctor);
        System.out.println("Doctor " + doctor.getFirstName() + " " + doctor.getLastName() + " deleted successfully.");
        System.out.print("\nPress Enter to return.");
        scanner.nextLine();
    }
    
    /**
     * Accepts a new prescription by creating sample patient, doctor, medication,
     * and prescription objects, linking them together, and adding them to the system.
     * Ensures that only one prescription acceptance can occur per runtime.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void accPresc(Scanner scanner) {
        // Scanner buffer
        scanner.nextLine();

        // Check if function has been run already. If so, tell user and return to menu
        if (prescAccepted) {
            System.out.println("\nYou have already accepted the prescription!\nPress enter to return to menu...");
            scanner.nextLine();
            return;
        }
        // Tells function it has been run already
        prescAccepted = true;

        // Create a new patient and add them to the list
        Patient patient = new Patient("Charlie", "Brown", LocalDate.of(1950, 10, 30), "7098675309", 'M', new ArrayList<>(), new ArrayList<>());
        patients.add(patient);

        // Create a new doctor, assign them to the patient, and add them to the list
        Doctor doctor = new Doctor("Gregory", "House", LocalDate.of(1959, 5, 15), "7095766936", 'M', "Autism", new ArrayList<>());
        doctor.addPatient(patient);
        doctors.add(doctor);

        // Create a new medication and add it to the list
        Medication medication = new Medication("Adderall", 10, 30, LocalDate.of(2025, 11, 4));
        medications.add(medication);

        // Create a new prescription, assign it to the doctor and the patient, and add it to the list
        Prescription prescription = new Prescription(doctor, patient, medication, LocalDate.of(2025, 6, 26), LocalDate.of(2025, 7, 10));
        prescriptions.add(prescription);

        // Assign the new prescription to the patient
        patient.addPrescription(prescription);

        // Give confirmation that the function succeeded and promt to return to menu
        System.out.println("\nMedication accepted successfully!\nPress enter to return to menu...");
        scanner.nextLine();
    }

    /**
     * Preloads sample data into the system for testing and demonstration purposes.
     * This includes creating sample doctors, patients, medications, and prescriptions,
     * and linking the medications and prescriptions to the appropriate patients.
     */
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

    /**
     * Displays the Reports menu and handles user input to generate various reports.
     * Allows the user to generate general reports, expired medication reports,
     * prescription reports by doctor, and patient prescription reports from the past year.
     * The menu loops until the user chooses to return to the main menu.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void reportsMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            clearConsole();
        System.out.println("\n***** Reports Menu *****");
        System.out.println("\nPlease make a selection:\n");
        System.out.println("1. Generate a General Report");
        System.out.println("2. Generate a Report for Expired Medication");
        System.out.println("3. Generate a Prescriptions Report by Doctor");
        System.out.println("4. Generate a Report of Patients Prescriptions (past year)");
        System.out.println("5. Back to main menu");
        System.out.println("*************************************************************");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        generateGeneralReport(scanner);
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

    }

    /**
     * Generates a report of prescriptions issued in the past year for all patients provided.
     * Displays each patient's recent prescriptions or a message if no patients are found.
     *
     * @param patients The list of patients to generate reports for.
     * @param scanner The Scanner object used to read user input from the console.
     */
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

        System.out.print("\nPress Enter/Return to return to the main menu.");
        // Clear the scanner
        scanner.nextLine();

        System.out.println("\n");
    }

    /**
     * Prompts the user to find a doctor either by ID or by first and last name,
     * then generates and displays the prescription report for the found doctor.
     * If no matching doctor is found, displays an appropriate message and returns to the reports menu.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    private static void generateDoctorReport(Scanner scanner) {
        int doctorId = 0;
        String firstName = null;
        String lastName = null;
        Doctor doctor = null;
    
        System.out.print("\nEnter Doctor ID (leave blank to search by name): ");
        String input = scanner.nextLine().trim();
    
        if (!input.isEmpty()) {
            try {
                doctorId = Integer.parseInt(input);
                if (doctorId <= 0) {
                    System.out.println("Doctor ID must be a positive number.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Doctor ID. Please enter a numeric value.");
                return;
            }
    
            for (Doctor d : doctors) {
                if (d.getId() == doctorId) {
                    doctor = d;
                    break;
                }
            }
    
            if (doctor == null) {
                System.out.println("Doctor not found with ID: " + doctorId);
                System.out.print("Press Enter to continue back to Reports Menu...");
                scanner.nextLine();
                return;
            }
    
        } else {
            // Prompt for name search
            while (true) {
                System.out.print("Enter Doctor First Name: ");
                firstName = scanner.nextLine().trim();
                if (firstName.matches("^[A-Za-z]+$")) break;
                else System.out.println("Invalid first name. Use letters only.");
            }
    
            while (true) {
                System.out.print("Enter Doctor Last Name: ");
                lastName = scanner.nextLine().trim();
                if (lastName.matches("^[A-Za-z]+$")) break;
                else System.out.println("Invalid last name. Use letters only.");
            }
    
            for (Doctor d : doctors) {
                if (d.getFirstName().equalsIgnoreCase(firstName) &&
                    d.getLastName().equalsIgnoreCase(lastName)) {
                    doctor = d;
                    break;
                }
            }
    
            if (doctor == null) {
                System.out.println("Doctor not found with name: " + firstName + " " + lastName);
                System.out.print("Press Enter to continue back to Reports Menu...");
                scanner.nextLine();
                return;
            }
        }
    
        // If doctor was found by ID or name
        printPrescriptionReportForDoctor(doctor, scanner);
    }
    

    /**
     * Displays a report of all medications that have expired as of the current date.
     * For each expired medication, it shows the name, dose, quantity in stock, and expiration date.
     * If no expired medications are found, an appropriate message is displayed.
     * The method waits for user input before returning.
     *
     * @param scanner The Scanner object used to read user input to pause the report display.
     */
    private static void expiredMedicationReport(Scanner scanner) {
        boolean foundExpired = false;
        LocalDate today = LocalDate.now();

        // Header
        System.out.println("\n***** Expired Medication Report *****");

        for (Medication m : medications) {
            if (m.getExpiryDate().isBefore(today)) {
                foundExpired = true;
                System.out.println("\nMedication name: " + m.getName());
                System.out.println("Dose: " + m.getDose());
                System.out.println("Quantity in stock: " + m.getQuantity());
                System.out.println("Expiration date: " + m.getExpiryDate());
            }
        }

        if (!foundExpired) {
            System.out.println("No expired medications found.\n");
        }

        System.out.println("\nPress Enter to return. ");
        scanner.nextLine();
    }

    /**
     * Prints a report of all prescriptions issued by a specific doctor.
     * The report includes prescription details such as patient name,
     * medication, dose, quantity, issue date, and prescription expiry.
     * If no prescriptions are found for the doctor, an appropriate message is shown.
     * The method pauses after printing each prescription, waiting for user input to continue.
     *
     * @param doctor The Doctor object for whom the prescription report is generated.
     * @param scanner The Scanner object used to read user input to pause the report display.
     */
    private static void printPrescriptionReportForDoctor(Doctor doctor, Scanner scanner) {
        // Header
        System.out.println("\n******** Expired Medication *********");
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
            System.out.print("Press Enter to continue...");
            scanner.nextLine();
        }
    }

    /**
     * Generates and prints a detailed general report of the system.
     * The report includes information about all doctors, patients, and medications.
     * It displays doctors with their details and patient lists,
     * patients with their details, medications, and prescriptions,
     * and all medications available in the system.
     *
     * @param scanner The Scanner object used to wait for user input before returning to the menu.
     */
    private static void generateGeneralReport(Scanner scanner) {
        //Header
        System.out.println("\n********** General Report ***********");

        System.out.println("\nDoctors in system:");
        System.out.println("-------------------------------------");

        for (Doctor d : doctors) {
            System.out.println("Name: " + d.getFirstName() + " " + d.getLastName());
            System.out.println("Date of birth: " + d.getDateOfBirth());
            System.out.println("Phone number: " + d.getPhone());
            System.out.println("Gender: " + d.getGender());
            System.out.println("Specialization: " + d.getSpecialization());
            System.out.println("Patient list: ");
            for (Patient p : d.getPatients()) {
                System.out.println("   - " + p.getFirstName() + " " + p.getLastName() + ", " + p.getDateOfBirth());
            }
            System.out.println();
        }
        System.out.println("-------------------------------------\n");

        System.out.println("Patients in system: ");
        System.out.println("-------------------------------------");

        for (Patient p : patients) {
            System.out.println("Name: " + p.getFirstName() + " " + p.getLastName());
            System.out.println("Date of birth: " + p.getDateOfBirth());
            System.out.println("Phone number: " + p.getPhone());
            System.out.println("Gender: " + p.getGender());
            System.out.println("Medication list: ");
            for (Medication m : p.getMedications()) {
                System.out.println("   - " + m.getName() + ", " + m.getDose() + "mg");
            }
            System.out.println("Prescription list: ");
            for (Prescription pr : p.getPrescriptions()) {
                System.out.println("   - " + pr.getName() + ", " + pr.getDose() + "mg, prescribed by "
                        + pr.getDoctor().getFirstName() + " " + pr.getDoctor().getLastName());
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");

        System.out.println("Medications in system: ");
        System.out.println("-------------------------------------");

        for (Medication m : medications) {
            System.out.println("Name: " + m.getName());
            System.out.println("Dose: " + m.getDose());
            System.out.println("Quantity in stock: " + m.getQuantity());
            System.out.println("Expiry date: " + m.getExpiryDate());
            System.out.println();
        }
        System.out.println("-------------------------------------\n");

        System.out.println("\nPress enter to return to menu...");
        scanner.nextLine();
    }
    /**
     * Clears the console screen by printing special escape characters.
     * This method simulates clearing the terminal in most UNIX-based systems.
     * It may not work as expected on some operating systems like Windows.
     */
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
