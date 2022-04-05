package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionHotelGroup extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}
	
	public void navigateToHotelGroupection() {
		gm.refresh("HotelGroupSection", 5);
		gm.hold(5);
		gm.click(master.entities_HotelGroup, "entities_HotelGroup");
		gm.hold(5);
	}

	public void openAddHotelGroupModal(String HotelGroupName) {

		gm.click(master.AddHotelGroup, "AddHotelGroup");
		gm.waitforElementVisible(master.nameField, 5, "HotelGroupName");
		gm.setText(master.nameField, HotelGroupName, "HotelGroupName");
		gm.click(master.AddHotelGroupButton, "AddHotelGroupButton");
	}

	@Test
	public void HotelGroup() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique HotelGroup", "As a User I need to add a New HotelGroup");

		navigateToHotelGroupection();

		String HotelGroupName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("HotelGroup Name is :" + HotelGroupName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddHotelGroupModal(HotelGroupName);
		validateToastMessage("Created successfully", "Hotel group created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New HotelGroup is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Hotel Group", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added HotelGroup", "As a User I search for the HotelGroup details added Newly");
		navigateToHotelGroupection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, HotelGroupName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.HotelGroupNameList("position()"), "List of HotelGroup");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.HotelGroupNameList("position()"), HotelGroupName, "List of HotelGroup");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing HotelGroup Code",
				"As a User I need to add a HotelGroup with Existing county code");
		navigateToHotelGroupection();
		openAddHotelGroupModal(HotelGroupName);
		validateToastMessage("Bad Request", "Hotel group with same name already exists");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added HotelGroup Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String HotelGroupNameUpdated = HotelGroupName + " Updated";
		navigateToHotelGroupection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, HotelGroupName, "searchBar_input");
		gm.hold(5);
		gm.click(master.HotelGroupEditButton(HotelGroupName), "editButton(" + HotelGroupName + ")");
		gm.waitforElementVisible(master.nameField, 5, "HotelGroupName");
		gm.clearbyBackspace(master.nameField, "HotelGroupName");
		gm.setText(master.nameField, HotelGroupNameUpdated, "HotelGroupName");
		gm.verifyElementNotPresent(master.codeField, 1, "HotelGroupCode");
		gm.click(master.EditHotelGroupButton, "EditHotelGroupButton");
		validateToastMessage("Updated successfully", "Hotel group updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New HotelGroup is Edited", "");
		navigateToHotelGroupection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Hotel Group",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated HotelGroup name", "");
		navigateToHotelGroupection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, HotelGroupNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.HotelGroupNameList("position()"), "List of roup");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.HotelGroupNameList("position()"), HotelGroupNameUpdated, "List of HotelGroup");

		gm.EndTest();

	}

}
