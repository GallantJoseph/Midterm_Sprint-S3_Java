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
     // Parameterized
     public Medication (String name, double dose, int quantity, LocalDate expiryDate) {
         this.id = idCounter++;
         this.name = name;
         this.dose = dose;
         this.quantity = quantity;
         this.expiryDate = expiryDate;
     }
 
     // Copy
     public Medication(Medication medication) {
         this.id = idCounter++;
         this.name = medication.name;
         this.dose = medication.dose;
         this.quantity = medication.quantity;
         this.expiryDate = medication.expiryDate;
     }
 
     // Getters and Setters
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }
 
     public double getDose() {
         return dose;
     }

     public void setDose(double dose) {
         this.dose = dose;
     }
 
     public int getQuantity() {
         return quantity;
     }

     public void setQuantity(int quantity) {
         this.quantity = quantity;
     }

     public void addQuantity(int value) {
         this.quantity+=value;
     }
 
     public LocalDate getExpiryDate() {
         return expiryDate;
     }

     public void setExpiryDate(LocalDate expiryDate) {
         this.expiryDate = expiryDate;
     }

     public int getId() {
         return this.id;
     }
     
     @Override
public String toString() {
    return "Medication ID: " + id +
           "\nName: " + name +
           "\nDosage: " + dose +
           "\nQuantity in stock: " + quantity +
           "\nExpiry Date: " + expiryDate;
}

 }