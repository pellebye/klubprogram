package common.out.file.filetest;

/**
 * @formatter:off
 *                <ul>
 * 
 *                <li>DATE</li>
 *                <ol start=1>
 *                <li>DATE_CONFLICT</li>
 *                <li>DATE_NUMBERMISSING</li>
 *                </ol>
 * 
 *                <li>DATABASE</li>
 *                <ol start=3>
 *                <li>DATABASE_ADAPTION</li>
 *                <li>DATABASE_READDATAFROM</li>
 *                <li>DATABASE_UPLOADTO</li>
 *                </ol>
 * 
 *                <li>BOARDS</li>
 *                <ol start=6>
 *                <li>EMPTYBOARDS</li>
 *                </ol>
 * 
 *                <li>FILES</li>
 *                <ol start=7>
 *                <li>FILETEST</li>
 *                <li>FILETEST_RESOURCE</li>
 *                <li>FILETEST_SYSTEMFILES</li>
 *                </ol>
 *
 *                <li>INTERPRET</li>
 *                <ol start=10>
 *                <li>INTERPRET_ERROR</li>
 *                <li>INTERPRETFILE_ILLEGALCOMMAND</li>
 *                </ol>
 *
 *                <li>NAVIGATE</li>
 *                <ol start=12>
 *                <li>NAVIGATE_SEARCHUPWARDS</li>
 *                </ol>
 *
 *                <li>PAIR</li>
 *                <ol start=13>
 *                <li>PAIR_ID</li>
 *                </ol>
 *
 *                <li>PERIODE</li>
 *                <ol start=14>
 *                <li>PERIODE_UNITERROR</li>
 *                </ol>
 *
 *                <li>RESULTFILE</li>
 *                <ol start=15>
 *                <li>RESULTFILE_SECTIONNUMBERLIMIT</li>
 *                <li>RESULTFILE_SECTIONERROR</li>
 *                <li>RESULTFILE_TITLEPROBLEM</li>
 *                </ol>
 *
 *                <li>TEST</li>
 *                <ol start=18>
 *                <li>TEST_FORTESTMODE</li>
 *                <li>TEST_GENERAL</li>
 *                </ol>
 *
 *                <li>TOURNAMENT</li>
 *                <ol start=20>
 *                <li>TOURNAMENT_FILENAMETITLEPROBLEM</li>
 *                <li>TOURNAMENT_TYPEPROBLEM</li>
 *                </ol>
 *
 *                <li>WATCH</li>
 *                <ol start=22>
 *                <li>WATCH_FONTSIZEATPROGRAMSTARTUP</li>
 *                <li>WATCH_FONTSIZEATPROGRAMEND</li>
 *                </ol>
 *                </ul>
 * @formatter:on
 */
public enum ModeChecks {

	/*
	 * No club lines has been readen.
	 */
	CLUBLINES_EMPTY,
	// Date
	/*
	 * Current date in result file is not equal with current date in 'ComData'.
	 */
	DATE_CONFLICT,
	/*
	 * Date string array is empty. First date has to be set up.
	 */
	DATE_NUMBERMISSING,

	// Database
	/*
	 * Is used when creation of a bws file which adapt to the format in
	 * BridgeCentral.
	 */
	DATABASE_ADAPTION,
	/*
	 * Is used when there are results in database. User is asked for the results
	 * to be read into program.
	 */
	DATABASE_READDATAFROM,
	/*
	 * Is used when user decides to upload reset values to database.
	 */
	DATABASE_UPLOADTO,

	// Boards
	/*
	 * checks that all boards have been filled out.
	 */
	EMPTYBOARDS,

	// Files
	/*
	 * A base for all tests concerning files, folders and paths.
	 */
	FILETEST,
	/*
	 * Tests concerning resource files.
	 */
	FILETEST_RESOURCE,
	/*
	 * Tests concerning system files, folders and paths.
	 */
	FILETEST_SYSTEMFILES,
	/*
	 * Test run under 'FILETEST' to check if folder exists.
	 */
	FOLDEREXISTS,
	/*
	 * Test run under 'FILETEST' to check if filename exists.
	 */
	FILENAMEEXISTS,

	// Interpret
	/*
	 * Error in interpret control.
	 */
	INTERPRET_ERROR,
	/*
	 * Result file contains an illegal command.
	 */
	INTERPRETFILE_ILLEGALCOMMAND,

	// Navigate
	/*
	 * Error when searching upwards in key in cells where the number of cells
	 * containing text to search for, is less than the number of cells
	 * containing text.
	 */
	NAVIGATE_SEARCHUPWARDS,
	/*
	 * Pair id in result file and in pair list discrepancy.
	 */
	PAIR_ID,
	/*
	 * Error when creating period units.
	 */
	PERIOD_UNITERROR,

	/*
	 * Used for print out of random sorted pair list.
	 */
	PROMOTION,

	// Result file
	/*
	 * Section name in result file does not exists.
	 */
	RESULTFILE_SECTIONERROR,
	/*
	 * Checks if section number is correct.
	 */
	RESULTFILE_SECTIONNUMBERLIMIT,
	/*
	 * Title in tournament file and title in result file does not correlate.
	 */
	RESULTFILE_TITLEPROBLEM,

	// Test
	TEST_FORTESTMODE,
	/*
	 * Used to setup a test dialog any place for test.
	 */
	TEST_GENERAL,

	// Tournament
	/*
	 * Tournament filename and title in tournament file does not correlate.
	 */
	TOURNAMENT_FILENAMETITLEPROBLEM,
	/*
	 * Tournament type does not correlate in result file and tournament file.
	 */
	TOURNAMENT_TYPEPROBLEM,

	// Watch
	/*
	 * Set font size at program startup.
	 */
	WATCH_FONTSIZEATPROGRAMSTARTUP,
	/*
	 * Set font size at program end.
	 */
	WATCH_FONTSIZEATPROGRAMEND,
	/*
	 * Used when a new watch file has been created.
	 */
	WATCH_NEWFILE,

	// others
	UNDEFINED,
	/*
	 * Test if a file exists.
	 */
	new_FILEEXIST,
	/*
	 * Test if a folder exists.
	 */
	new_FOLDEREXIST,
}
