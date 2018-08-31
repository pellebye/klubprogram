package _start.vejledning;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import common.LocalMethods;
import common.log.CommonLog;
import common.out.info.InfoUnexpectedError;

public class Vejledning {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public Vejledning() {
		CommonLog.logger.info("heading//");

		// Browse a URL
		try {
			String filename = LocalMethods.getUserDirectory() + "/vejledning/vejledning.htm";
			File htmlFile = new File(filename);
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
			new InfoUnexpectedError("001 Vejledning");
		}
	}
}
