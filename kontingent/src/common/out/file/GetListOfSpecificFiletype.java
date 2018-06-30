package common.out.file;

import java.io.File;
import java.util.ArrayList;

import common.LocalMethods;
import common.log.CommonLog;

/**
 * This class creates a list of filenames with a specific file extension.
 */
public class GetListOfSpecificFiletype {

	private ArrayList<String> list = new ArrayList<>();

	/**
	 * Returns a string array of filenames with the specified file extension.
	 */
	public String[] getList() {
		return LocalMethods.getArrayListToStringArray(list);
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class creates a list of filenames with a specific file extension.
	 * 
	 * @param fileExtension
	 *            File extension e.g. 'tur'.
	 * @param folderName
	 *            Folder name.
	 */
	public GetListOfSpecificFiletype(String fileExtension, String folderName) {
		CommonLog.logger.info("class//");

		File path = new File(folderName + "\\");
		File[] files = path.listFiles();
		list = new ArrayList<>();
		FilenameAndExtensionSplit extensionSplit = null;

		for (int i = 0; i < files.length; i++) {
			String filename = files[i].getName();
			extensionSplit = new FilenameAndExtensionSplit(filename);
			if (extensionSplit.getFilenameExtension().compareTo(fileExtension) == 0) {
				list.add(extensionSplit.getFilename());
			}
		}
		extensionSplit = null;
	}
}
