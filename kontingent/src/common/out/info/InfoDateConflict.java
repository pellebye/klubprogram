package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoDateConflict {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoDateConflict(String datoLinje) {
		CommonLog.logger.info("heading//");

		String heading = "Fejl! Der er noget galt i en datoangivelse.";
		String text = "Ret i datoangivelsen: " + datoLinje + " ... og kør programmet igen.";
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_ERROR | SWT.OK);
		System.exit(0);
	}
}
