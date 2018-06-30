package common.out.file.filetest;

import java.io.File;

import common.LocalMethods;
import common.Status;
import common.log.CommonLog;

/**
 * This class holds following information about a test of a filename or a folder
 * name:
 *
 * <ul>
 * <li>type - type of test (file or folder (default is UNDEFINED))</li>
 * <ol start=0>
 * <li>FILE_NOCOPY</li>
 * <li>FILE_MAKECOPY</li>
 * <li>FOLDER</li>
 * <li>UNDEFINED</li>
 * </ol>
 * <li>path - path of file or folder to be tested.</li>
 * <li>modeChecks - default is UNDEFINED</li>
 * <ol start=0>
 * <li>CONFIGBASICNOLANGUAGE</li>
 * <li>CONFIGBASICWARNING</li>
 *
 * <li>DATECONFLICT</li>
 * <li>DATENUMBERMISSING</li>
 *
 * <li>DATABASEERRORIN</li>
 * <li>DATABASENOCONNECTIONTO</li>
 * <li>DATABASEREADDATAFROM</li>
 * <li>DATABASEUPLOADTO</li>
 *
 * <li>EMPTYBOARDS</li>
 *
 * <li>FILETEST</li>
 * <li>FOLDEREXISTS</li>
 * <li>FILENAMEEXISTS</li>
 *
 * <li></li>
 *
 * <li>NAVIGATEDELETE</li>
 * <li>NAVIGATESEARCHUPWARDS</li>
 *
 * <li>PERIODEUNITERROR</li>
 *
 * <li>RESULTFILEROWNUMBERLIMIT</li>
 *
 * <li>TESTFORTESTMODE</li>
 *
 * <li>TITLEPROBLEMTOURNAMENTFILENAME</li>
 * <li>TITLEPROBLEMRESULTFILE</li>
 * <li>TOURNAMENTTYPEPROBLEM</li>
 *
 * <li>WATCHFONTSIZEATPROGRAMSTARTUP</li>
 * <li>WATCHFONTSIZEATPROGRAMEND</li>
 * <li>WATCHFILEERROR</li>
 * <li>UNDEFINED</li>
 * </ol>
 * <li>modeCheckDetail</li>
 * <ol start=0>
 * <li>BASICCONFIG - Indicate missing basic config file.</li>
 * <li>BWS - Indicate missing .bws file.</li>
 * <li>CHECKANDSTOP - Indicates program stop on error.</li>
 * <li>CURRENTCONFIG - Indicate missing current config file.</li>
 * <li>LANGUAGE - Indicate correlation to language folder or files.</li>
 * <li>LOG - Indicate correlation to log folder or files.</li>
 * <li>MOVEMENTS - Indicate correlation to movements folder or files.</li>
 * <li>SOUND - Indicate correlation to sound folder or files.</li>
 * <li>SOUNDPARTS - Indicate correlation to sound parts folder or files.</li>
 * <li>SYSTEM - Indicate correlation to system folder or files.</li>
 * <li>TOURNAMENTFILE - Indicate correlation to tournament folder or files.</li>
 * <li>WATCH - Indicate correlation to watch folder or files.</li>
 * <li>UNDEFINED</li>
 * </ol>
 * <li>systemExit - true if test result will possibly end program.</li>
 * <li>errorMessage - possible error message.</li>
 * <li>status - OK, ERROR or UPSTART (default is UNDEFINED)</li>
 * </ul>
 */
public class Filetest {

	// Do it
	private boolean fileRebuildAccepted = false;

	/**
	 * Returns true if user has accepted to rebuild system files.
	 */
	public boolean isFileRebuildAccepted() {
		return fileRebuildAccepted;
	}

	public void setFileRebuildAccepted(boolean fileRecreationAccepted) {
		this.fileRebuildAccepted = fileRecreationAccepted;
	}

	// Details
	private ModeCheckDetail modeCheckDetail = ModeCheckDetail.UNDEFINED;

	public ModeCheckDetail getModeCheckDetail() {
		return modeCheckDetail;
	}

	public void setModeCheckDetail(ModeCheckDetail modeCheckDetail) {
		this.modeCheckDetail = modeCheckDetail;
		if (modeCheckDetail == ModeCheckDetail.PATHACTUAL) {
			setFileTestInfo(pathAndFilenameWithoutUserDir);
		}
	}

	// ErrorMessage
	private String errorMessage = "";

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	// File
	private File file = null;

	public File getFile() {
		return file;
	}

	// Filename
	private String filename = "";

	public String getFilename() {
		return filename;
	}

	// ModeChecks
	private ModeChecks modeChecks = ModeChecks.UNDEFINED;

	public ModeChecks getModeChecks() {
		return modeChecks;
	}

	public void setModeChecks(ModeChecks modeChecks) {
		this.modeChecks = modeChecks;
	}

	// destination
	private String destinationPathAndFilename = "";

	public void setDestinationPathAndFilename(String destination) {
		this.destinationPathAndFilename = destination;
	}

	public String getDestinationPathAndFilename() {
		return destinationPathAndFilename;
	}

	// PathOnly
	private String pathOnly = "";

	public String getPathOnly() {
		return pathOnly;
	}

	public void setPathOnly(String pathOnly) {
		this.pathOnly = pathOnly;
		createFile();
	}

