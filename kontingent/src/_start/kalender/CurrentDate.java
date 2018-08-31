package _start.kalender;

import java.time.LocalDate;

import common.Data;
import common.log.CommonLog;

public class CurrentDate {

	private LocalDate date = null;

	public LocalDate getDate() {
		return date;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public CurrentDate(int dateNo, int firstYearlength, boolean lastDayOfFirstYear) {
		CommonLog.logger.info("heading//");

		int tempDayNo = -1;
		int year = -1;
		int test = dateNo - firstYearlength;
		if (test == 0) {
			tempDayNo = firstYearlength;
			year = Data.getKalenderStart().getYear();
			lastDayOfFirstYear = true;
		} else if (test < 0) {
			year = Data.getKalenderStart().getYear();
			tempDayNo = dateNo;
		} else if (test > 0) {
			tempDayNo = dateNo - firstYearlength;
			year = Data.getKalenderSlut().getYear();
		}

		date = LocalDate.ofYearDay(year, tempDayNo);
	}
}
