package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoUnexpectedError {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoUnexpectedError(String errorNo) {
		CommonLog.logger.info("heading//");

		String heading = "Fejl! Dette er uvented.";
		String text = "Der er sket en uvented fejl. Fejlkoden er: " + errorNo
				+ " ... prøv at køre programmet igen. Kontakt programudvikleren hvis fejlen gentager sig!";
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_ERROR | SWT.OK);
		System.exit(0);
	}
}
