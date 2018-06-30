package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoFirstTimeProgramStart {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public InfoFirstTimeProgramStart() {
		CommonLog.logger.info("heading//");

		String heading = "Første gang programmet bruges.";
		String text = "Vær opmærksom på " + LocalMethods.getNewLineDouble()
				+ "# at programmet skal bruge nogle filer genereret " + "i BridgeCentral. Læs vejledningen."
				+ LocalMethods.getNewLineDouble()
				+ "# at disse filnavne skal nævnes i konfigurationsfilen (config.txt). "
				+ "Se vejledningen i konfigurationsfilen.";
		LocalMethods.methodShowMessage(heading, text, SWT.ICON_INFORMATION | SWT.OK);

	}
}
