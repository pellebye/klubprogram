package common.system;

import common.log.CommonLog;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.ModeChecks;
import common.out.file.filetest.TypeOfTest;
import common.out.file.filetest.mac.MessagesAndChecks;

public class ChkVejledningFolder {

	private common.out.file.filetest.Filetest filetest;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public ChkVejledningFolder() {
		CommonLog.logger.info("heading//");

		for (int fileNo = 0; fileNo < 9; fileNo++) {
			switch (fileNo) {
			case 0:
				// vejledning folder
				filetest = new Filetest("/vejledning");
				filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_FOLDER);
				break;
			case 1:
				// 1_udskrifter
				filetest = new Filetest("/vejledning/1_udskrifter.png");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/vejledning/1_udskrifter.png");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 2:
				// 1_udskrifter
				filetest = new Filetest("/vejledning/2_medlemskaber.png");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/vejledning/2_medlemskaber.png");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 3:
				// 3_placering
				filetest = new Filetest("/vejledning/3_placering.png");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/vejledning/3_placering.png");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 4:
				// 4_bridgecentralmappe
				filetest = new Filetest("/vejledning/4_bridgecentralmappe.png");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/vejledning/4_bridgecentralmappe.png");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 5:
				// 5_config
				filetest = new Filetest("/vejledning/5_config.png");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/vejledning/5_config.png");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 6:
				// 6_medlemsbehandling
				filetest = new Filetest("/vejledning/6_medlemsbehandling.png");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/vejledning/6_medlemsbehandling.png");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 7:
				// vejledning.css
				filetest = new Filetest("/vejledning/vejledning.css");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/vejledning/vejledning.css");
				filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;
			case 8:
				// vejledning.htm
				filetest = new Filetest("/vejledning/vejledning.htm");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setResourceFilename("resource/vejledning/vejledning.htm");
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
