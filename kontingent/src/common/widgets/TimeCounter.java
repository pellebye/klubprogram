package common.widgets;

import common.log.CommonLog;

public class TimeCounter {

	private static Thread thread = new Thread();

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	public TimeCounter(int seconds) throws InterruptedException {
		CommonLog.logger.info("heading//");

		for (int i = seconds; i > 0; i++) {
			Thread.sleep(1000);
			System.out.println(i);
		}
	}
}
