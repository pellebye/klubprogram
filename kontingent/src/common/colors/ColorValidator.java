package common.colors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorValidator {

	private Pattern pattern;
	private Matcher matcher;
	private String colorString = "FFF";

	/**
	 * Returns a string representing the validated color. E.g. Both "#FF0" and
	 * "yellow" returns "#FF0".
	 */
	public String getColorString() {
		return colorString;
	}

	private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public ColorValidator() {
		pattern = Pattern.compile(HEX_PATTERN);
	}

	/**
	 * Return true if string value validates as a color. String parameter can be
	 * named colors (blue, red, yellow, green, light blue, pink), or can start
	 * with '#' with following hex digits with exactly 6 or 3 length..
	 *
	 * @param colorString
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(String colorString) {

		colorString = checkForNamedColor(colorString);

		this.colorString = colorString;

		matcher = pattern.matcher(colorString);

		return matcher.matches();
	}

	private String checkForNamedColor(String colorString) {

		String namedColor = colorString.toLowerCase();
		switch (namedColor) {
		case "blue":
			colorString = "#F00";
			break;
		case "lightblue":
			colorString = "#99EBFF";
			break;
		case "red":
			colorString = "#FF99B8";
			break;
		case "yellow":
			colorString = "#FF0";
			break;
		case "green":
			colorString = "#99FFF3";
			break;
		case "pink":
			colorString = "#FF9AEB";
			break;

		default:
			break;
		}
		return colorString;
	}
}
