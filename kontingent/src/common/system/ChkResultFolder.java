package common.system;

import common.log.CommonLog;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.ModeChecks;
import common.out.file.filetest.TypeOfTest;
import common.out.file.filetest.mac.MessagesAndChecks;

public class ChkResultFolder {

	private common.out.file.filetest.Filetest filetest;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * Checks missing system folder with content:
	 *
	 * <ol>
	 * <li>'system' folder</li>
	 * <li>'system/print' folder</li>
	 * <li>'system/log' folder</li>
	 * <li>'system/jquery' folder</li>
	 * </ol>
	 */
	public ChkResultFolder() {
		CommonLog.logger.info("heading//");

		for (int fileNo = 0; fileNo < 6; fileNo++) {
			switch (fileNo) {
			case 0:
				// system folder
				filetest = new Filetest("/resultater");
				filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_FOLDER);
				break;
			case 1:
				// kontingentliste folder
				filetest = new Filetest("/resultater/kontingentliste");
				filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_FOLDER);
				break;
			case 2:
				// telefonliste folder
				filetest = new Filetest("/resultater/telefonliste");
				filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_FOLDER);
				break;
			case 3:
				// kalender folder
				filetest = new Filetest("/resultater/kalender");
				filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_FOLDER);
				break;
			case 4:
				// kalender.js
				filetest = new Filetest("/resultater/kalender/kalender.js");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/kalender/kalender.js");
				filetest.setModeCheckDetail(ModeCheckDetail.EXAMPLES);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 5:
				// gmail import folder
				filetest = new Filetest("/resultater/gmail import");
				filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_FOLDER);
				break;

			default:
				throw new IllegalArgumentException("Illegal keyword - ChkSysFolder fileNo > " + 1);
			}

			new MessagesAndChecks(null, ModeChecks.FILETEST_SYSTEMFILES, filetest);
		}
	}
}
