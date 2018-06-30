package common.out.print;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import common.Status;
import common.log.BBexcLog;
import common.out.file.filetest.Filetest;
import common.out.file.filetest.ModeCheckDetail;
import common.out.file.filetest.ModeChecks;
import common.out.file.filetest.TypeOfTest;

/**
 * This class read text from a file with given name. If file is found
 * 'fileFound' remains true in @param 'textfile'. Otherwise 'fileFound' is set
 * to false. In the last case no exception will occur. User must handle 'file
 * not found' outside this class.
 */
public class ReadTxtFile {

	private ArrayList<String> textlinesAll = new ArrayList<String>();

	private Boolean fileFound = false;

	/**
	 * Returns true if the underlying file has been found.
	 */
	public Boolean getFileFound() {
		return fileFound;
	}

	/**
	 * Set to true if the underlying file exists.
	 * 
	 * @param fileFound
	 *            True if the underlying file exists.
	 */
	public void setFileFound(Boolean fileFound) {
		this.fileFound = fileFound;
	}

	/**
	 * Returns text lines.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getTextlines() {
		return (ArrayList<String>) textlinesAll.clone();
	}

	private ArrayList<String> textlinesReduced = new ArrayList<String>();

	/**
	 * Returns text lines without empty lines and lines starting with '//'
	 * (double forward slashes).
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getTextlinesReduced() {
		return (ArrayList<String>) textlinesReduced.clone();
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class read text from a file with given name. If file exists
	 * 'fileFound' remains true in @param 'textfile'. Otherwise 'fileFound' is
	 * set to false. In the last case no exception will occur. User must handle
	 * 'file not found' outside this class.
	 * 
	 * <p>
	 * User directory is added to filename during test of existence.
	 * </p>
	 *
	 * <p>
	 * For some reason when string has length of one and contain -1 it is fake
	 * and must be deleted not to make intermittent failure.
	 * </p>
	 *
	 * @param filename
	 *            <ol start=1>
	 *            <li>Filename and path (relative) of file from where to read
	 *            text.</li>
	 *            <li>User can get all text in file, or all text except empty
	 *            lines and lines starting with '//' (double forward
	 *            slashes).</li>
	 *            <li>This class adds user directory to filename.</li>
	 *            </ol>
	 * @param modeTextLines
	 *            ALLLINES or WITHOUTCOMMENTS.
	 * @param textlines
	 *            Contains text lines (with or without comments) of and
	 *            information about whether file was found.
	 * @param typeOfTest
	 *            Can be FILE_NOCOPY, FILE_MAKECOPY, FILE_FOLDER, or DONOTHING.
	 * @param modeCheckDetail
	 *            Current details about test.
	 */
	public ReadTxtFile(String filename, ModeTextLines modeTextLines, Textlines textlines, TypeOfTest typeOfTest,
			ModeCheckDetail modeCheckDetail) {

		Filetest fileTest = checkFilenameForExistence(filename, typeOfTest, modeCheckDetail);

		/*
		 * User may handle file not found status.
		 */
		fileFound = (fileTest.getStatus() == Status.OK);

		readFileContent(modeTextLines, textlines, fileTest);
	}

	private void readFileContent(ModeTextLines modeTextLines, Textlines textlines, Filetest fileTest) {

		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream(fileTest.getDestinationPathAndFilename()), "UTF-8"));

			String line = "";
			boolean firstLine = true;

			while ((line = in.readLine()) != null) {
				System.out.println(line);

				if (firstLine) {
					/*
					 * When string has length of one and contain -1 it must be
					 * deleted. I guess it is wrong utf-8 format (without BOM).
					 */
					char[] c = line.toCharArray();
					byte b = 0;
					if (c.length > 0) {
						b = (byte) c[0];
					}
					if (b == -1) {
						// TODO ??? what ???
						byte[] byt = line.getBytes();

						if (byt[0] == 63) {
							// if b[0] = 63 -> delete
							byte[] temp = new byte[byt.length - 1];
							for (int i = 1; i < byt.length; i++) {
								temp[i - 1] = byt[i];
							}
							line = new String(temp);
						}
					} else {
					}
					firstLine = false;
				}
				textlinesAll.add(line);
				addReducedLinesIfNotEmptyOrRemark(line);
			}

			in.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			BBexcLog.log("in = new BufferedReader - 1", e);
		} catch (IOException e) {
			e.printStackTrace();
			BBexcLog.log("in = new BufferedReader - 2", e);
		} catch (Exception e) {
			e.printStackTrace();
			common.log.BBexcLog.log("in = new BufferedReader - 3", e);
		}

		switch (modeTextLines) {
		case ALLLINES:
			textlines.addLines(textlinesAll);
			break;
		case WITHOUTCOMMENTS:
			textlines.addLines(textlinesReduced);
			break;
		default:
			break;
		}
	}

	/**
	 * Returns an instance of 'FileTestInfo' after test for existence of
	 * 'filename' (without user directory).
	 * 
	 * @param filename
	 *            Current filename. (This procedure adds user directory.)
	 * @param typeOfTest
	 *            Can be FILE_NOCOPY, FILE_MAKECOPY, FILE_FOLDER, or DONOTHING.
	 * @param modeCheckDetail
	 *            Current details about test.
	 */
	private Filetest checkFilenameForExistence(String filename, TypeOfTest typeOfTest,
			ModeCheckDetail modeCheckDetail) {

		Filetest filetest = new Filetest(filename);
		filetest.setModeChecks(ModeChecks.FILENAMEEXISTS);

		if (modeCheckDetail == ModeCheckDetail.PATHACTUAL)
			filetest.setModeCheckDetail(modeCheckDetail);
		else
			filetest.setModeCheckDetail(ModeCheckDetail.CHECKANDSTOP);

		filetest.setSystemExit(true);
		filetest.setType(typeOfTest);

		// new MessagesAndChecks(null, ModeChecks.FILETEST, filetest);

		return filetest;
	}

	/**
	 * Add lines to reduced text lines if lines are empty or begin with '//'.
	 * Note that lines containing only spaces are treated as empty lines.
	 * 
	 * @param line
	 */
	private void addReducedLinesIfNotEmptyOrRemark(String line) {

		// Make sure that empty lines with spaces are treated as empty lines.
		line = line.trim();

		if (line.compareTo("") != 0 && !line.startsWith("//")) {
			textlinesReduced.add(line);
		}
	}
}
