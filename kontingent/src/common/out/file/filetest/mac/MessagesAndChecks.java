package common.out.file.filetest.mac;

import org.eclipse.swt.SWT;

import common.LocalMethods;
import common.Status;
import common.log.CommonLog;
import common.out.file.filetest.CaseFiletest;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeChecks;
import common.out.info.InfoClublinesEmptyInBCfolder;

public class MessagesAndChecks {

	Status status = Status.OK;

	/**
	 * Returns current status.
	 */
	public Status getStatus() {
		return status;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public MessagesAndChecks(Object testObject1, ModeChecks modeChecks, Object testObject2) {
		CommonLog.logger.info("class//");
		CommonLog.logger.info("message//MessagesAndChecks modeChecks = " + modeChecks);

		switch (modeChecks) {
		case CLUBLINES_EMPTY:
			new InfoClublinesEmptyInBCfolder();
			/*
			 * Date
			 */
		case DATE_CONFLICT:
			// new CaseDateConflict((String) testObject2);
			break;
		case DATE_NUMBERMISSING:
			// CaseDatenumberMissing datenumberMissing = new
			// CaseDatenumberMissing((String) testObject2);
			// status = datenumberMissing.getStatus();
			// datenumberMissing = null;
			break;

		/*
		 * Database
		 */
		case DATABASE_ADAPTION:
			// new CaseDatabaseAdaption();
			break;
		case DATABASE_READDATAFROM:
			// CaseDatabaseReadDataFrom readData = new
			// CaseDatabaseReadDataFrom((String) testObject2);
			// status = readData.getStatus();
			// readData = null;
			break;
		case DATABASE_UPLOADTO:
			// new CaseDatabaseUploadTo();
			break;

		/*
		 * Boards
		 */
		case EMPTYBOARDS:
			// CaseBoardNoteEmpty emptyBoardNotes = new CaseBoardNoteEmpty();
			// status = emptyBoardNotes.getStatus();
			// emptyBoardNotes = null;
			break;

		/*
		 * Files
		 */
		case FILETEST:
			new CaseFiletest((Filetest) testObject2);
			break;
		case FILETEST_RESOURCE:
			caseFiletest_resourceFiles((String) testObject2);
			break;
		case FILETEST_SYSTEMFILES:
			new CaseFiletest_systemFiles((Filetest) testObject2);
			break;

		/*
		 * Interpret errors.
		 */
		case INTERPRET_ERROR:
			// new CaseInterpretError((ControlFlags) testObject2, (String)
			// testObject1);
			break;
		case INTERPRETFILE_ILLEGALCOMMAND:
			// new CaseFileToInterpretHasIllegalCommand((String) testObject1,
			// (String) testObject2);
			break;

		/*
		 * Navigate
		 */
		case NAVIGATE_SEARCHUPWARDS:
			// new CaseSearchUpwardError((int[]) testObject2);
			break;

		/*
		 * Pair
		 */
		case PAIR_ID:
			// new CasePairId((String) testObject1);
			break;

		/*
		 * Periods
		 */
		case PERIOD_UNITERROR:
			// new CasePeriodUnitError((PeriodeError) testObject1,
			// (ErrorDescription) testObject2);
			break;

		/*
		 * Promotion
		 */
		case PROMOTION:
			// new CasePromotion((String) testObject1);
			break;
		/*
		 * Result file
		 */
		case RESULTFILE_SECTIONNUMBERLIMIT:
			// CaseSectionNumberLimit sectionNumberLimit = new
			// CaseSectionNumberLimit((Integer) testObject1,
			// (String) testObject2);
			// status = sectionNumberLimit.getStatus();
			// sectionNumberLimit = null;
			break;
		case RESULTFILE_SECTIONERROR:
			// CaseResultFileSectionError sectionError = new
			// CaseResultFileSectionError((String) testObject1);
			// status = sectionError.getStatus();
			// sectionError = null;
			break;
		case RESULTFILE_TITLEPROBLEM:
			// new CaseResultfileTitleProblem((String) testObject2);
			break;

		/*
		 * Test
		 */
		case TEST_FORTESTMODE:
			// new CaseResultfileTestForPrintout();
			break;

		/*
		 * Tournament
		 */
		case TOURNAMENT_FILENAMETITLEPROBLEM:
			// new CaseTournamentFilenameTitleProblem((String) testObject1,
			// (String) testObject2);
			break;
		case TOURNAMENT_TYPEPROBLEM:
			// new CaseTournamentTypeProblem((String) testObject2);
			break;

		/*
		 * Watch
		 */
		case WATCH_FONTSIZEATPROGRAMSTARTUP:
		case WATCH_FONTSIZEATPROGRAMEND:
			// new CaseWatchFontSize((String) testObject1);
			break;

		default:
			throw new IllegalArgumentException("Illegal keyword - modeChecks = " + modeChecks.toString());
		}
	}

	/**
	 * Creates a user message when missing resource file. This error will arise
	 * so early after program start that language files are not loaded.
	 * Therefore the only language at this point is english.
	 * 
	 * @param resourceFilename
	 *            Name of the file which is missing.
	 */
	private void caseFiletest_resourceFiles(String resourceFilename) {
		String headline = "Serious error!";
		String text = " Resource file (" + resourceFilename + ") is missing. "
				+ "This error must be redeemed before continuation.";
		int style = SWT.ICON_ERROR | SWT.OK;
		LocalMethods.methodShowMessage(headline, text, style);
		System.exit(0);
	}

	// /**
	// * Creates a user warning about no language setting. This error will arise
	// * so early after program start that language files are not loaded.
	// * Therefore the only language at this point is english.
	// */
	// private void caseConfigBasicNoLanguage() {
	// LocalMethods.methodShowMessage("Warning!",
	// "No language setting has been detected. Choose a language before submit."
	// + ". ",
	// SWT.ICON_WARNING | SWT.OK);
	// }
}
