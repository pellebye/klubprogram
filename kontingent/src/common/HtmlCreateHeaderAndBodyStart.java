package common;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class HtmlCreateHeaderAndBodyStart {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * Create html header.
	 *
	 * @param title
	 *            Title on browser tab.
	 * @param result
	 *            Html lines.
	 * @param revisedDate
	 *            Html lines creation date
	 * @param description
	 *            List of description for search machines.
	 * @param cssFilename
	 *            File name of css file ("none" for no css file).
	 * @param extraLine
	 *            Possible extra line(s) just before end of html element '<
	 *            /head >'.
	 */
	public HtmlCreateHeaderAndBodyStart(String title, ArrayList<String> result, String revisedDate, String description,
			String cssFilename, String extraLine) {
		common.log.CommonLog.logger.info("class//");

		// Document type definition
		result.add("<!DOCTYPE html " + "PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' "
				+ "'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
		result.add("<html xmlns='http://www.w3.org/1999/xhtml' dir='ltr' lang='da-DK'>");

		result.add("<head>");
		result.add("  <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
		result.add("  <title>" + title + "</title>"); // Title on browser tab.
		result.add("  <meta name='Author' content='Preben Ellebye' />");

		if (revisedDate.length() == 0) {
			LocalDate date = LocalDate.now();
			revisedDate = String.valueOf(date.getDayOfMonth());
			revisedDate += ". " + Month.values()[date.getMonthValue() - 1] + " " + date.getYear();
		}
		result.add("  <meta name='revised' content='" + revisedDate + "' />");
		result.add("  <meta name='description' content='" + description + "' />");
		result.add("  <meta http-equiv='pragma' content='no-cache' />");
		result.add("  <meta http-equiv='cache-control' content='no-cache' />");

		if (cssFilename.compareTo("none") != 0) {
			result.add("  <link rel='stylesheet' type='text/css' href='" + cssFilename + "' />");
		}

		result.add(extraLine);

		result.add("</head>");
		result.add(""); // Space

		result.add("<body onload='jump()'>");
		result.add("");
		result.add("<!--=====================================-->");
		result.add("<!--          START HTML OUTPUT  -->");
		result.add("<!--=====================================-->");
		result.add("");
	}

}
