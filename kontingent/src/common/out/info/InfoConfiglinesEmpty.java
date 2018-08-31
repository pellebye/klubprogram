package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoConfiglinesEmpty {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoConfiglinesEmpty() {
		CommonLog.logger.info("heading//");

		String heading = "Fejl! Der ingen linjer i filen 'config.txt'.";
		String text = "Slet filen 'config.txt' for at genskabe opstart filen.";
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_ERROR | SWT.OK);
		System.exit(0);
	}
}
