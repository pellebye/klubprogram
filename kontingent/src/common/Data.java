package common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

import _start.kalender.MinusWeek;
import _start.kalender.PlayTime;
import _start.kontingent.Person;
import common.log.CommonLog;

public class Data {

	/**
	 * Indicate if program is started for the first time.
	 */
	private static boolean firstTimeStart = false;

	public static boolean isFirstTimeStart() {
		return firstTimeStart;
	}

	public static void setFirstTimeStart(boolean firstTimeStart) {
		Data.firstTimeStart = firstTimeStart;
	}

	/**
	 * Indicates when the calendar starts.
	 */
	private static LocalDate kalenderStart = null;

	/**
	 * Returns the local date when calendar starts.
	 */
	public static LocalDate getKalenderStart() {
		return kalenderStart;
	}

	/**
	 * Sets when the calendar starts.
	 * 
	 * @param kalenderStart
	 *            A local date where the calendar starts.
	 */
	public static void setKalenderStart(LocalDate kalenderStart) {
		Data.kalenderStart = kalenderStart;
	}

	/**
	 * Indicates when the calendar ends.
	 */
	private static LocalDate kalenderSlut = null;

	/**
	 * Returns the local date when calendar ends.
	 */
	public static LocalDate getKalenderSlut() {
		return kalenderSlut;
	}

	/**
	 * Sets when the calendar ends.
	 * 
	 * @param kalenderSlut
	 *            A local date where the calendar ends.
	 */
	public static void setKalenderSlut(LocalDate kalenderSlut) {
		Data.kalenderSlut = kalenderSlut;
	}

	/**
	 * A collection of 'PlayTime' objects.
	 */
	private static ArrayList<PlayTime> playTimeCollection = new ArrayList<>();

	/**
	 * Returns the collection of 'PlayTime' objects.
	 */
	public static ArrayList<PlayTime> getPlayTimeCollection() {
		return playTimeCollection;
	}

	/**
	 * Sets the collection of 'PlayTime' objects.
	 * 
	 * @param playTimeCollection
	 *            Current collection of 'PlayTime' objects to be set.
	 */
	public static void setPlayTimeCollection(ArrayList<PlayTime> playTimeCollection) {
		Data.playTimeCollection = playTimeCollection;
	}

	/**
	 * Aliases for club file names as indicated in configuration file.
	 */
	private static Hashtable<Integer, String> aliasser = null;

	/**
	 * Returns aliases for club file names as indicated in configuration file.
	 */
	public static Hashtable<Integer, String> getAliasser() {
		return aliasser;
	}

	/**
	 * Sets aliases for club file names as indicated in configuration file.
	 * 
	 * @param hashAliases
	 *            Aliases for club file names as indicated in configuration file.
	 */
	public static void setAliasser(Hashtable<Integer, String> hashAliases) {
		Data.aliasser = hashAliases;
	}

	/**
	 * Original club file names as indicated in configuration file.
	 */
	private static String[] originals = null;

	/**
	 * Returns original club file names as indicated in configuration file.
	 */
	public static String[] getOriginals() {
		return originals;
	}

	/**
	 * Sets original club file names as indicated in configuration file.
	 * 
	 * @param originals
	 *            Original club file names as indicated in configuration file.
	 */
	public static void setOriginals(String[] originals) {
		Data.originals = originals;
	}

	/**
	 * Number of days current club is playing. Club index is the same as for
	 * originals and aliases.
	 */
	private static String[] daysPerMonth = null;

	/**
	 * Returns number of days current club is playing. Club index is the same as for
	 * originals and aliases.
	 */
	public static String[] getDaysPerMonth() {
		return daysPerMonth;
	}

	/**
	 * Sets number of days current club is playing. Club index is the same as for
	 * originals and aliases.
	 * 
	 * @param days
	 *            number of days current club is playing.
	 */
	public static void setDaysPerMonth(String[] days) {
		Data.daysPerMonth = days;
	}

	/**
	 * List of contingent algorithms for each club status.
	 */
	private static ArrayList<String[]> contingentAlgorithms = new ArrayList<>();

	/**
	 * Returns a list of contingent algorithms for each club status.
	 */
	public static ArrayList<String[]> getContingentAlgorithms() {
		return contingentAlgorithms;
	}

	/**
	 * Sets a list of contingent algorithms for each club status.
	 * 
	 * @param contingent
	 *            A list of contingent algorithms for each club status.
	 */
	public static void setContingentAlgorithms(ArrayList<String[]> contingent) {
		Data.contingentAlgorithms = contingent;
	}

	/**
	 * Persons merged with club days.
	 */
	private static ArrayList<Person> mergedPersons = new ArrayList<>();

	/**
	 * Returns an array list of persons merged with club days.
	 */
	public static ArrayList<Person> getMergedPersons() {
		return mergedPersons;
	}

	/**
	 * Sets an array list of persons merged with club days.
	 * 
	 * @param mergedPersons
	 *            An array list of persons merged with club days.
	 */
	public static void setMergedPersons(ArrayList<Person> mergedPersons) {
		Data.mergedPersons = mergedPersons;
	}

	/**
	 * Headings from first line in first club file added club aliases, total playing
	 * days, and contingent designed for contingent output.
	 */
	private static String[] headings = null;

