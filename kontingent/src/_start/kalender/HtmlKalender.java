package _start.kalender;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import common.Data;
import common.HtmlCreateEndOfTableBodyAndHtml;
import common.HtmlCreateHeaderAndBodyStart;
import common.LocalMethods;
import common.log.CommonLog;
import common.out.print.PrintStringFile;

public class HtmlKalender {

	private Calendar cal = Calendar.getInstance(); // GregorianCalendar
	private ArrayList<String> result = new ArrayList<String>();
	private String[] maaned = new String[] { "Januar", "Februar", "Marts", "April", "Maj", "Juni", "Juli", "August",
			"September", "Oktober", "November", "December" };
	private String[] ugenavne = new String[] { "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag", "Søndag" };
	private ArrayList<PlayTime> playTimeCollection = Data.getPlayTimeCollection();

	private String newLine = LocalMethods.getNewline();
	private int numberOfDatelines = 7;
	private String[] dateLines = null;
	private int dateLineNo = -1;
	private boolean firstDayOfCalendar = true;
	private boolean lastDayOfFirstYear = false;

	// Current month.
	private int month = 0;
	private boolean nextMonth = false;
	private LocalDate date = null;
	private int firstYearlength = -1;
	private int yearSecondPart = -1;
	private int yearFirstPart = -1;

	private int daysBetween = -1;
	private int dateNo = -1;
	private boolean minusWeek = false;
	private MinusWeek currentMinusWeek = null;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class generate a calendar with html codes.
	 */
	public HtmlKalender() {
		CommonLog.logger.info("heading//");

		String title = "Kalender for Vestlolland Bridgecenter";
		String description = "Kalender for Vestlolland Briddgecenter";
		String cssFilename = "kalender.css";
		String extraLine = "  <script type='text/javascript' src='kalender.js'></script>";

		new HtmlCreateHeaderAndBodyStart(title, result, "", description, cssFilename, extraLine);

		String idAndClass = "";
		createHeaderHtmlTableStart(idAndClass);

		createKalenderCss();

		createKalender();

		test();

		new HtmlCreateEndOfTableBodyAndHtml(result, false, true, true);

		new PrintStringFile(result.toArray(new String[result.size()]), "resultater/kalender", "kalender.htm");
	}

	private void createKalenderCss() {
		CreateKalenderCssText cssText = new CreateKalenderCssText();
		String[] cssLines = cssText.getCssLines();
		cssText = null;

		new PrintStringFile(cssLines, "resultater/kalender", "kalender.css");
	}

	private void createKalender() {
		getSolidInfoOfFirstDate();

		int numberOfDays = daysBetween + yearFirstPart;

		for (dateNo = yearFirstPart; dateNo < numberOfDays; dateNo++) {

			getCurrenDate();
			handleFirstDayOfCalendar(yearFirstPart, dateNo);

			// Also check for minus week.
			checkForMondayOrCalendarFirstDay();
			checkForNextMonth(date);
			getPlayTimeInfo();

			// add rest of the date lines for current calendar day.
			for (int i = dateLineNo + 1; i < dateLines.length; i++)
				addEmptyDateLines();

			checkForLastDayOfFirstYear();

			// Reset date lines.
			dateLineNo = -1;

			checkForSunday();
		}
	}

