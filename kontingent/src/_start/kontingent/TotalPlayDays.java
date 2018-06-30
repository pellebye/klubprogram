package _start.kontingent;

import java.util.ArrayList;

import common.Data;

public class TotalPlayDays {

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class calculates the total days each person is playing per month.
	 */
	public TotalPlayDays() {
		common.log.CommonLog.logger.info("heading//");

		ArrayList<Person> mergedPersons = Data.getMergedPersons();

		for (int i = 0; i < mergedPersons.size(); i++) {
			Person person = mergedPersons.get(i);
			ArrayList<Integer> temp = person.getPlayDays();
			int result = 0;

			for (int j = 0; j < temp.size(); j++) {
				result += temp.get(j);
			}

			person.setNumberOfPlayingDays(result);
			mergedPersons.set(i, person);
		}
	}
}