	/**
	 * Returns a list of headings designed for contingent output included club
	 * aliases, total playing days, and contingent.
	 */
	public static String[] getHeadings() {
		return headings;
	}

	/**
	 * Sets a list of headings designed for contingent output included club aliases,
	 * total playing days, and contingent.
	 * 
	 * @param headings
	 *            A list of headings designed for contingent output included club
	 *            aliases, total playing days, and contingent..
	 */
	public static void setHeadings(String[] headings) {
		Data.headings = headings;
	}

	/**
	 * An array list of strings each containing information about persons separated
	 * by semicolons.
	 */
	private static ArrayList<String> outlines = null;

	/**
	 * Returns an array list of strings each containing information about persons
	 * separated by semicolons.
	 */
	public static ArrayList<String> getOutlines() {
		return outlines;
	}

	/**
	 * An array list of strings each containing information about persons separated
	 * by semicolons.
	 * 
	 * @param outlines
	 *            An array list of strings each containing information about persons
	 *            separated by semicolons.
	 */
	public static void setOutlines(ArrayList<String> outlines) {
		Data.outlines = outlines;
	}

	/**
	 * A String array of columns of contingent printout used in phone list.
	 */
	private static ArrayList<Integer> phoneColumnNumbers = new ArrayList<>();

	/**
	 * Returns a String array of columns of contingent printout used in phone list.
	 */
	public static ArrayList<Integer> getPhoneColumnNumbers() {
		return phoneColumnNumbers;
	}

	/**
	 * Sets a String array of columns of contingent printout used in phone list.
	 * 
	 * @param phoneColumnNumbers
	 *            A String array of columns of contingent printout used in phone
	 *            list.
	 */
	public static void setPhoneColumnNumbers(ArrayList<Integer> phoneColumnNumbers) {
		Data.phoneColumnNumbers = phoneColumnNumbers;
	}

	private static String[] phoneColumnNames = null;

	public static String[] getPhoneColumnNames() {
		return phoneColumnNames;
	}

	public static void setPhoneColumnNames(String[] phoneColumnNames) {
		Data.phoneColumnNames = phoneColumnNames;
	}

	private static ArrayList<String> phoneColumnColors = new ArrayList<>();

	public static ArrayList<String> getPhoneColumnColors() {
		return phoneColumnColors;
	}

	public static void setPhoneColumnColors(ArrayList<String> columnColors) {
		Data.phoneColumnColors = columnColors;
	}

	public static ArrayList<String> getPhoneColumnColorsDefault() {
		ArrayList<String> phoneColumnColorsDefault = new ArrayList<>();
		for (int i = 0; i < 18; i++) {
			switch (i) {
			case 3:
			case 4:
			case 7:
			case 8:
				phoneColumnColorsDefault.add("#FFF");
				break;
			case 10:
			case 11:
				// Mandag
				phoneColumnColorsDefault.add("#E7FF95");
				break;
			case 12:
			case 17:
				// Tirsdag og torsdag
				phoneColumnColorsDefault.add("#D8D7D4");
				break;
			case 13:
			case 14:
			case 15:
			case 16:
				// Onsdag
				phoneColumnColorsDefault.add("#FFE79D");
				break;

			default:
				break;
			}
		}

		return phoneColumnColorsDefault;
	}

	/**
	 * Returns default column numbers in contingent list used in phone list. This
	 * list of columns can be changed in configuration file.
	 */
	public static ArrayList<Integer> getPhoneColumnsDefault() {
		ArrayList<Integer> phoneColumnsDefault = new ArrayList<>();
		for (int i = 0; i < 18; i++) {
			switch (i) {
			case 3:
			case 4:
			case 7:
			case 8:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
				phoneColumnsDefault.add(i);
				break;

			default:
				break;
			}
		}

		return phoneColumnsDefault;
	}

	private static String randomList = "";

	public static String getRandomList() {
		return randomList;
	}

	public static void setRandomList(String randomList) {
		Data.randomList = randomList;
		CommonLog.logger.info("message//randomList =" + randomList);
	}

	public static String thinline = "<tr class='thin'><td colspan='12'></td></tr>";

	public static String getThinline() {
		return thinline;
	}

	/**
	 * Heading line used for each 30 lines.
	 */
	public static String headingHtmlDefault = "";

	public static String getHeadingHtmlDefault() {
		return headingHtmlDefault;
	}

	public static void setHeadingHtmlDefault(String htmlDefaultHeading) {
		Data.headingHtmlDefault = htmlDefaultHeading;
	}

	private static ArrayList<MinusWeek> weekList = new ArrayList<>();

	public static void setMinusWeekList(ArrayList<MinusWeek> weekList) {
		Data.weekList = weekList;
	}

	public static ArrayList<MinusWeek> getMinusWeekList() {
		return weekList;
	}

	public static void addMinusWeekList(MinusWeek weekList) {
		Data.weekList.add(weekList);
	}

	public static int numberContingentErrors = 0;

	public static int getNumberContingentErrors() {
		return numberContingentErrors;
	}

	public static void setNumberContingentErrors(int numberContingentErrors) {
		Data.numberContingentErrors = numberContingentErrors;
	}

	public static int totalContingent = -1;

	public static int getTotalContingent() {
		return totalContingent;
	}

	public static void setTotalContingent(int totalContingent) {
		Data.totalContingent = totalContingent;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public Data() {
		common.log.CommonLog.logger.info("heading//");

	}
}
