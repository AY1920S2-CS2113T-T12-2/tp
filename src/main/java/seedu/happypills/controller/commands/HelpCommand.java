package seedu.happypills.controller.commands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.VisitMap;
import seedu.happypills.view.ui.TextUi;

/**
 * Displays full help instructions for every command.
 */
public class HelpCommand implements Command {
    protected String command;

    public HelpCommand(String command) {
        this.command = command;
    }

    /**
     * Return help command instructions.
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     * @return message The command instructions.
     */
    public String execute(PatientMap patients, AppointmentMap appointments, VisitMap visits) {
        String message;
        if (command.equals("")) {
            message = TextUi.getHelp();
        } else if (command.equalsIgnoreCase("add")) {
            message = TextUi.printAddHelp();
        } else if (command.equalsIgnoreCase("list")) {
            message = TextUi.printListHelp();
        } else if (command.equalsIgnoreCase("get")) {
            message = TextUi.printGetHelp();
        } else if (command.equalsIgnoreCase("edit")) {
            message = TextUi.printEditHelp();
        } else if (command.equalsIgnoreCase("delete")) {
            message = TextUi.printDeleteHelp();
        } else if (command.equalsIgnoreCase("help")) {
            message = TextUi.printHelpHelp();
        } else if (command.equalsIgnoreCase("exit")) {
            message = TextUi.printExitHelp();
        } else {
            message = TextUi.printIncorrectCommand(command);
        }
        return message;
    }
}
