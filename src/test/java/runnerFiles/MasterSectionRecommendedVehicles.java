package runnerFiles;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionRecommendedVehicles extends BeforeRun {

	public String VehicleTypes = "";
	public String VehicleTypesUpdated = "";

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	@BeforeClass
	public void getARandomVehicleTypesDetails() {
		gm.StartTest("Navigate to VehicleTypes details page and get the Details", "");
		gm.refresh("VehicleTypes", 5);
		gm.click(master.entities_VehicleTypes, "entities_VehicleTypes");
		gm.hold(5);
		VehicleTypes = gm.getText(master.VehicleTypesNameList(1), "VehicleTypesNameList(1)");
		VehicleTypesUpdated = gm.getText(master.VehicleTypesNameList(2), "VehicleTypesNameList(2)");
		gm.EndTest();
	}

	public void navigateToRecommendedVehiclesSection() {
		gm.refresh("VehicleTypes", 5);
		gm.click(master.entities_RecommendedVehicles, "entities_RecommendedVehicles");
		gm.hold(5);
	}

	public void openAddRecommendedVehiclesModal(String RecommendedVehiclesTotalPax, String VehicleType) {
		gm.click(master.AddRecommendedVehicles, "AddRecommendedVehicles");
		gm.waitforElementVisible(master.nameField, 5, "RecommendedVehiclesName");

		gm.setText(master.RecommendedVehiclestotalPax_input, RecommendedVehiclesTotalPax,
				"RecommendedVehiclestotalPax_input");
		gm.setText(master.vehicleTypesearchComboField, VehicleType, "vehicleTypesearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Index("position()"), 4,
				"Vehicle Category selectFromComboOptions_Index");
		gm.click(master.selectFromComboOptions_Text(VehicleType), "selectFromComboOptions_Text(" + VehicleType + ")");

		gm.click(master.AddRecommendedVehiclesButton, "AddRecommendedVehiclesButton");
	}

	public void validateTheTabledata(String RecommendedVehiclesTotalPax, String VehicleType) {

		int count = gm.getSizeofWebelements(master.RecommendedVehiclesTotalPaxList("position()"),
				"List of RecommendedVehicless");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.RecommendedVehiclesTotalPaxList("position()"), RecommendedVehiclesTotalPax,
				"List of RecommendedVehiclesTotalPaxList");

		gm.verifyListofElementText(master.RecommendedVehiclesVehicleTypesNameList("position()"), VehicleType,
				"List of RecommendedVehiclesVehicleTypesNameList");

	}

	public void searchAndEditTheModaleWindow(String RecommendedVehiclesTotalpax) {
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, RecommendedVehiclesTotalpax, "searchBar_input");
		gm.hold(5);
		gm.click(master.RecommendedVehiclesEditButton(RecommendedVehiclesTotalpax),
				"editButton(" + RecommendedVehiclesTotalpax + ")");
		gm.waitforElementVisible(master.RecommendedVehiclestotalPax_input, 5, "RecommendedVehiclestotalPax_input");
	}

	@Test
	public void RecommendedVehicless() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique RecommendedVehicles", "As a User I need to add a New RecommendedVehicles");

		navigateToRecommendedVehiclesSection();

		String RecommendedVehiclesTotalpax = gm.getCurrentTime("hhmmss", "GMT");

		gm.loginfo("RecommendedVehiclesTotalpax Name is :" + RecommendedVehiclesTotalpax);
		gm.loginfo("RecommendedVehicles VehicleTypes is :" + VehicleTypes);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddRecommendedVehiclesModal(RecommendedVehiclesTotalpax, VehicleTypes);
		validateToastMessage("Created successfully", "Recommended vehicle created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New RecommendedVehicles is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Recommended Vehicles",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added RecommendedVehicles",
				"As a User I search for the RecommendedVehicles details added Newly");
		navigateToRecommendedVehiclesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, RecommendedVehiclesTotalpax, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(RecommendedVehiclesTotalpax, VehicleTypes);

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing RecommendedVehicles Name",
				"As a User I need to add a RecommendedVehicles with Existing RecommendedVehicles code");
		navigateToRecommendedVehiclesSection();
		openAddRecommendedVehiclesModal(RecommendedVehiclesTotalpax, VehicleTypes);
		validateToastMessage("Bad Request", "Recommended vehicle with same name already exists");
		gm.EndTest();

		/*
		 * Test 5 part 1
		 */

		gm.StartTest("Edit the Total Pax in newly added RecommendedVehicles Name", "");

		String RecommendedVehiclesTotalpaxUpdated = gm.getCurrentTime("hhmmss", "GMT");

		gm.loginfo("RecommendedVehiclesTotalpax Name is :" + RecommendedVehiclesTotalpaxUpdated);
		gm.loginfo("RecommendedVehicles VehicleTypes is :" + VehicleTypesUpdated);

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		navigateToRecommendedVehiclesSection();
		searchAndEditTheModaleWindow(RecommendedVehiclesTotalpax);
		gm.clearbyBackspace(master.RecommendedVehiclestotalPax_input, "RecommendedVehiclestotalPax_input");
		gm.setText(master.RecommendedVehiclestotalPax_input, RecommendedVehiclesTotalpaxUpdated,
				"RecommendedVehiclestotalPax_input");
		gm.click(master.EditRecommendedVehiclesButton, "EditRecommendedVehiclesButton");
		validateToastMessage("Updated successfully", "Recommended vehicle updated");
		gm.EndTest();

		/*
		 * Test 5 part 2
		 */

		gm.StartTest("Edit the Vehicle Type Dropdown in newly added RecommendedVehicles Name", "");

		navigateToRecommendedVehiclesSection();
		searchAndEditTheModaleWindow(RecommendedVehiclesTotalpaxUpdated);
		gm.clearbyBackspace(master.vehicleTypesearchComboField, "vehicleTypesearchComboField");
		gm.setText(master.vehicleTypesearchComboField, VehicleTypesUpdated, "vehicleTypesearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(VehicleTypesUpdated), 5,
				"selectFromComboOptions_Text(" + VehicleTypesUpdated + ")");
		gm.click(master.selectFromComboOptions_Text(VehicleTypesUpdated),
				"selectFromComboOptions_Text(" + VehicleTypesUpdated + ")");
		gm.click(master.EditRecommendedVehiclesButton, "EditRecommendedVehiclesButton");
		validateToastMessage("Updated successfully", "Vehicle type updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New RecommendedVehicles is Edited", "");
		navigateToRecommendedVehiclesSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Vehicle Types",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated RecommendedVehicles name", "");
		navigateToRecommendedVehiclesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, RecommendedVehiclesTotalpaxUpdated, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(RecommendedVehiclesTotalpaxUpdated, VehicleTypesUpdated);
		gm.EndTest();

	}

}
