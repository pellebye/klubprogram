package common.system;

import common.Data;
import common.Status;
import common.log.CommonLog;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.ModeChecks;
import common.out.file.filetest.TypeOfTest;
import common.out.file.filetest.mac.MessagesAndChecks;
import common.widgets.ShellAbout;

public class ChkJquery {

	private Filetest filetest;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * It is decided that if 'tablesorter.js' in system folder does not exist then
	 * the program has been started for the first time.
	 */
	public ChkJquery() {
		CommonLog.logger.info("heading//");

		for (int fileNo = 0; fileNo < 2; fileNo++) {
			switch (fileNo) {
			case 0:
				// jquery-3.2.1.js
				filetest = new Filetest("\\system\\jquery\\jquery-3.2.1.js");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/jquery/jquery-3.2.1.js");
				filetest.setModeCheckDetail(ModeCheckDetail.EXAMPLES);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 1:
				// tablesorter.js
				filetest = new Filetest("\\system\\jquery\\tablesorter.js");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/jquery/tablesorter.js");
				filetest.setModeCheckDetail(ModeCheckDetail.EXAMPLES);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;

			default:
				throw new IllegalArgumentException("Illegal keyword - ChkLanguages fileNo > " + 2);
			}

			/*
			 * Check for first time program start.
			 */
			if (fileNo == 1 && filetest.getStatus().compareTo(Status.FILEDOESNOTEXIST) == 0) {
				// new InfoFirstTimeProgramStart();
				new ShellAbout(true);
				Data.setFirstTimeStart(true);
			} else {
				if (fileNo == 1) {
					new ShellAbout(false);
					Data.setFirstTimeStart(false);
				}
			}

			new MessagesAndChecks(null, ModeChecks.FILETEST_SYSTEMFILES, filetest);
		}
	}
}
