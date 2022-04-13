package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionMonumentSpecialDays_pavan extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToMonumentSpecialDaysection() {
		gm.refresh("MonumentSpecialDays", 5);
		gm.click(master.entities_MonumentSpecialDays, "entities_MonumentSpecialDays");
		gm.hold(5);
	}

	public void openAddMonumentSpecialDaysModal(String MonumentSpecialDaysName) {
		gm.click(master.AddMonumentSpecialDays, "AddMonumentSpecialDays");
		gm.waitforElementVisible(master.nameField, 5, "MonumentSpecialDaysName");
		gm.setText(master.nameField, MonumentSpecialDaysName, "MonumentSpecialDaysName");

		gm.click(master.AddMonumentSpecialDaysButton, "AddMonumentSpecialDaysButton");
	}

	public void validateTheTabledata(String MonumentSpecialDaysName) {

		int count = gm.getSizeofWebelements(master.MonumentSpecialDaysNameList("position()"),
				"List of MonumentSpecialDaysNameList");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.MonumentSpecialDaysNameList("position()"), MonumentSpecialDaysName,
				"List of MonumentSpecialDaysNameList");
	}

	public void searchTheMonumentSpecialDays(String MonumentSpecialDaysName) {
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, MonumentSpecialDaysName, "searchBar_input");
		gm.hold(5);
		gm.waitforElementVisible(master.MonumentSpecialDaysNameList("position()"), 5,
				"List of MonumentSpecialDaysNameList");
	}

	@Test
	public void MonumentSpecialDays() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique MonumentSpecialDays", "As a User I need to add a New MonumentSpecialDays");

		navigateToMonumentSpecialDaysection();

		String MonumentSpecialDaysName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("MonumentSpecialDays Name is :" + MonumentSpecialDaysName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		gm.loginfo("Initial Count as displayed is :" + Initialvalue);
		openAddMonumentSpecialDaysModal(MonumentSpecialDaysName);
		validateToastMessage("Created successfully", "Special day created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New MonumentSpecialDays is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Special Days", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added MonumentSpecialDays",
				"As a User I search for the MonumentSpecialDays details added Newly");
		navigateToMonumentSpecialDaysection();
		searchTheMonumentSpecialDays(MonumentSpecialDaysName);
		gm.hold(5);

		validateTheTabledata(MonumentSpecialDaysName);

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing MonumentSpecialDays Name",
				"As a User I need to add a MonumentSpecialDays with Existing MonumentSpecialDays code");
		navigateToMonumentSpecialDaysection();
		openAddMonumentSpecialDaysModal(MonumentSpecialDaysName);
		validateToastMessage("Bad Request", "Special day with this name already exits");
		gm.EndTest();

		/*
		 * Test 8
		 */

		gm.StartTest("Validate if Edit button is not present in Add on Page", "");

		navigateToMonumentSpecialDaysection();
		searchTheMonumentSpecialDays(MonumentSpecialDaysName);
		gm.verifyElementNotPresent(master.MonumentSpecialDaysEditButton(MonumentSpecialDaysName), 5,
				"MonumentSpecialDaysyEditButton");

		gm.EndTest();
	}

}
