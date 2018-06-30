package common.log;

import java.io.File;

import common.LocalMethods;
import common.Status;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeChecks;
import common.out.file.filetest.TypeOfTest;

/**
 * This class creates a log path for current logger:
 * 
 * @formatter:off <blockquote>
 *                <ol start=1>
 *                <li>If /system/log folder does not exist (i.e. when first
 *                start of program) log folder will be 'user directory' which is
 *                default in log handler.</li>
 *                <li>Number log files log, log1, log2 etc. and create the first
 *                free number.</li>
 *                <li>Reset log number sequence if more than 25 log files in log
 *                folder.</li>
 *                </ol>
 *                </blockquote> @formatter:on
 */
public class CreateCurrentLogFile {

	private String logPathAndFilename = "system/log/log.txt";

	public String getLogPathAndFilename() {
		return logPathAndFilename;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class creates a log path for current logger:
	 * 
	 * @formatter:off <blockquote>
	 *                <ol start=1>
	 *                <li>If /system/log folder does not exist (i.e. when first
	 *                start of program) log folder will be 'user directory'
	 *                which is default in log handler.</li>
	 *                <li>Number log files log, log1, log2 etc. and create the
	 *                first free number.</li>
	 *                <li>Reset log number sequence if more than 25 log files in
	 *                log folder.</li>
	 *                </ol>
	 *                </blockquote> @formatter:on
	 * 
	 * @param logPath
	 *            Current path to log files.
	 */
	public CreateCurrentLogFile(String logPath) {
		Filetest filetest;

		/*
		 * Test variable to indicate if program is still searching for a free
		 * log file number.
		 */
		boolean stillSearching = true;

		/*
		 * Current log file number.
		 */
		int logFileNo = 0;

		String filename = "";

		while (stillSearching) {

			/*
			 * Reset search indicator
			 */
			stillSearching = false;
			if (logFileNo == 0)
				filename = "log";
			else
				filename = "log" + String.valueOf(logFileNo);

			/*
			 * Test if current log file number exists.
			 */
			filetest = new Filetest(logPath + "/" + filename + ".txt");
			filetest.setResourceFilename("system/log/" + filename + ".txt");
			filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
			filetest.setSystemExit(true);
			filetest.setType(TypeOfTest.FILE_DONOTHING);

			new common.out.file.filetest.CaseFiletest(filetest);

			if (filetest.getStatus() == Status.OK) {

				/*
				 * Yes, it exists ... try next one.
				 */
				stillSearching = true;
				logFileNo++;
			} else {

				/*
				 * No, this is a new non-existence log number.
				 */
				stillSearching = false;
				logPathAndFilename = filetest.getResourceFilename();

				/*
				 * Delete all log files if maximum is reached.
				 */
				handleMoreThanMaximumLogfiles(logFileNo);
			}
		}
	}

	/**
	 * Deletes all log files if more than 25 and start over again with log file
	 * number one (i.e. "system/log/log1.txt").
	 * 
	 * @param logFileNo
	 *            Current log file number.
	 */
	private void handleMoreThanMaximumLogfiles(int logFileNo) {
		if (logFileNo > 25) {
			/*
			 * Log file number equals 26.
			 */
			File file;
			/*
			 * Important! Log files is not deleted without running garbage
			 * collection. Maybe an eclipse problem.
			 */
			System.gc();
			/*
			 * Count deleted files.
			 */
			int counter = 0;

			try {
				/*
				 * Delete log files and set log file start name.
				 */
				while (logFileNo >= counter) {

					/*
					 * Delete '.txt.lck'
					 */
					file = new File(LocalMethods.getUserDirectory() + "/system/log/log" + counter + ".txt.lck");
					file.delete();

					/*
					 * Delete '.txt'
					 */
					file = new File(LocalMethods.getUserDirectory() + "/system/log/log" + counter + ".txt");
					file.delete();

					counter++;
				}
				logPathAndFilename = "system/log/log1.txt";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
