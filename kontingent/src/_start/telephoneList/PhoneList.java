package _start.telephoneList;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import common.Data;
import common.HtmlCreateEndOfTableBodyAndHtml;
import common.HtmlCreateHeaderAndBodyStart;
import common.LocalMethods;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.ModeChecks;
import common.out.file.filetest.TypeOfTest;
import common.out.file.filetest.mac.MessagesAndChecks;
import common.out.print.CheckWhiteColor;
import common.out.print.PrintStringFile;
import common.system.ComSys;

public class PhoneList {

	String tempPerson = "";
	String headingCsv = "";

	/**
	 * Heading line used for sorting.
	 */
	String htmlSortingHeading = "";

	/**
	 * Persons (without headings) to be used for a phone list
	 */
	ArrayList<String> phonePersons = new ArrayList<>();

	/**
	 * List of html lines to be printed out.
	 */
	ArrayList<String> outList = new ArrayList<>();

	private Filetest filetest;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class creates a html phone list of persons.
	 */
	public PhoneList() {
		common.log.CommonLog.logger.info("heading//");

		ArrayList<String> result = new ArrayList<>();

		createBasicList();

		createHtmlHeading();

		new HtmlCreateHeaderAndBodyStart("Telefonliste", result, getCurrentDate(), "Telefonliste", "none",
				getExtraLine());

		createTableStart(result);

		htmlAddPhonePersons(result);

		result.add("</tbody>" + LocalMethods.getNewline() + "</table>" + LocalMethods.getNewline() + "</div>"
				+ LocalMethods.getNewline());

		new HtmlCreateEndOfTableBodyAndHtml(result, false, true, true);

		new PhoneCSSfile("html.css");

		new PrintStringFile(result.toArray(new String[0]), "resultater/telefonliste", "tlf-list.htm");

		checkRequiredFiles();
	}

	/**
	 * Checks that 3 required files are present in result folder:
	 * 
	 * <ul>
	 * <li>jquery-3.2.1.js</li>
	 * <li>tablesorter.js</li>
	 * <li>tlf-list.css</li>
	 * </ul>
	 */
	private void checkRequiredFiles() {
		ComSys.setRebuildAccepted(true);

		for (int fileNo = 0; fileNo < 3; fileNo++) {
			switch (fileNo) {
			case 0:
				// jquery-3.2.1.js
				filetest = new Filetest("\\resultater\\telefonliste\\jquery-3.2.1.min.js");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/jquery/jquery-3.2.1.min.js");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 1:
				// tablesorter.js
				filetest = new Filetest("\\resultater\\telefonliste\\tablesorter.js");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/jquery/tablesorter.js");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 2:
				// tlf-list.css (a new file has just been created)
				filetest = new Filetest("\\resultater\\telefonliste\\tlf-list.css");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/tlf-list.css");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;

			default:
				throw new IllegalArgumentException("Illegal keyword - ChkLanguages fileNo > " + 2);
			}

			new MessagesAndChecks(null, ModeChecks.FILETEST_SYSTEMFILES, filetest);
		}
		ComSys.setRebuildAccepted(false);
	}

	/**
	 * Creates table start with
	 * <ul>
	 * <li>class 'tablesorter'</li>
	 * <li>heading for sorting</li>
	 * <li>the first td-line</li>
	 * </ul>
	 * 
	 * @param result
	 *            Array list gathering lines to print out.
	 */
	private void createTableStart(ArrayList<String> result) {
		result.add("<div class='preben'>");
		result.add("Klik p� en kolonne p� den �verste overskriftslinje for at sortere.");
		result.add("<table id='phoneTable' class='tablesorter'>");
		result.add(htmlSortingHeading);
		result.add("<tbody>");

		result.add("<tr>");
		result.add(
				"<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>");
		result.add("</tr>");
	}

