package _start.kontingent;

import java.util.Hashtable;

import common.Data;

public class Heading {

	/**
	 * Headings for phone list columns set to an unrealistic high amount.
	 */
	private String[] headings = new String[50];

	/**
	 * Return final calculated headings trimmed to size.
	 */
	public String[] getHeadings() {

		int i = 0;
		for (i = 0; i < headings.length; i++) {
			if (headings[i] == null) {
				break;
			}
		}
		String[] result = new String[i];
		for (int j = 0; j < result.length; j++) {
			result[j] = headings[j];
		}

		return result;
	}

	/**
	 * Index for headings.
	 */
	private int headingCount = 0;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * Calculate headlines from first line in first original csv file. Change
	 * heading 3 and 4 to 'Adresse'.
	 * 
	 * @param oneClubLine
	 *            Current line of current club.
	 * @param clubCount
	 *            Number of current club file.
	 * @param clubLineNo
	 *            current line number of current club file.
	 */
	public Heading(String oneClubLine, int clubCount, int clubLineNo) {
		common.log.CommonLog.logger.info("heading//");

		String[] temp = oneClubLine.split(";");

		for (int i = 0; i < temp.length; i++) {
			switch (i) {
			case 0:
				headings[headingCount] = "Status";
				headingCount++;
				break;
			case 1:
				headings[headingCount] = "Nr.";
				headingCount++;
				break;
			case 3:
				headings[headingCount] = "Adresse";
				headingCount++;
				break;
			case 6:
				headings[headingCount] = "Post";
				headingCount++;
				break;
			case 4:
				// Do nothing for adresse 2.
			case 5:
				// Do nothing for Landekode.
			case 10:
				// Do nothing for Telefon 3.
			case 12:
				// Do nothing for Fødseldag.
			case 13:
				// Do nothing for Betalt år til dato.
			case 14:
				/*
				 * Do nothing for Adgang til personlig hjemmeside.
				 */
				break;

			default:
				headings[headingCount] = temp[i];
				headingCount++;
				break;
			}
		}
	}

	/**
	 * Add club aliases, total play days, and contingent to heading.
	 */
	public void addClubNameAliasesToHeading() {
		Hashtable<Integer, String> aliases = Data.getAliasser();
		for (int i = 0; i < aliases.size(); i++) {
			headings[headingCount] = aliases.get(i);
			headingCount++;
		}
		/*
		 * Add columns after clubs.
		 */
		headings[headingCount] = "Dage ialt";
		headingCount++;
		headings[headingCount] = "Kontingent";
		headingCount++;
	}
}
