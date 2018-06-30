package common.out.file;

public class FilenameAndExtensionSplit {

	private String filename = "";

	public String getFilename() {
		return filename;
	}

	private String filenameExtension = "";

	public String getFilenameExtension() {
		return filenameExtension;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class splits file name and file name extension from original file
	 * name. If either file name or file name extension is empty they will
	 * return empty.
	 *
	 * @param originalFilename
	 *            File name including extension (e.g. 'some_file.txt' or
	 *            'some_file').
	 */
	public FilenameAndExtensionSplit(String originalFilename) {
		String[] splitString = originalFilename.split("\\.");
		if (splitString.length > 1) {
			/*
			 * Extension is what is after the last dot in file name.
			 */
			filenameExtension = splitString[splitString.length - 1];
			/*
			 * File name is original file name from start to last dot
			 * (exclusive).
			 */
			filename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
		} else
			/*
			 * File name does not contain any dot.
			 */
			filename = originalFilename;
	}
}
