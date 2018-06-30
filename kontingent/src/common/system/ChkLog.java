package common.system;

import java.io.File;

import common.LocalMethods;
import common.Status;
import common.log.CommonLog;
import common.out.file.GetListOfSpecificFiletype;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.ModeChecks;
import common.out.file.filetest.TypeOfTest;
import common.out.file.filetest.mac.MessagesAndChecks;

/**
 * This class checks missing log folder in system folder. If log folder exists
 * delete possible log files (i.e. 'log.txt' and 'comDataLog.txt' files) which
 * possibly are in user directory from the moment where the program was
 * installed.
 */
public class ChkLog {

	private common.out.file.filetest.Filetest filetest;

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class checks missing log folder in system folder. If log folder
	 * exists delete possible log files (i.e. 'log.txt' and 'comDataLog.txt'
	 * files) which possibly are in user directory from the moment where the
	 * program was installed.
	 */
	public ChkLog() {
		CommonLog.logger.info("heading//");

		filetest = new Filetest("/system/log");
		filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
		filetest.setModeCheckDetail(ModeCheckDetail.LOG);
		filetest.setSystemExit(true);
		filetest.setType(TypeOfTest.FILE_FOLDER);

		/*
		 * If log folder exists delete possible log files (i.e. 'log.txt' and
		 * 'comDataLog<date>.txt' files) which possibly are in user directory
		 * from the moment where the program was installed.
		 */
		if (filetest.getStatus() == Status.FILEEXISTS) {
			// Delete possible log.txt file.
			filetest = new Filetest("/log.txt");
			filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
			filetest.setModeCheckDetail(ModeCheckDetail.LOG);
			filetest.setSystemExit(false);
			filetest.setType(TypeOfTest.FILE_NOCOPY);
			if (filetest.getStatus() == Status.FILEEXISTS) {
				File file = new File(filetest.getDestinationPathAndFilename());
				file.delete();
				file = null;
			}
			// Delete possible comDataLog<date>.txt file in user folder.
			GetListOfSpecificFiletype listOfFiles = new common.out.file.GetListOfSpecificFiletype("txt",
					LocalMethods.getUserDirectory());
			String[] list = listOfFiles.getList();
			for (String string : list) {
				if (string.contains("comDataLog")) {
					String pathAndFilename = common.LocalMethods.getUserDirectory() + "\\" + string + ".txt";
					File file = new File(pathAndFilename);
					file.delete();
					file = null;
				}
			}
			listOfFiles = null;
		} else {
			new MessagesAndChecks(null, ModeChecks.FILETEST_SYSTEMFILES, filetest);
		}
	}
}
