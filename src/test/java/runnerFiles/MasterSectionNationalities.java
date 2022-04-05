package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionNationalities extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToCountiresSection() {
		gm.refresh("Nationality", 5);
		gm.click(master.entities_nationalities, "entities_Nationality");
		gm.hold(5);
	}

	public void openAddCountyModal(String NationalityName, String NationalityCode) {
		gm.click(master.AddNationality, "AddNationality");
		gm.waitforElementVisible(master.nameField, 5, "NationalityName");
		gm.setText(master.nameField, NationalityName, "NationalityName");
		gm.setText(master.codeField, NationalityCode, "NationalityCode");
		gm.click(master.AddNationalityButton, "AddNationalityButton");
	}

	@Test
	public void Nationality() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique Nationality", "As a User I need to add a New Nationality");

		navigateToCountiresSection();

		String NationalityName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String NationalityCode = RandomStringUtils.randomAlphabetic(3);

		gm.loginfo("County Name is :" + NationalityName);
		gm.loginfo("Nationality code is :" + NationalityCode);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddCountyModal(NationalityName, NationalityCode);
		validateToastMessage("Created successfully", "Nationality created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New county is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Nationalities", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Company", "As a User I search for the company details added Newly");
		navigateToCountiresSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, NationalityName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.nationalityNameList("position()"), "List of Nationality");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.nationalityNameList("position()"), NationalityName, "List of Nationality");

		gm.verifyListofElementText(master.nationalityCodeList("position()"), NationalityCode.toUpperCase(),
				"List of Nationality code");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Nationality Code",
				"As a User I need to add a Nationality with Existing county code");
		navigateToCountiresSection();
		openAddCountyModal(NationalityName, NationalityCode);
		validateToastMessage("Bad Request", "Nationality with same code already exits");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added Nationality Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String countyNameUpdated = NationalityName + " Updated";
		navigateToCountiresSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, NationalityName, "searchBar_input");
		gm.hold(5);
		gm.click(master.nationalityEditButton(NationalityName), "editButton(" + NationalityName + ")");
		gm.waitforElementVisible(master.nameField, 5, "NationalityName");
		gm.clearbyBackspace(master.nameField, "NationalityName");
		gm.setText(master.nameField, countyNameUpdated, "NationalityName");
		gm.verifyElementNotPresent(master.codeField, 1, "NationalityCode");
		gm.click(master.EditNationalityButton, "EditNationalityButton");
		validateToastMessage("Updated successfully", "Nationality updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Nationality is Edited", "");
		navigateToCountiresSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Nationalities",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Company name", "");
		navigateToCountiresSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, countyNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.nationalityNameList("position()"), "List of Nationality");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.nationalityNameList("position()"), countyNameUpdated, "List of Nationality");

		gm.verifyListofElementText(master.nationalityCodeList("position()"), NationalityCode.toUpperCase(),
				"List of Nationality code");
		gm.EndTest();

	}

}
