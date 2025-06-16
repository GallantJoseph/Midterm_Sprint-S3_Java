import java.util.ArrayList;

public class Doctor extends Person {
    private ArrayList<Patient> patients;
    private String specialization;

    public Doctor(int id, String firstName, String lastName, int age, String phone, String specialization, ArrayList<Patient> patients) {
        super(id, firstName, lastName, age, phone);
        this.patients = new ArrayList<>(patients);
        this.specialization = specialization;
    }
}
