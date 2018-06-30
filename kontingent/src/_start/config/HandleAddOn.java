package _start.config;

import java.util.ArrayList;

import common.log.CommonLog;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.TypeOfTest;
import common.out.print.ModeTextLines;
import common.out.print.PrintStringFile;
import common.out.print.ReadTxtFile;
import common.out.print.Textlines;

public class HandleAddOn {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public HandleAddOn(String line) {
		CommonLog.logger.info("heading//");

		String[] lineSplit = line.split(" ");

		if (lineSplit.length != 3) {
			// Message an error
			System.exit(0);
		}

		String filename = lineSplit[2];

		Textlines textlines = new Textlines();

		ReadTxtFile readTxtFile = new ReadTxtFile(filename, ModeTextLines.WITHOUTCOMMENTS, textlines,
				TypeOfTest.FILE_NOCOPY, ModeCheckDetail.UNDEFINED);
		ArrayList<String> lines = readTxtFile.getTextlinesReduced();

		String before = lineSplit[0];
		String after = lineSplit[1];

		for (int i = 0; i < lines.size(); i++) {
			String tempLine = lines.get(i);
			tempLine = before + tempLine + after;
			lines.set(i, tempLine);
		}

		String[] splitString = filename.split("\\.");

		if (splitString.length != 2) {
			// Message an error
			System.exit(0);
		}

		filename = splitString[0] + "_behandlet." + splitString[1];

		String[] printlist = lines.toArray(new String[0]);

		new PrintStringFile(printlist, "", filename);
	}
}
