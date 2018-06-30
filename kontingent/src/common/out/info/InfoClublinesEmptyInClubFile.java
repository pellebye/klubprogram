package common.out.info;

import org.eclipse.swt.SWT;

import common.Data;
import common.LocalMethods;
import common.log.CommonLog;

public class InfoClublinesEmptyInClubFile {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoClublinesEmptyInClubFile(String filename) {
		CommonLog.logger.info("heading//");

		String heading = "Fejl! Der mangler klublinjer!";
		String text = "Der er ingen klublinjer i filen " + filename + LocalMethods.getNewLineDouble();
		text += "Læg den rigtige fil i 'bridgecentral' mappen eller slet den tomme fil både i mappen og"
				+ " i konfigurationsfilen (config.txt). Kør derefter programmet igen.";
		if (Data.isFirstTimeStart() == false) {
			LocalMethods.methodShowMessage(heading, text, SWT.ICON_ERROR | SWT.OK);
		}
		System.exit(0);
	}
}
