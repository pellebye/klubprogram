package common.out.file.filetest.mac;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.Status;
import common.log.CommonLog;
import common.out.file.CopyFile;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.ModeChecks;
import common.system.ComSys;

/**
 * This class test if a file or a folder exists. Also this class will take
 * special action when it comes to two special files in system folder, that is
 * 'basic.config' and 'current.config'.
 */
public class CaseFiletest_systemFiles {

	Status status = Status.OK;

	public Status getStatus() {
		return status;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTORS
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class test if a file or a folder exists. Also this class will take
	 * special action when it comes to two special files in system folder, that
	 * is 'basic.config' and 'current.config'.
	 *
	 * <ol>
	 * <li>BASICCONFIG</li>
	 * <li>BWS</li>
	 * <li>CURRENTCONFIG</li>
	 * <li>LANGUAGE</li>
	 * <li>SOUND</li>
	 * <li>SOUNDPARTS</li>
	 * <li>SYSTEM</li>
	 * </ol>
	 *
	 * @param filetest
	 *            Class containing result of, and information necessary for,
	 *            test of a file or a folder.
	 */
	public CaseFiletest_systemFiles(Filetest filetest) {
		CommonLog.logger.info("class//");

		ModeChecks checks = filetest.getModeChecks();

		status = Status.OK;

		if (filetest.getStatus() == Status.FILEDOESNOTEXIST) {
			if (ComSys.isRebuildAccepted() == true) {
				handlePathNotFound(filetest, checks);
			} else {
				status = Status.ERROR;

				if (filetest.getModeCheckDetail() != ModeCheckDetail.LOGSTARTUP) {
					userMessageMissingFolder();
					handlePathNotFound(filetest, checks);
				}
				return;
			}
		}

		furtherFilenameExistsTest(filetest);

		filetest.setStatus(status);
	}

	private void handlePathNotFound(Filetest filetest, ModeChecks checks) {
		status = Status.ERROR;

		if (checks == ModeChecks.FOLDEREXISTS) {
			caseFolderDoesNotExist(filetest);
		} else if (checks == ModeChecks.FILENAMEEXISTS) {
			status = caseFilenameDoesNotExist(filetest);
		}
	}

	/**
	 * Return status unchanged if 'ModeCheckDetail' in 'filetest' is:
	 * <ul>
	 * <li>BASICCONFIG - if basic configuration file (basic.config) is missing
	 * user must make new configuration</li>
	 * <li>BWS - if default database is missing it is copied from resources</li>
	 * <li>LANGUAGE - if default language files are missing new ones are copied
	 * from resources</li>
	 * <li>SOUND - if sound files for watch are missing new ones are copied from
	 * resources</li>
	 * <li>SOUNDPARTS - if sound parts files are missing new ones are copied
	 * from resources</li>
	 * <li>SYSTEM - if system folder is missing a new one is made</li>
	 * </ul>
	 *
	 * @param filetest
	 *            Current filetest.
	 */
	private Status caseFilenameDoesNotExist(Filetest filetest) {

		switch (filetest.getModeCheckDetail()) {
		case CONFIG_BASIC:
			caseFileBasicConfig();
			break;
		case CONFIG_CURRENT:
			// new ConfigCurrent();
			break;
		case CONFIG_TESTMODE:
			// caseFileTestmodeConfig();
			break;
		case SYSTEM:
		case BWS:
		case EXAMPLES:
		case FIRSTSTARTUP:
		case JQERY:
		case LANGUAGE:
		case MOVEMENTS:
		case SOUND:
		case SOUNDPARTS:
		case TEST:
			CopyFile copyFile = new CopyFile(filetest);
			status = copyFile.getStatus();
			break;

		default:
			throw new IllegalArgumentException("Illegal keyword - checksDetail = " + filetest.getModeCheckDetail());
		}

		return status; // Return status unchanged.
	}

	// private void caseFileTestmodeConfig() {
	// new ShellTestmodeConfiguration();
	// }

	/**
	 * (Re)create basic config file after showing user have chosen language.
	 *
	 * @param filetest
	 *            Holds information of test of filename or folder name.
	 */
	private void caseFileBasicConfig() {
		CommonLog.logger.info("class//");

		/*
		 * Check that language is present!
		 */
		// @SuppressWarnings("unused")
		// String test =
		/*
		 * <lang><index> 1 </index><base> Clubs </base><dk> Klør
		 * </dk><description> in bidding process </description></lang>
		 */
		// ReadLanguage.getLanguage(1);

		// Create basic config file.
		// new ShellBasicConfig();
	}

	/**
	 * If 'ModeChecks' in 'filetest' is 'FILENAMEEXISTS' do some action
	 * depending on whether it is possible to read the file as 'UTF-8'.
	 *
	 * @param filetest
	 *            Current filetest.
	 */
	private void furtherFilenameExistsTest(Filetest filetest) {

		if (filetest.getModeChecks() == ModeChecks.FILENAMEEXISTS) {
			// File file = new File(filetest.getDestinationPathAndFilename());

			// CreateBufferedReader createBufferedReader = new
			// CreateBufferedReader(file, filetest);
			//
			// if (createBufferedReader.getStatus() != Status.OK)
			// System.exit(0);
			//
			// createBufferedReader = null;
		}
	}

	/**
	 * Creates a missing folder and sets status to ok when succeed.
	 *
	 * @param filetest
	 *            Holds information of test of filename or folder name.
	 * @param pathToTest
	 *            File holding information about which folder to make.
	 */
	private void caseFolderDoesNotExist(Filetest filetest) {
		CommonLog.logger.info("class//");

		boolean result = filetest.getFile().mkdir();

		if (!result)
			status = Status.ERROR;
		else
			status = Status.OK;
	}

	/**
	 * User message to inform about missing folder.
	 *
	 * @param folder
	 *            Missing folder.
	 */
	private void userMessageMissingFolder() {
		CommonLog.logger.info("class//");

		String header = "Manglende mappe eller fil" + ".";
		String errorMessage = "En eller flere nødvendige system mapper og" + "/"
				+ "eller filer mangler eller er beskadiget. Skal alt genskabes" + "?" + ". " + LocalMethods.getNewline()
				+ LocalMethods.getNewline() + "Tryk 'Ok' for at genskabe mapper og filer" + LocalMethods.getNewline()
				+ LocalMethods.getNewline() + "." + "Tryk 'Annuler' for at lukke programmet" + ".";
		int SWTcollection = SWT.ICON_WARNING | SWT.OK | SWT.CANCEL;

		int response = LocalMethods.methodShowMessage(header, errorMessage, SWTcollection);

		if (response == SWT.OK)
			// ComSys.setRebuildAccepted(true);
			if (response == SWT.CANCEL)
				System.exit(0);
	}
}