	/**
	 * Returns a string containing lines to be just before </head>-element.
	 */
	private String getExtraLine() {
		String extraLine = "  <link rel='stylesheet' type='text/css' href='tlf-list.css' />"
				+ LocalMethods.getNewline();
		extraLine += "  <script type='text/javascript' src='./jquery-3.2.1.min.js'></script>"
				+ LocalMethods.getNewline();
		extraLine += "  <script type='text/javascript' src='./tablesorter.js'></script>" + LocalMethods.getNewline();
		extraLine += "  <script type='text/javascript' src='./tlf-list.js'></script>" + LocalMethods.getNewline();
		extraLine += "  <script>" + LocalMethods.getNewline();
		extraLine += "  $(document).ready(function() {" + LocalMethods.getNewline();
		extraLine += "    $('#phoneTable')" + LocalMethods.getNewline();
		extraLine += "      .tablesorter({   " + LocalMethods.getNewline();
		extraLine += "      // default sortInitialOrder setting" + LocalMethods.getNewline();
		extraLine += "      sortInitialOrder: 'asc'" + LocalMethods.getNewline();
		extraLine += "    }); " + LocalMethods.getNewline();
		extraLine += "    $('#phoneTable')" + LocalMethods.getNewline();
		extraLine += "      .bind('sortEnd',function(e, t){" + LocalMethods.getNewline();
		extraLine += "        $('.thin').remove();" + LocalMethods.getNewline();
		extraLine += "        $('.default').remove();" + LocalMethods.getNewline();
		extraLine += "        $('.highlight').remove();" + LocalMethods.getNewline();
		extraLine += "        $( 'tr' ).each(function( index ) {" + LocalMethods.getNewline();
		extraLine += "        	  if (index % 30 == 0){" + LocalMethods.getNewline();
		extraLine += "        	    if (index != 0){" + LocalMethods.getNewline();
		extraLine += "        	      $( this ).after(\"" + removeNewlines() + "\");" + LocalMethods.getNewline();
		extraLine += "        	    }" + LocalMethods.getNewline();
		extraLine += "        	  }" + LocalMethods.getNewline();
		extraLine += "        	  if (index % 5 == 0){" + LocalMethods.getNewline();
		extraLine += "        	    $( this ).after(\"" + Data.getThinline() + "\");" + LocalMethods.getNewline();
		extraLine += "        	  }" + LocalMethods.getNewline();
		extraLine += "        });" + LocalMethods.getNewline();
		extraLine += "    });" + LocalMethods.getNewline();
		extraLine += "  }); " + LocalMethods.getNewline();
		extraLine += "<!-- " + LocalMethods.getNewline();
		extraLine += "--> " + LocalMethods.getNewline();
		extraLine += "  </script>";

		return extraLine;
	}

	private String removeNewlines() {
		String s = Data.getHeadingHtmlDefault();
		s = s.replace("\r\n", "");

		return s;
	}

	/**
	 * Creates two strings of headings, one for 'tablesorter' and one for repeating
	 * headlines.
	 */
	private void createHtmlHeading() {
		String[] splitString = headingCsv.split(";");
		StringBuffer sbSorting = new StringBuffer("<thead><tr class='header'>" + LocalMethods.getNewline());
		StringBuffer sbDefault = new StringBuffer("<tr class='header default'>" + LocalMethods.getNewline());
		for (int i = 0; i < splitString.length; i++) {
			if (i == 2 || i == 3) {
				// Position phone heading.
				splitString[i] = "&nbsp;&nbsp;" + splitString[i] + "&nbsp;&nbsp;";
			}
			// First heading designed for sorting.
			sbSorting.append("<th><b>" + splitString[i] + "</b></th>" + LocalMethods.getNewline());
			// Other headings showed for every 30 lines.
			sbDefault.append("<td><b>" + splitString[i] + "</b></td>" + LocalMethods.getNewline());
		}
		sbSorting.append("</tr></thead>");
		sbDefault.append("</tr>");
		htmlSortingHeading = sbSorting.toString();
		Data.setHeadingHtmlDefault(sbDefault.toString());
	}

