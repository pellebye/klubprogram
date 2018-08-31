package _start;

import java.util.ArrayList;
import java.util.Hashtable;

import common.Data;
import common.LocalMethods;
import common.log.CommonLog;
import common.out.file.ReplaceAsciiAndISO8859;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.TypeOfTest;
import common.out.info.InfoClublinesEmptyInClubFile;
import common.out.print.ModeTextLines;
import common.out.print.PrintStringFile;
import common.out.print.ReadTxtFileOfAscii;
import common.out.print.Textlines;

public class ConvertToUtf8 {

	private int clubCount = 0;

	private String[] clubNames = null;

	private String aliasFilename = "";

	public String getAliasFilename() {
		return aliasFilename;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class read files with original file names, replace characters from
	 * some encodings, and Finally write using encoding UTF-8 to files with
	 * alias file names.
	 */
	public ConvertToUtf8() {
		CommonLog.logger.info("heading//");

		clubNames = Data.getOriginals();
		Hashtable<Integer, String> aliasser = Data.getAliasser();

		for (clubCount = 0; clubCount < clubNames.length; clubCount++) {

			// Read file to be converted.
			String filename = clubNames[clubCount];
			Textlines textlines = new Textlines();
			String path = LocalMethods.getUserDirectory() + "\\bridgecentral\\" + filename + ".csv";
			ArrayList<String> clubLines = readOneKlubLines(textlines, path, aliasser.get(clubCount));

			// Report an error if club lines are empty.
			if (clubLines.size() == 0)
				new InfoClublinesEmptyInClubFile(path);

			new ReplaceAsciiAndISO8859(clubLines);

			// Write converted file in utf-8 to alias filename.
			path = "bridgecentral";
			filename = aliasFilename;
			String[] printlist = clubLines.toArray(new String[0]);
			new PrintStringFile(printlist, path, filename);
		}
	}

	/**
	 * Returns lines from one club.
	 * 
	 * @param textlines
	 *            An array list of strings.
	 * @param path
	 *            Absolute path of current club file.
	 * @param alias
	 */
	private ArrayList<String> readOneKlubLines(Textlines textlines, String path, String alias) {
		ReadTxtFileOfAscii fileOfAscii = new ReadTxtFileOfAscii(path, ModeTextLines.ALLLINES, textlines,
				TypeOfTest.FILE_NOCOPY, ModeCheckDetail.PATHACTUAL, alias);
		ArrayList<String> lines = fileOfAscii.getTextlinesReduced();

		aliasFilename = fileOfAscii.getAliasFilename();
		fileOfAscii = null;

		return lines;
	}
}
