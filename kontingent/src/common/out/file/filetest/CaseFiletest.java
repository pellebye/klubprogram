package common.out.file.filetest;

import java.io.File;
import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.Status;
import common.log.BBexcLog;
import common.log.CommonLog;
import common.out.file.CopyFile;

/**
 * This class test if a file or a folder exists. Also this class will take
 * special action when it comes to two special files in system folder, that is
 * 'basic.config' and 'current.config'.
 */
public class CaseFiletest {

	private Status status = common.Status.OK;

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
	public CaseFiletest(Filetest filetest) {
		CommonLog.logger.info("class//");

		ModeChecks checks = filetest.getModeChecks();
		status = Status.OK;

		if (filetest.getStatus() == Status.FILEDOESNOTEXIST) {
			status = Status.ERROR;
			filetest.setStatus(Status.ERROR);

			// must be before 'userMessage'.
			if (filetest.getType() == TypeOfTest.FILE_DONOTHING)
				return;

			int response = 0;
			// if (ComSys.isRebuildAccepted()) {
			// response = userMessage(filetest);
			// }

			if (checks == ModeChecks.FOLDEREXISTS) {
				caseFolderExists(filetest);
			} else if (checks == ModeChecks.FILENAMEEXISTS) {
				// Handle filename does not exist.
				status = filenameExistsTest(filetest);
				/*
				 * Return with status Ok when file does not exist.
				 */
				if (filetest.getModeCheckDetail() == ModeCheckDetail.WATCH)
					return;
			}

			if (filetest.getType() == TypeOfTest.FILE_NOCOPY)
				System.exit(0);
			if (response == SWT.CANCEL)
				cancelActions(filetest); // After 'TypeOfTest'.
		}

		CommonLog.logger.info("message//CaseFiletest filetest.getDestinationPathAndFilename = "
				+ filetest.getDestinationPathAndFilename());
		CommonLog.logger.info("message//CaseFiletest filetest.getErrorMessage = " + filetest.getErrorMessage());
		CommonLog.logger.info("message//CaseFiletest filetest.getFilename = " + filetest.getFilename());
		CommonLog.logger.info("message//CaseFiletest filetest.getResourceFilename = " + filetest.getResourceFilename());
		CommonLog.logger.info(
				"message//CaseFiletest filetest.getSourcePathAndFilename = " + filetest.getSourcePathAndFilename());
		CommonLog.logger.info("message//CaseFiletest filetest.getModeChecks = " + filetest.getModeChecks());
		CommonLog.logger.info("message//CaseFiletest filetest.getModeCheckDetail = " + filetest.getModeCheckDetail());
		CommonLog.logger.info("message//CaseFiletest filetest.getStatus = " + filetest.getStatus());
		CommonLog.logger.info("message//CaseFiletest filetest.getSystemExit = " + filetest.getSystemExit());
		CommonLog.logger.info("message//CaseFiletest filetest.getType = " + filetest.getType());
		CommonLog.logger
				.info("message//CaseFiletest filetest.isFileRebuildAccepted = " + filetest.isFileRebuildAccepted());

		furtherFilenameExistsTest(filetest);

		filetest.setStatus(status);
	}

	/**
	 * At this point the filetest shows that filename does not exist.
	 * <p>
	 * Return status unchanged if 'ModeCheckDetail' in 'filetest' is:
	 * </p>
	 * <ul>
	 * <li>BASICCONFIG</li>
	 * <li>BWS</li>
	 * <li>CHECKANDSTOP</li>
	 * <li>LANGUAGE</li>
	 * <li>RESOURCES</li>
	 * <li>SOUND</li>
	 * <li>SOUNDPARTS</li>
	 * <li>SYSTEM</li>
	 * </ul>
	 *
	 * or as 'RETURN' (i.e. return in upper procedure) if 'ModeCheckDetail' in
	 * 'filetest' is 'CURRENTCONFIG'.
	 *
	 * @param filetest
	 *            Current filetest.
	 */
	private Status filenameExistsTest(Filetest filetest) {

		switch (filetest.getModeCheckDetail()) {
		case CONFIG_BASIC:
			// caseFileBasicConfig(filetest);
			break;
		case BWS:
			caseFileBws(filetest);
			break;
		case PATHACTUAL:
		case CHECKANDSTOP:
			userMessage(filetest);
			break;
		case CONFIG_CURRENT:
			return Status.RETURN; // Action in 'ConfigCurrent'.
		case DATABASENOTPRESENT:
			caseDatabaseNotPresent(filetest);
			break;
		case LANGUAGE:
			// caseLanguage(filetest);
			break;
		case MOVEMENTS_FILE_MISSING:
			caseMovementsFileMissing(filetest);
			break;
		case PAIRLIST:
			caseMissingReadPairlist(filetest);
			break;
		case RESOURCES:
			// not implemented yet.
			caseResources(filetest);
			break;
		case RESULTFILE:
			// When result filename does not exist.
			caseResultFile(filetest);
			break;
		case SECTION:
			caseSectionFileMissing(filetest);
		case SOUND:
			// caseFileSound(filetest);
			break;
		case SOUNDPARTS:
			// caseFileSoundParts(filetest);
			break;
		case SYSTEM:
			break;
		case WATCH:
			// Watch file does not already exist.
			status = Status.FILEDOESNOTEXIST;
			filetest.setStatus(status);
			break;

		default:
			throw new IllegalArgumentException("Illegal keyword - checksDetail = " + filetest.getModeCheckDetail());
		}

		// Return status unchanged.
		return status;
	}

