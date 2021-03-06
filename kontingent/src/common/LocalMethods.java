package common;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import common.log.BBexcLog;
import common.log.CommonLog;
import common.out.info.InfoDateConflict;

public class LocalMethods {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public LocalMethods() {
		CommonLog.logger.info("heading//");

	}

	/**
	 * Returns a string consisting of each part of an array of strings separated by
	 * semicolons.
	 *
	 * @param strings
	 *            The array of strings to concatenate.
	 */
	public static String setConcatenatedStringsSeparatedBySemicolons(String[] strings) {
		String concatenateString = "";
		for (int i = 0; i < strings.length; i++) {
			concatenateString += strings[i] + ";";
		}
		return concatenateString;
	}

	/**
	 * Returns 'Arial' font in size 'normal' plus 'addToSize'. 'addToSize' can be
	 * minus.
	 *
	 * @param addToSize
	 *            Font size in relation to normal. Can be minus, plus or zero.
	 */
	public static Font getFont(int addToSize) {
		return new Font(Display.getCurrent(), "Arial", 12 + addToSize, SWT.NORMAL);
	}

	/**
	 * This procedure returns a local date converted from a string formated as
	 * 'YYYYMMDD'.
	 * 
	 * @param string
	 *            Current string formated as 'YYYYMMDD'.
	 */
	public static LocalDate getLocalDate(String string) {

		LocalDate date = null;
		string = string.replace(" ", "");
		try {
			int y = Integer.parseInt(string.substring(0, 4));
			int m = Integer.parseInt(string.substring(4, 6));
			int d = Integer.parseInt(string.substring(6));
			date = LocalDate.of(y, m, d);
		} catch (Exception e) {
			new InfoDateConflict(string);
			e.printStackTrace();
		}

		return date;
	}

	public static int getWeekNumber(LocalDate date) {

		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		int weekNumber = date.get(woy);
		woy = null;

		return weekNumber;
	}

	public static String removeDoubleSpaces(String temp) {
		while (temp.contains("  ")) {
			temp = temp.replaceAll("  ", " ");
		}
		return temp;
	}

	/**
	 * Returns user directory without backslash.
	 */
	public static String getUserDirectory() {
		return System.getProperty("user.dir");
	}

	// Shell
	private static Shell shell;

	public static Shell getShell() {
		return shell;
	}

	public static void setShell(Shell shell) {
		LocalMethods.shell = shell;
	}

	/**
	 * Returns path and filename with forward slashes only, i.e. "/system\\watch/"
	 * is converted to "/system/watch/".
	 * 
	 * @param pathAndFilename
	 *            Path and file name to be converted.
	 */
	public static String getFilenameWithForwardSlashes(String pathAndFilename) {
		pathAndFilename = pathAndFilename.replace("\\", "/");

		return pathAndFilename;
	}

	/**
	 * <p>
	 * Show a message.
	 * </p>
	 *
	 * <p>
	 * The styles is put together of an icon and return button(s). Icons are:
	 * </p>
	 *
	 * <p>
	 * SWT.ICON_ERROR | SWT.ICON_INFORMATION | SWT.ICON_QUESTION | SWT.ICON_WARNING
	 * | SWT.ICON_WORKING
	 * </p>
	 *
	 * <p>
	 * Return buttons are:
	 * </p>
	 * <p>
	 * SWT.YES | SWT.NO | SWT.CANCEL | SWT.ABORT | SWT.RETRY | SWT.IGNORE;
	 * </p>
	 *
	 * <dd>MessageBox msgBox = new MessageBox(getShell(), SWT.YES | SWT.NO);</dd>
	 * <dd>msgBox.setMessage("How the heck do I use this?");</dd>
	 * <dd>int choice = msgBox.open();</dd>
	 * <dd>switch (choice) {</dd>
	 * <dd>
	 * <dd>case SWT.YES: break; //user said yes</dd></dd>
	 * <dd>
	 * <dd>case SWT.NO: break; //user said no</dd></dd>
	 * <dd></dd><br />
	 * <br />
	 *
	 * @param heading
	 *            The heading for the message window.
	 * @param text
	 *            The message to show.
	 * @param style
	 *            The style (e.g. SWT.YES | SWT.NO).
	 * @return An integer (e.g. SWT.OK) which corresponds to users choice.
	 */
	public static int methodShowMessage(String heading, String text, int style) {
		CommonLog.logger.info("class//");

		System.out.println("methodShowMessage = " + text);

		// Search google on "Demonstrates the MessageBox class"
		Shell shell = LocalMethods.getShell();
		shell.setActive();
		MessageBox messageBox = new MessageBox(shell, style);
		messageBox.setText(heading);
		messageBox.setMessage(text);

		int choice = messageBox.open();
		return choice;
	}

	// New line and tab
	private final static String newLine = "\r\n";

	public static String getNewline() {
		return newLine;
	}

	public static String getNewLineDouble() {
		return newLine + newLine;
	}

	// Test if file exists
	public static Boolean isFileExisting(String absolutePath) {
		File f = new File(absolutePath);
		return (f.exists() ? true : false);
	}

	// Possible Integer
	/**
	 * <p>
	 * Returns true if it is possible to convert a string type to an integer type.
	 * Else return false. Following is parsable: "12","-4556","0", "4558978".
	 * Following is not parsable: "test123456","#afafah","-klj15", null,"","#afaf",
	 * "45k"
	 * </p>
	 *
	 * @param isInteger
	 *            The string to test for possibility to convert to integer.
	 */
	public static boolean isParsableToInt(String isInteger) {
		CommonLog.logger.info("class//");

		try {
			Integer.parseInt(isInteger);
			return true;
		} catch (NumberFormatException nfe) {
			BBexcLog.log("Integer.parseInt(isInteger)", nfe);
			return false;
		}
	}

	/**
	 * Returns an array list of strings to a string array.
	 * 
	 * @param arraylist
	 *            Array list to be converted to a string array.
	 */
	public static String[] getArrayListToStringArray(ArrayList<String> arraylist) {
		return arraylist.toArray(new String[arraylist.size()]);
	}

	/**
	 * Returns index (zero based) of the first digit in input line.
	 *
	 * <p>
	 * If input line contains no digits the returned value will be one more than the
	 * length of the actual line.
	 * </p>
	 *
	 * @param line
	 *            Current line.
	 */
	public static int getFirstDigit(String line) {
		int counter = 0;
		for (counter = 0; counter < line.length(); counter++) {
			int charCode = line.charAt(counter);
			if (charCode >= 48 && charCode <= 57)
				break;
		}
		return counter;
	}

	/**
	 * Returns trimmed string array. Each string in the array is trimmed.
	 *
	 * @param untrimmed
	 *            The array to be trimmed.
	 */
	public static String[] trimStringArray(String[] untrimmed) {

		String[] trimmed = new String[untrimmed.length];
		for (int i = 0; i < untrimmed.length; i++)
			trimmed[i] = untrimmed[i].trim();

		return trimmed;
	}

	public static int getNumberOfMatches(String s, String stringToMatch) {

		int result = 0;
		int index = -1;

		do {
			index = s.indexOf(stringToMatch, index + 1);
			if (index >= 0)
				result++;
		} while (index > -1);

		return result;
	}
}
