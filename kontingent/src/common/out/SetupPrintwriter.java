package common.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import common.LocalMethods;
import common.log.BBexcLog;
import common.log.CommonLog;

public class SetupPrintwriter {

	private PrintWriter out;

	public PrintWriter getOut() {
		return out;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class create a print writer where file has UTF-8 encoding.
	 * 
	 * @param filename
	 * @param path
	 */
	public SetupPrintwriter(String filename, String path) {
		CommonLog.logger.info("heading//");

		new File(path).mkdir();
		String filenameForFile = LocalMethods.getUserDirectory() + "/" + path + "/" + filename;

		filenameForFile = LocalMethods.getFilenameWithForwardSlashes(filenameForFile);

		// Create a file.
		File file = new File(filenameForFile);
		try {
			out = new PrintWriter(file, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			BBexcLog.log("out = new PrintWriter - 1", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			BBexcLog.log("out = new PrintWriter - 2", e);
		}
	}
}
