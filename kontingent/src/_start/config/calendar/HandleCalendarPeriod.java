package _start.config.calendar;

import java.time.LocalDate;

import _start.kalender.MinusWeek;
import common.Data;
import common.LocalMethods;
import common.log.CommonLog;
import common.out.info.InfoWeekConflict;

public class HandleCalendarPeriod {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class handle calendar period.
	 * 
	 * @param line
	 *            Current configuration line.
	 */
	public HandleCalendarPeriod(String line) {
		CommonLog.logger.info("heading//");

		String sub = line.substring(8);
		/*
		 * Check if the period string has a minus (e.g. "20180601-20190901" or
		 * "periode -uge 2018 7").
		 */
		String[] periodSplit = sub.split("-");
		if (periodSplit.length == 2) {
			/*
			 * remove unexpected spaces before 'uge' (e.g. if "periode - uge 2018 7").
			 */
			String trimmed = periodSplit[1].trim();
			if (trimmed.startsWith("uge")) {
				String temp = trimmed.substring(4).trim();
				/*
				 * Check if text is added to -uge (e.g. "uge 2018 7, Ingen bridge i denne uge").
				 */
				String[] ugeSplit = temp.split(" ");
				if (ugeSplit.length == 2) {
					// No text.
					createMinusWeekList(ugeSplit, "", line);
				} else {
					// Possible week text.
					if (ugeSplit[1].contains(",")) {
						String[] textTemp = temp.split(",");
						if (textTemp.length == 2) {
							// Split year and week.
							ugeSplit = textTemp[0].split(" ");
							if (ugeSplit.length == 2) {
								createMinusWeekList(ugeSplit, textTemp[1], line);
							} else
								new InfoWeekConflict(line);
						} else
							new InfoWeekConflict(line);
					} else
						new InfoWeekConflict(line);
				}
			} else {
				// get configuration start date of period.
				LocalDate start = LocalMethods.getLocalDate(periodSplit[0]);
				int startWeekDay = start.getDayOfWeek().getValue();
				int startMonth = start.getMonthValue();
				if (startWeekDay > 1 && startMonth > 1) {
					/*
					 * First day of calender period has to be a monday. So if this is not the case
					 * let start month be the previous month except for January.
					 */
					startMonth = startMonth - 1;

				}
				// get first day of month.
				start = getLocalDate(start.getYear(), start.getMonthValue());
				Data.setKalenderStart(start);
				// get configuration end date of period.
				LocalDate ends = LocalMethods.getLocalDate(periodSplit[1]);
				// get first day of next month.
				ends = getLocalDate(ends.getYear(), ends.plusMonths(1).getMonthValue());
				Data.setKalenderSlut(ends);
			}
		}
	}

	private void createMinusWeekList(String[] ugeSplit, String text, String line) {
		int year = -1;
		int week = -1;

		if (LocalMethods.isParsableToInt(ugeSplit[0]))
			year = Integer.parseInt(ugeSplit[0]);
		if (LocalMethods.isParsableToInt(ugeSplit[1]))
			week = Integer.parseInt(ugeSplit[1]);

		MinusWeek weekList = new MinusWeek(year, week, text.trim(), line);
		Data.addMinusWeekList(weekList);
		weekList = null;
	}

	private LocalDate getLocalDate(int y, int m) {
		int d = 1;
		return LocalDate.of(y, m, d);
	}
}
