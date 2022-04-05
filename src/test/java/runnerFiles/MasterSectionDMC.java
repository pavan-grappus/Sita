package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionDMC extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToDMCection() {
		gm.refresh("DMCSection", 5);
		gm.hold(5);
		gm.click(master.entities_DMC, "entities_DMC");
		gm.hold(5);
	}

	public void openAddDMCModal(String DMCName) {

		gm.click(master.AddDMC, "AddDMC");
		gm.waitforElementVisible(master.nameField, 5, "DMCName");
		gm.setText(master.nameField, DMCName, "DMCName");
		gm.click(master.AddDMCButton, "AddDMCButton");
	}

	@Test
	public void DMC() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique DMC", "As a User I need to add a New DMC");

		navigateToDMCection();

		String DMCName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("DMC Name is :" + DMCName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddDMCModal(DMCName);
		validateToastMessage("Created successfully", "DMC created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New DMC is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " DMC", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added DMC", "As a User I search for the DMC details added Newly");
		navigateToDMCection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, DMCName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.DMCNameList("position()"), "List of DMC");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.DMCNameList("position()"), DMCName, "List of DMC");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing DMC Code", "As a User I need to add a DMC with Existing county code");
		navigateToDMCection();
		openAddDMCModal(DMCName);
		validateToastMessage("Bad Request", "DMC with same name already exists");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added DMC Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String DMCNameUpdated = DMCName + " Updated";
		navigateToDMCection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, DMCName, "searchBar_input");
		gm.hold(5);
		gm.click(master.DMCEditButton(DMCName), "editButton(" + DMCName + ")");
		gm.waitforElementVisible(master.nameField, 5, "DMCName");
		gm.clearbyBackspace(master.nameField, "DMCName");
		gm.setText(master.nameField, DMCNameUpdated, "DMCName");
		gm.verifyElementNotPresent(master.codeField, 1, "DMCCode");
		gm.click(master.EditDMCButton, "EditDMCButton");
		validateToastMessage("Updated successfully", "DMC updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New DMC is Edited", "");
		navigateToDMCection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " DMC", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated DMC name", "");
		navigateToDMCection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, DMCNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.DMCNameList("position()"), "List of DMC");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.DMCNameList("position()"), DMCNameUpdated, "List of DMC");

		gm.EndTest();

	}

}
