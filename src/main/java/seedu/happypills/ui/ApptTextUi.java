package seedu.happypills.ui;

import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;

import java.util.ArrayList;
import java.util.Map;

public class ApptTextUi extends TextUi {
    /**
     * Shows list of appointments in the program.
     * @param appointments The list of appointments.
     * @return message to be displayed to the user.
     */
    public static String getAppointmentList(AppointmentMap appointments) {
        String message = "    Here is your list of appointments:\n"
                + "    ID | date       | time     | NRIC\n";
        for (Map.Entry appointment : appointments.entrySet()) {
            Appointment a = (Appointment)appointment.getValue();
            String nric = a.getNric();
            String id = a.getAppointmentId();
            String date = a.getDate();
            String time = a.getTime();
            message += "    " + id + "  | " + date + " | " + time + " | " + nric + "\n";
        }
        message += DIVIDER;
        return message;
    }

    /**
     * Displays empty list message when there are no appointments in the list.
     * @return a message to be displayed to user
     */
    public static String getEmptyAppointmentList() {
        String emptyListMessage = "    There are no appointments in the list.\n" + DIVIDER;
        return emptyListMessage;
    }

    /**
     * Generate a success message upon finding the correct patient.
     * @param patient the patient which the program have to find the appointment for.
     * @return Appointment details if any, and a notification message otherwise.
     */
    public static String getAppointmentSuccessMessage(Patient patient) {
        String returnMessage = "    Here are the patient's appointments:\n"
                + "     | ID | NRIC   | Reason | Date      | Time     |\n";
        String content = "";
        ArrayList<Appointment> tempList = patient.getAppointments();
        for (Appointment appointment : tempList) {
            String id = appointment.getAppointmentId();
            String nric = appointment.getNric();
            String reason = appointment.getReason();
            String date = appointment.getDate();
            String time = appointment.getTime();
            content += "     | " + id + " | " + nric + " | " + reason + " | " + date + " | " + time + " | \n";
        }
        if (content.isEmpty()) {
            returnMessage = "    There are no appointments in the list.\n";
        } else {
            returnMessage += content + DIVIDER;
        }

        return returnMessage;
    }

    /**
     * Display updated patient's appointment.
     *
     * @param appointment The appointment that was updated.
     * @return The patient's updated appointment.
     */
    public static String editAppointmentSuccessMessage(Appointment appointment) {
        String message = "    Patient appointment have been updated as follows:\n"
                + appointment + DIVIDER;
        return message;
    }

    /**
     * Display updated patient's appointment.
     *
     * @param appointment The appointment that was updated.
     * @return The patient's updated appointment.
     */
    public static String doneAppointmentSuccessMessage(Appointment appointment) {
        String message = "    The following appointment has been marked done:\n"
                + appointment + DIVIDER;
        return message;
    }
}