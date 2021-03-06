package _start.config;

import static java.time.temporal.TemporalAdjusters.next;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import _start.kalender.PlayTime;
import _start.kalender.WeekHandling;
import common.LocalMethods;
import common.log.CommonLog;
import common.out.info.InfoDateConflict;

public class CreatePlayTimeDates {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class calculates dates for current club alias and save these in current
	 * 'PlayTime' object.
	 * 
	 * @param period
	 *            Current period.
	 * @param line
	 *            Current configuration line.
	 * @param skipEverySecond
	 *            Set when play time is set just every second time.
	 * @param playTime
	 */
	public CreatePlayTimeDates(String period, String line, boolean skipEverySecond, PlayTime playTime) {
		CommonLog.logger.info("heading//");

		// LocalDate date = null;
		ArrayList<LocalDate> datoer = new ArrayList<>();
		int dayOfWeek = -1;

		if (period.startsWith("+")) {
			dayOfWeek = addMandatoryDate(period, datoer);
		} else {
			// Split period according to minus.
			String[] splitString = period.split("-");
			// Check for date conflict.
			if (splitString.length != 2)
				dateConflictError(line);

			// Set first date of period.
			LocalDate date = LocalMethods.getLocalDate(splitString[0]);
			LocalDate slut = LocalMethods.getLocalDate(splitString[1]);
			dayOfWeek = getDatesForCurrentConfigLine(skipEverySecond, datoer, date, slut);
		}

		playTime.setDatoer(datoer);
		playTime.setDayOfWeek(dayOfWeek);
	}

	/**
	 * Save each relevant date in period of current configuration line to collection
	 * of dates.
	 * 
	 * @param skipEverySecond
	 *            Set when play time is set just every second time.
	 * @param datoer
	 *            Collection of dates relevant to alias text in current
	 *            configuration line.
	 * @param date
	 *            Current date of current period starting with first date of period
	 *            which also is deciding for the day of week for current period.
	 * @param slut
	 *            Last date of current period.
	 */
	private int getDatesForCurrentConfigLine(boolean skipEverySecond, ArrayList<LocalDate> datoer, LocalDate date,
			LocalDate slut) {
		int dayOfWeek;
		// Get the day of week for this period.
		dayOfWeek = date.getDayOfWeek().getValue();
		// If skip every second is on then start to accept the first date.
		boolean skipDayOn = true;

		// Go through dates of current period.
		while (date.isBefore(slut) || date.isEqual(slut)) {
			addDateToDateCollection(skipEverySecond, date, datoer, skipDayOn);
			// Get date of next week day in current period.
			LocalDate next = date.with(next(DayOfWeek.of(dayOfWeek)));
			date = next;
			skipDayOn = alternateSkipDayOn(skipDayOn);
		}
		return dayOfWeek;
	}

	/**
	 * If skip every second is set alternate skip day on.
	 * 
	 * @param skipDayOn
	 *            If true set to false and vice versa. Is true for first date to be
	 *            sure the first date is added to date collection.
	 */
	private boolean alternateSkipDayOn(boolean skipDayOn) {
		if (skipDayOn) {
			skipDayOn = false;
		} else {
			skipDayOn = true;
		}

		return skipDayOn;
	}

	/**
	 * Add current date to date collection depending on 'skip every second' and
	 * 'minus week'.
	 * 
	 * @param skipEverySecond
	 *            Set when play time is set just every second time.
	 * @param date
	 *            Current date of current period starting with first date of period
	 *            which also is deciding for the day of week for current period.
	 * @param datoer
	 *            Collection of dates relevant to alias text in current
	 *            configuration line.
	 * @param skipDayOn
	 *            If true set to false and vice versa. Is true for first date to be
	 *            sure the first date is added to date collection.
	 */
	private void addDateToDateCollection(boolean skipEverySecond, LocalDate date, ArrayList<LocalDate> datoer,
			boolean skipDayOn) {
		/*
		 * When skip every second is not set or if skip every second is set and and skip
		 * day on. Skip day on is alternate between on and off.
		 */
		if (!skipEverySecond || (skipEverySecond && skipDayOn)) {
			WeekHandling weekHandling = new WeekHandling(date);

			// If not minus week add date to date list.
			if (!weekHandling.isMinusWeek()) {
				datoer.add(date);
			}

			weekHandling = null;
		}
	}

	/**
	 * If not a period of dates between two dates inform user of a date conflict in
	 * current configuration line.
	 * 
	 * @param datoLinje
	 *            Current line expected to contain a period af dates.
	 */
	private void dateConflictError(String datoLinje) {
		new InfoDateConflict(datoLinje);
	}

	/**
	 * Returns day of week after adding a mandatory date to date list.
	 * 
	 * @param period
	 *            Current period.
	 * @param datoer
	 *            Current array list of dates.
	 */
	private int addMandatoryDate(String period, ArrayList<LocalDate> datoer) {
		LocalDate date;
		int dayOfWeek;

		date = LocalMethods.getLocalDate(period.substring(1));
		dayOfWeek = date.getDayOfWeek().getValue();
		datoer.add(date);

		return dayOfWeek;
	}
}
