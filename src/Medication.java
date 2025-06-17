/*
    Description: Midterm Sprint - Semester 3 - Medication Class
    Authors:    Ashton Dennis
                Justin Greenslade
                Joseph Gallant
    Dates: June 16, 2025 -
 */

 import java.util.Date;

 public class Medication {
     // Private attributes
     private static int idCounter = 1;
     private int id;
     private String name;
     private double dose;
     private int quantity;
     private Date expiryDate;
 
     // Constructors
     // Parameterized
     public Medication (String name, double dose, int quantity, Date expiryDate) {
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
 
     public void addQuantity(int value) {
         this.quantity+=value;
     }
 
     // Getters and Setters
     public String getName() {
         return name;
     }
 
     public double getDose() {
         return dose;
     }
 
     public int getQuantity() {
         return quantity;
     }
 
     public Date getExpiryDate() {
         return expiryDate;
     }
 
     public void setExpiryDate(Date expiryDate) {
         this.expiryDate = expiryDate;
     }
 }