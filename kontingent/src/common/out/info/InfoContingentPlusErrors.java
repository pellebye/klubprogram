package common.out.info;

import org.eclipse.swt.SWT;

import common.Data;
import common.LocalMethods;
import common.log.CommonLog;

public class InfoContingentPlusErrors {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoContingentPlusErrors() {
		CommonLog.logger.info("heading//");

		int numberContingentErrors = Data.getNumberContingentErrors();

		String total = String.format("%,20d kr.", Data.getTotalContingent());
		String errors = "Der er ikke registreret kontingentfejl.";
		if (numberContingentErrors > 0) {
			errors = "Der er registreret " + numberContingentErrors + " fejl i kontingentudregningen. "
					+ LocalMethods.getNewline();
			errors += "Fejl vises som '-1' (minus en) som kontingentbeløb.";
		}
		String heading = "Kontingent totalsum";
		String text = "Samlet kontingent" + total + LocalMethods.getNewLineDouble() + errors;
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_INFORMATION | SWT.OK);
		// System.exit(0);
	}
}
