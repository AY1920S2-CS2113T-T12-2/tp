package seedu.happypills.commands;

import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

public class EditCommand extends Command {
    protected String nric;
    protected String editField;
    protected String newContent;

    /**
     * Constructor for EditCommand Class.
     * It creates a new EditCommand Object with the information provided.
     *
     * @param nric Contains the nric of the patient that is to be retrieved.
     * @param newContent Contains the string that the attribute is to be updated to.
     */
    public EditCommand(String nric, String newContent) {
        this.nric = nric;
        this.newContent = newContent;
    }

    /**
     * Retrieve the patient from the NRIC of the Edit command.
     *
     * @param patients Contains the list of patients to be searched.
     */
    private Patient findPatient(PatientList patients) {
        for (Patient patient : patients) {
            if (patient.getNric().equalsIgnoreCase(nric)) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Edit the phone number of the patient.
     *
     * @param patient The patient whose phone number is to be edited.
     * @param content The patient's new phone number.
     */
    private void editPhone(Patient patient, String content) {
        patient.setPhoneNumber(Integer.parseInt(content));
        TextUi.printPatient(patient);
    }

    /**
     * Edit the allergies of the patient.
     *
     * @param patient The patient whose allergies is to be edited.
     * @param content The patient's updated allergies.
     */
    private void editAllergies(Patient patient, String content) {
        patient.setAllergies(content);
        TextUi.printPatient(patient);
    }

    /**
     * Edit the remarks of the patient.
     *
     * @param patient The patient whose remarks is to be edited.
     * @param content The patient's new remarks.
     */
    private void editRemarks(Patient patient, String content) {
        patient.setRemarks(content);
        TextUi.printPatient(patient);
    }

    /**
     * Adds a new task to the list with the information provided by calling.
     * {} (or) {}
     * (or) {} as require
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     * @throws HappyPillsException Throws an exception if the edit field is not valid.
     */
    @Override
    public String execute(PatientList patients) throws HappyPillsException {
        if (newContent.length() < 2) {
            throw new HappyPillsException("    Content is invalid. Please try again");
        }
        assert newContent.length() >= 2 : "Length of content has to be more than 2 characters.";
        String field = newContent.substring(0,2);
        String content = newContent.substring(2);
        System.out.println(content);
        Patient editPatient = findPatient(patients);
        if (editPatient == null) {
            throw new HappyPillsException("    Patient not found. Please try again.");
        }
        if (field.equals("/p")) {
            editPhone(editPatient, content);
        } else if (field.equals("/r")) {
            editRemarks(editPatient, content);
        } else if (field.equals("/a")) {
            editAllergies(editPatient, content);
        } else {
            throw new HappyPillsException("    Please try again. To edit, use the following commands: \n"
                                    + "    edit [NRIC] /p[PHONE_NUMBER] to edit patient's phone number,\n"
                                    + "    edit [NRIC] /p[ALLERGIES] to edit patient's allergies,\n"
                                    + "    edit [NRIC] /p[REMARKS] to edit patient's remarks");
        }
        return null;
    }
}
