package common.out.file;

import java.util.ArrayList;

import common.log.CommonLog;

public class ReplaceAsciiAndISO8859 {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public ReplaceAsciiAndISO8859(ArrayList<String> lines) {
		CommonLog.logger.info("heading//");

		String line = "";
		for (int i = 0; i < lines.size(); i++) {

			// Replace ISO 8859-1 or windows 1252 characters interpreted by
			// utf-8.
			line = lines.get(i);
			line = line.replaceAll("Ã¦", "æ");
			line = line.replaceAll("Ã", "Æ");
			line = line.replaceAll("Ã¸", "ø");
			line = line.replaceAll("Ã", "Ø");
			line = line.replaceAll("Ã¥", "å");
			line = line.replaceAll("Ã…", "Å");

			// Replace non-ascii characters
			line = line.replaceAll("xE6", "æ");
			line = line.replaceAll("xC6", "Æ");
			line = line.replaceAll("xF8", "ø");
			line = line.replaceAll("xD8", "Ø");
			line = line.replaceAll("xE5", "å");
			line = line.replaceAll("xC5", "Å");
			lines.set(i, line);
		}
	}
}
