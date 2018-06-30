package _start.telephoneList;

import java.util.ArrayList;

import common.Data;
import common.log.CommonLog;
import common.out.print.CheckWhiteColor;
import common.out.print.PrintStringFile;

public class PhoneCSSfile {

	private ArrayList<String> result = new ArrayList<>();

	public ArrayList<String> getResult() {
		return result;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public PhoneCSSfile(String filename) {
		CommonLog.logger.info("heading//");

		ArrayList<String> colors = Data.getPhoneColumnColors();

		String[] headings = Data.getPhoneColumnNames();
		;

		for (int i = 0; i < colors.size(); i++) {
			String color = colors.get(i);

			if (getColor(i).compareTo("") != 0) {
				String heading = headings[i];
				result.add("." + heading + "{");
				result.add("    background-color:" + color + ";");
				result.add("}");
			} else {
				// Error! not expected. Empty colors should contain "#None"
			}
		}

		// Highlight
		result.add(".highlight{");
		result.add("    background-color: #D8D7D4;");
		result.add("}");

		// Chosen
		result.add(".chosen{");
		result.add("    background-color: #EFEFEF;");
		result.add("}");

		// Thin
		result.add(".thin{");
		result.add("    background-color: #000;");
		result.add("    height: 2px;");
		result.add("}");

		// Red
		result.add(".red{");
		result.add("    background-color: #F00;");
		result.add("}");

		new PrintStringFile(result.toArray(new String[0]), "resultater/telefonliste", "tlf-list.css");
	}

	private String getColor(int index) {
		CheckWhiteColor whiteColor = new CheckWhiteColor(index);
		String result = whiteColor.getColumnColor();
		whiteColor = null;

		return result;
	}
}
