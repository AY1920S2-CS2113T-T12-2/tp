package seedu.happypills.controller.commands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.exception.HappyPillsException;

public interface Command {
    public String execute(PatientMap patients, AppointmentMap appointments) throws HappyPillsException;
}
