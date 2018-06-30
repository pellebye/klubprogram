package common.system;

import common.Status;
import common.log.CommonLog;

/**
 * <p>
 * This class checks that important folder and files are present as resources
 * and/or in system folder.
 */
public class CheckSystemFiles {

	private Status status = Status.NOERROR;

	public Status getStatus() {
		return status;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * Checks that important folder and files are present as resources and/or in
	 * system folder. Copies any missing files and folders after asking user
	 * what to do.
	 */
	public CheckSystemFiles() {
		CommonLog.logger.info("heading//");

		ComSys.setRebuildAccepted(true);

		// system
		new ChkSysFolder();

		/*
		 * Checks missing log folder in system folder. If log folder exists
		 * delete possible log files (i.e. 'log.txt' and 'comDataLog.txt' files)
		 * which possibly are in user directory from the moment where the
		 * program was installed.
		 */
		new ChkLog();

		// Jquery in system folder.
		new ChkJquery();

		// Check for result folder.
		new ChkResultFolder();

		// Check for bridgecentral folder.
		new ChkBridgecentralFolder();

		new ChkVejledningFolder();

		/*
		 * This flag is set true in two situations. If program is started for
		 * the very first time and if user has chosen to reinstall some importen
		 * files and/or folders. At this point the flag is reset to false.
		 */
		ComSys.setRebuildAccepted(false);
	}
}
