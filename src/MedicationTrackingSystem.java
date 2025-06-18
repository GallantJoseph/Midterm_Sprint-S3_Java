/*
    Description: Midterm Sprint - Semester 3 - MedicationTrackingSystem Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class MedicationTrackingSystem {
    // Private attributes
    private static ArrayList<Patient> patients;
    private static ArrayList<Doctor> doctors;
    private static ArrayList<Medication> medications;

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
        System.out.println("\nPlease make a selection:\n");
        System.out.println("1. Add a medication");
        System.out.println("2. Search for a medication");
        System.out.println("3. Edit a medication");
        System.out.println("4. Delete a medication");
        System.out.println("5. Restock a medication");
        System.out.println("6. Back to main menu");

        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Medication name:");
                    String name = scanner.nextLine();

                    System.out.println("Dosage:");
                    double dose = scanner.nextDouble();

                    System.out.println("Quantity in stock:");
                    int quantity = scanner.nextInt();

                    System.out.println("Expiry date:");
                    Date expiry = null; // Temporary until dates are sorted

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
                            // to be implemented when dates are sorted
                            
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

    private static void manageDoctor(Scanner scanner) {

    }

    private static void accPresc() {

    }

    private static void generateReport(Scanner scanner) {

    }
}