	/**
	 * Send a message to user about a missing movement file and shut down
	 * program.
	 * 
	 * @param filetest
	 *            Current object concerning test of files and folders.
	 */
	private void caseMovementsFileMissing(Filetest filetest) {
		int SWTcollection = SWT.ICON_ERROR | SWT.OK;
		String heading = "Manglende skifteplan";
		String line1 = "Programmet kan ikke finde skifteplanen " + LocalMethods.getNewline()
				+ LocalMethods.getNewline();
		String line2 = filetest.getFilename() + LocalMethods.getNewline() + LocalMethods.getNewline();
		String line3 = " i mappen " + filetest.getPathOnly() + "." + LocalMethods.getNewline()
				+ LocalMethods.getNewline();
		String line4 = "Programmet kan ikke fortsætte uden denne fil." + LocalMethods.getNewline()
				+ LocalMethods.getNewline();
		String line5 = "Opret filen og start igen.";

		LocalMethods.methodShowMessage(heading, line1 + line2 + line3 + line4 + line5, SWTcollection);
		System.exit(0);
	}

	private void caseResultFile(Filetest filetest) {

		// Path for current date.
		// String path;
		// path = "/" + ComData.getTournamentTitle() + "/" +
		// ComData.getCurrentDate();
		//
		// new CreateResultsfile(filetest.getFilename(), path,
		// ComData.getCurrentDate(),
		// ResultFile.RESULTFILE_CURRENTDATE);
	}

	private void caseSectionFileMissing(Filetest filetest) {
		status = Status.ERROR;

		LocalMethods.methodShowMessage("Rækkefil mangler",
				"Filen (" + filetest.getDestinationPathAndFilename() + ") mangler" + ". "
						+ "Programmet kan ikke fortsætte uden denne fil. Opret filen og start igen." + ".",
				SWT.ICON_ERROR | SWT.OK);
	}

	private void caseDatabaseNotPresent(Filetest filetest) {
		status = Status.ERROR;

		LocalMethods.methodShowMessage("Databasen mangler",
				"Databasen (" + filetest.getDestinationPathAndFilename() + ") mangler"
						+ ". Databasen 'test.bws' skal være i mappen 'system/test' den valgte funktion kan gennemføres"
						+ ".",
				SWT.ICON_ERROR | SWT.OK);
	}

	private void caseMissingReadPairlist(Filetest filetest) {
		status = Status.ERROR;
		filetest.setStatus(Status.ERROR);

		LocalMethods.methodShowMessage("Parlisten mangler",
				"Parlisten mangler i datomappen (" + filetest.getErrorMessage()
						+ "). Placer parlisten i den rigtige datomappe og start programmet igen" + ". "
						+ LocalMethods.getNewline() + LocalMethods.getNewline()
						+ "Programmet kan ikke fortsætte uden filen",
				SWT.ICON_ERROR | SWT.OK);
	}

	/**
	 * Copy default.bws file from resource folder to appropriate tournament
	 * folder and filename equal to tournament title.
	 *
	 * @param filetest
	 *            Holds information of test of filename or folder name.
	 */
	private void caseFileBws(Filetest filetest) {
		CopyFile copyFile = new CopyFile(filetest);
		status = copyFile.getStatus();
	}

