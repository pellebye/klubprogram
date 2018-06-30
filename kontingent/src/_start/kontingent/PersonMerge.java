package _start.kontingent;

import java.util.ArrayList;

public class PersonMerge {

	/**
	 * Persons merged with club days.
	 */
	private ArrayList<Person> mergedPersons = new ArrayList<>();

	/**
	 * Returns an array list of persons merged with club days.
	 */
	public ArrayList<Person> getMergedPersons() {
		return mergedPersons;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class merge persons (with same member number) created from club
	 * files to one person which have information from the club files where that
	 * person are playing.
	 * 
	 * @param personsInClubLines
	 *            Persons from club files. I.e. several lines with same person
	 *            member number.
	 */
	public PersonMerge(ArrayList<Person> personsInClubLines) {
		common.log.CommonLog.logger.info("heading//");

		@SuppressWarnings("unchecked")
		ArrayList<Person> clonedPersons = (ArrayList<Person>) personsInClubLines.clone();
		// ArrayList<Person> mergedPersons = new ArrayList<>();
		ArrayList<Integer> playDates = new ArrayList<>();
		Person tempObject = null;

		for (int i = 1; i < clonedPersons.size(); i++) {
			int memberNo_0 = clonedPersons.get(i - 1).getMemberNo();
			int memberNo_1 = clonedPersons.get(i).getMemberNo();
			if (memberNo_0 == memberNo_1) {
				// Names are equal
				if (playDates.size() == 0) {
					/*
					 * First time they are equal there will be no play dates.
					 * And temp object has been set to zero.
					 */
					if (tempObject == null) {
						/*
						 * So let the first one be the gathering object.
						 */
						tempObject = clonedPersons.get(i - 1);
					}
					/*
					 * Add current number of play dates to the number of play
					 * dates in the gathering object.
					 */
					tempObject.addOnePlayDate(clonedPersons.get(i).getPlayDays().get(0));
					tempObject.addOnePlayClub(clonedPersons.get(i).getPlayClubs().get(0));
				} else {
					// Next time they are equal
					tempObject.addOnePlayDate(clonedPersons.get(i).getPlayDays().get(0));
					tempObject.addOnePlayClub(clonedPersons.get(i).getPlayClubs().get(0));
				}
			} else {
				// Names are unequal
				tempObject.getPlayDays().trimToSize();

				mergedPersons.add(tempObject);
				tempObject = clonedPersons.get(i);
			}
		}
		// Add the last person.
		mergedPersons.add(tempObject);
		mergedPersons.trimToSize();
	}
}
