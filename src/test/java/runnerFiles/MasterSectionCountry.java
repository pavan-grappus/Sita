package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionCountry extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}
	
	public void navigateToCountiresSection() {
		gm.refresh("countries", 5);
		gm.click(master.entities_countries, "entities_countries");
		gm.hold(5);
	}

	public void openAddCountyModal(String countryName, String countryCode) {
		gm.click(master.AddCountries, "AddCountries");
		gm.waitforElementVisible(master.nameField, 5, "countryName");
		gm.setText(master.nameField, countryName, "countryName");
		gm.setText(master.codeField, countryCode, "countryCode");
		gm.click(master.AddCountryButton, "AddCountryButton");
	}

	@Test
	public void countries() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique Country", "As a User I need to add a New Country");

		navigateToCountiresSection();

		String countryName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String countryCode = RandomStringUtils.randomAlphabetic(3);

		gm.loginfo("County Name is :" + countryName);
		gm.loginfo("Country code is :" + countryCode);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddCountyModal(countryName, countryCode);
		validateToastMessage("Created successfully", "Country created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New county is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Countries", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Company", "As a User I search for the company details added Newly");
		navigateToCountiresSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, countryName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.countryNameList("position()"), "List of Countries");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.countryNameList("position()"), countryName, "List of Countries");

		gm.verifyListofElementText(master.countryCodeList("position()"), countryCode.toUpperCase(),
				"List of Countries code");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Country Code",
				"As a User I need to add a Country with Existing county code");
		navigateToCountiresSection();
		openAddCountyModal(countryName, countryCode);
		validateToastMessage("Bad Request", "Country with this Id : " + countryCode.toUpperCase() + " already exits");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added Country Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String countyNameUpdated = countryName + " Updated";
		navigateToCountiresSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, countryName, "searchBar_input");
		gm.hold(5);
		gm.click(master.countryEditButton(countryName), "editButton(" + countryName + ")");
		gm.waitforElementVisible(master.nameField, 5, "countryName");
		gm.clearbyBackspace(master.nameField, "countryName");
		gm.setText(master.nameField, countyNameUpdated, "countryName");
		gm.verifyElementNotPresent(master.codeField, 1, "countryCode");
		gm.click(master.EditCountryButton, "EditCountryButton");
		validateToastMessage("Updated successfully", "Country updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New country is Edited", "");
		navigateToCountiresSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Countries", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Company name", "");
		navigateToCountiresSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, countyNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.countryNameList("position()"), "List of Countries");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.countryNameList("position()"), countyNameUpdated, "List of Countries");

		gm.verifyListofElementText(master.countryCodeList("position()"), countryCode.toUpperCase(),
				"List of Countries code");
		gm.EndTest();

	}

}
