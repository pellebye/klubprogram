package common.out.info;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.log.CommonLog;

public class InfoMissingConfigCommands {

	private String heading = "Obs! Der mangler måske linjer i filen 'config.txt'.";
	private String text = "Konfigurationsfilen 'config.txt' mangler ";

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class informs user which command lines are missing.
	 * 
	 * @param bitResult
	 *            Results from configuration (i.e. < 15). 15 indicates no
	 *            missing lines. bitResult consist of bit 1 + 2 + 4 + 8.
	 */
	public InfoMissingConfigCommands(int bitResult) {
		CommonLog.logger.info("heading//");

		checkWhichLinesAreMissing(bitResult);

		replaceDoubleSpaces();

		// Replace "og" after "mangler".
		text = text.replace("mangler og", "mangler");

		replaceOgWithComma();

		LocalMethods.methodShowMessage(heading, text, SWT.ICON_WARNING | SWT.OK);
		System.exit(0);
	}

	/**
	 * Replace "og" with comma except the last one.
	 */
	private void replaceOgWithComma() {
		boolean goOn = true;
		while (goOn) {
			goOn = false;
			// check for "og".
			if (text.contains(" og ")) {
				// check for first "og".
				int index1 = text.indexOf(" og ");
				if (index1 > -1) {
					index1 += 4;
					/*
					 * If "og" exists twice replace the first one with comma.
					 */
					int index2 = text.indexOf(" og ", index1);
					if (index2 > -1) {
						text = text.replaceFirst(" og ", ", ");
						goOn = true;
					}
				}
			}
		}
	}

	/**
	 * Replace double spaces.
	 */
	private void replaceDoubleSpaces() {
		boolean goOn = true;
		while (goOn) {
			goOn = false;
			if (text.contains("  ")) {
				goOn = true;
				text = text.replace("  ", " ");
			}
		}
	}

	private void checkWhichLinesAreMissing(int bitResult) {
		if ((bitResult & 0x1) == 0)
			text += " klublinjer ";
		if ((bitResult & 0x2) == 0)
			text += " og kontingentlinjer ";
		if ((bitResult & 0x4) == 0)
			text += " og telefonlistekolonner ";
		if ((bitResult & 0x8) == 0)
			text += " og random filinterval ";
	}
}
