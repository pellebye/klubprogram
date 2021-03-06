package _start.config;

import java.util.ArrayList;
import java.util.Hashtable;

import _start.config.calendar.HandleCalendar;
import common.Data;
import common.LocalMethods;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.TypeOfTest;
import common.out.info.InfoAlgorithmError;
import common.out.info.InfoClublineInConfigFileError;
import common.out.info.InfoConfiglinesEmpty;
import common.out.info.InfoMissingConfigCommands;
import common.out.info.InfoUnexpectedError;
import common.out.print.ModeTextLines;
import common.out.print.ReadTxtFile;
import common.out.print.Textlines;

public class Configuration {

	/**
	 * Number and sequence of aliases. An array list of club aliases (correspond to
	 * club names).
	 */
	private ArrayList<String> tempAliases = new ArrayList<>();
	/**
	 * Original club names in sequence given in configuration file. An array list of
	 * original club names.
	 */
	private ArrayList<String> tempOriginals = new ArrayList<>();
	/**
	 * Days played per month for clubs. An array list of days per month (correspond
	 * to club names).
	 */
	private ArrayList<String> tempDaysPerMonth = new ArrayList<>();
	private String command = "";
	/**
	 * List of algorithms inclusive club status. Resulting array list of string
	 * arrays containing algorithms.
	 */
	private ArrayList<String[]> algorithms = new ArrayList<>();
	/**
	 * Columns of contingent printout used in phone list.
	 */
	private ArrayList<Integer> phoneColums = new ArrayList<>();

	private ArrayList<String> columnColors = new ArrayList<>();

	private int bitResult = 0x0;

	private ArrayList<String> configLines = null;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class handles configuration file:
	 * <ul>
	 * <li>save original file names.</li>
	 * <li>save alias file names.</li>
	 * <li>save days per month for each play day.</li>
	 * <li>save contingent algorithms.</li>
	 * <li>save number of phone list columns.</li>
	 * <li>save colors of phone list columns.</li>
	 * <li>save play time collection.</li>
	 * </ul>
	 * 
	 * It also take care of 3 hidden options:
	 * <ul>
	 * <li>randomlist: Creates lists of random numbers.</li>
	 * <li>addon: Add codes before and after lines in files.</li>
	 * <li>utf8: Convert files to UTF-8.</li>
	 * </ul>
	 */
	public Configuration() {

		configLines = readConfigFile();

		for (int lineIndex = 0; lineIndex < configLines.size(); lineIndex++) {
			String line = configLines.get(lineIndex);
			line = LocalMethods.removeDoubleSpaces(line);

			if (line.startsWith("*")) {
				// This is a command line.
				command = line.substring(1).toLowerCase();
			} else {
				// This is a configuration line.
				switch (command) {
				case "klub":
					bitResult = bitResult | 0x1;
					handleClubConfigLine(line);
					break;
				case "kontingent":
					bitResult = bitResult | 0x2;
					handleContingentConfigLine(line);
					break;
				case "telefonliste":
					bitResult = bitResult | 0x4;
					handlePhoneList(line);
					break;
				case "kalender":
					// handleKalender(line);
					new HandleCalendar(line);
					break;

				// Hidden
				case "randomliste":
					new HandleRandomList(line);
					break;
				case "addon":
					new HandleAddOn(line);
					break;
				case "utf8":
					new HandleUtf8(line);
					break;

				default:
					new InfoUnexpectedError("002 Configuration");
					break;
				}
			}
		}

		afterCare();
		saveResults();
	}

	private void afterCare() {
		if (bitResult != 7) {
			new InfoMissingConfigCommands(bitResult);
		}
		removeColumnDoublePhoneNumbers();

		tempOriginals.trimToSize();
		tempAliases.trimToSize();
		tempDaysPerMonth.trimToSize();
		algorithms.trimToSize();
	}

	/**
	 * Remove column doubles in phone columns array which user has applied by an
	 * accident in configuration file.
	 */
	private void removeColumnDoublePhoneNumbers() {
		boolean goOn = true;
		while (goOn) {
			goOn = false;
			for (int i = 0; i < phoneColums.size(); i++) {
				for (int j = i; j < phoneColums.size(); j++) {
					if (i != j) {
						if (phoneColums.get(i) == phoneColums.get(j)) {
							if (columnColors.get(i) == "#None") {
								/*
								 * When equal phone numbers in two columns remove the first one if no color.
								 */
								phoneColums.remove(i);
								columnColors.remove(i);
							} else {
								/*
								 * Else remove the second one.
								 */
								phoneColums.remove(j);
								columnColors.remove(j);
							}
							goOn = true;
							break;
						}
					}
				}
			}
		}
		phoneColums.trimToSize();
		columnColors.trimToSize();
	}

	/**
	 * Save results from configuration file.
	 */
	private void saveResults() {

		// Default columns if no columns definition.
		if (phoneColums == null || phoneColums.size() == 0) {
			phoneColums = Data.getPhoneColumnsDefault();
			columnColors = Data.getPhoneColumnColorsDefault();
		}

		// White color if no color definition.
		for (int i = 0; i < columnColors.size(); i++) {
			if (columnColors.get(i).toLowerCase().contains("none"))
				columnColors.set(i, "#FFF");
		}

		Hashtable<Integer, String> hashAliases = new Hashtable<>();
		for (int i = 0; i < tempAliases.size(); i++) {
			hashAliases.put(i, tempAliases.get(i));
		}

		// Save results in Data.
		Data.setOriginals(tempOriginals.toArray(new String[0]));
		Data.setAliasser(hashAliases);
		Data.setDaysPerMonth(tempDaysPerMonth.toArray(new String[0]));
		Data.setContingentAlgorithms(algorithms);
		Data.setPhoneColumnNumbers(phoneColums);
		Data.setPhoneColumnColors(columnColors);
	}