	/**
	 * Sets end of date lines and reset 'minusWeek' and 'currentMinusWeek' when
	 * current date is a Sunday.
	 */
	private void checkForSunday() {
		if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			endDateLines();

			// Reset minus week.
			minusWeek = false;
			currentMinusWeek = null;
		}
	}

	private void checkForLastDayOfFirstYear() {
		if (lastDayOfFirstYear) {
			int countDown = DayOfWeek.values()[6].getValue() - date.getDayOfWeek().getValue();

			while (countDown > 0) {
				addToDateLinesStartup();
				countDown--;
			}
			lastDayOfFirstYear = false;
			checkForNextMonth(date);
		}
	}

	private void getPlayTimeInfo() {
		int calDayOfWeek = date.getDayOfWeek().getValue();
		for (int playTimeNo = 0; playTimeNo < playTimeCollection.size(); playTimeNo++) {
			PlayTime playTime = playTimeCollection.get(playTimeNo);
			int dayOfWeek = playTime.getDayOfWeek();
			LocalDate first = playTime.getDateFirst();
			LocalDate last = playTime.getDateLast();
			if (calDayOfWeek == dayOfWeek) {
				if ((date.isAfter(first) || date.isEqual(first)) && (date.isBefore(last) || date.isEqual(last))) {
					// Some play times may exist.
					addToDateLines(date, playTime);
				}
			}
		}
	}

	/**
	 * Creates date lines with week number (and check for minus week).
	 * 
	 */
	private void checkForMondayOrCalendarFirstDay() {
		if (date.getDayOfWeek() == DayOfWeek.MONDAY || firstDayOfCalendar) {
			createDateLines(date);
			if (firstDayOfCalendar) {
				int countDown = date.getDayOfWeek().getValue() - DayOfWeek.values()[0].getValue();

				while (countDown > 0) {
					addToDateLinesStartup();
					countDown--;
				}
				firstDayOfCalendar = false;
			}
		}
	}

	private void getCurrenDate() {
		CurrentDate currentDate = new CurrentDate(dateNo, firstYearlength, lastDayOfFirstYear);
		date = currentDate.getDate();
		currentDate = null;
	}

	private void getSolidInfoOfFirstDate() {
		date = Data.getKalenderStart();

		firstYearlength = date.lengthOfYear();
		yearSecondPart = firstYearlength - date.getDayOfYear();
		yearFirstPart = firstYearlength - yearSecondPart;

		daysBetween = (int) java.time.temporal.ChronoUnit.DAYS.between(Data.getKalenderStart(), Data.getKalenderSlut());
	}

	private void handleFirstDayOfCalendar(int yearFirstPart, int dateNo) {
		if (dateNo == yearFirstPart) {
			month = date.getMonthValue();
			monthHeading(date);
		}
	}

	private void addToDateLinesStartup() {
		for (int i = 0; i < dateLines.length; i++) {
			switch (i) {
			case 0:
				dateLines[i] += "<td class='dashedLine lastMonth" + "'></td>";
				break;
			case 6:
				dateLines[i] += "<td class='dashedUnderLine lastMonth'></td>";
				break;

			default:
				dateLines[i] += "<td class='dashedLine lastMonth" + "'></td>";
				break;
			}
		}
	}

	private void addToDateLines(LocalDate date, PlayTime playTime) {

		if (!checkForValidDate(date, playTime))
			return;

		String currentClass = "dashedLine";
		String currentClassUnderline = "dashedUnderLine";

		String playTimeName = playTime.getName();
		if (playTime.getDatoer().size() > 1) {
			if (!minusWeek) {
				playTime.nextNummber();
			}
			playTimeName += playTime.getComment();
		}

		String startTime = playTime.getStartTime();
		if (minusWeek) {
			playTimeName = "";
			startTime = "";
			currentClass += " minusWeek";
			currentClassUnderline += " minusWeek";
		}

		dateLineNo++;
		switch (dateLineNo) {
		case 0:
			dateLines[dateLineNo] += "<td class='" + currentClass + "'>" + date.getDayOfMonth() + "</td>";
			dateLineNo++;
			dateLines[dateLineNo] += "<td class='" + currentClass + "' bgcolor='" + playTime.getShowColor() + "'>"
					+ playTimeName + " " + startTime + "</td>";
			break;
		case 6:
			dateLines[dateLineNo] += "<td class='" + currentClassUnderline + "'></td>";
			break;

		default:
			dateLines[dateLineNo] += "<td class='" + currentClass + "' bgcolor='" + playTime.getShowColor() + "'>"
					+ playTimeName + " " + startTime + "</td>";
			break;
		}
	}

	/**
	 * Returns true if a play time date exists for current date.
	 * 
	 * @param date
	 *            Current date.
	 * @param playTime
	 *            Current play time.
	 */
	private boolean checkForValidDate(LocalDate date, PlayTime playTime) {
		ArrayList<LocalDate> dates = playTime.getDatoer();
		for (int i = 0; i < dates.size(); i++) {
			LocalDate playTimeDate = dates.get(i);
			if (date.isEqual(playTimeDate)) {
				return true;
			}
		}

		return false;
	}

	private void endDateLines() {
		for (int i = 0; i < dateLines.length; i++) {
			dateLines[i] += "</tr>";
			if (i == dateLines.length - 1) {
				dateLines[i] += newLine;
			}
		}
		if (nextMonth) {
			nextMonthSwitchover(dateLines.clone());
			nextMonth = false;
			monthHeading(date);
			nextMonthSwitchover(dateLines.clone());
		} else {
			datelinesToResult();
		}
	}

	private void nextMonthSwitchover(String[] dateLinesClone) {
		int numberOfTd = findNumberOfTd(dateLinesClone[0]);
		for (int i = 0; i < dateLinesClone.length; i++) {
			String before = getSubstring(dateLinesClone[i], numberOfTd, true);
			String after = getSubstring(dateLinesClone[i], numberOfTd, false);
			if (nextMonth) {
				after = after.replaceAll("dashedLine", "dashedLine lastMonth");
				after = after.replaceAll("dashedUnderLine", "dashedUnderLine lastMonth");
				dateLinesClone[i] = before + after;
			} else {
				after = after.replaceAll(" lastMonth", "");
				before = before.replaceAll("dashedLine", "dashedLine lastMonth");
				before = before.replaceAll("dashedUnderLine", "dashedUnderLine lastMonth");
				dateLinesClone[i] = before + after;
			}
		}
		dateLines = dateLinesClone;
		datelinesToResult();
	}

	/**
	 * Returns a part of first calendar line which is either before or after first
	 * date of next month.
	 * 
	 * @param string
	 *            First calendar line.
	 * @param numberOfTd
	 *            Number of 'td' after first date of next month.
	 * @param before
	 *            True if part to return is before first date of next month.
	 */
	private String getSubstring(String string, int numberOfTd, boolean before) {
		int result = 0;
		int x = 0;
		/*
		 * Count further occurrences of 'td'.
		 */
		while (x > -1 && (result != numberOfTd + 1)) {
			x = string.indexOf("<td", x + 1);
			result++;
		}

		if (before) {
			return string.substring(0, x);
		} else {
			return string.substring(x);
		}
	}

	/**
	 * Returns number of days ('td') before first date of next month. If result is
	 * zero the first date of next month is the last date on calendar line.
	 * 
	 * @param string
	 *            String containing dates of current month followed by dates of next
	 *            month.
	 */
	private int findNumberOfTd(String string) {
		/*
		 * Split before date one.
		 */
		int splitIndex = string.indexOf(">1<");
		if (splitIndex == -1) {
			new PrintStringFile(result.toArray(new String[result.size()]), "resultater/kalender", "kalender.htm");
		}
		string = string.substring(0, splitIndex);
		string = string.substring(0, string.lastIndexOf("<td"));

		/*
		 * First date ('td') after first date of next month.
		 */
		int x = string.indexOf("<td");
		int result = 0;

		/*
		 * Count further dates ('td') after first date of next month.
		 */
		x = 0;
		while (x > -1) {
			x = string.indexOf("<td", x + 1);
			if (x > -1) {
				result++;
			}
		}

		/*
		 * If result is zero the first date is the last date on calendar line.
		 */
		return result;
	}

	private void datelinesToResult() {
		for (int i = 0; i < dateLines.length; i++) {
			result.add(dateLines[i]);
		}
	}

	private void addEmptyDateLines() {
		String currentClass = "dashedLine";
		String currentClassUnderline = "dashedUnderLine";
		String[] minusWeekText = null;
		String dayTextForMinusWeek = "";
		if (minusWeek) {
			currentClass += " minusWeek textCenter";
			currentClassUnderline += " minusWeek textCenter";
			minusWeekText = currentMinusWeek.getMinusWeekText();
			int weekDay = date.getDayOfWeek().getValue();
			if (weekDay < minusWeekText.length + 1) {
				dayTextForMinusWeek = minusWeekText[weekDay - 1];
			}
		}
		dateLineNo++;
		switch (dateLineNo) {
		case 0:
			dateLines[dateLineNo] += "<td class='" + currentClass + "'>" + date.getDayOfMonth() + "</td>";
			break;
		case 3:
			if (minusWeek) {
				dateLines[dateLineNo] += "<td class='" + currentClass + "'><b>" + dayTextForMinusWeek + "</b></td>";
			} else
				dateLines[dateLineNo] += "<td class='" + currentClass + "'></td>";
			break;
		case 6:
			dateLines[dateLineNo] += "<td class='" + currentClassUnderline + "'></td>";
			dateLineNo = -1;
			break;

		default:
			dateLines[dateLineNo] += "<td class='" + currentClass + "'></td>";
			break;
		}
	}

	/**
	 * Creates date lines with week number (and check for minus week).
	 * 
	 * @param date
	 *            Current calendar date.
	 */
	private void createDateLines(LocalDate date) {
		WeekHandling weekHandling = new WeekHandling(date);
		int weekNumber = weekHandling.getWeekNumber();
		minusWeek = weekHandling.isMinusWeek();
		currentMinusWeek = weekHandling.getCurrentMinusWeek();

		result.add("<!--");
		result.add("************************");
		result.add(" " + "uge " + weekNumber + " " + maaned[month - 1]);
		result.add("************************");
		result.add("-->");

		dateLines = new String[numberOfDatelines];
		for (int i = 0; i < dateLines.length; i++) {
			if (i == 3) {
				dateLines[i] = "<tr><td>" + weekNumber + "</td>";
			} else {
				dateLines[i] = "<tr><td></td>";
			}
		}
	}

	/**
	 * Checks for a new month. If yes creates the headlines.
	 * 
	 * @param date
	 *            Current local date.
	 */
	private void checkForNextMonth(LocalDate date) {
		if (month != date.getMonthValue()) {
			nextMonth = true;
			month = date.getMonthValue();
		}
	}

	private void monthHeading(LocalDate date) {
		result.add("<!--=================================== " + date.getMonth() + " -->" + newLine);
		result.add("<tr><td id='" + date.getMonth().toString().toLowerCase() + date.getYear()
				+ "' class='basicColor'></td><td colspan='5' class='bigFont basicColor'>"
				+ "VBC Kalender &nbsp; &nbsp; &nbsp;<b>" + date.getYear() + "</b></td>"
				+ "<td colspan='2' class='bigFont basicColor'>" + maaned[date.getMonthValue() - 1] + "</td></tr>");
		result.add("<tr><td class='basicColor'></td>");
		for (int i = 0; i < ugenavne.length; i++) {
			result.add("<td class='basicColor'>" + ugenavne[i] + "</td>");
		}
		result.add("</tr>");
	}

	private void createHeaderHtmlTableStart(String idAndClass) {
		CommonLog.logger.info("class//");

		result.add("");
		result.add("<!-- - - - - - - - - - - Header table - - - - - - - - - - -->");
		result.add("<table border='0' " + idAndClass + ">");
		result.add("  <colgroup>");
		result.add("    <col width='10%' />");
		result.add("    <col width='12%' />");
		result.add("    <col width='12%' />");
		result.add("    <col width='12%' />");
		result.add("    <col width='12%' />");
		result.add("    <col width='12%' />");
		result.add("    <col width='12%' />");
		result.add("    <col width='12%' />");
		result.add("  </colgroup>");
		result.add("");
	}

	@SuppressWarnings({ "unused", "static-access" })
	private void test() {

		// An Easier way to print the timestamp by getting a Date instance
		Date date2 = cal.getTime();
		System.out.println("Current date and time in Date's toString() is : " + date2 + "\n");

		// Print Calendar's field
		System.out.println("Year  : " + cal.get(Calendar.YEAR));
		System.out.println("Month : " + cal.get(Calendar.MONTH));
		System.out.println("Day of Month : " + cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("Day of Week  : " + cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("Day of Year  : " + cal.get(Calendar.DAY_OF_YEAR));
		System.out.println("Week of Year : " + cal.get(Calendar.WEEK_OF_YEAR));
		System.out.println("Week of Month : " + cal.get(Calendar.WEEK_OF_MONTH));
		System.out.println("Day of the Week in Month : " + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println("Hour  : " + cal.get(Calendar.HOUR));
		System.out.println("AM PM : " + cal.get(Calendar.AM_PM));
		System.out.println("Hour of the Day : " + cal.get(Calendar.HOUR_OF_DAY));
		System.out.println("Minute : " + cal.get(Calendar.MINUTE));
		System.out.println("Second : " + cal.get(Calendar.SECOND));
		System.out.println();

		// cal.set
		int week = cal.get(Calendar.DAY_OF_WEEK);
		int week2 = cal.getWeekYear();
		int week3 = cal.DAY_OF_WEEK;
		int month2 = Calendar.DAY_OF_MONTH;

		int year = date.getYear(); // 2014
		int dayOfYear = date.getDayOfYear(); // 46
		boolean isLeapYear = date.isLeapYear(); // false

		DayOfWeek dayOfWeek = date.getDayOfWeek();
		int dayOfWeekIntValue = dayOfWeek.getValue(); // 6
		String dayOfWeekName = dayOfWeek.name(); // SATURDAY

		int dayOfMonth = date.getDayOfMonth(); // 15
	}
}
