package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionActivityUnits extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToActivityUnitsection() {
		gm.refresh("ActivityUnitsSection", 5);
		gm.hold(5);
		gm.click(master.entities_ActivityUnits, "entities_ActivityUnits");
		gm.hold(5);
	}

	public void openAddActivityUnitsModal(String ActivityUnitsName) {

		gm.click(master.AddActivityUnits, "AddActivityUnits");
		gm.waitforElementVisible(master.nameField, 5, "ActivityUnitsName");
		gm.setText(master.nameField, ActivityUnitsName, "ActivityUnitsName");
		gm.click(master.AddActivityUnitsButton, "AddActivityUnitsButton");
	}

	@Test
	public void ActivityUnits() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique ActivityUnits", "As a User I need to add a New ActivityUnits");

		navigateToActivityUnitsection();

		String ActivityUnitsName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("ActivityUnits Name is :" + ActivityUnitsName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddActivityUnitsModal(ActivityUnitsName);
		validateToastMessage("Created successfully", "Activity unit created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New ActivityUnits is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Activity Units", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added ActivityUnits",
				"As a User I search for the ActivityUnits details added Newly");
		navigateToActivityUnitsection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, ActivityUnitsName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.ActivityUnitsNameList("position()"), "List of ActivityUnits");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.ActivityUnitsNameList("position()"), ActivityUnitsName, "List of ActivityUnits");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing ActivityUnits Code",
				"As a User I need to add a ActivityUnits with Existing county code");
		navigateToActivityUnitsection();
		openAddActivityUnitsModal(ActivityUnitsName);
		validateToastMessage("Bad Request", "Activity unit with same name already exists");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added ActivityUnits Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String ActivityUnitsNameUpdated = ActivityUnitsName + " Updated";
		navigateToActivityUnitsection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, ActivityUnitsName, "searchBar_input");
		gm.hold(5);
		gm.click(master.ActivityUnitsEditButton(ActivityUnitsName), "editButton(" + ActivityUnitsName + ")");
		gm.waitforElementVisible(master.nameField, 5, "ActivityUnitsName");
		gm.clearbyBackspace(master.nameField, "ActivityUnitsName");
		gm.setText(master.nameField, ActivityUnitsNameUpdated, "ActivityUnitsName");
		gm.verifyElementNotPresent(master.codeField, 1, "ActivityUnitsCode");
		gm.click(master.EditActivityUnitsButton, "EditActivityUnitsButton");
		validateToastMessage("Updated successfully", "Activity unit updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New ActivityUnits is Edited", "");
		navigateToActivityUnitsection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Activity Units",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated ActivityUnits name", "");
		navigateToActivityUnitsection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, ActivityUnitsNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.ActivityUnitsNameList("position()"), "List of ActivityUnits");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.ActivityUnitsNameList("position()"), ActivityUnitsNameUpdated,
				"List of ActivityUnits");

		gm.EndTest();

	}

}
