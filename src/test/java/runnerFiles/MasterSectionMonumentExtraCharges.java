package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionMonumentExtraCharges extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToMonumentsExtraChargesection() {
		gm.refresh("MonumentsExtraChargesSection", 5);
		gm.hold(5);
		gm.click(master.entities_MonumentsExtraCharges, "entities_MonumentsExtraCharges");
		gm.hold(5);
	}

	public void openAddMonumentsExtraChargesModal(String MonumentsExtraChargesName) {

		gm.click(master.AddMonumentsExtraCharges, "AddMonumentsExtraCharges");
		gm.waitforElementVisible(master.nameField, 5, "MonumentsExtraChargesName");
		gm.setText(master.nameField, MonumentsExtraChargesName, "MonumentsExtraChargesName");
		gm.click(master.AddMonumentsExtraChargesButton, "AddMonumentsExtraChargesButton");
	}

	@Test
	public void MonumentsExtraCharges() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique MonumentsExtraCharges", "As a User I need to add a New MonumentsExtraCharges");

		navigateToMonumentsExtraChargesection();

		String MonumentsExtraChargesName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("MonumentsExtraCharges Name is :" + MonumentsExtraChargesName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddMonumentsExtraChargesModal(MonumentsExtraChargesName);
		validateToastMessage("Created successfully", "Monument extra charge created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New MonumentsExtraCharges is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Monument Extra Charges",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added MonumentsExtraCharges",
				"As a User I search for the MonumentsExtraCharges details added Newly");
		navigateToMonumentsExtraChargesection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, MonumentsExtraChargesName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.MonumentsExtraChargesNameList("position()"),
				"List of MonumentsExtraCharges");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.MonumentsExtraChargesNameList("position()"), MonumentsExtraChargesName,
				"List of MonumentsExtraCharges");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing MonumentsExtraCharges Code",
				"As a User I need to add a MonumentsExtraCharges with Existing county code");
		navigateToMonumentsExtraChargesection();
		openAddMonumentsExtraChargesModal(MonumentsExtraChargesName);
		validateToastMessage("Bad Request", "Monument extra charge with same name already exists");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added MonumentsExtraCharges Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String MonumentsExtraChargesNameUpdated = MonumentsExtraChargesName + " Updated";
		navigateToMonumentsExtraChargesection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, MonumentsExtraChargesName, "searchBar_input");
		gm.hold(5);
		gm.click(master.MonumentsExtraChargesEditButton(MonumentsExtraChargesName),
				"editButton(" + MonumentsExtraChargesName + ")");
		gm.waitforElementVisible(master.nameField, 5, "MonumentsExtraChargesName");
		gm.clearbyBackspace(master.nameField, "MonumentsExtraChargesName");
		gm.setText(master.nameField, MonumentsExtraChargesNameUpdated, "MonumentsExtraChargesName");
		gm.verifyElementNotPresent(master.codeField, 1, "MonumentsExtraChargesCode");
		gm.click(master.EditMonumentsExtraChargesButton, "EditMonumentsExtraChargesButton");
		validateToastMessage("Updated successfully", "Monument extra charge updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New MonumentsExtraCharges is Edited", "");
		navigateToMonumentsExtraChargesection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Monument Extra Charges",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated MonumentsExtraCharges name", "");
		navigateToMonumentsExtraChargesection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, MonumentsExtraChargesNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.MonumentsExtraChargesNameList("position()"),
				"List of MonumentsExtraCharges");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.MonumentsExtraChargesNameList("position()"), MonumentsExtraChargesNameUpdated,
				"List of MonumentsExtraCharges");

		gm.EndTest();

	}

}
