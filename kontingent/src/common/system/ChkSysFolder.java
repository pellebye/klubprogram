package common.system;

import common.log.CommonLog;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.ModeChecks;
import common.out.file.filetest.TypeOfTest;
import common.out.file.filetest.mac.MessagesAndChecks;

public class ChkSysFolder {

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
	public ChkSysFolder() {
		CommonLog.logger.info("heading//");

		for (int fileNo = 0; fileNo < 4; fileNo++) {
			switch (fileNo) {
			case 0:
				// system folder
				filetest = new Filetest("/system");
				filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_FOLDER);
				break;
			case 1:
				// log folder
				filetest = new Filetest("/system/log");
				filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_FOLDER);
				break;
			case 2:
				// jquery folder
				filetest = new Filetest("/system/jquery");
				filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_FOLDER);
				break;
			case 3:
				// config.txt
				filetest = new Filetest("\\config.txt");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/config.txt");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;

			default:
				throw new IllegalArgumentException("Illegal keyword - ChkSysFolder fileNo > " + 1);
			}

			new MessagesAndChecks(null, ModeChecks.FILETEST_SYSTEMFILES, filetest);
		}
	}
}
