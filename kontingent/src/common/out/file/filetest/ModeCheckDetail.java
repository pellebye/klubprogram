package common.out.file.filetest;

/**
 * Possible states:
 * 
 * <blockquote>
 * <ol start=0>
 * <li>BASICCONFIG - Indicate missing basic config file.</li>
 * <li>BWS - Indicate missing .bws file.</li>
 * <li>CHECKANDSTOP - Indicates program stop on error.</li>
 * <li>CURRENTCONFIG - Indicate missing current config file.</li>
 * <li>DATABASENOTPRESENT - Indicate that database file is missing.</li>
 * <li>EXAMPLES - Indicate missing example file.</li>
 * <li>LANGUAGE - Indicate correlation to language folder or files.</li>
 * <li>LOG - Indicate correlation to log folder or files.</li>
 * <li>LOGSTARTUP - Indicate correlation to log startup, i.e. at this movement
 * log folder will not be created.</li>
 * <li>MOVEMENTS - Indicate correlation to movements folder or files.</li>
 * <li>MOVEMENTS_FILE_MISSING - Indicate that a movements file is missing.</li>
 * <li>PAIRLIST - Indicate missing pairlist.</li>
 * <li>PATHACTUAL - Indicate that path in filename should be used as is (i.e.
 * without adding user dir).</li>
 * <li>RESOURCES - Indicate correlation to resource folder or files.</li>
 * <li>RESULTFILE - Creates result file if it does not exist.</li>
 * <li>SECTION - Indicate correlation to section file.</li>
 * <li>SOUND - Indicate correlation to sound folder or files.</li>
 * <li>SOUNDPARTS - Indicate correlation to sound parts folder or files.</li>
 * <li>SYSTEM - Indicate correlation to system folder or files.</li>
 * <li>TEST - Indicate correlation to test folder or files.</li>
 * <li>TOURNAMENTFILE - Indicate correlation to tournament folder or files.</li>
 * <li>WATCH - Indicate correlation to watch folder or files.</li>
 * <li>UNDEFINED</li>
 * </ol>
 * </blockquote>
 */
public enum ModeCheckDetail {

	/*
	 * Indicate missing .bws file.
	 */
	BWS,
	/*
	 * Indicates program stop on error.
	 */
	CHECKANDSTOP,
	/*
	 * Indicate missing basic config file.
	 */
	CONFIG_BASIC,
	/*
	 * Indicate missing current config file.
	 */
	CONFIG_CURRENT,
	/*
	 * Indicate missing test mode config file.
	 */
	CONFIG_TESTMODE,
	/*
	 * Indicate that database file is missing.
	 */
	DATABASENOTPRESENT,
	/*
	 * Indicate missing example file.
	 */
	EXAMPLES,
	/*
	 * Indicate correlation to first start up of program.
	 */
	FIRSTSTARTUP,
	/*
	 * Indicate correlation to jquery ui.
	 */
	JQERY,
	/*
	 * Indicate correlation to language folder or files.
	 */
	LANGUAGE,
	/*
	 * Indicate correlation to log folder or files.
	 */
	LOG,
	/*
	 * Indicate correlation to log startup, i.e. at this movement log folder
	 * will not be created.
	 */
	LOGSTARTUP,
	/*
	 * Indicate correlation to movements folder or files.
	 */
	MOVEMENTS,
	/*
	 * Movements file is missing.
	 */
	MOVEMENTS_FILE_MISSING,
	/*
	 * Indicate missing pair list.
	 */
	PAIRLIST,
	/*
	 * Indicate that path in filename should be used as is (i.e. without adding
	 * user dir).
	 */
	PATHACTUAL,
	/*
	 * Indicate correlation to resource folder or files.
	 */
	RESOURCES,
	/*
	 * Creates result file if it does not exist.
	 */
	RESULTFILE,
	/*
	 * Indicate correlation to section file.
	 */
	SECTION,
	/*
	 * Indicate correlation to sound folder or files.
	 */
	SOUND,
	/*
	 * Indicate correlation to sound parts folder or files.
	 */
	SOUNDPARTS,
	/*
	 * Indicate correlation to system folder or files.
	 */
	SYSTEM,
	/*
	 * Indicate correlation to test folder or files.
	 */
	TEST,
	/*
	 * Indicate correlation to tournament folder or files.
	 */
	TOURNAMENTFILE,
	/*
	 * Indicate correlation to watch folder or files.
	 */
	WATCH,
	/*
	 * Indicate undefined mode details.
	 */
	UNDEFINED,
}
