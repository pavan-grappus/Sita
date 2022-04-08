package runnerFiles;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionVehicleTypes extends BeforeRun {

	public String VehicleCategories = "";
	public String VehicleCategoriesUpdated = "";

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	@BeforeClass
	public void getARandomVehicleCategoriesDetails() {
		gm.StartTest("Navigate to VehicleCategories details page and get the Details", "");
		gm.refresh("VehicleCategories", 5);
		gm.click(master.entities_vehicleCategories, "entities_vehicleCategories");
		gm.hold(5);
		VehicleCategories = gm.getText(master.vehicleCategoriesNameList(1), "vehicleCategoriesNameList(1)");
		VehicleCategoriesUpdated = gm.getText(master.vehicleCategoriesNameList(2), "vehicleCategoriesNameList(1)");
		gm.EndTest();
	}

	public void navigateToVehicleTypesSection() {
		gm.refresh("VehicleTypes", 5);
		gm.click(master.entities_VehicleTypes, "entities_VehicleTypes");
		gm.hold(5);
	}

	public void openAddVehicleTypesModal(String VehicleTypesName, String VehicleCategory, String minAdultcap,
			String maxAdultCap, String minChildCap, String maxChildCap) {
		gm.click(master.AddVehicleTypes, "AddVehicleTypes");
		gm.waitforElementVisible(master.nameField, 5, "VehicleTypesName");
		gm.setText(master.nameField, VehicleTypesName, "VehicleTypesName");
		gm.setText(master.vehicleCategorysearchComboField, VehicleCategory, "vehicleCategorysearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Index("position()"), 1,
				"Vehicle Category selectFromComboOptions_Index");
		gm.click(master.selectFromComboOptions_Text(VehicleCategory), "VehicleCategory(" + VehicleCategory + ")");
		gm.setText(master.minAdultCapacity, minAdultcap, "minAdultCapacity");
		gm.setText(master.maxAdultCapacity, maxAdultCap, "maxAdultCapacity");
		gm.setText(master.minChildCapacity, minChildCap, "minChildCapacity");
		gm.setText(master.maxChildCapacity, maxChildCap, "maxChildCapacity");

		gm.click(master.AddVehicleTypesButton, "AddVehicleTypesButton");
	}

	public void validateTheTabledata(String VehicleTypesName, String minAdultcap, String maxAdultCap,
			String minChildCap, String maxChildCap, String VehicleCategory) {

		int count = gm.getSizeofWebelements(master.VehicleTypesNameList("position()"), "List of VehicleTypess");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.VehicleTypesNameList("position()"), VehicleTypesName,
				"List of VehicleTypesName");

		gm.verifyListofElementText(master.VehicleTypesMinimumAdultCapacityList("position()"), minAdultcap,
				"List of VehicleTypesMinimumAdultCapacityList");

		gm.verifyListofElementText(master.VehicleTypesMaximumAdultCapacityList("position()"), maxAdultCap,
				"List of VehicleTypesMaximumAdultCapacityList");
		gm.verifyListofElementText(master.VehicleTypesMinimumChildCapacityList("position()"), minChildCap,
				"List of VehicleTypesMinimumChildCapacityList");
		gm.verifyListofElementText(master.VehicleTypesMaximumChildCapacityList("position()"), maxChildCap,
				"List of VehicleTypesMaximumChildCapacityList");
		gm.verifyListofElementText(master.VehicleTypesVehicleCategoryList("position()"), VehicleCategory,
				"List of VehicleTypesVehicleCategoryList");

	}

	public void searchAndEditTheModaleWindow(String VehicleTypesName) {
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, VehicleTypesName, "searchBar_input");
		gm.hold(5);
		gm.click(master.VehicleTypesEditButton(VehicleTypesName), "editButton(" + VehicleTypesName + ")");
		gm.waitforElementVisible(master.nameField, 5, "VehicleTypesName");
	}

	@Test
	public void VehicleTypess() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique VehicleTypes", "As a User I need to add a New VehicleTypes");

		navigateToVehicleTypesSection();

		String VehicleTypesName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String minAdultCap = "1";
		String maxAdultCap = "11";
		String minChildCap = "21";
		String maxChildCap = "31";

		gm.loginfo("VehicleTypes Name is :" + VehicleTypesName);
		gm.loginfo("VehicleTypes Vehicle Category is :" + VehicleCategories);
		gm.loginfo("VehicleTypes minAdultCap is :" + minAdultCap);
		gm.loginfo("VehicleTypes maxAdultCap is :" + maxAdultCap);
		gm.loginfo("VehicleTypes minChildCap is :" + minChildCap);
		gm.loginfo("VehicleTypes maxChildCap is :" + maxChildCap);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddVehicleTypesModal(VehicleTypesName, VehicleCategories, minAdultCap, maxAdultCap, minChildCap,
				maxChildCap);
		validateToastMessage("Created successfully", "Vehicle type created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New VehicleTypes is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Vehicle Types", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added VehicleTypes",
				"As a User I search for the VehicleTypes details added Newly");
		navigateToVehicleTypesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, VehicleTypesName, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(VehicleTypesName, minAdultCap, maxAdultCap, minChildCap, maxChildCap, VehicleCategories);

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing VehicleTypes Name",
				"As a User I need to add a VehicleTypes with Existing VehicleTypes code");
		navigateToVehicleTypesSection();
		openAddVehicleTypesModal(VehicleTypesName, VehicleCategories, minAdultCap, maxAdultCap, minChildCap,
				maxChildCap);
		validateToastMessage("Bad Request", "Vehicle Type with same name already exists");
		gm.EndTest();

		/*
		 * Test 5 part 1
		 */

		String VehicleTypesNameUpdated = VehicleTypesName + " Updated";
		String minAdultCapUpdated = "5";
		String maxAdultCapUpdated = "15";
		String minChildCapUpdated = "25";
		String maxChildCapUpdated = "35";

		gm.StartTest("Edit the VehicleCategory Name in newly added VehicleTypes Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		gm.loginfo("VehicleTypes Name is :" + VehicleTypesNameUpdated);
		gm.loginfo("VehicleTypes Vehicle Category is :" + VehicleCategoriesUpdated);
		gm.loginfo("VehicleTypes minAdultCap is :" + minAdultCapUpdated);
		gm.loginfo("VehicleTypes maxAdultCap is :" + maxAdultCapUpdated);
		gm.loginfo("VehicleTypes minChildCap is :" + minChildCapUpdated);
		gm.loginfo("VehicleTypes maxChildCap is :" + maxChildCapUpdated);

		navigateToVehicleTypesSection();
		searchAndEditTheModaleWindow(VehicleTypesName);
		gm.clearbyBackspace(master.nameField, "nameField");
		gm.setText(master.nameField, VehicleTypesNameUpdated, "Vehicle Type nameField");
		gm.click(master.EditVehicleTypesButton, "EditVehicleTypesButton");
		validateToastMessage("Updated successfully", "Vehicle type updated");
		gm.EndTest();

		/*
		 * Test 5 part 2
		 */

		gm.StartTest("Edit the VehicleCategory Dropdown in newly added VehicleTypes Name", "");

		navigateToVehicleTypesSection();
		searchAndEditTheModaleWindow(VehicleTypesNameUpdated);
		gm.clearbyBackspace(master.vehicleCategorysearchComboField, "vehicleCategorysearchComboField");
		gm.setText(master.vehicleCategorysearchComboField, VehicleCategoriesUpdated, "vehicleCategorysearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(VehicleCategoriesUpdated), 5,
				"selectFromComboOptions_Text(" + VehicleCategoriesUpdated + ")");
		gm.click(master.selectFromComboOptions_Text(VehicleCategoriesUpdated),
				"selectFromComboOptions_Text(" + VehicleCategoriesUpdated + ")");
		gm.click(master.EditVehicleTypesButton, "EditVehicleTypesButton");
		validateToastMessage("Updated successfully", "Vehicle type updated");
		gm.EndTest();

		/*
		 * Test 5 part 3
		 */

		gm.StartTest("Edit the Min Adult Capacity in newly added VehicleTypes Name", "");

		navigateToVehicleTypesSection();
		searchAndEditTheModaleWindow(VehicleTypesNameUpdated);
		gm.clearbyBackspace(master.minAdultCapacity, "minAdultCapacity");
		gm.setText(master.minAdultCapacity, minAdultCapUpdated, "minAdultCapacity");
		gm.click(master.EditVehicleTypesButton, "EditVehicleTypesButton");
		validateToastMessage("Updated successfully", "Vehicle type updated");
		gm.EndTest();

		/*
		 * Test 5 part 4
		 */

		gm.StartTest("Edit the Max Adult Capacity in newly added VehicleTypes Name", "");

		navigateToVehicleTypesSection();
		searchAndEditTheModaleWindow(VehicleTypesNameUpdated);
		gm.clearbyBackspace(master.maxAdultCapacity, "maxAdultCapacity");
		gm.setText(master.maxAdultCapacity, maxAdultCapUpdated, "maxAdultCapacity");
		gm.click(master.EditVehicleTypesButton, "EditVehicleTypesButton");
		validateToastMessage("Updated successfully", "Vehicle type updated");
		gm.EndTest();

		/*
		 * Test 5 part 5
		 */

		gm.StartTest("Edit the Min Child Capacity in newly added VehicleTypes Name", "");

		navigateToVehicleTypesSection();
		searchAndEditTheModaleWindow(VehicleTypesNameUpdated);
		gm.clearbyBackspace(master.minChildCapacity, "minChildCapacity");
		gm.setText(master.minChildCapacity, minChildCapUpdated, "minChildCapacity");
		gm.click(master.EditVehicleTypesButton, "EditVehicleTypesButton");
		validateToastMessage("Updated successfully", "Vehicle type updated");
		gm.EndTest();

		/*
		 * Test 5 part 6
		 */

		gm.StartTest("Edit the Max Child Capacity in newly added VehicleTypes Name", "");

		navigateToVehicleTypesSection();
		searchAndEditTheModaleWindow(VehicleTypesNameUpdated);
		gm.clearbyBackspace(master.maxChildCapacity, "maxChildCapacity");
		gm.setText(master.maxChildCapacity, maxChildCapUpdated, "maxChildCapacity");
		gm.click(master.EditVehicleTypesButton, "EditVehicleTypesButton");
		validateToastMessage("Updated successfully", "Vehicle type updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New VehicleTypes is Edited", "");
		navigateToVehicleTypesSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Vehicle Types",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated VehicleTypes name", "");
		navigateToVehicleTypesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, VehicleTypesNameUpdated, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(VehicleTypesNameUpdated, minAdultCapUpdated, maxAdultCapUpdated, minChildCapUpdated,
				maxChildCapUpdated, VehicleCategoriesUpdated);
		gm.EndTest();

	}

}
