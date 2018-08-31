package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoClublinesEmptyInBCfolder {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoClublinesEmptyInBCfolder() {
		CommonLog.logger.info("heading//");

		String heading = "Fejl! Der ingen klublinjer i bridgecentral-mappen.";
		String text = "Hent klubfilerne fra BridgeCentral og læg dem i mappen 'bridgecentral' og kør programmet igen. Læs vejledningen!";
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_ERROR | SWT.OK);
		System.exit(0);
	}
}
