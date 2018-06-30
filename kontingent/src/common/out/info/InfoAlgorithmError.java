package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoAlgorithmError {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoAlgorithmError(String line) {
		CommonLog.logger.info("heading//");

		String heading = "Fejl! i algoritmen.";
		String text = "Ret i algoritmen: " + line + " ... og kør programmet igen.";
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_ERROR | SWT.OK);
		System.exit(0);
	}
}
