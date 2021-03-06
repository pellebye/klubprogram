package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoCommentError {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoCommentError(String line) {
		CommonLog.logger.info("heading//");

		String heading = "Fejl! i kommentarfeltet.";
		String text = "Ret i kommentarfeltet: " + line + " ... og kør programmet igen.";
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_ERROR | SWT.OK);
		System.exit(0);
	}
}
