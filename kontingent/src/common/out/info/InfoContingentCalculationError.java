package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoContingentCalculationError {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoContingentCalculationError(String line) {
		CommonLog.logger.info("heading//");

		String heading = "Fejl! i kontingent udregningen.";
		String text = "Ret i konfigurationslinjen: " + line + " ... og kør programmet igen.";
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_ERROR | SWT.OK);
		System.exit(0);
	}
}
