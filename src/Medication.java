/*
    Description: Midterm Sprint - Semester 3 - Medication Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

 import java.time.LocalDate;

 public class Medication {
     // Private attributes
     private static int idCounter = 1;
     private int id;
     private String name;
     private double dose;
     private int quantity;
     private LocalDate expiryDate;
 
     // Constructors
 
     /**
      * Creates a new medication with the given information.
      *
      * @param name        The name of the medication.
      * @param dose        The dosage amount (in mg).
      * @param quantity    The number of units in stock.
      * @param expiryDate  The date the medication expires.
      */
     public Medication(String name, double dose, int quantity, LocalDate expiryDate) {
         this.id = idCounter++;
         this.name = name;
         this.dose = dose;
         this.quantity = quantity;
         this.expiryDate = expiryDate;
     }
 
     /**
      * Creates a copy of an existing medication.
      *
      * @param medication The medication object to copy.
      */
     public Medication(Medication medication) {
         this.id = idCounter++;
         this.name = medication.name;
         this.dose = medication.dose;
         this.quantity = medication.quantity;
         this.expiryDate = medication.expiryDate;
     }
 
     // Getters and Setters
 
     /**
      * Gets the name of the medication.
      *
      * @return The medication name.
      */
     public String getName() {
         return name;
     }
 
     /**
      * Sets the name of the medication.
      *
      * @param name The new medication name.
      */
     public void setName(String name) {
         this.name = name;
     }
 
     /**
      * Gets the dosage of the medication.
      *
      * @return The dosage amount in mg.
      */
     public double getDose() {
         return dose;
     }
 
     /**
      * Sets the dosage of the medication.
      *
      * @param dose The new dosage amount in mg.
      */
     public void setDose(double dose) {
         this.dose = dose;
     }
 
     /**
      * Gets the quantity of medication in stock.
      *
      * @return The quantity in stock.
      */
     public int getQuantity() {
         return quantity;
     }
 
     /**
      * Sets the quantity of medication in stock.
      *
      * @param quantity The new quantity in stock.
      */
     public void setQuantity(int quantity) {
         this.quantity = quantity;
     }
 
     /**
      * Adds more medication to the current quantity.
      *
      * @param value The amount to add to the stock.
      */
     public void addQuantity(int value) {
         this.quantity += value;
     }
 
     /**
      * Gets the expiry date of the medication.
      *
      * @return The expiry date.
      */
     public LocalDate getExpiryDate() {
         return expiryDate;
     }
 
     /**
      * Sets the expiry date of the medication.
      *
      * @param expiryDate The new expiry date.
      */
     public void setExpiryDate(LocalDate expiryDate) {
         this.expiryDate = expiryDate;
     }
 
     /**
      * Gets the unique ID for this medication.
      *
      * @return The medication ID.
      */
     public int getId() {
         return this.id;
     }
 
     /**
      * Returns a string with the medication details.
      *
      * @return A formatted string with medication info.
      */
     @Override
     public String toString() {
         return "Medication ID: " + id +
             "\nName: " + name +
             "\nDosage: " + dose +
             "\nQuantity in stock: " + quantity +
             "\nExpiry Date: " + expiryDate;
     }
 }
 