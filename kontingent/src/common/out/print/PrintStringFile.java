package common.out.print;

import java.io.PrintWriter;

import common.out.SetupPrintwriter;

public class PrintStringFile {

	private PrintWriter out;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class prints out a file with UTF-8 encoding containing lines in
	 * 'printlist'
	 * 
	 * @param printlist
	 *            String array containing lines to be printed out.
	 * @param path
	 *            Path without user directory and with no slash in the end.
	 * @param filename
	 *            Pure filename.
	 */
	public PrintStringFile(String[] printlist, String path, String filename) {
		common.log.CommonLog.logger.info("heading//");

		// Setup file writer
		SetupPrintwriter printwriter = new SetupPrintwriter(filename, path);
		out = printwriter.getOut();
		printwriter = null;

		for (int printlistCounter = 0; printlistCounter < printlist.length; printlistCounter++) {
			out.println(printlist[printlistCounter]);
		}
		out.close();
	}

}