	/**
	 * Converts persons to html lines.
	 * 
	 * @param result
	 *            Array list gathering lines to print out.
	 */
	private void htmlAddPhonePersons(ArrayList<String> result) {

		for (int pNo = 0; pNo < phonePersons.size(); pNo++) {
			String person = phonePersons.get(pNo);
			while (person.contains(";;")) {
				person = person.replaceAll(";;", "; ;");
			}
			String[] splitString = person.split(";");

			mod3andMod30(result, pNo);

			result.add(getOneHtmlLine(splitString).toString());
		}
	}

	/**
	 * Returns a string buffer containing one person as a html-line.
	 * 
	 * @param person
	 *            One Person as a string array.
	 */
	private StringBuffer getOneHtmlLine(String[] person) {
		String[] columnNames = Data.getPhoneColumnNames();
		StringBuffer sb;
		sb = new StringBuffer("<tr>");
		for (int i = 0; i < person.length; i++) {

			switch (i) {
			case 2:
			case 3:
				person[i] = person[i].replaceAll(" ", "");
				person[i] = "&nbsp;&nbsp;" + person[i] + "&nbsp;&nbsp;";
				addColorClass(person, columnNames, sb, i);
				break;

			default:
				addColorClass(person, columnNames, sb, i);
				break;
			}

		}
		sb.append("</tr>");

		return sb;
	}

	private void addColorClass(String[] person, String[] columnNames, StringBuffer sb, int i) {
		// Add color class if color is other than white.
		if (getColor(i).compareTo("") != 0) {
			sb.append("<td class='" + columnNames[i] + "'>" + person[i] + "</td>" + LocalMethods.getNewline());
		} else {
			sb.append("<td>" + person[i] + "</td>" + LocalMethods.getNewline());
		}
	}

	private String getColor(int index) {
		CheckWhiteColor whiteColor = new CheckWhiteColor(index);
		String result = whiteColor.getColumnColor();
		whiteColor = null;

		return result;
	}

	/**
	 * Adds a thin line for every 5 lines, and a heading for every 30 lines.
	 * 
	 * @param result
	 *            Array list gathering lines to print out.
	 * @param pNo
	 *            Current person number.
	 */
	private void mod3andMod30(ArrayList<String> result, int pNo) {
		if (pNo % 30 == 0) {
			if (pNo != 0) {
				result.add(Data.thinline);
				result.add(Data.getHeadingHtmlDefault());
			}
		}
		if (pNo % 5 == 0) {
			result.add(Data.thinline);
		}
	}

	/**
	 * Returns current date in format YYYY.MM.DD
	 */
	private String getCurrentDate() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		LocalDate localDate = zonedDateTime.toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();

		return String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);
	}

	/**
	 * Creates an array list of persons (without any headings) designed to be used
	 * as a phone list. First persons of the array list is saved as heading.
	 */
	private void createBasicList() {
		boolean first = true;
		ArrayList<String> persons = Data.getOutlines();
		ArrayList<Integer> phoneColumns = Data.getPhoneColumnNumbers();

		for (int pNo = 0; pNo < persons.size(); pNo++) {
			tempPerson = "";
			String[] splitString = persons.get(pNo).split(";");
			for (int index = 0; index < splitString.length; index++) {
				for (int columnNo = 0; columnNo < phoneColumns.size(); columnNo++) {
					int column = phoneColumns.get(columnNo);
					if (column - 1 == index) {
						addColumn(splitString, index);
					}
				}
			}
			if (first) {
				headingCsv = tempPerson;
				Data.setPhoneColumnNames(tempPerson.split(";"));
				first = false;
			} else
				phonePersons.add(tempPerson);
		}
	}

	/**
	 * Returns a string with an added column of current person.
	 * 
	 * @param splitString
	 *            String array containing current person
	 * @param index
	 *            Column number of current person.
	 */
	private String addColumn(String[] splitString, int index) {
		tempPerson += splitString[index] + ";";

		return tempPerson;
	}
}
