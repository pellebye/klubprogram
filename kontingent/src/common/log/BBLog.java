package common.log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class BBLog extends Logger {

	final static String logStartSeparator = "==========================================================================================";
	final static String newLine = "\r\n";
	static Logger log = BBLog.getLogger(CommonLog.class.getName());
	final static String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Log message with default log level ('info') and default number of
	 * underlines (35).
	 * 
	 * @param message
	 *            The message.
	 */
	public static void logHeadings(String message) {
		logHeadings(message, "info", 35);
	}

	/**
	 * Log message with the number of underlines and default log level (info).
	 * 
	 * @param message
	 *            The message.
	 * @param numberOfUnderlines
	 *            The number of underlines ('=').
	 */
	public static void logHeadings(String message, Integer numberOfUnderlines) {
		logHeadings(message, "info", numberOfUnderlines);
	}

	/**
	 * Log message with log level and default number of underlines (35).
	 * 
	 * @param message
	 *            The message.
	 * @param logLevel
	 *            The logLevel ('info' or 'fine').
	 */
	public static void logHeadings(String message, String logLevel) {
		logHeadings(message, logLevel, 55);
	}

	/**
	 * Log message with log level and number of underlines.
	 * 
	 * @param message
	 *            The message.
	 * @param logLevel
	 *            The logLevel ('info' or 'fine').
	 * @param numberOfUnderlines
	 *            The number of underlines ('=').
	 */
	public static void logHeadings(String message, String logLevel, Integer numberOfUnderlines) {
		StringBuffer sb = new StringBuffer();
		sb.append(newLine);
		sb.append(newLine);
		// Right align the message.
		for (int i = 0; i <= numberOfUnderlines - message.length() - 1; i++) {
			sb.append(" ");
		}
		sb.append(message + newLine);
		for (int i = 0; i <= numberOfUnderlines - 1; i++) {
			sb.append("=");
		}
		if (logLevel.toLowerCase() == "info") {
			log.info(sb.toString());
		} else {
			log.fine(sb.toString());
		}
	}

	/**
	 * Log Headings for Tab selection.
	 *
	 * @param message
	 *            The message.
	 */
	public static void logTabHeadings(String message) {
		log.info("class//");
		StringBuffer sb = new StringBuffer();
		sb.append("message//");
		sb.append(newLine);
		sb.append(newLine);
		// Right align the message.
		message = "Tab: " + message;
		for (int i = 0; i <= 90 - message.length() - 1; i++) {
			sb.append(" ");
		}
		sb.append(message + newLine);
		for (int i = 0; i <= 90; i++) {
			sb.append("=");
		}
		log.info(sb.toString());
		sb.setLength(0);
	}

	public static void logClassHeadings2(String message) {
		log.info("class//");
		StringBuffer sb = new StringBuffer();
		sb.append("message//");
		sb.append(newLine);
		sb.append(newLine + "OLD ");
		// Right align the message.
		message = "Class: " + message;
		for (int i = 0; i <= 80 - message.length() - 1; i++) {
			sb.append(" ");
		}
		sb.append(message + newLine);
		for (int i = 0; i <= 80; i++) {
			sb.append("-");
		}
		log.info(sb.toString());
		sb.setLength(0);
	}

	/**
	 * Log the names from current color-section.
	 *
	 * @param names
	 *            The names to log.
	 */
	public static void logColorRowNames(String[] names) {
		for (int i = 0; i <= names.length - 1; i++) {
			log.fine("message//" + names[i]);
		}
	}

	/**
	 * Used by SaveTheScores
	 *
	 * @param obj
	 * @param lineHeading
	 */
	public static void logTextOrLabel(Object[] obj, String lineHeading) {
		StringBuilder sb = new StringBuilder();
		sb.append(lineHeading + ": ");
		CommonLog.logger.fine(sb.toString());
		sb.setLength(0);
	}

	/**
	 * LogStart
	 *
	 * Make 3 lines to indicate start of the log.
	 */
	public static void logStart() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		String dateNow = dateFormatter.format(cal.getTime());
		String calDay = "";

		switch (Calendar.DAY_OF_WEEK) {
		case 1:
			calDay = "Monday ";
			break;
		case 2:
			calDay = "Tuesday ";
			break;
		case 3:
			calDay = "Wednesday ";
			break;
		case 4:
			calDay = "Thursday ";
			break;
		case 5:
			calDay = "Friday ";
			break;
		case 6:
			calDay = "Saturday ";
			break;
		case 7:
			calDay = "Sunday ";
			break;
		default:
			;
		}

		log.info("message//" + logStartSeparator);
		log.info("message//" + "                               - " + calDay + dateNow + " LogStart");
		log.info("message//" + logStartSeparator);
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	protected BBLog(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}
}
