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
        // Person is abstract, so we create an anonymous subclass
        Person person = new Person("Justin", "Greenslade", LocalDate.of(1992, 2, 19), "7091234567", 'M') {};

        if (person.getFirstName().equals("Justin")) {
            System.out.println("Person getFirstName: PASS");
        } else {
            System.out.println("Person getFirstName: FAIL");
        }

        if (person.getLastName().equals("Greenslade")) {
            System.out.println("Person getLastName: PASS");
        } else {
            System.out.println("Person getLastName: FAIL");
        }

        person.setFirstName("Joseph");

        if (person.getFirstName().equals("Joseph")) {
            System.out.println("Person setFirstName: PASS");
        } else {
            System.out.println("Person setFirstName: FAIL");
        }

        person.setLastName("Gallant");

        if (person.getLastName().equals("Gallant")) {
            System.out.println("Person setLastName: PASS");
        } else {
            System.out.println("Person setLastName: FAIL");
        }

        if (person.getDateOfBirth().equals(LocalDate.of(1992, 2, 19))) {
            System.out.println("Person getDateOfBirth: PASS");
        } else {
            System.out.println("Person getDateOfBirth: FAIL");
        }

        if (person.getPhone().equals("7091234567")) {
            System.out.println("Person getPhone: PASS");
        } else {
            System.out.println("Person getPhone: FAIL");
        }

        person.setPhone("7097654321");

        if (person.getPhone().equals("7097654321")) {
            System.out.println("Person setPhone: PASS");
        } else {
            System.out.println("Person setPhone: FAIL");
        }

        if (person.getGender() == 'M') {
            System.out.println("Person getGender: PASS");
        } else {
            System.out.println("Person getGender: FAIL");
        }

        person.setGender('F');

        if (person.getGender() == 'F') {
            System.out.println("Person setGender: PASS");
        } else {
            System.out.println("Person setGender: FAIL");
        }

        // Test age roughly 
        int age = person.getAge();
        if (age > 0 && age < 150) {
            System.out.println("Person getAge: PASS");
        } else {
            System.out.println("Person getAge: FAIL");
        }

        // toString test
        String expectedString = "Gallant, Joseph";
        if (person.toString().equals(expectedString)) {
            System.out.println("Person toString: PASS");
        } else {
            System.out.println("Person toString: FAIL");
        }
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
        Patient patient = new Patient("Barba", "Brown", LocalDate.of(1975, 3, 15), "7093334444", 'F', new ArrayList<>(), new ArrayList<>());

        if (patient.getFirstName().equals("Barba")) {
            System.out.println("Patient getFirstName: PASS");
        } else {
            System.out.println("Patient getFirstName: FAIL");
        }

        Medication advil = new Medication("Advil", 200, 20, LocalDate.of(2025, 12, 31));
        patient.addMedication(advil);

        if (patient.getMedications().contains(advil)) {
            System.out.println("Patient addMedication: PASS");
        } else {
            System.out.println("Patient addMedication: FAIL");
        }

        patient.removeMedication(advil);

        if (!patient.getMedications().contains(advil)) {
            System.out.println("Patient removeMedication: PASS");
        } else {
            System.out.println("Patient removeMedication: FAIL");
        }

        Prescription prescription = new Prescription(
            new Doctor("Joseph", "Gallant", LocalDate.of(1990, 1, 1), "7095556666", 'M', "Oncology", new ArrayList<>()),
            patient,
            "Tylenol",
            500,
            30,
            LocalDate.of(2025, 1, 1),
            LocalDate.of(2025, 12, 31),
            LocalDate.of(2026, 1, 1));

        patient.addPrescription(prescription);

        if (patient.getPrescriptions().contains(prescription)) {
            System.out.println("Patient addPrescription: PASS");
        } else {
            System.out.println("Patient addPrescription: FAIL");
        }

        patient.removePrescription(prescription);

        if (!patient.getPrescriptions().contains(prescription)) {
            System.out.println("Patient removePrescription: PASS");
        } else {
            System.out.println("Patient removePrescription: FAIL");
        }
    }

    private static void testMedication() {
        System.out.println("Testing Medication Class:");
        Medication tylenol = new Medication("Tylenol", 500, 30, LocalDate.of(2025, 6, 30));

        if (tylenol.getName().equals("Tylenol")) {
            System.out.println("Medication getName: PASS");
        } else {
            System.out.println("Medication getName: FAIL");
        }

        tylenol.setName("Tylenol Extra");

        if (tylenol.getName().equals("Tylenol Extra")) {
            System.out.println("Medication setName: PASS");
        } else {
            System.out.println("Medication setName: FAIL");
        }

        if (tylenol.getDose() == 500) {
            System.out.println("Medication getDose: PASS");
        } else {
            System.out.println("Medication getDose: FAIL");
        }

        tylenol.setDose(650);

        if (tylenol.getDose() == 650) {
            System.out.println("Medication setDose: PASS");
        } else {
            System.out.println("Medication setDose: FAIL");
        }

        if (tylenol.getQuantity() == 30) {
            System.out.println("Medication getQuantity: PASS");
        } else {
            System.out.println("Medication getQuantity: FAIL");
        }

        tylenol.setQuantity(50);

        if (tylenol.getQuantity() == 50) {
            System.out.println("Medication setQuantity: PASS");
        } else {
            System.out.println("Medication setQuantity: FAIL");
        }

        tylenol.addQuantity(10);

        if (tylenol.getQuantity() == 60) {
            System.out.println("Medication addQuantity: PASS");
        } else {
            System.out.println("Medication addQuantity: FAIL");
        }

        if (tylenol.getExpiryDate().equals(LocalDate.of(2025, 6, 30))) {
            System.out.println("Medication getExpiryDate: PASS");
        } else {
            System.out.println("Medication getExpiryDate: FAIL");
        }

        tylenol.setExpiryDate(LocalDate.of(2026, 1, 1));

        if (tylenol.getExpiryDate().equals(LocalDate.of(2026, 1, 1))) {
            System.out.println("Medication setExpiryDate: PASS");
        } else {
            System.out.println("Medication setExpiryDate: FAIL");
        }

        if (tylenol.getId() > 0) {
            System.out.println("Medication getId: PASS");
        } else {
            System.out.println("Medication getId: FAIL");
        }
    }

    private static void testPrescription() {
        System.out.println("Testing Prescription Class:");
        Doctor doctor = new Doctor("Joseph", "Gallant", LocalDate.of(1990, 1, 1), "7095556666", 'M', "Oncology", new ArrayList<>());
        Patient patient = new Patient("Lisa", "Cuddy", LocalDate.of(1970, 4, 23), "7094443333", 'F', new ArrayList<>(), new ArrayList<>());

        // Test constructor with medication details
        Prescription prescription1 = new Prescription(doctor, patient, "Advil", 200, 20,
                LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31), LocalDate.of(2026, 1, 1));

        if (prescription1.getDoctor() == doctor) {
            System.out.println("Prescription getDoctor: PASS");
        } else {
            System.out.println("Prescription getDoctor: FAIL");
        }

        if (prescription1.getPatient() == patient) {
            System.out.println("Prescription getPatient: PASS");
        } else {
            System.out.println("Prescription getPatient: FAIL");
        }

        if (prescription1.getIssueDate().equals(LocalDate.of(2025, 1, 1))) {
            System.out.println("Prescription getIssueDate: PASS");
        } else {
            System.out.println("Prescription getIssueDate: FAIL");
        }

        if (prescription1.getPrescriptionExpiry().equals(LocalDate.of(2026, 1, 1))) {
            System.out.println("Prescription getPrescriptionExpiry: PASS");
        } else {
            System.out.println("Prescription getPrescriptionExpiry: FAIL");
        }

        if (prescription1.getName().equals("Advil")) {
            System.out.println("Prescription getName: PASS");
        } else {
            System.out.println("Prescription getName: FAIL");
        }

        if (prescription1.getPrescriptionId() > 0) {
            System.out.println("Prescription getPrescriptionId: PASS");
        } else {
            System.out.println("Prescription getPrescriptionId: FAIL");
        }

        // Test constructor with Medication object
        Medication med = new Medication("Tylenol", 500, 30, LocalDate.of(2025, 6, 30));
        Prescription prescription2 = new Prescription(doctor, patient, med,
                LocalDate.of(2025, 2, 1), LocalDate.of(2026, 2, 1));

        if (prescription2.getName().equals("Tylenol")) {
            System.out.println("Prescription constructor with Medication getName: PASS");
        } else {
            System.out.println("Prescription constructor with Medication getName: FAIL");
        }
    }
}
