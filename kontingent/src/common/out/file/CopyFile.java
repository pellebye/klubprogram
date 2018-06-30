package common.out.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import common.LocalMethods;
import common.Status;
import common.log.BBexcLog;
import common.log.CommonLog;
import common.out.file.filetest.Filetest;

public class CopyFile {

	Status status = Status.UNDEFINED;

	public Status getStatus() {
		return status;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTORS
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class copy a file from source to destination. If source happens to
	 * be a resource file this class converts backslashes to forward slashes
	 * because resource file addresses must not contain backslashes
	 * (important!).
	 *
	 * @param filetest
	 *            Current filetest.
	 */
	public CopyFile(Filetest filetest) {
		CommonLog.logger.info("class//");
		InputStream in = null;
		OutputStream out = null;
		String sourceFile = "";

		try {
			status = Status.OK;
			if (filetest.getResourceFilename().compareTo("") == 0) {
				sourceFile = LocalMethods.getFilenameWithForwardSlashes(filetest.getSourcePathAndFilename());

				in = new FileInputStream(new File(sourceFile));
			} else {
				in = CopyFile.class.getClassLoader().getResourceAsStream(filetest.getResourceFilename());
			}

			out = new FileOutputStream(new File(filetest.getDestinationPathAndFilename()));

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			System.out.println("File copied.");
			CommonLog.logger.info("message//File copied." + sourceFile);

		} catch (FileNotFoundException ex) {
			status = Status.ERROR;
			System.out.println(ex.getMessage() + " in the specified directory.");
			BBexcLog.log("FileNotFoundException", ex);
			System.exit(0);

		} catch (IOException e) {
			status = Status.ERROR;
			System.out.println(e.getMessage());
			BBexcLog.log("IOException", e);
		} catch (NullPointerException e) {
			e.printStackTrace();
			BBexcLog.log("NullPointerException", e);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			BBexcLog.log("IndexOutOfBoundsException", e);
		} finally {
			closeStreams(in, out);
		}
	}

	private void closeStreams(InputStream in, OutputStream out) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				BBexcLog.log("in != null", e);
			}
		}
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				BBexcLog.log("out != null", e);
			}
		}
	}
}