	/**
	 * Returns a list of column numbers to be used in phone list
	 * 
	 * @param configLines
	 *            Lines from configuration file.
	 * @param line
	 *            One line from configuration file.
	 */
	private void handlePhoneList(String line) {
		String[] SplitString = line.split(" ");
		if (SplitString.length > 1) {
			for (int i = 0; i < SplitString.length; i++) {
				if (LocalMethods.isParsableToInt(SplitString[i])) {
					// Add to collection of phone column.
					phoneColums.add(Integer.parseInt(SplitString[i]));
					if (SplitString.length > i + 1) {
						// If more data step forward to next one.
						i++;
						if (SplitString[i].startsWith("#")) {
							// If next one is a color add to color collection.
							columnColors.add(SplitString[i]);
						} else {
							/*
							 * If not a color step backwards and add none to color collection.
							 */
							i--;
							columnColors.add("#None");
						}
					} else {
						// If last data add none to color collection.
						columnColors.add("#None");
					}
				} else
					new InfoUnexpectedError("003 Configuration");
			}
		} else {
			// only one item.
			if (LocalMethods.isParsableToInt(SplitString[0])) {
				phoneColums.add(Integer.parseInt(SplitString[0]));
				columnColors.add("#None");
			} else
				new InfoUnexpectedError("004 Configuration");
		}
	}

	/**
	 * Handles a contingent algorithm. There are 3 types:
	 * <ul>
	 * <li>line contains "<"</li>
	 * <li>line contains ">"</li>
	 * <li>line contains "-"</li>
	 * </ul>
	 * 
	 * @param config
	 * @param configLines
	 *            Lines from configuration file.
	 * @param line2
	 *            Index of lines from configuration file.
	 */
	private void handleContingentConfigLine(String line) {

		line = LocalMethods.removeDoubleSpaces(line);

		String[] splitString = line.split(" ");
		if (splitString.length < 7) {
			if (splitString.length == 6 && splitString[2].compareTo("-") == 0) {
				String[] temp = new String[5];
				for (int j = 0; j < splitString.length; j++) {
					if (j < 5) {
						temp[j] = splitString[j];
					} else
						temp[4] = splitString[j];
				}
				algorithms.add(temp);
			} else if (splitString.length == 5) {
				if (splitString[1].compareTo("<") == 0 || splitString[1].compareTo(">") == 0) {
					String[] temp = new String[4];
					for (int j = 0; j < splitString.length; j++) {
						if (j < 4) {
							temp[j] = splitString[j];
						} else
							temp[3] = splitString[j];
					}
					algorithms.add(temp);
				}
			} else
				new InfoUnexpectedError("007 Configuration");
		} else
			new InfoAlgorithmError(line);
	}

	/**
	 * Handles a club line in configuration file, i.e.
	 * <ul>
	 * <li>store originals e.g. 'Klub2484_Mandag_eftermiddag'</li>
	 * <li>store play dates per month e.g. 4 from 'ManE (4)'</li>
	 * <li>store aliases e.g.'ManE' from 'ManE (4)'</li>
	 * </ul>
	 * 
	 * @param configLines
	 *            Lines from configuration file.
	 * @param line
	 *            Index of lines from configuration file.
	 */
	private void handleClubConfigLine(String line) {

		// E.g. Klub2484_Mandag_eftermiddag = ManE (4)
		String[] splitString = line.split("=");
		if (splitString.length == 2) {
			// E.g Klub2484_Mandag_eftermiddag
			tempOriginals.add(splitString[0].trim());
			/*
			 * Split on '(' to get number of play dates E.g. ManE (4)
			 */
			String temp = splitString[1].trim();
			splitString = temp.split("\\(");
			// First character is number of play dates.
			tempDaysPerMonth.add(splitString[1].substring(0, 1));

			// E.g. ManE
			tempAliases.add(splitString[0].trim());
		} else
			new InfoClublineInConfigFileError(line);
	}

	/**
	 * Returns lines of configuration file 'config.txt'. Returns null if file does
	 * not exist.
	 */
	private ArrayList<String> readConfigFile() {
		ArrayList<String> configLines = null;
		boolean configFileExists = checkConfigFile();

		/*
		 * Read config lines if any. The file "config.txt" is generated at program start
		 * from default resource if it does not exist. So at this point the file exists.
		 */
		if (configFileExists) {
			Textlines textlines = new Textlines();

			ReadTxtFile readTxtFile = new ReadTxtFile("/config.txt", ModeTextLines.WITHOUTCOMMENTS, textlines,
					TypeOfTest.FILE_NOCOPY, ModeCheckDetail.CONFIG_CURRENT);
			configLines = readTxtFile.getTextlinesReduced();
		} else
			new InfoUnexpectedError("005 Configuration");

		if (configLines.size() == 0) {
			new InfoConfiglinesEmpty();
		}

		return configLines;
	}

	/**
	 * Returns true if configuration file exists.
	 */
	private boolean checkConfigFile() {
		String configFilename = LocalMethods.getUserDirectory() + "/config.txt";
		configFilename = LocalMethods.getFilenameWithForwardSlashes(configFilename);

		return LocalMethods.isFileExisting(configFilename);
	}
}
