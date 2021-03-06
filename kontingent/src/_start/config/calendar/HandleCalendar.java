package _start.config.calendar;

import java.time.LocalDate;
import java.util.ArrayList;

import _start.config.CreatePlayTimeDates;
import _start.kalender.PlayTime;
import common.Data;
import common.LocalMethods;
import common.log.CommonLog;
import common.out.info.InfoCommentError;

public class HandleCalendar {

	/**
	 * Set when play time is set just every second time.
	 */
	private boolean skipEverySecond = false;

	private PlayTime playTime = null;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class handle one calender line in configuration file by creating a play
	 * time and add a variable number of options like:
	 * 
	 * <ul>
	 * <li>Add mandatory dates.</li>
	 * <li>Remove dates in exception list.</li>
	 * <li>Add start of play time.</li>
	 * <li>Add color of play time in Calendar.</li>
	 * <li>Add information to be changed for each date (double colons).</li>
	 * <ul>
	 * 
	 * @param line
	 *            Current line from configuration file.
	 * @param playTimeCollection
	 *            Collection of play times.
	 */
	public HandleCalendar(String line) {
		CommonLog.logger.info("heading//");

		ArrayList<PlayTime> playTimeCollection = Data.getPlayTimeCollection();

		if (line.startsWith("periode")) {
			// kalenderPeriod(line);
			new HandleCalendarPeriod(line);
		} else {
			/*
			 * Split lines according to commas.
			 */
			String[] lineSplit = LocalMethods.trimStringArray(line.split(","));
			int aliasEndIndex = createPlayTime(lineSplit);
			String period = lineSplit[0].substring(aliasEndIndex);

			new CreatePlayTimeDates(period, line, skipEverySecond, playTime);
			// Reset skip every second.
			skipEverySecond = false;

			addPlayTimeOptions(lineSplit);
			// ArrayList<LocalDate> localDates = this.playTime.getDatoer();
			ArrayList<LocalDate> localDates = playTime.getDatoer();
			playTime.setDateFirst(localDates.get(0));
			playTime.setDateLast(localDates.get(localDates.size() - 1));
			playTimeCollection.add(playTime);
		}
		playTimeCollection.trimToSize();
		Data.setPlayTimeCollection(playTimeCollection);
	}

	/**
	 * Adds options to play time date:
	 * <ul>
	 * <li>Add mandatory dates.</li>
	 * <li>Remove dates in exception list.</li>
	 * <li>Add start of play time.</li>
	 * <li>Add color of play time in Calendar.</li>
	 * <li>Add information to be changed for each date (double colons).</li>
	 * <ul>
	 * 
	 * @param lineSplit
	 *            Current configuration line split by commas.
	 */
	private void addPlayTimeOptions(String[] lineSplit) {
		/*
		 * Go through rest of current configuration line.
		 */
		for (int splitNo = 1; splitNo < lineSplit.length; splitNo++) {
			if (lineSplit[splitNo].startsWith("-")) {
				deleteExceptionDates(lineSplit[splitNo], playTime);
			} else if (lineSplit[splitNo].startsWith("kl")) {
				playTime.setStartTime(lineSplit[splitNo]);
			} else if (lineSplit[splitNo].startsWith("farve")) {
				playTime.setShowColor(lineSplit[splitNo].substring(6));
			} else if (lineSplit[splitNo].startsWith("+")) {
				playTime.getDatoer().add(LocalMethods.getLocalDate(lineSplit[splitNo].substring(1)));
			} else if (lineSplit[splitNo].startsWith("::")) {
				handleDoubleColons(lineSplit[splitNo]);
			}
		}
	}

	/**
	 * Handles double colons e.g. ":: gg1 gg2 gg3 ::"
	 * 
	 * @param lineSplit
	 *            Current line split number.
	 */
	private void handleDoubleColons(String lineSplit) {
		String doubleColonText = LocalMethods.removeDoubleSpaces(lineSplit);
		int numberOfMatches = LocalMethods.getNumberOfMatches(doubleColonText, "::");
		if (numberOfMatches == 2) {
			// get everything between two times of '::'.
			int index = doubleColonText.lastIndexOf("::");
			doubleColonText = doubleColonText.substring(2, index - 1).trim();
			// split up the comments.
			String[] split = doubleColonText.split(" ");
			// save in play time.
			playTime.setShiftArray(split);
			playTime.setArrayIndex(0);
		} else {
			new InfoCommentError(lineSplit);
		}
	}

	/**
	 * Delete dates beginning with a minus.
	 * 
	 * @param splitNo
	 *            Current number of split string.
	 * @param playTime
	 *            Current play time.
	 */
	private void deleteExceptionDates(String string, PlayTime playTime) {
		LocalDate minus = LocalMethods.getLocalDate(string.substring(1));
		ArrayList<LocalDate> tempDatoer = playTime.getDatoer();
		for (int j = 0; j < tempDatoer.size(); j++) {
			if (tempDatoer.get(j).isEqual(minus)) {
				tempDatoer.remove(j);
			}
		}
		playTime.setDatoer(tempDatoer);
	}

	/**
	 * Returns index where alias end and create object 'PlayTime' with alias text as
	 * name.
	 * 
	 * @param lineSplit
	 *            Current configuration line split up by commas.
	 */
	private int createPlayTime(String[] lineSplit) {

		/*
		 * Set index to where alias text end and first digit begin.
		 */
		int aliasEndIndex = LocalMethods.getFirstDigit(lineSplit[0]);
		if (lineSplit[0].contains("+")) {
			/*
			 * if '+' adjust index to let first digit contain the plus sign.
			 */
			aliasEndIndex = lineSplit[0].indexOf("+");
		} else if (lineSplit[0].contains("(")) {
			/*
			 * if '(' adjust index to the digit inside parentheses and set skip every second
			 * to true.
			 */
			aliasEndIndex = lineSplit[0].indexOf("(");
			skipEverySecond = true;
		}
		playTime = new PlayTime(lineSplit[0].substring(0, aliasEndIndex));

		if (skipEverySecond) {
			/*
			 * Adjust index to first digit ahead of parentheses.
			 */
			aliasEndIndex += 3;
		}

		return aliasEndIndex;
	}
}
