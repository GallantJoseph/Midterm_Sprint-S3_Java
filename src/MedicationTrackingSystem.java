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
            }
        }
        System.out.println("\nReturning to the main menu...\n");
    }


    public static void addDoctor(Scanner scanner) {
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
        }
    }

    public static void searchDoctor(Scanner scanner) {
    
        // Ask for ID
        int searchId;
        while (true) {
            System.out.print("Enter Doctor ID: ");
            String idInput = scanner.nextLine().trim();
            try {
                searchId = Integer.parseInt(idInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter numeric digits only.");
            }
        }
    
        // Ask for first name
        String searchFirstName;
        while (true) {
            System.out.print("Enter Doctor First Name: ");
            searchFirstName = scanner.nextLine().trim();
            if (searchFirstName.matches("^[A-Za-z]+$")) {
                break;
            } else {
                System.out.println("Invalid first name. Use letters only.");
            }
        }
    
        // Ask for last name
        String searchLastName;
        while (true) {
            System.out.print("Enter Doctor Last Name: ");
            searchLastName = scanner.nextLine().trim();
            if (searchLastName.matches("^[A-Za-z]+$")) {
                break;
            } else {
                System.out.println("Invalid last name. Use letters only.");
            }
        }
    
        boolean doctorFound = false;
    
        // Loop through the list to find a matching doctor
        for (Doctor doctor : doctors) {
    
            if (doctor.getId() == searchId &&
                doctor.getFirstName().equalsIgnoreCase(searchFirstName) &&
                doctor.getLastName().equalsIgnoreCase(searchLastName)) {
        
                // If match doctor found print out the doctors details
                System.out.println("\nDoctor found:");
                System.out.println("ID: " + doctor.getId());
                System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                System.out.println("Date of Birth: " + doctor.getDateOfBirth());
                System.out.println("Phone: " + doctor.getPhone());
                System.out.println("Gender: " + doctor.getGender());
                System.out.println("Specialization: " + doctor.getSpecialization());
        
                // Set found to true and stop searching
                doctorFound = true;
    

            }
        }
        
        // If doctor was not found shows a message
        if (!doctorFound) {
            System.out.println("No doctor found with that ID and full name.");
        }
                        // Pause to let user read result
                        System.out.print("To return press enter: ");
                        scanner.nextLine();
    }
    
    public static void editDoctor(Scanner scanner) {
    
        // Ask for Doctor ID and validate it's numeric
        int editId;
        while (true) {
            System.out.print("Enter Doctor ID to edit: ");
            String input = scanner.nextLine().trim();
            try {
                editId = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter numeric digits only.");
            }
        }
    
        // Ask for first name
        String firstName;
        while (true) {
            System.out.print("Enter Doctor First Name: ");
            firstName = scanner.nextLine().trim();
            if (firstName.matches("^[A-Za-z]+$")) {
                break;
            } else {
                System.out.println("Invalid first name. Use letters only.");
            }
        }
    
        // Ask for last name
        String lastName;
        while (true) {
            System.out.print("Enter Doctor Last Name: ");
            lastName = scanner.nextLine().trim();
            if (lastName.matches("^[A-Za-z]+$")) {
                break;
            } else {
                System.out.println("Invalid last name. Use letters only.");
            }
        }
    
        for (Doctor d : doctors) {
    
            if (d.getId() == editId &&
                    d.getFirstName().equalsIgnoreCase(firstName) &&
                    d.getLastName().equalsIgnoreCase(lastName)) {
    
                System.out.println("Editing Doctor: " + d.getFirstName() + " " + d.getLastName());
    
                // Ask for new phone number
                System.out.print("New Phone (press Enter to keep current): ");
                String phone = scanner.nextLine().trim();
                if (!phone.isEmpty()) {
                    if (phone.matches("^\\d{10}$")) {
                        d.setPhone(phone); // Only updates if valid input entered
                    } else {
                        System.out.println("Invalid phone number. Must be 10 digits. Keeping existing phone.");
                    }
                }
    
                // Ask for new specialization
                System.out.print("New Specialization (press Enter to keep current): ");
                String spec = scanner.nextLine();
                if (!spec.isEmpty()) {
                    d.setSpecialization(spec);
                }
    
                System.out.println("Doctor info for " + firstName + " " + lastName + " updated.");
                // Pause to let user read result
                System.out.print("To return press enter: ");
                scanner.nextLine();
                return;
            }
        }
    
        // If not found
        System.out.println("Doctor not found with that ID and name.");
        System.out.print("To return press enter: ");
        scanner.nextLine();
    }
    

// removes a doctor from the list using their ID and full name
public static void deleteDoctor(Scanner scanner) {

    // Ask for Doctor ID and validate it's numeric
    int deleteId;
    while (true) {
        System.out.print("Enter Doctor ID to delete: ");
        String input = scanner.nextLine().trim();
        try {
            deleteId = Integer.parseInt(input);
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter numeric digits only.");
        }
    }

    // Ask for first name and validate
    String firstName;
    while (true) {
        System.out.print("Enter Doctor First Name: ");
        firstName = scanner.nextLine().trim();
        if (firstName.matches("^[A-Za-z]+$")) {
            break;
        } else {
            System.out.println("Invalid first name. Use letters only.");
        }
    }

    // Ask for last name and validate
    String lastName;
    while (true) {
        System.out.print("Enter Doctor Last Name: ");
        lastName = scanner.nextLine().trim();
        if (lastName.matches("^[A-Za-z]+$")) {
            break;
        } else {
            System.out.println("Invalid last name. Use letters only.");
        }
    }

    // Loop through the list of doctors to find a match
    for (Doctor d : doctors) {

        if (d.getId() == deleteId &&
            d.getFirstName().equalsIgnoreCase(firstName) &&
            d.getLastName().equalsIgnoreCase(lastName)) {

            // Remove the matching doctor from the list
            doctors.remove(d);
            System.out.println("Doctor " + firstName + " " + lastName+ " deleted successfully.");
                // Pause to let user read result
                System.out.print("To return press enter: ");
                scanner.nextLine();
                return;
        }
    }

    // If the loop completes without finding a match
    System.out.println("Doctor not found with that ID and name.");
    System.out.print("To return press enter: ");
    scanner.nextLine();
}



    // Accept a prescription
    private static void accPresc() {

    }

// created some data to preload in until additional data can be created.
    public static void preloadData() {
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
        Medication med1 = new Medication("Aspirin", 500, 30, LocalDate.of(2026, 12, 31));
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


    //TODO We can add extra cases based on what reports me want
    while (!exit) {
        System.out.println("\n***** Reports Menu *****");
        System.out.println("\nPlease make a selection:\n");
        System.out.println("1. Print all prescriptions for a specific doctor");
        System.out.println("2. Back to main menu");
        System.out.println("*************************************************************\n");


        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                generateReport(scanner);
                    break;
                case 2:
                    System.out.println("\n***** Back to the main menu *****");
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter a value between 1-2.");
            }
        } else {
            System.out.println("Invalid input. Must be a numeric value.");
            scanner.nextLine(); // consume invalid input
        }
    }

    System.out.println("\nReturning to the main menu...\n");
}

    private static void generateReport(Scanner scanner) {
        scanner.nextLine();
 // Ask for Doctor ID
 System.out.print("Enter Doctor ID to generate report: ");
 String input = scanner.nextLine().trim();
 int doctorId;

 try {
     doctorId = Integer.parseInt(input);
 } catch (NumberFormatException e) {
     System.out.println("Invalid Doctor ID. Please enter a numeric value.");
     return;
 }

 // Find the doctor by ID
 Doctor doctor = null;
 for (Doctor d : doctors) {
     if (d.getId() == doctorId) {
         doctor = d;
         break;
     }
 }

 if (doctor == null) {
     System.out.println("Doctor not found with ID: " + doctorId);
     return;
 }

 // Print report header
 System.out.println("\n--- Prescription Report for Dr. " 
     + doctor.getFirstName() + " " + doctor.getLastName() + " ---");

 boolean foundAny = false;

 // Loop through prescriptions to find ones prescribed by this doctor
 for (Prescription p : prescriptions) {
     if (p.getDoctor().getId() == doctorId) {
         foundAny = true;
         System.out.println("Prescription ID: " + p.getId());
         System.out.println("Patient: " + p.getPatient().getFirstName() + " " + p.getPatient().getLastName());
         System.out.println("Medication: " + p.getName());
         System.out.println("Dose: " + p.getDose());
         System.out.println("Quantity: " + p.getQuantity());
         System.out.println("Issue Date: " + p.getIssueDate());
         System.out.println("Prescription Expiry: " + p.getPrescriptionExpiry());
         System.out.println("-----------------------------------");
                         // Pause to let user read result
                         System.out.print("To return press enter: ");
                         scanner.nextLine();
     }
 }

 if (!foundAny) {
     System.out.println("No prescriptions found for this doctor.");
 }
    }
}
