package seedu.happypills.commands.patient_commands;

import seedu.happypills.HappyPills;
import seedu.happypills.data.AppointmentMap;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.TextUi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddPatientPatientCommand extends PatientCommand {
    protected String name;
    protected String nric;
    protected int phoneNumber;
    protected String dateOfBirth;
    protected String bloodType;
    protected String allergies;
    protected String remarks;
    Logger logger = Logger.getLogger(HappyPills.class.getName());
    Level logLevel = Level.INFO;

    /**
     * Constructor for AddCommand Class.
     * It creates a new AddCommand Object with the information provided.
     *
     * @param name        Contains the name of the patient.
     * @param nric        Contains the nric of the patient.
     * @param phoneNumber Contains the phone number of the patient.
     * @param dateOfBirth Contains the date of birth of the patient.
     * @param bloodType   Contains the blood type of the patient.
     * @param allergies   Contains any allergies the patient has.
     * @param remarks     Contains any remarks for the patient.
     */
    public AddPatientPatientCommand(String name, String nric, int phoneNumber, String dateOfBirth,
                                    String bloodType, String allergies, String remarks) {
        this.name = name;
        this.nric = nric;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.remarks = remarks;
        logger.log(logLevel, "patient is add");
    }

    /**
     * Adds a new task to the list with the information provided by calling.
     * {} (or) {}
     * (or) {} as require
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     */
    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) throws HappyPillsException {
        assert !patients.containsKey(nric) : "New nric can be added";
        Patient tempPatient = new Patient(name, nric, phoneNumber, dateOfBirth, bloodType, allergies, remarks);
        if (tempPatient.getNric() == null) {
            return "    Patient not added.\n"
                    + TextUi.DIVIDER;
        }
        patients.add(tempPatient);
        assert patients.containsKey(nric) : "nric added successfully";
        try {
            Storage.addSingleItemToFile(Storage.PATIENT_FILEPATH, tempPatient.toSave());
        } catch (IOException e) {
            logger.info("Patient not added to file.");
        }
        String message = "";
        message = TextUi.getPatient(patients.get(nric));
        logger.log(logLevel, "end of addCommand");
        return message;
    }
}
