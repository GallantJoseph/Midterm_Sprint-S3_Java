import java.time.LocalDate;
import java.util.ArrayList;

public class Tests {

    public static void main(String[] args) {
        System.out.println("--------------------------------------------------");
        testPerson();
        System.out.println("--------------------------------------------------");
        testDoctor();
        System.out.println("--------------------------------------------------");
        testPatient();
        System.out.println("--------------------------------------------------");
        testMedication();
        System.out.println("--------------------------------------------------");
        testPrescription();
        System.out.println("--------------------------------------------------");
    }

    private static void testPerson() {
        System.out.println("Testing Person Class:");
    }

    private static void testDoctor() {
        System.out.println("Testing Doctor Class:");
        Doctor doctor = new Doctor("Ashton", "Dennis", LocalDate.of(2006, 3, 10), "7091112222", 'M', "Cardiology", new ArrayList<>());

        if (doctor.getFirstName().equals("Ashton")) {
            System.out.println("Doctor getFirstName: PASS");
        } else {
            System.out.println("Doctor getFirstName: FAIL");
        }

        if (doctor.getSpecialization().equals("Cardiology")) {
            System.out.println("Doctor getSpecialization: PASS");
        } else {
            System.out.println("Doctor getSpecialization: FAIL");
        }

        doctor.setSpecialization("Neurology");

        if (doctor.getSpecialization().equals("Neurology")) {
            System.out.println("Doctor setSpecialization: PASS");
        } else {
            System.out.println("Doctor setSpecialization: FAIL");
        }

        Patient patient = new Patient("Barba", "Brown", LocalDate.of(1975, 3, 15), "7093334444", 'F', new ArrayList<>(), new ArrayList<>());
        doctor.addPatient(patient);

        if (doctor.getPatients().contains(patient)) {
            System.out.println("Doctor addPatient: PASS");
        } else {
            System.out.println("Doctor addPatient: FAIL");
        }

        doctor.removePatient(patient);

        if (!doctor.getPatients().contains(patient)) {
            System.out.println("Doctor removePatient: PASS");
        } else {
            System.out.println("Doctor removePatient: FAIL");
        }
    }

    private static void testPatient() {
        System.out.println("Testing Patient Class:");
    }

    private static void testMedication() {
        System.out.println("Testing Medication Class:");
    }

    private static void testPrescription() {
        System.out.println("Testing Prescription Class:");
    }
}
