import java.util.Date;

public class Medication {
    private int id;
    private String name;
    private double dose;
    private int quantity;
    private Date expiryDate;

    public Medication (int id, String name, double dose, int quantity, Date expiryDate) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }
}
