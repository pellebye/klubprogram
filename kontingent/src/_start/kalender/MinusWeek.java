package _start.kalender;

import common.log.CommonLog;
import common.out.info.InfoWeekConflict;

public class MinusWeek {

	private int year = 0;

	public int getYear() {
		return year;
	}

	private int week = 0;

	public int getWeek() {
		return week;
	}

	private String[] minusWeekText = null;

	public String[] getMinusWeekText() {
		return minusWeekText;
	}

	private boolean active = false;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public MinusWeek(int year, int week, String text, String line) {
		CommonLog.logger.info("heading//");

		if (year == -1 || week == -1)
			new InfoWeekConflict(line);

		this.year = year;
		this.week = week;

		// Split week text into words.
		minusWeekText = text.trim().split(" ");

		// Check for any text.
		if (minusWeekText.length == 0 || minusWeekText == null)
			new InfoWeekConflict(line);
	}
}
