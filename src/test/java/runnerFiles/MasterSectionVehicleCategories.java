package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionVehicleCategories extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateTovehicleCategoriesection() {
		gm.refresh("vehicleCategoriesSection", 5);
		gm.hold(5);
		gm.click(master.entities_vehicleCategories, "entities_vehicleCategories");
		gm.hold(5);
	}

	public void openAddvehicleCategoriesModal(String vehicleCategoriesName) {

		gm.click(master.AddvehicleCategories, "AddvehicleCategories");
		gm.waitforElementVisible(master.nameField, 5, "vehicleCategoriesName");
		gm.setText(master.nameField, vehicleCategoriesName, "vehicleCategoriesName");
		gm.click(master.AddvehicleCategoriesButton, "AddvehicleCategoriesButton");
	}

	@Test
	public void vehicleCategories() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique vehicleCategories", "As a User I need to add a New vehicleCategories");

		navigateTovehicleCategoriesection();

		String vehicleCategoriesName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("vehicleCategories Name is :" + vehicleCategoriesName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddvehicleCategoriesModal(vehicleCategoriesName);
		validateToastMessage("Created successfully", "Vehicle category created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New vehicleCategories is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Vehicle Categories",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added vehicleCategories",
				"As a User I search for the vehicleCategories details added Newly");
		navigateTovehicleCategoriesection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, vehicleCategoriesName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.vehicleCategoriesNameList("position()"), "List of vehicleCategories");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.vehicleCategoriesNameList("position()"), vehicleCategoriesName,
				"List of vehicleCategories");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing vehicleCategories Code",
				"As a User I need to add a vehicleCategories with Existing county code");
		navigateTovehicleCategoriesection();
		openAddvehicleCategoriesModal(vehicleCategoriesName);
		validateToastMessage("Bad Request", "vehicle Categories with same name already exists");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added vehicleCategories Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String vehicleCategoriesNameUpdated = vehicleCategoriesName + " Updated";
		navigateTovehicleCategoriesection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, vehicleCategoriesName, "searchBar_input");
		gm.hold(5);
		gm.click(master.vehicleCategoriesEditButton(vehicleCategoriesName),
				"editButton(" + vehicleCategoriesName + ")");
		gm.waitforElementVisible(master.nameField, 5, "vehicleCategoriesName");
		gm.clearbyBackspace(master.nameField, "vehicleCategoriesName");
		gm.setText(master.nameField, vehicleCategoriesNameUpdated, "vehicleCategoriesName");
		gm.verifyElementNotPresent(master.codeField, 1, "vehicleCategoriesCode");
		gm.click(master.EditvehicleCategoriesButton, "EditvehicleCategoriesButton");
		validateToastMessage("Updated successfully", "vehicle category updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New vehicleCategories is Edited", "");
		navigateTovehicleCategoriesection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Vehicle Categories",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated vehicleCategories name", "");
		navigateTovehicleCategoriesection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, vehicleCategoriesNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.vehicleCategoriesNameList("position()"), "List of vehicleCategories");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.vehicleCategoriesNameList("position()"), vehicleCategoriesNameUpdated,
				"List of vehicleCategories");

		gm.EndTest();

	}

}
