package common.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * <p>
 * MyCustomFormatter formats the LogRecord as follows:
 * </p>
 * <p>
 * date level localized message with parameters
 * </p>
 *
 * Use
 * <ol>
 * <li>"class//" for class- and method information.</li>
 * <li>"event//" for events, i.e. update and notify.</li>
 * <li>"message//" for only message information.</li>
 * <li>'heading//' Logs a class heading.</li>
 * <li>"time//" for time information.</li>
 * <li>"mark//" for marking with <- MARK -> <- MARK ->.</li>
 * </ol>
 */
public class LogFormatter extends Formatter {

	private final String newLine = "\r\n";
	private final String markLine = "====================================================================================================";
	private final String spaces = "                              ";

	private static int lineCounter = 0;

	private String logClassName = "";
	private String logMethodeName = "";
	@SuppressWarnings("unused")
	private String logName = "";
	private String logMessage = "";
	private String logTime = null;
	@SuppressWarnings("unused")
	private Date logDate = null;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public LogFormatter() {
		super();
	}

	private String getDateTime() {
		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss => ");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * <p>
	 * If number of lines are of modus 25 then returns the number of lines as a
	 * string formatted with spaces placed ahead of the number to make the
	 * numbers more readable. Else returns only spaces
	 * </p>
	 *
	 * @param numberOfLines
	 *            The number of lines as integer.
	 * @param formatTo
	 *            The total number of characters in the formatted string.
	 */
	private String getFormatedNumberOfLines(int numberOfLines, int formatTo) {

		// String spaces = " ";
		String string = spaces.substring(0, formatTo);

		if (lineCounter % 25 == 0) {
			string = String.valueOf(numberOfLines);

			for (int i = formatTo - 1; i > 0; i--) {
				if (numberOfLines < Math.pow(10, i))
					string = " " + string;
				else
					break;
			}
		}

		return string;
	}

	@Override
	public String format(LogRecord logRecord) {

		// Get the name of the class
		logClassName = logRecord.getSourceClassName();
		// Get the name of the method
		logMethodeName = logRecord.getSourceMethodName();
		// if (logMethodeName.contains("logUpdate")) {
		// // To avoid log of logUpdate itself
		// int i=8;
		// return "";
		// }

		logName = logRecord.getLevel().getName();
		// The date.
		logDate = new Date(logRecord.getMillis());
		// The time.
		Calendar cal = Calendar.getInstance();
		String min = String.valueOf(cal.get(Calendar.MINUTE));
		if (min.length() < 2)
			min = " " + min;
		String sec = String.valueOf(cal.get(Calendar.SECOND));
		if (sec.length() < 2)
			sec = "0" + sec;

		logTime = "(" + min + ":" + sec + ")";
		while (logTime.length() < 5)
			logTime += " ";
		logTime = " " + logTime;

		lineCounter++;

		logMessage = formatMessage(logRecord);

		return createMessage();
	}

	/**
	 * <p>
	 * Returns the the formatted log message dependent on the command in the
	 * beginning of the log message.
	 * </p>
	 *
	 * <p>
	 * The original log message starts with a command followed by two slashes:
	 * </p>
	 * <dd>* 'class//' Logs the time, the name of the class and the name of the
	 * method.</dd>
	 * <dd>* 'event//' The event command is further split up in 'update' and
	 * 'notify'.</dd>
	 * <dd>* 'message//' Logs the following message.</dd>
	 * <dd>* 'heading//' Logs a class heading.</dd>
	 * <dd>* 'time//' Logs the time.</dd>
	 * <dd>* 'mark//' Logs a distinct marking line which can be quick
	 * found.</dd>
	 */
	private String createMessage() {
		// Create a StringBuffer to contain the formatted record
		StringBuffer sb = new StringBuffer();

		String[] splitLogmessage = logMessage.split("//");

		if (splitLogmessage.length > 0) {

			// class
			if (splitLogmessage[0].compareTo("class") == 0)
				sb.append(getFormatedNumberOfLines(lineCounter, 6) + "           " + logClassName + " - "
						+ logMethodeName);
			// event
			else if (splitLogmessage[0].compareTo("event") == 0)
				sb.append(logTime + newLine + "   " + getEventMessage(splitLogmessage[1]));
			// message
			else if (splitLogmessage[0].compareTo("message") == 0)
				sb.append("              " + splitLogmessage[1]);
			// heading
			else if (splitLogmessage[0].compareTo("heading") == 0)
				splitHeading(sb, splitLogmessage);
			// tab heading
			else if (splitLogmessage[0].compareTo("tabheading") == 0)
				splitTabheading(sb, splitLogmessage);
			// time
			else if (splitLogmessage[0].compareTo("time") == 0)
				sb.append(" *** " + getDateTime() + logClassName + " - " + logMethodeName);
			// mark
			else if (splitLogmessage[0].compareTo("mark") == 0)
				sb.append(newLine + newLine + logClassName + " " + logMethodeName + newLine + markLine + newLine
						+ markLine + newLine + newLine + spaces + "<- M A R K ->    <- M A R K ->    <- M A R K ->"
						+ newLine + newLine + markLine + newLine + markLine + newLine);
			// error
			else
				sb.append(getErrorMessage());

			sb.append(newLine);
		} else
			sb.append(getErrorMessage());
		return sb.toString();
	}

	/**
	 * @param sb
	 * @param splitLogmessage
	 */
	private void splitTabheading(StringBuffer sb, String[] splitLogmessage) {
		sb.append(newLine + newLine);

		String message = "Tab: ";
		if (splitLogmessage.length > 1)
			message = message + splitLogmessage[1];
		for (int i = 0; i <= 80 - message.length(); i++) {
			sb.append(" ");
		}
		sb.append(message + newLine);
		for (int i = 0; i <= 80; i++) {
			sb.append("=");
		}
	}

	/**
	 * @param sb
	 * @param splitLogmessage
	 */
	private void splitHeading(StringBuffer sb, String[] splitLogmessage) {
		sb.append(newLine + newLine);

		String message = "Class constructor: " + logClassName;
		if (splitLogmessage.length > 1)
			message = message + " - " + splitLogmessage[1];
		for (int i = 0; i <= 80 - message.length(); i++) {
			sb.append(" ");
		}
		sb.append(message + newLine);
		for (int i = 0; i <= 80; i++) {
			sb.append("-");
		}
	}

	/**
	 * Returns an error message to the log when none of the possible log
	 * messages was found.
	 */
	private String getErrorMessage() {
		StringBuffer localSb = new StringBuffer();

		localSb.append(newLine + newLine + " E R R O R ----- E R R O R ----- E R R O R ----- E R R O R ----- E R R O R"
				+ newLine);
		localSb.append(" ----- missing 'class', 'event', 'message', 'heading', 'tabheading', 'mark' or 'time' ----- "
				+ newLine);
		localSb.append("Error-logMessage = " + logClassName + " - " + logMethodeName + " - " + logMessage + newLine);
		localSb.append(newLine + " E R R O R ----- E R R O R ----- E R R O R ----- E R R O R ----- E R R O R" + newLine
				+ newLine);

		return localSb.toString();
	}

	/**
	 * Returns a string dependent on the event message type (update or notify).
	 *
	 * @param splitLogmessageOne
	 *            The message string with 'event//' excluded.
	 */
	private String getEventMessage(String splitLogmessageOne) {
		StringBuffer localSb = new StringBuffer();

		String[] splitString;
		splitString = splitLogmessageOne.split(";");
		localSb.setLength(0);
		if (splitString[0].compareTo("update") == 0) {
			localSb.append(newLine + "< - U P D A T E - > " + splitString[splitString.length - 3] + " - "
					+ splitString[splitString.length - 2]);
		} else if (splitString[0].compareTo("notify") == 0) {
			localSb.append(newLine + "< - N O T I F Y - > by " + splitString[4] + " - " + splitString[5]);
		}
		localSb.append(newLine + newLine);
		localSb.append("1-3 ");
		for (int i = 1; i < 4; i++)
			localSb.append(splitString[i] + " - ");
		localSb.append(newLine + "4-> ");
		for (int i = 4; i < splitString.length; i++)
			localSb.append(splitString[i] + " - ");
		localSb.append(newLine);

		return localSb.toString();
	}
}
