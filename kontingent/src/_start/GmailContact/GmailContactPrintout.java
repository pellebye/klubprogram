package _start.GmailContact;

import java.util.ArrayList;
import java.util.Hashtable;

import _start.kontingent.Person;
import common.Data;
import common.log.CommonLog;
import common.out.print.PrintStringFile;

/**
 * This class creates a file for each alias to import contacts into gmail.
 */
public class GmailContactPrintout {

	/**
	 * This has to be first line in each file to import contacts into gmail.
	 */
	private String defaultFirstLine = "Name,Given Name,Additional Name,Family Name,"
			+ "Yomi Name,Given Name Yomi,Additional Name Yomi,Family Name Yomi,"
			+ "Name Prefix,Name Suffix,Initials,Nickname,Short Name,Maiden Name,"
			+ "Birthday,Gender,Location,Billing Information,Directory Server,"
			+ "Mileage,Occupation,Hobby,Sensitivity,Priority,Subject," + "Notes,Language,Photo,Group Membership,"
			+ "E-mail 1 - Type,E-mail 1 - Value,E-mail 2 - Type,E-mail 2 - Value,"
			+ "Phone 1 - Type,Phone 1 - Value,Phone 2 - Type,Phone 2 - Value,"
			+ "Address 1 - Type,Address 1 - Formatted,Address 1 - Street,"
			+ "Address 1 - City,Address 1 - PO Box,Address 1 - Region,"
			+ "Address 1 - Postal Code,Address 1 - Country,Address 1 - Extended Address,"
			+ "Website 1 - Type,Website 1 - Value";

	// ---------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------
	/**
	 * This class creates a file for each alias to import contacts into gmail.
	 */
	public GmailContactPrintout() {
		CommonLog.logger.info("heading//");

		ArrayList<Person> persons = Data.getMergedPersons();
		Hashtable<Integer, String> aliasses = Data.getAliasser();

		for (int i = 0; i < aliasses.size(); i++) {
			ArrayList<String> aliasContacts = new ArrayList<>();
			aliasContacts.add(defaultFirstLine);

			/*
			 * Create one file for each alias with the name 'contactImport.csv'
			 */
			String alias = aliasses.get(i);
			// For each person ...
			for (int j = 0; j < persons.size(); j++) {
				Person p = persons.get(j);
				// check if he is signed up for current alias.
				ArrayList<String> clubs = p.getPlayClubs();
				for (int k = 0; k < clubs.size(); k++) {
					String club = clubs.get(k);
					if (alias.compareTo(club) == 0) {
						// Yes, current person is signed up this alias.
						aliasContacts.add(p.getGmailImportContacts());
						break;
					}
				}
			}
			new PrintStringFile(aliasContacts.toArray(new String[0]), "resultater/gmail import",
					alias + "contactImport.csv");
		}

	}
}
