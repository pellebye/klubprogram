package common.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import common.LocalMethods;
import common.Status;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.ModeChecks;
import common.out.file.filetest.TypeOfTest;
import common.system.CheckSystemFiles;

public class CommonLog {
	/**
	 * Logger to log activity structure. Use:
	 * <ol>
	 * <li>"class//" for class- and method information.</li>
	 * <li>"event//" for events, i.e. update or notify.</li>
	 * <li>"message//" for only message information.</li>
	 * <li>'heading//' Logs a class heading.</li>
	 * <li>"time//" for time information.</li>
	 * <li>"mark//" for marking with <- MARK -> <- MARK ->.</li>
	 * </ol>
	 */
	public static Logger logger = Logger.getLogger(CommonLog.class.getName());

	private static String logPathAndFilename = "system/log/log.txt";

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class starts the tournament process and contains the close listener,
	 *
	 * @param shell
	 *            The basic composite for the tournament.
	 * @param logLevel
	 *            The log level.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public CommonLog(final Shell shell, Level logLevel) throws IOException, InterruptedException {

		CommonLog.logger.info("heading//" + "BridgeCharts (loglevel = " + logLevel + ")");

		shell.setLocation(300, 300);

		/*
		 * The real size is set in TabTournament.createTabSizeWidget
		 */
		shell.setSize(360, 650);

		/*
		 * Start program.
		 */
		// final TournamentProcess process = new TournamentProcess();

		/*
		 * Add shell listener.
		 */
		shell.addListener(SWT.Close, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// process.setCloseProcess();
				// event.doit = false;
			}
		});
	}

	/**
	 * Creates a new logger with a formatter (LogFormatter) and a log file. The
	 * log file will be in /system/log directory. But if directory does not
	 * exist yet (when program start for the first time) the log file will be in
	 * program user directory.
	 * 
	 * @param level
	 *            Current log level
	 */
	private static void createLogger(Level level) {
		logger.info("class//");

		createLogfiles();

		try {
			Handler logHandler = new FileHandler(logPathAndFilename);
			logHandler.setFormatter(new LogFormatter());
			BBLog.getLogger(CommonLog.class.getName()).addHandler(logHandler);
			BBLog.getLogger(CommonLog.class.getName()).setLevel(level);
			BBLog.logStart();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a log path for current logger:
	 * <ol start=1>
	 * <li>If /system/log folder does not exist (i.e. when first start of
	 * program) log folder will be 'user directory' which is default in log
	 * handler.</li>
	 * <li>Number log files log, log1, log2 etc. and create the first free
	 * number.</li>
	 * <li>Delete all log files when 25 log files in log folder.</li>
	 * </ol>
	 */
	private static void createLogfiles() {
		String logPath = "/system/log";

		Filetest filetest;
		filetest = new Filetest("/system/log");
		filetest.setModeChecks(ModeChecks.FOLDEREXISTS);
		filetest.setModeCheckDetail(ModeCheckDetail.LOGSTARTUP);
		filetest.setSystemExit(true);
		filetest.setType(TypeOfTest.FILE_FOLDER);

		/*
		 * If /system/log/ does not exist use 'user directory' which is default
		 * in 'logHandler'. When a total of 25 logs are in log folder delete all
		 * and start over again from log1.
		 */
		if (filetest.getStatus() == Status.FILEDOESNOTEXIST) {
			/*
			 * Create log file in user 'user directory'.
			 */
			logPathAndFilename = "log.txt";
		} else {
			/*
			 * Log folder exists. Create new log file in log file sequence.
			 */
			CreateCurrentLogFile currentLogFile = new CreateCurrentLogFile(logPath);
			logPathAndFilename = currentLogFile.getLogPathAndFilename();
			currentLogFile = null;
		}
	}

	/**
	 * MAIN
	 *
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {

		/*
		 * Create main shell.
		 */
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		shell.setData("shell");
		shell.setText("BridgeBuddy");
		LocalMethods.setShell(shell);

		/*
		 * Set log level.
		 */
		Level defaultLevel = Level.FINE;

		/*
		 * Create logger.
		 */
		createLogger(defaultLevel);
		CommonLog.logger.info("heading//" + "BridgeCharts (loglevel = " + defaultLevel + ")");

		checksImportantFilesAndFoldersArePresent();

		chkFirstStartup();

		new CommonLog(shell, defaultLevel);

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * Return status after having checked that important files and folders are
	 * present.
	 */
	private static void checksImportantFilesAndFoldersArePresent() {
		// ComSys.setRebuildAccepted(true);
		CheckSystemFiles systemFiles = new CheckSystemFiles();
		Status status = systemFiles.getStatus();
		systemFiles = null;

		if (status == Status.ERROR) {
			/*
			 * Status must be ok. At this point important folders and files are
			 * present but language has not yet been initialized.
			 */
			throw new IllegalArgumentException("TournamentProcess Wrong status = " + status);
		}
	}

	/**
	 * Returns true if the program is started for the first time, i.e. file
	 * 'firstStartup.txt' is missing in system folder. In that case the missing
	 * file is copied from resource folder to system folder. if
	 * 'firstStartup.txt' is already in system folder this procedure returns
	 * false. This check is needed to be sure that all necessary files are
	 * present.
	 */
	private static boolean chkFirstStartup() {

		Filetest filetest = null;

		for (int fileNo = 0; fileNo < 1; fileNo++) {
			switch (fileNo) {
			case 0:
				// firstOpstart.txt
				filetest = new Filetest("/system/firstStartup.txt");
				filetest.setResourceFilename("resource/firstStartup.txt");
				filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);
				filetest.setModeCheckDetail(ModeCheckDetail.FIRSTSTARTUP);
				filetest.setSystemExit(true);
				filetest.setType(TypeOfTest.FILE_MAKECOPY);
				break;

			default:
				throw new IllegalArgumentException("Illegal keyword - ChkSysFolder fileNo > " + 1);
			}
		}

		Status status = filetest.getStatus();

		if (status != Status.FILEEXISTS) {
			/*
			 * - - - - - - - - - - Yes, this is first startup.
			 */

			/*
			 * So copy 'firstStartup.txt' if program is started for the very
			 * first time.
			 */
			filetest = null;

			return true;
		} else {
			/*
			 * - - - - - - - - - - No, this is not first startup.
			 */
			filetest = null;

			return false;
		}
	}
}
