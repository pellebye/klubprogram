package common.out.print;

import java.util.ArrayList;

public class Textlines extends ArrayList<String> {

	private static final long serialVersionUID = 1L;

	/**
	 * Returns this class which holds an array list of strings and information
	 * about the underlying file (existence and absolute path).
	 */
	public ArrayList<String> getLines() {
		return this;
	}

	/**
	 * Sets the array list of text lines in this class.
	 * 
	 * @param lines
	 *            The array list of text lines to be set.
	 */
	public void addLines(ArrayList<String> lines) {
		this.addAll(lines);
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class holds an array list of strings.
	 */
	public Textlines() {

	}
}
