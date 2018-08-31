package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoClublineInConfigFileError {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoClublineInConfigFileError(String line) {
		CommonLog.logger.info("heading//");

		String heading = "Fejl! i Klublinje.";
		String text = "Ret klublinjen: " + line + " ... og kør programmet igen.";
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_ERROR | SWT.OK);
		System.exit(0);
	}
}
