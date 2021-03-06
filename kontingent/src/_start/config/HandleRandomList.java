package _start.config;

import common.Data;
import common.log.CommonLog;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.ModeChecks;
import common.out.file.filetest.TypeOfTest;
import common.out.file.filetest.mac.MessagesAndChecks;
import common.system.ComSys;

public class HandleRandomList {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class handles random list from configuration file.
	 * 
	 * @param randomList
	 *            A list of random list files to generate (e.g. '8-30').
	 */
	public HandleRandomList(String randomList) {
		CommonLog.logger.info("heading//");

		// Create random list folder if not there.
		common.out.file.filetest.Filetest filetest;
		filetest = new Filetest("/resultater/randomlister");
		filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
		filetest.setModeCheckDetail(ModeCheckDetail.SYSTEM);
		filetest.setSystemExit(true);
		filetest.setType(TypeOfTest.FILE_FOLDER);

		ComSys.setRebuildAccepted(true);
		new MessagesAndChecks(null, ModeChecks.FILETEST_SYSTEMFILES, filetest);
		ComSys.setRebuildAccepted(false);

		while (randomList.contains("  ")) {
			randomList = randomList.replace("  ", " ");
		}

		Data.setRandomList(randomList);
	}
}
