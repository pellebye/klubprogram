package _start.kalender;

import java.time.LocalDate;
import java.util.ArrayList;

import common.Data;
import common.LocalMethods;
import common.log.CommonLog;

public class WeekHandling {

	private int weekNumber = -1;

	public int getWeekNumber() {
		return weekNumber;
	}

	private boolean minusWeek = false;

	public boolean isMinusWeek() {
		return minusWeek;
	}

	MinusWeek currentMinusWeek = null;

	public MinusWeek getCurrentMinusWeek() {
		return currentMinusWeek;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class sets 'minusWeek' true if date is in a week which is marked as
	 * such. At the same time the current 'MinusWeekList' of the collection is set
	 * to active.
	 * 
	 * @param date
	 *            Current calendar date.
	 */
	public WeekHandling(LocalDate date) {
		CommonLog.logger.info("heading//");

		weekNumber = LocalMethods.getWeekNumber(date);
		int year = date.getYear();

		ArrayList<MinusWeek> weekList = Data.getMinusWeekList();

		for (int i = 0; i < weekList.size(); i++) {
			MinusWeek tempMinusWeek = weekList.get(i);
			if (tempMinusWeek.getWeek() == weekNumber) {
				if (tempMinusWeek.getYear() == year) {
					minusWeek = true;
					tempMinusWeek.setActive(true);
					currentMinusWeek = tempMinusWeek;
				}
			} else
				tempMinusWeek.setActive(false);
		}
		Data.setMinusWeekList(weekList);
	}
}
