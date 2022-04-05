package runnerFiles;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionQuerySpecialNeeds extends BeforeRun {

	public String SpecialNeedsHotelFacility = "";

	public String SpecialNeedsHotelFacilityUpdated = "";

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	@BeforeClass
	public void getARandomHotelFacilityDetails() {
		gm.StartTest("Navigate to Hotel Facility details page and get the Details", "");
		gm.refresh("Special Needss", 5);
		gm.click(master.entities_hotelFacility, "entities_hotelFacility");
		gm.hold(5);
		SpecialNeedsHotelFacility = gm.getText(master.hotelFacilityNameList(1), "hotelFacilityNameList(1)");
		SpecialNeedsHotelFacilityUpdated = gm.getText(master.hotelFacilityNameList(2), "hotelFacilityNameList(2)");
		gm.EndTest();
	}

	public void navigateToSpecialNeedssSection() {
		gm.refresh("SpecialNeedss", 5);
		gm.click(master.entities_SpecialNeeds, "entities_SpecialNeeds");
		gm.hold(5);
	}

	public void openAddSpecialNeedsModal(String SpecialNeedsName, String SpecialNeedsHotelFacilityName) {
		gm.click(master.AddSpecialNeeds, "AddSpecialNeeds");
		gm.waitforElementVisible(master.nameField, 5, "SpecialNeedsName");
		gm.setText(master.nameField, SpecialNeedsName, "SpecialNeedsName");
		gm.setText(master.HotelFacilitysearchComboField, SpecialNeedsHotelFacilityName,
				"HotelFacilitysearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Index("position()"), 1,
				"HotelFacility selectFromComboOptions_Index");
		gm.click(master.selectFromComboOptions_Text(SpecialNeedsHotelFacilityName), "SpecialNeeds HotelFacility");
		gm.click(master.AddSpecialNeedsButton, "AddSpecialNeedsButton");
	}

	@Test
	public void SpecialNeedss() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique SpecialNeeds", "As a User I need to add a New SpecialNeeds");

		navigateToSpecialNeedssSection();

		String SpecialNeedsName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("SpecialNeeds Name is :" + SpecialNeedsName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddSpecialNeedsModal(SpecialNeedsName, SpecialNeedsHotelFacility);
		validateToastMessage("Created successfully", "Special Need created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New SpecialNeeds is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Special Needs", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added SpecialNeeds",
				"As a User I search for the SpecialNeeds details added Newly");
		navigateToSpecialNeedssSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SpecialNeedsName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.specialNeedsNameList("position()"), "List of SpecialNeedss");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.specialNeedsNameList("position()"), SpecialNeedsName,
				"List of SpecialNeedss");

		gm.verifyListofElementText(master.specialNeedsHotelFacilityList("position()"), SpecialNeedsHotelFacility,
				"List of SpecialNeedss code");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing SpecialNeeds Code",
				"As a User I need to add a SpecialNeeds with Existing SpecialNeeds code");
		navigateToSpecialNeedssSection();
		openAddSpecialNeedsModal(SpecialNeedsName, SpecialNeedsHotelFacility);
		validateToastMessage("Bad Request", "Special need with same name already exists");
		gm.EndTest();

		/*
		 * Test 5 part 1
		 */

		gm.StartTest("Edit the HotelFacility Name in newly added SpecialNeeds Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		navigateToSpecialNeedssSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SpecialNeedsName, "searchBar_input");
		gm.hold(5);
		gm.click(master.specialNeedsEditButton(SpecialNeedsName), "editButton(" + SpecialNeedsName + ")");
		gm.waitforElementVisible(master.nameField, 5, "SpecialNeedsName");
		gm.clearbyBackspace(master.HotelFacilitysearchComboField, "HotelFacilitysearchComboField");
		gm.setText(master.HotelFacilitysearchComboField, SpecialNeedsHotelFacilityUpdated, "SpecialNeedsName");
		gm.waitforElementVisible(master.selectFromComboOptions_Index("position()"), 1,
				"HotelFacility selectFromComboOptions_Index");
		gm.click(master.selectFromComboOptions_Text(SpecialNeedsHotelFacilityUpdated), "SpecialNeeds HotelFacility");
		gm.click(master.EditSpecialNeedsButton, "EditSpecialNeedsButton");
		validateToastMessage("Updated successfully", "Special Need updated");
		gm.EndTest();

		/*
		 * Test 5 part 2
		 */

		gm.StartTest("Edit the newly added SpecialNeeds Name", "");

		String SpecialNeedsNameUpdated = SpecialNeedsName + " Updated";
		navigateToSpecialNeedssSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SpecialNeedsName, "searchBar_input");
		gm.hold(5);
		gm.click(master.specialNeedsEditButton(SpecialNeedsName), "editButton(" + SpecialNeedsName + ")");
		gm.waitforElementVisible(master.nameField, 5, "SpecialNeedsName");
		gm.clearbyBackspace(master.nameField, "SpecialNeedsName");
		gm.setText(master.nameField, SpecialNeedsNameUpdated, "SpecialNeedsName");
		gm.verifyElementNotPresent(master.codeField, 1, "SpecialNeedsCode");
		gm.click(master.EditSpecialNeedsButton, "EditSpecialNeedsButton");
		validateToastMessage("Updated successfully", "Special need updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New SpecialNeeds is Edited", "");
		navigateToSpecialNeedssSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Special Needs",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated SpecialNeeds name", "");
		navigateToSpecialNeedssSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SpecialNeedsNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.specialNeedsNameList("position()"), "List of SpecialNeedss");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.specialNeedsNameList("position()"), SpecialNeedsNameUpdated,
				"List of SpecialNeedss");

		gm.verifyListofElementText(master.specialNeedsHotelFacilityList("position()"), SpecialNeedsHotelFacilityUpdated,
				"List of SpecialNeedss code");
		gm.EndTest();

	}

}