	private void caseResources(Filetest filetest) {
		FileNotFoundException e = new FileNotFoundException();
		e.printStackTrace();
		status = Status.ERROR;
		filetest.setStatus(Status.ERROR);

		LocalMethods.methodShowMessage("Filen eksisterer ikke",
				filetest.getFilename() + ": " + "mangler eller er beskadiget" + ". " + LocalMethods.getNewline()
						+ LocalMethods.getNewline() + "Programmet kan ikke fortsÃ¦tte uden filen",
				SWT.ICON_ERROR | SWT.OK);
		BBexcLog.log("new BufferedReader", e);
		System.exit(0);
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
	private void caseFolderExists(Filetest filetest) {
		CommonLog.logger.info("class//");

		File file = filetest.getFile();

		if (!file.exists()) {
			boolean result = file.mkdir();
			if (!result) {
				userMessageMissingFolder(file.getAbsolutePath());
				status = Status.ERROR;
			} else {
				status = Status.OK;
			}
		}
	}

	/**
	 * End program when user has canceled any actions to recreate missing folder
	 * or file.
	 *
	 * @param filetest
	 *            Holds information of test of filename or folder name.
	 */
	private void cancelActions(Filetest filetest) {
		CommonLog.logger.info("class//");

		if (filetest.getSystemExit()) {
			System.exit(0);
		}
	}

	/**
	 * Returns user response to a general message about missing folder or file.
	 * Depending on the situation user get an error message or a warning, and
	 * can take different actions.
	 *
	 * @param filetest
	 *            Holds information of test of filename or folder name.
	 * @param pathToTest
	 *            File holding information about which folder to make.
	 */
	private int userMessage(Filetest filetest) {
		CommonLog.logger.info("class//");

		String choice_1 = "";
		String choice_2 = "";
		int SWTcollection = 0;

		String folderOrFile = "";

		switch (filetest.getType()) {
		case FILE_FOLDER:
		case FILE_MAKECOPY:
			folderOrFile = (filetest.getType() == TypeOfTest.FILE_FOLDER ? "mappen " : "filen ");
			choice_1 = "Tryk 'Ok' for at oprette " + folderOrFile + LocalMethods.getNewline()
					+ LocalMethods.getNewline() + ".";
			choice_2 = "Tryk 'Annuler' for at lukke programmet" + LocalMethods.getNewline() + LocalMethods.getNewline()
					+ ".";
			SWTcollection = SWT.ICON_WARNING | SWT.OK | SWT.CANCEL;
			break;
		case FILE_NOCOPY:
			// Message and OK button.
			choice_1 = "Programmet kan ikke fortsætte før fejlen er rettet." + "." + LocalMethods.getNewline()
					+ LocalMethods.getNewline();
			choice_2 = "Tryk 'Ok' for at lukke programmet" + ".";
			SWTcollection = SWT.ICON_ERROR | SWT.OK;
			break;

		default:
			throw new IllegalArgumentException("Illegal keyword - CaseFiletest = " + filetest.getType());
		}

		if (filetest.getType() == TypeOfTest.FILE_MAKECOPY || filetest.getType() == TypeOfTest.FILE_FOLDER) {
			// Message, cancel and OK buttons.
			choice_1 = "Tryk 'Ok' for at oprette " + folderOrFile + LocalMethods.getNewline()
					+ LocalMethods.getNewline() + ".";
			choice_2 = "Tryk 'Annuler' for at lukke programmet" + LocalMethods.getNewline() + LocalMethods.getNewline()
					+ ".";
			SWTcollection = SWT.ICON_WARNING | SWT.OK | SWT.CANCEL;
		} else {
			// Message and OK button.
			choice_1 = "Programmet kan ikke fortsætte før fejlen er rettet." + "." + LocalMethods.getNewline()
					+ LocalMethods.getNewline();
			choice_2 = "Tryk 'Ok' for at lukke programmet" + ".";
			SWTcollection = SWT.ICON_ERROR | SWT.OK;
		}

		int response = LocalMethods
				.methodShowMessage("Manglende mappe eller fil",
						"Programmet kan ikke finde " + folderOrFile + " '" + filetest.getDestinationPathAndFilename()
								+ "' " + LocalMethods.getNewline() + LocalMethods.getNewline() + choice_1 + choice_2,
						SWTcollection);
		return response;
	}

	/**
	 * User message to inform about missing folder.
	 *
	 * @param folder
	 *            Missing folder.
	 */
	private void userMessageMissingFolder(String folder) {
		CommonLog.logger.info("class//");

		String errorMessage = "" + "Mappen '" + folder + "' eksisterer ikke og kunne ikke skabes" + ". "
				+ "Programmet kan ikke fortsÃ¦tte fÃ¸r fejlen er udbedret" + ".";

		LocalMethods.methodShowMessage("Der mangler en mappe" + ".", errorMessage, SWT.ICON_ERROR | SWT.OK);
		System.out.println("Directory was not restored: " + folder);
		System.exit(0);
	}
}
