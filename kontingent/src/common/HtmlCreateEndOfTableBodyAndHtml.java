package common;

import java.util.ArrayList;

public class HtmlCreateEndOfTableBodyAndHtml {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class add html information necessary to end html page.
	 *
	 * @param out
	 *            Current print writer
	 * @param inclTbody
	 *            True current table has 'tbody' elements.
	 * @param inclW3Cvalidator
	 *            True for including W3C validation icons.
	 * @param lastLine
	 *            True for creating a last '
	 *            <tr>
	 *            '-line in table.
	 */
	public HtmlCreateEndOfTableBodyAndHtml(ArrayList<String> result, Boolean inclTbody, Boolean inclW3Cvalidator,
			Boolean lastLine) {
		common.log.CommonLog.logger.info("class//");

		// result.add("<table>"); // Added 20140518 with reference to
		// 'TodayHtml'.

		// End with one empty row.
		if (inclTbody)
			result.add("<tbody>");
		if (lastLine) {
			result.add("<tr>");
			result.add("<td>&nbsp;</td>");
			result.add("<td>&nbsp;</td>");
			result.add("<td>&nbsp;</td>");
			result.add("<td>&nbsp;</td>");
			result.add("<td>&nbsp;</td>");
			result.add("<td>&nbsp;</td>");
			result.add("<td>&nbsp;</td>");
			result.add("</tr>");
		}
		if (inclTbody)
			result.add("</tbody>");
		result.add("</table>");

		if (inclW3Cvalidator) {
			result.add("<div>");
			result.add("  <!-- XHTML validation ok -->");
			result.add("  <p>");
			result.add("    <a href=\"http://validator.w3.org/check?uri=referer\">");
			result.add("    <img src=\"http://www.w3.org/Icons/valid-xhtml10\"");
			result.add("alt=\"Valid XHTML 1.0 Transitional\" height=\"31\" width=\"88\" /></a>");
			result.add("  </p>");
			result.add("  <!-- CSS validation ok -->");
			result.add("  <p>");
			result.add("    <a href=\"http://jigsaw.w3.org/css-validator/check/referer\">");
			result.add("    <img style=\"border:0;width:88px;height:31px\"");
			result.add("      src=\"http://jigsaw.w3.org/css-validator/images/vcss\"");
			result.add("      alt=\"Valid CSS!\" />");
			result.add("</a>");
			result.add("</p>");
			result.add("</div>");
		}
		result.add("");
		result.add("<!--======================================-->");
		result.add("<!-- SLUT PÅ HTML OUTPUT FRA BRIDGECHARTS -->");
		result.add("<!--======================================-->");
		result.add("");
		result.add("</body>");
		result.add("</html>");

	}
}
