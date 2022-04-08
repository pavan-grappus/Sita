package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionHotelFacility extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToHotelFacilityection() {
		gm.refresh("HotelFacilitySection", 5);
		gm.hold(5);
		gm.click(master.entities_hotelFacility, "entities_hotelFacility");
		gm.hold(5);
	}

	public void selectHotelfacility(String FacilityType) {
		gm.click(master.facilityTypesearchComboField, "facilityTypesearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(FacilityType), 5,
				"selectFromComboOptions_Text Facility Type");
		gm.click(master.selectFromComboOptions_Text(FacilityType),
				"selectFromComboOptions_Text(" + FacilityType + ") Facility Type");

	}

	public void selectinputType(String InputType) {
		gm.click(master.inputTypesearchComboField, "");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(InputType), 5,
				"selectFromComboOptions_Text Facility Type");
		gm.click(master.selectFromComboOptions_Text(InputType),
				"selectFromComboOptions_Text(" + InputType + ") Facility Type");

	}

	public void selectisMandatory(boolean ifisRequired) {

		if (ifisRequired) {
			gm.toggleOn(master.isRequired_switch, "isRequired_switch");

		} else {
			gm.toggleOff(master.isRequired_switch, "isRequired_switch");
		}

	}

	public void openAddHotelFacilityModal(String HotelFacilityName, String FacilityType, String InputType,
			boolean ifisRequired) {

		gm.click(master.AddHotelFacility, "AddHotelFacility");
		gm.waitforElementVisible(master.nameField, 5, "HotelFacilityName");
		gm.setText(master.nameField, HotelFacilityName, "HotelFacilityName");
		selectHotelfacility(FacilityType);

		selectinputType(InputType);

		selectisMandatory(ifisRequired);

		gm.click(master.AddHotelFacilityButton, "AddHotelFacilityButton");
	}

	public void validateTheTabledata(String HotelFacilityName, String HotelFacilityType, String HotelFacilityInputType,
			boolean isRequired) {
		gm.verifyListofElementText(master.hotelFacilityNameList("position()"), HotelFacilityName,
				"List of HotelFacility Name");

		gm.verifyListofElementText(master.hotelFacilityTypeList("position()"), HotelFacilityType,
				"List of hotelFacilityTypeList");

		gm.verifyListofElementText(master.hotelFacilityInputTypeList("position()"), HotelFacilityInputType,
				"List of hotelFacilityInputTypeList");

		String image = "";
		if (isRequired) {
			image = "https://sita.grappus.com/img/green-check-mark.svg";
		} else {
			image = "https://sita.grappus.com/img/minus.svg";
		}
		gm.verifyElementAttributeValue(master.hotelFacilityMandatoryList_img("position()"), "src", image,
				"hotelFacilityMandatoryList_img");

	}

	@Test
	public void HotelFacility() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique HotelFacility", "As a User I need to add a New HotelFacility");

		navigateToHotelFacilityection();

		String HotelFacilityName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		String HotelFacilityType = "Hotel";

		String HotelFacilityInputType = "Text";

		boolean isRequired = true;

		gm.loginfo("HotelFacility Name is :" + HotelFacilityName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddHotelFacilityModal(HotelFacilityName, HotelFacilityType, HotelFacilityInputType, isRequired);
		validateToastMessage("Created successfully", "Hotel facility created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New HotelFacility is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Hotel Facilities",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 3 -1
		 */

		gm.StartTest("Validate the Options in the Facility type dropdown", "");
		navigateToHotelFacilityection();
		gm.click(master.AddHotelFacility, "AddHotelFacility");
		gm.waitforElementVisible(master.nameField, 5, "HotelFacilityName");
		gm.click(master.facilityTypesearchComboField, "facilityTypesearchComboField");
		gm.verifyElementVisible(master.selectFromComboOptions_Text("Hotel"), "selectFromComboOptions_Text(\"Hotel\")");
		gm.verifyElementVisible(master.selectFromComboOptions_Text("Room"), "selectFromComboOptions_Text(\"Room\")");

		gm.EndTest();

		/*
		 * Test 3 -2
		 */

		gm.StartTest("Validate the Options in the Input type dropdown", "");
		navigateToHotelFacilityection();
		gm.click(master.AddHotelFacility, "AddHotelFacility");
		gm.waitforElementVisible(master.nameField, 5, "HotelFacilityName");
		gm.click(master.inputTypesearchComboField, "inputTypesearchComboField");
		gm.verifyElementVisible(master.selectFromComboOptions_Text("Text"), "selectFromComboOptions_Text(\"Text\")");
		gm.verifyElementVisible(master.selectFromComboOptions_Text("Toggle"),
				"selectFromComboOptions_Text(\"Toggle\")");

		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added HotelFacility",
				"As a User I search for the HotelFacility details added Newly");
		navigateToHotelFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, HotelFacilityName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.hotelFacilityNameList("position()"), "List of HotelFacility");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		validateTheTabledata(HotelFacilityName, HotelFacilityType, HotelFacilityInputType, isRequired);
		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing HotelFacility Code",
				"As a User I need to add a HotelFacility with Existing county code");
		navigateToHotelFacilityection();
		openAddHotelFacilityModal(HotelFacilityName, HotelFacilityType, HotelFacilityInputType, isRequired);
		validateToastMessage("Bad Request", "Hotel facility with same name already exists");
		gm.EndTest();

		/*
		 * Test 5 - 1
		 */

		gm.StartTest("Edit the newly added HotelFacility Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String HotelFacilityNameUpdated = HotelFacilityName + " Updated";
		navigateToHotelFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, HotelFacilityName, "searchBar_input");
		gm.hold(5);
		gm.click(master.HotelFacilityEditButton(HotelFacilityName), "editButton(" + HotelFacilityName + ")");
		gm.waitforElementVisible(master.nameField, 5, "HotelFacilityName");
		gm.clearbyBackspace(master.nameField, "HotelFacilityName");
		gm.setText(master.nameField, HotelFacilityNameUpdated, "HotelFacilityName");
		gm.click(master.EditHotelFacilityButton, "EditHotelFacilityButton");
		validateToastMessage("Updated successfully", "Hotel facility updated");
		gm.EndTest();

		/*
		 * Test 5 - 2
		 */

		gm.StartTest("Edit the newly added HotelFacility Facility Type", "");

		String HotelFacilityTypeUpdated = "Room";
		navigateToHotelFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, HotelFacilityNameUpdated, "searchBar_input");
		gm.hold(5);
		gm.click(master.HotelFacilityEditButton(HotelFacilityNameUpdated),
				"editButton(" + HotelFacilityNameUpdated + ")");
		gm.waitforElementVisible(master.nameField, 5, "HotelFacilityName");
		selectHotelfacility(HotelFacilityTypeUpdated);
		gm.click(master.EditHotelFacilityButton, "EditHotelFacilityButton");
		validateToastMessage("Updated successfully", "Hotel facility updated");
		gm.EndTest();

		/*
		 * Test 5 - 3
		 */

		gm.StartTest("Edit the newly added HotelFacility Facility inputType", "");

		String HotelFacilityInputTypeUpdated = "Toggle";
		navigateToHotelFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, HotelFacilityNameUpdated, "searchBar_input");
		gm.hold(5);
		gm.click(master.HotelFacilityEditButton(HotelFacilityNameUpdated),
				"editButton(" + HotelFacilityNameUpdated + ")");
		gm.waitforElementVisible(master.nameField, 5, "HotelFacilityName");
		selectinputType(HotelFacilityInputTypeUpdated);
		gm.click(master.EditHotelFacilityButton, "EditHotelFacilityButton");
		validateToastMessage("Updated successfully", "Hotel facility updated");
		gm.EndTest();

		/*
		 * Test 5 - 4
		 */

		gm.StartTest("Edit the newly added HotelFacility Facility isRequired", "");

		boolean HotelFacilityisRequiredUpdated = false;
		navigateToHotelFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, HotelFacilityNameUpdated, "searchBar_input");
		gm.hold(5);
		gm.click(master.HotelFacilityEditButton(HotelFacilityNameUpdated),
				"editButton(" + HotelFacilityNameUpdated + ")");
		gm.waitforElementVisible(master.nameField, 5, "HotelFacilityName");
		selectisMandatory(HotelFacilityisRequiredUpdated);
		gm.click(master.EditHotelFacilityButton, "EditHotelFacilityButton");
		validateToastMessage("Updated successfully", "Hotel facility updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New HotelFacility is Edited", "");
		navigateToHotelFacilityection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Hotel Facilities",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated HotelFacility name", "");
		navigateToHotelFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, HotelFacilityNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.hotelFacilityNameList("position()"), "List of roup");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		validateTheTabledata(HotelFacilityNameUpdated, HotelFacilityTypeUpdated, HotelFacilityInputTypeUpdated,
				HotelFacilityisRequiredUpdated);

		gm.EndTest();

	}

}
