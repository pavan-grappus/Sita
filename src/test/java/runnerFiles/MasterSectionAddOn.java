package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionAddOn extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToAddOnSection() {
		gm.refresh("AddOn", 5);
		gm.click(master.entities_AddOns, "entities_AddOns");
		gm.hold(5);
	}

	public void openAddAddOnModal(String AddOnName) {
		gm.click(master.AddAddOns, "AddAddOns");
		gm.waitforElementVisible(master.nameField, 5, "AddOnName");
		gm.setText(master.nameField, AddOnName, "AddOnName");

		gm.click(master.AddAddOnButton, "AddAddOnButton");
	}

	public void validateTheTabledata(String AddOnName) {

		int count = gm.getSizeofWebelements(master.AddOnNameList("position()"), "List of AddOnNameList");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.AddOnNameList("position()"), AddOnName, "List of AddOnNameList");
	}

	public void searchTheAddOn(String AddOnName) {
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, AddOnName, "searchBar_input");
		gm.hold(5);
		gm.waitforElementVisible(master.AddOnNameList("position()"), 5, "List of AddOnNameList");
	}

	@Test
	public void AddOns() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique AddOn", "As a User I need to add a New AddOn");

		navigateToAddOnSection();

		String AddOnName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("AddOn Name is :" + AddOnName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		gm.loginfo("Initial Count as displayed is :" + Initialvalue);
		openAddAddOnModal(AddOnName);
		validateToastMessage("Created successfully", "Add on created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New AddOn is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Add Ons", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added AddOn", "As a User I search for the AddOn details added Newly");
		navigateToAddOnSection();
		searchTheAddOn(AddOnName);
		gm.hold(5);

		validateTheTabledata(AddOnName);

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing AddOn Name", "As a User I need to add a AddOn with Existing AddOn code");
		navigateToAddOnSection();
		openAddAddOnModal(AddOnName);
		validateToastMessage("Bad Request", "Add on with this name already exits");
		gm.EndTest();

		/*
		 * Test 8
		 */

		gm.StartTest("Validate if Edit button is not present in Add on Page", "");

		navigateToAddOnSection();
		searchTheAddOn(AddOnName);
		gm.verifyElementNotPresent(master.AddOnEditButton(AddOnName), 5, "AddOnyEditButton");

		gm.EndTest();
	}

}
