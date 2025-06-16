import java.util.ArrayList;

public class Patient extends Person {
    private ArrayList<Medication> medications;
    private ArrayList<Prescription> prescriptions;

    public Patient(int id, String firstName, String lastName, int age, String phone, ArrayList<Medication> medications,
                   ArrayList<Prescription> prescriptions) {
        super(id, firstName, lastName, age, phone);

        this.medications = new ArrayList<>(medications);
        this.prescriptions = new ArrayList<>(prescriptions);
    }
}