	// source
	private String sourcePathAndFilename = "";

	public String getSourcePathAndFilename() {
		return sourcePathAndFilename;
	}

	/**
	 * Adds user directory to @param sourcePathAndFilename and convert to
	 * forward slashes.
	 * 
	 * @param sourcePathAndFilename
	 *            Source file name without user directory e.g.
	 *            system/default.bws
	 */
	public void setSourcePathAndFilename(String sourcePathAndFilename) {
		// Add user directory.
		sourcePathAndFilename = LocalMethods.getUserDirectory() + "/" + sourcePathAndFilename;

		// Use forward slashes.
		sourcePathAndFilename = sourcePathAndFilename.replace("\\", "/");

		this.sourcePathAndFilename = sourcePathAndFilename;
	}

	// resource path
	private String resourceFilename = "";

	/**
	 * Sets resource filename. Only forward slashes are ok in both eclipse IDE
	 * and in compiled program.
	 * 
	 * @param resourceFilename
	 *            resource filename.
	 */
	public void setResourceFilename(String resourceFilename) {

		// Convert possible backward slashes
		resourceFilename = resourceFilename.replace("\\", "/");

		if (resourceFilename.startsWith("resource")) {
			if (this.getClass().getClassLoader().getResourceAsStream(resourceFilename) == null) {
				// new MessagesAndChecks(null, ModeChecks.FILETEST_RESOURCE,
				// resourceFilename);
			}

			File file = new File(resourceFilename);
			if (!file.exists()) {
				/*
				 * Forward slashes ok both in IDE and when compiled e.g.
				 * .getResourceAsStream("resource/default.bws");
				 */
				this.getClass().getClassLoader().getResourceAsStream("firstOpstart.txt");
			}
		}
		this.resourceFilename = resourceFilename;
	}

	public String getResourceFilename() {
		return resourceFilename;
	}

	// Status
	private Status status = Status.UNDEFINED;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	// SystemExit
	private Boolean systemExit = false;

	/**
	 * Returns true if result of current filetest will possibly end program.
	 */
	public Boolean getSystemExit() {
		return systemExit;
	}

	/**
	 * Sets possibility of current filetest to end program.
	 *
	 * @param systemExit
	 *            True if current filetest will possibly end program.
	 */
	public void setSystemExit(Boolean systemExit) {
		this.systemExit = systemExit;
	}

	// Type of test
	private TypeOfTest type = TypeOfTest.FILE_UNDEFINED;

	/**
	 * Returns type of current test. Can be FILE, FOLDER or DEFINED.
	 */
	public TypeOfTest getType() {
		return type;
	}

	/**
	 * Sets type of current test. Can be FILE, FOLDER or DEFINED.
	 *
	 * @param type
	 *            Type of current test. Can be FILE, FOLDER or DEFINED.
	 */
	public void setType(TypeOfTest type) {
		this.type = type;
	}

	private String pathAndFilenameWithoutUserDir = "";

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class holds following information about a test of a filename or a
	 * folder name:
	 *
	 * <ul>
	 * <li>destinationPathAndFilename - path of file or folder to be tested.
	 * User directory is added to path and filename.</li>
	 * <li>resourceFilename - possible resource path and filename</li>
	 * <li>modeChecks - default is UNDEFINED</li>
	 * <li>systemExit - true if test result allows program to end.</li>
	 * <li>errorMessage - possible error message.</li>
	 * <li>status - FILEEXISTS or FILEDOESNOTEXIST depending on existent of
	 * 'destinationPathAndFilename'.</li>
	 * </ul>
	 *
	 * @param destinationPathAndFilename
	 *            Path of file or folder to be tested.
	 */
	public Filetest(String destinationPathAndFilename) {
		CommonLog.logger.info("class//");

		pathAndFilenameWithoutUserDir = destinationPathAndFilename;
		CommonLog.logger.info("message//FileTestInfo pathAndFilenameWithoutUserDir = " + destinationPathAndFilename);

		// Full path
		destinationPathAndFilename = LocalMethods.getUserDirectory() + destinationPathAndFilename;

		setFileTestInfo(destinationPathAndFilename);
	}

	/**
	 * Sets file test information when path and file name are known
	 * 
	 * @param destinationPathAndFilename
	 */
	private void setFileTestInfo(String destinationPathAndFilename) {
		// Convert backward slashes.
		destinationPathAndFilename = destinationPathAndFilename.replace("\\", "/");

		this.destinationPathAndFilename = destinationPathAndFilename;

		int lastIndexOf = this.destinationPathAndFilename.lastIndexOf("/");

		pathOnly = this.destinationPathAndFilename.substring(0, lastIndexOf);

		filename = this.destinationPathAndFilename.substring(lastIndexOf + 1, destinationPathAndFilename.length());

		createFile();
	}

	private void createFile() {
		CommonLog.logger
				.info("message//FileTestInfo.createFile.destinationPathAndFilename = " + destinationPathAndFilename);

		file = null;
		file = new File(this.destinationPathAndFilename);

		if (file.exists())
			status = Status.FILEEXISTS;
		else
			status = Status.FILEDOESNOTEXIST;

		CommonLog.logger.info("message//FileTestInfo.createFile status = " + status.toString());
	}
}
