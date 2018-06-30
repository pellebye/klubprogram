package common.system;

public class ComSys extends CheckSystemFiles {

	/**
	 * This flag is set true in two situations. If program is started for the
	 * very first time and if user has chosen to reinstall some importen files
	 * and/or folders. At this point the flag is reset to false.
	 */
	private static boolean rebuildAccepted;

	/**
	 * Returns true if user has answered YES to accept rebuild of important
	 * system file.
	 */
	public static boolean isRebuildAccepted() {
		return rebuildAccepted;
	}

	/**
	 * This flag is set true in two situations. If program is started for the
	 * very first time and if user has chosen to reinstall some important files
	 * and/or folders. Later the flag is reset to false again.
	 * 
	 * @param rebuildAccepted
	 *            True if user want to rebuild important system file.
	 */
	public static void setRebuildAccepted(boolean rebuildAccepted) {
		ComSys.rebuildAccepted = rebuildAccepted;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class hold user answer to rebuild important system file.
	 */
	public ComSys() {

	}
}
