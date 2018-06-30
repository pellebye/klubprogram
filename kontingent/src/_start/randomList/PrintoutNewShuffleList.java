package _start.randomList;

import java.util.ArrayList;
import java.util.Random;

import common.log.CommonLog;
import common.out.print.PrintStringFile;

public class PrintoutNewShuffleList {

	private String filename = "";

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * Prints out a new pair list where pairs are sorted in different ways
	 * dependent on promotion:
	 *
	 * <ul>
	 * <li>NOPROMOTION => Sorts global pairlist on local pair numbers to get
	 * same order as at start.</li>
	 * <li>SOMEPROMOTION => Lets a chosen number of pairs promote (up and down).
	 * </li>
	 * <li>ALLSORTEDANDROWSRANDOM => Sorts global pairlist on sum of tournament
	 * results and match points, most points in section one, second most in
	 * section two, and so on. Then sorts random in sections.</li>
	 * <li>ALLSORTEDANDNOTHINGMORE => Sorts global pairlist on sum of tournament
	 * results and match points, most points in section one, second most in
	 * section two, and so on.</li>
	 * <li>ALLSORTEDINONEPOOL => Makes a random list of global pairlist.</li>
	 * </ul>
	 * 
	 * @param nextdate
	 * @param promotion
	 *
	 * @param turnament
	 *            Current tournament.
	 */
	public PrintoutNewShuffleList(int max) {
		CommonLog.logger.info("heading//");

		int[] result = new int[max];
		Random randomGenerator = new Random();
		int resultNumber = 0;
		int randomInt = 0;
		while (resultNumber < max) {
			randomInt = randomGenerator.nextInt(max);
			int i = 0;
			for (i = 0; i < resultNumber; i++) {
				if (result[i] == randomInt)
					break;
			}
			if (i == resultNumber) {
				result[resultNumber] = randomInt;
				resultNumber++;
			}
		}

		ArrayList<String> printlist = new ArrayList<String>();
		String path = "resultater/randomlister";
		filename = "random_" + max + ".txt";

		/*
		 * Convert from integer (plus one to change from base zero to base one)
		 * to string.
		 */
		for (int i = 0; i < result.length; i++) {
			printlist.add(String.valueOf(result[i] + 1));
		}

		new PrintStringFile(printlist.toArray(new String[0]), path, filename);
	}
}
