package _start.randomList;

import common.Data;
import common.log.CommonLog;
import common.out.info.InfoUnexpectedError;

public class ShuffleList {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class prints random lists from what is listed in configuration file.
	 */
	public ShuffleList() {
		CommonLog.logger.info("heading//");
		CommonLog.logger.info("heading//");

		String randomList = null;
		try {
			randomList = Data.getRandomList();
		} catch (Exception e) {
			new InfoUnexpectedError("008 Shufflelist");
			e.printStackTrace();
		}
		String[] splitString = randomList.split("-");

		if (splitString.length == 2) {
			CommonLog.logger.info("message//splitString[0] =" + splitString[0]);
			CommonLog.logger.info("message//splitString[1] =" + splitString[1]);

			int from = Integer.parseInt(splitString[0]);
			int to = Integer.parseInt(splitString[1]) + 1;
			CommonLog.logger.info("message//randomList =" + randomList);
			CommonLog.logger.info("message//from =" + from);
			CommonLog.logger.info("message//to =" + to);
			for (int i = from; i < to; i++) {
				new PrintoutNewShuffleList(i);
				CommonLog.logger.info("message//i =" + i);
			}
		} else
			new InfoUnexpectedError("009 Shufflelist");

	}
}
