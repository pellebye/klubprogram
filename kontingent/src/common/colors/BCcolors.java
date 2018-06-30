package common.colors;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public enum BCcolors {

	// Blue
	Blue(10), BlueWeak(11), BlueWeakExtra(12), BlueWatch(13), BluePurple(14), BluePurpleLight(15),

	// Green
	Green(20), GreenMarker(21), GreenWeak(22), GreenWeakExtra(23), GreenWatch(24),

	// Red
	Red(30), RedComposite(31), RedIndicatorlabel(32), RedWeak(33), RedWeakExtra(34), RedZone(35), RedWatch(36), RedPink(
			37),

	// White
	White(40), Grey(41), GreyDark(42),

	// Yellow
	Yellow(50), YellowLight(51), YellowMarker(52),

	;

	/**
	 * The value which is passed to the constructor from the enum constants.
	 */
	@SuppressWarnings("unused")
	private int value;

	private BCcolors(int value) {
		this.value = value;
	}

	/**
	 * Returns a color corresponding to the enum parameter integer value. If no
	 * valid value is found then returns white color.
	 *
	 * @param value
	 *            'BCcolor' value (e.g. BCcolor.Red)
	 */
	public static Color getColor(BCcolors value) {

		// --------------------------------------------------------------------------------------------------
		// Blue
		if (value == BCcolors.Blue)
			return new Color(Display.getCurrent(), 0, 0, 255); // Blue.
		else if (value == BCcolors.BlueWeak)
			return new Color(Display.getCurrent(), 128, 128, 255); // BlueWeeak.
		else if (value == BCcolors.BlueWeakExtra)
			return new Color(Display.getCurrent(), 210, 210, 255); // BlueWeeakExtra.
		else if (value == BCcolors.BlueWatch)
			return new Color(Display.getCurrent(), 153, 235, 255); // BlueWatch.
		else if (value == BCcolors.BluePurple)
			return new Color(Display.getCurrent(), 204, 0, 255); // BluePurple.
		else if (value == BCcolors.BluePurpleLight)
			return new Color(Display.getCurrent(), 245, 205, 255); // BluePurpleLight.

		// --------------------------------------------------------------------------------------------------
		// Green
		else if (value == BCcolors.Green)
			return new Color(Display.getCurrent(), 0, 255, 0); // Green.
		else if (value == BCcolors.GreenMarker)
			return new Color(Display.getCurrent(), 128, 255, 128); // GreenMarker.
		else if (value == BCcolors.GreenWeak)
			return new Color(Display.getCurrent(), 128, 255, 128); // GreenWeak.
		else if (value == BCcolors.GreenWeakExtra)
			return new Color(Display.getCurrent(), 200, 255, 200); // GreenWeakExtra.
		else if (value == BCcolors.GreenWatch)
			return new Color(Display.getCurrent(), 153, 255, 153); // GreenWatch.

		// --------------------------------------------------------------------------------------------------
		// Red
		else if (value == BCcolors.Red)
			return new Color(Display.getCurrent(), 255, 0, 0); // Red.
		else if (value == BCcolors.RedComposite)
			return new Color(Display.getCurrent(), 252, 221, 208); // RedComposite.
		else if (value == BCcolors.RedIndicatorlabel)
			return new Color(Display.getCurrent(), 255, 128, 128); // RedPlaylabel.
		else if (value == BCcolors.RedWeak)
			return new Color(Display.getCurrent(), 255, 128, 128); // RedWeak.
		else if (value == BCcolors.RedWeakExtra)
			return new Color(Display.getCurrent(), 255, 210, 210); // RedWeakExtra.
		else if (value == BCcolors.RedZone)
			return new Color(Display.getCurrent(), 255, 210, 210); // RedZone.
		else if (value == BCcolors.RedWatch)
			return new Color(Display.getCurrent(), 255, 153, 184); // RedWatch.
		else if (value == BCcolors.RedPink)
			return new Color(Display.getCurrent(), 255, 154, 235); // RedPink.

		// --------------------------------------------------------------------------------------------------
		// White
		else if (value == BCcolors.White)
			return new Color(Display.getCurrent(), 255, 255, 255); // White.
		else if (value == BCcolors.Grey)
			return new Color(Display.getCurrent(), 240, 240, 240); // Grey.
		else if (value == BCcolors.GreyDark)
			return new Color(Display.getCurrent(), 228, 228, 228); // GreyDark.

		// --------------------------------------------------------------------------------------------------
		// Yellow
		else if (value == BCcolors.Yellow)
			return new Color(Display.getCurrent(), 255, 255, 0); // Yellow.
		else if (value == BCcolors.YellowLight)
			return new Color(Display.getCurrent(), 255, 254, 222); // YellowLight.
		else if (value == BCcolors.YellowMarker)
			return new Color(Display.getCurrent(), 255, 255, 0); // YellowMarker.

		else
			return new Color(Display.getCurrent(), 255, 255, 255); // White.
	}

	/**
	 * Returns background color for change seats.
	 */
	public static Color getChangeSeatsColor() {
		return BCcolors.getColor(BCcolors.YellowLight);
	}

	/**
	 * Returns background color for double substitutes.
	 */
	public static Color getDoubleSubstitutesColor() {
		return BCcolors.getColor(BCcolors.RedWeakExtra);
	}

	/**
	 * Returns default background color for tournament table.
	 */
	public static Color getTableDefaultColor() {
		return BCcolors.getColor(BCcolors.White);
	}

	/**
	 * Returns a color in html format (e.g. '#FF0000') converted from a color
	 * type 'Color' (e.g. 'BCcolor.Red').
	 *
	 * @param color
	 *            The passed color to be converted.
	 */
	public static String methodConvertRgbToHtml(Color color) {
		String s = "#";
		if (color.getRed() < 16)
			s += "0";
		s += Integer.toHexString(color.getRed());
		if (color.getGreen() < 16)
			s += "0";
		s += Integer.toHexString(color.getGreen());
		if (color.getBlue() < 16)
			s += "0";
		s += Integer.toHexString(color.getBlue());

		return s;
	}

	/**
	 * Returns color generated from html color string, fx Color red from
	 * '#FF0000'.
	 *
	 * @param htmlColor
	 *            Color string fx '#FF0000'.
	 */
	public static Color convertHtmlToColor(String htmlColor) {
		int red = 0;
		int green = 0;
		int blue = 0;

		if (htmlColor.length() == 4) { // E.g '#FFF'
			// Red.
			red = Integer.parseInt(htmlColor.substring(1, 2), 16);
			red = red * red;
			// Green.
			green = Integer.parseInt(htmlColor.substring(2, 3), 16);
			green = green * green;
			// Blue.
			blue = Integer.parseInt(htmlColor.substring(3), 16);
			blue = blue * blue;
		} else if (htmlColor.length() == 7) { // E.g. '#FFFFFF'
			red = Integer.parseInt(htmlColor.substring(1, 3), 16);
			green = Integer.parseInt(htmlColor.substring(3, 5), 16);
			blue = Integer.parseInt(htmlColor.substring(5), 16);
		}

		return new Color(Display.getCurrent(), red, green, blue);
	}

	public static Color validateColor(String colorString) {
		ColorValidator colorValidator = new ColorValidator();
		colorValidator.validate(colorString);
		String htmlColorString = colorValidator.getColorString();

		return convertHtmlToColor(htmlColorString);
	}
}
