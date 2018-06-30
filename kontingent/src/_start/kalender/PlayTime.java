package _start.kalender;

import java.time.LocalDate;
import java.util.ArrayList;

import common.log.CommonLog;

public class PlayTime {

	/**
	 * Dates to play
	 */
	private ArrayList<LocalDate> datoer = null;

	public ArrayList<LocalDate> getDatoer() {
		return datoer;
	}

	public void setDatoer(ArrayList<LocalDate> datoer) {
		datoer.trimToSize();
		this.datoer = datoer;
	}

	private String name = "";

	public String getName() {
		return name;
	}

	private String startTime = "";

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	private String showColor = "";

	public String getShowColor() {
		return showColor;
	}

	public void setShowColor(String showColor) {
		this.showColor = showColor;
	}

	private int dayOfWeek = -1;

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int value) {
		dayOfWeek = value;
	}

	private LocalDate dateFirst = null;

	public LocalDate getDateFirst() {
		return dateFirst;
	}

	public void setDateFirst(LocalDate dateFirst) {
		this.dateFirst = dateFirst;
	}

	private LocalDate dateLast = null;

	public LocalDate getDateLast() {
		return dateLast;
	}

	public void setDateLast(LocalDate dateLast) {
		this.dateLast = dateLast;
	}

	private int number = 0;

	public void nextNummber() {
		if (shiftArray == null) {
			number++;
			comment = " (" + String.valueOf(number) + ") ";
		} else {
			number++;
			comment = " (" + String.valueOf(number) + ") ";
			comment += shiftArray[arrayIndex];
			arrayIndex++;
			if (arrayIndex > shiftArray.length - 1) {
				arrayIndex = 0;
			}
		}
	}

	private String[] shiftArray = null;

	public void setShiftArray(String[] shiftArray) {
		this.shiftArray = shiftArray;
	}

	private int arrayIndex = -1;

	public void setArrayIndex(int arrayIndex) {
		this.arrayIndex = arrayIndex;
	}

	private String comment = "";

	public String getComment() {
		return comment;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public PlayTime(String name) {
		CommonLog.logger.info("heading//");

		this.name = name.trim();
		// nextNummber();
		// number = -1;
	}

}
