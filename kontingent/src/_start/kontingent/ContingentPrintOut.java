package _start.kontingent;

import java.util.ArrayList;
import java.util.Hashtable;

import common.Data;
import common.log.CommonLog;
import common.out.print.PrintStringFile;

public class ContingentPrintOut {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class prints out a list of persons and their contingent.
	 */
	public ContingentPrintOut() {
		common.log.CommonLog.logger.info("heading//");

		ArrayList<String> personOutLines = new ArrayList<>();

		ArrayList<Person> persons = Data.getMergedPersons();

		getHeadings(personOutLines);

		printout(persons, personOutLines);

		for (int i = 0; i < personOutLines.size(); i++) {
			System.out.println(personOutLines.get(i));
		}

		String[] outlines = personOutLines.toArray(new String[0]);
		new PrintStringFile(outlines, "resultater/kontingentliste", "kontingent.csv");

		// Reset list
		personOutLines = new ArrayList<>();
	}

	/**
	 * Adds a line of headings to the output lines.
	 * 
	 * @param personOutLines
	 *            Output lines.
	 */
	private void getHeadings(ArrayList<String> personOutLines) {
		String line = "";
		String[] headings = Data.getHeadings();

		for (int i = 0; i < headings.length; i++) {
			if (headings[i] == null)
				break;
			line += headings[i] + ";";
		}

		// Add mail domain as the end of heading list.
		line += "Mail domæne" + ";";
		personOutLines.add(line);
	}

	/**
	 * Modify persons to fit output lines.
	 * 
	 * @param persons
	 *            An array list of persons.
	 * @param personOutLines
	 *            Output lines.
	 */
	private void printout(ArrayList<Person> persons, ArrayList<String> personOutLines) {
		CommonLog.logger.info("heading//");

		persons.trimToSize();

		// Go through all persons
		for (int i = 0; i < persons.size(); i++) {

			// Modified line to out line.
			Person person = persons.get(i);
			String modifiedLine = person.getModifiedLine();
			String outLine = modifiedLine;

			// Mark playing days of current person.
			outLine = markPlayingDays(person, outLine);

			/*
			 * Add total playing days, contingent, and mail domain.
			 */
			outLine += person.getNumberOfPlayingDays() + ";";
			outLine += person.getContingent() + ";";
			outLine += person.getMailDomain() + ";";

			personOutLines.add(outLine);
		}
		personOutLines.trimToSize();
		Data.setOutlines(personOutLines);
	}

	/**
	 * Returns modified line of current person with added clubs the person has
	 * played.
	 * 
	 * @param person
	 *            An array list of persons.
	 * @param outLine
	 *            Modified line of current person, i.e. the final line without
	 *            number of playing days, contingent, and mail domain.
	 */
	private String markPlayingDays(Person person, String outLine) {
		Hashtable<Integer, String> aliases = Data.getAliasser();
		String[] playClubs = person.getPlayClubs().toArray(new String[0]);

		// Go through all clubs.
		for (int aliasNo = 0; aliasNo < aliases.size(); aliasNo++) {
			String clubAlias = aliases.get(aliasNo);

			// Go through clubs played of current person.
			String numberOfDays = checkMatch(aliasNo, clubAlias, playClubs);
			if (numberOfDays == null) {
				// If no match add ';' to out line.
				outLine += ";";
			} else {
				// If match add club alias to out line.
				outLine += aliases.get(aliasNo) + ";";
			}
		}
		return outLine;
	}

	/**
	 * Returns the number of days for current club alias played by current person.
	 * If current person has not played in the club this procedure returns null.
	 * 
	 * @param aliasNo
	 *            Current number of club aliases in the list of aliases for current
	 *            person.
	 * @param clubAlias
	 *            Current club alias for current person.
	 * @param playClubs
	 *            String array of clubs played by current person.
	 */
	private String checkMatch(int aliasNo, String clubAlias, String[] playClubs) {

		for (int i = 0; i < playClubs.length; i++) {
			if (clubAlias.compareTo(playClubs[i]) == 0)
				return Data.getDaysPerMonth()[aliasNo];
		}

		return null;
	}
}
