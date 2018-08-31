package common.out.print;

import java.util.ArrayList;

import common.Data;
import common.log.CommonLog;

public class CheckWhiteColor {

	ArrayList<String> columnColors = Data.getPhoneColumnColors();

	/**
	 * Default column color is empty (result in white color).
	 */
	String columnColor = "#FFF";

	public String getColumnColor() {
		return columnColor;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class equal column color as index color of column colors except for
	 * white color
	 * 
	 * @param index
	 *            Column index.
	 */
	public CheckWhiteColor(int index) {
		CommonLog.logger.info("heading//");

		String color = columnColors.get(index);

		if (color.compareTo("#FFF") != 0 && color.compareTo("#FFFFFF") != 0) {
			// No white color.
			columnColor = color;
		}
	}
}
