package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoWeekConflict {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoWeekConflict(String ugeLinje) {
		CommonLog.logger.info("heading//");

		String heading = "Fejl! Der er noget galt i ugeangivelsen.";
		String text = "Ret i angivelsen: " + ugeLinje + " ... og kør programmet igen.";
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_ERROR | SWT.OK);
		System.exit(0);
	}
}
