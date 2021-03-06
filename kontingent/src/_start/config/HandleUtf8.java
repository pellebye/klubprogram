package _start.config;

import java.util.ArrayList;

import common.log.CommonLog;
import common.out.file.ReplaceAsciiAndISO8859;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.TypeOfTest;
import common.out.print.ModeTextLines;
import common.out.print.ReadTxtFile;
import common.out.print.Textlines;

public class HandleUtf8 {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public HandleUtf8(String line) {
		CommonLog.logger.info("heading//");

		String filename = line;
		Textlines textlines = new Textlines();

		ReadTxtFile readTxtFile = new ReadTxtFile(filename, ModeTextLines.WITHOUTCOMMENTS, textlines,
				TypeOfTest.FILE_NOCOPY, ModeCheckDetail.UNDEFINED);
		ArrayList<String> lines = readTxtFile.getTextlinesReduced();

		new ReplaceAsciiAndISO8859(lines);
	}
}
