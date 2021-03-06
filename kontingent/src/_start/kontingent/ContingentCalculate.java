package _start.kontingent;

import java.util.ArrayList;

import common.Data;
import common.LocalMethods;
import common.out.info.InfoContingentCalculationError;

public class ContingentCalculate {

	private int totalContingent = 0;

	private int numberContingentErrors = 0;

	public int getNumberContingentErrors() {
		return numberContingentErrors;
	}

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class calculate contingent for all persons based on criteria from
	 * configuration file.
	 */
	public ContingentCalculate() {
		common.log.CommonLog.logger.info("heading//");

		ArrayList<Person> persons = Data.getMergedPersons();
		ArrayList<String[]> criterias = Data.getContingentAlgorithms();

		for (int personNo = 0; personNo < persons.size(); personNo++) {
			Person p = persons.get(personNo);
			String clubStatus = p.getClubStatus().toLowerCase();
			int playingDays = p.getNumberOfPlayingDays();

			getContingentForCurrentPerson(criterias, p, clubStatus, playingDays);
			persons.set(personNo, p);

			if (p.getContingent() > -1)
				totalContingent += p.getContingent();
			else
				numberContingentErrors++;
		}
		Data.setMergedPersons(persons);
		Data.setNumberContingentErrors(numberContingentErrors);
		Data.setTotalContingent(totalContingent);
	}

	/**
	 * Calculate contingent for current person on the basis of criteria.
	 * 
	 * @param criterias
	 *            Current list of criteria.
	 * @param p
	 *            Current person.
	 * @param clubStatus
	 *            Club status of current person.
	 * @param playingDays
	 *            Number of days current person plays each month.
	 */
	private void getContingentForCurrentPerson(ArrayList<String[]> criterias, Person p, String clubStatus,
			int playingDays) {

		for (int critNo = 0; critNo < criterias.size(); critNo++) {
			String[] criteria = criterias.get(critNo);

			/*
			 * Only the right club status of current person will be handled.
			 */
			if (clubStatus.compareTo(criteria[0]) == 0) {
				switch (criteria[1]) {
				case "<":
					contingentForLessThan(p, playingDays, criteria);
					break;
				case ">":
					contingentForGreaterThan(p, playingDays, criteria);
					break;

				default:
					contingentForOtherPossibilities(p, playingDays, criteria);
					break;
				}
			}
		}
	}

	/**
	 * Sets contingent for person if played days per month is in a range given in
	 * criteria.
	 * 
	 * @param p
	 *            Current person.
	 * @param playingDays
	 *            Number of days current person plays each month.
	 * @param criteria
	 *            Current criteria algorithm.
	 */
	private void contingentForOtherPossibilities(Person p, int playingDays, String[] criteria) {
		if (criteria[2].compareTo("-") == 0) {
			int min = checkForParsableToInt(criteria[1]);
			int max = checkForParsableToInt(criteria[3]);

			// min and max must be without error.
			if (min > -1 && max > -1) {
				if (playingDays >= min && playingDays <= max) {
					/*
					 * Set contingent of current person if playing days are in the interval between
					 * minimum and maximum.
					 */
					setContingent(p, criteria[4]);
				}
			} else
				new InfoContingentCalculationError("006 ContingentCalculation");
		} else
			new InfoContingentCalculationError("006 ContingentCalculation");
	}

	/**
	 * Sets contingent for person if played days per month is greater than days in
	 * criteria.
	 * 
	 * @param p
	 *            Current person.
	 * @param playingDays
	 *            Number of days current person plays each month.
	 * @param criteria
	 *            Current criteria algorithm.
	 */
	private void contingentForGreaterThan(Person p, int playingDays, String[] criteria) {
		int criteriaDays = checkForParsableToInt(criteria[2]);
		if (playingDays > criteriaDays) {
			setContingent(p, criteria[3]);
		}
	}

	/**
	 * Sets contingent for person if played days per month is less than days in
	 * criteria.
	 * 
	 * @param p
	 *            Current person.
	 * @param playingDays
	 *            Number of days current person plays each month.
	 * @param criteria
	 *            Current criteria algorithm.
	 */
	private void contingentForLessThan(Person p, int playingDays, String[] criteria) {
		int criteriaDays = checkForParsableToInt(criteria[2]);

		if (playingDays < criteriaDays) {
			setContingent(p, criteria[3]);
		}
	}

	/**
	 * Returns current string as an integer if possible to parse. Else returns -1.
	 * 
	 * @param string
	 *            Current string to be parsed to integer.
	 */
	private int checkForParsableToInt(String string) {
		// If not pars able return -1;
		int result = -1;

		if (LocalMethods.isParsableToInt(string))
			result = Integer.parseInt(string);

		return result;
	}

	/**
	 * Sets contingent for current person.
	 * 
	 * @param p
	 *            Current person.
	 * @param contingentAmount
	 *            Current contingent for current person.
	 */
	private void setContingent(Person p, String contingentAmount) {
		int contingent = Integer.parseInt(contingentAmount);

		p.setContigent(contingent);
	}
}
