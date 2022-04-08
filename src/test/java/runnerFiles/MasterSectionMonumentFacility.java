package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionMonumentFacility extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToMonumentFacilityection() {
		gm.refresh("MonumentFacilitySection", 5);
		gm.hold(5);
		gm.click(master.entities_monumentFacility, "entities_monumentFacility");
		gm.hold(5);
	}

	public void selectCategoryType(String CategoryType) {
		gm.click(master.categoryDropdownComboField, "categoryDropdownComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(CategoryType), 5,
				"selectFromComboOptions_Text Facility Type");
		gm.click(master.selectFromComboOptions_Text(CategoryType),
				"selectFromComboOptions_Text(" + CategoryType + ") Facility Type");

	}

	public void selectisMandatory(boolean ifisRequired) {

		if (ifisRequired) {
			gm.toggleOn(master.isRequired_switch, "isRequired_switch");

		} else {
			gm.toggleOff(master.isRequired_switch, "isRequired_switch");
		}
	}

	public void openAddMonumentFacilityModal(String MonumentFacilityName, String CategoryType, boolean ifisRequired) {

		gm.click(master.AddMonumentFacility, "AddMonumentFacility");
		gm.waitforElementVisible(master.nameField, 5, "MonumentFacilityName");
		gm.setText(master.nameField, MonumentFacilityName, "MonumentFacilityName");
		selectCategoryType(CategoryType);

		selectisMandatory(ifisRequired);

		gm.click(master.AddMonumentFacilityButton, "AddMonumentFacilityButton");
	}

	public void validateTheTabledata(String MonumentFacilityName, String MonumentCategotyType, boolean isRequired) {
		gm.verifyListofElementText(master.MonumentFacilityNameList("position()"), MonumentFacilityName,
				"List of MonumentFacility Name");

		gm.verifyListofElementText(master.MonumentFacilityCategoryList("position()"), MonumentCategotyType,
				"List of MonumentFacilityCategoryList");

		String image = "";
		if (isRequired) {
			image = "https://sita.grappus.com/img/green-check-mark.svg";
		} else {
			image = "https://sita.grappus.com/img/minus.svg";
		}

		gm.verifyElementAttributeValue(master.MonumentFacilityMandatoryList_img("position()"), "src", image,
				"MonumentFacilityMandatoryList_img");

	}

	@Test
	public void MonumentFacility() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique MonumentFacility", "As a User I need to add a New MonumentFacility");

		navigateToMonumentFacilityection();

		String MonumentFacilityName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		String MonumentCategoryType = "General";

		boolean isRequired = true;

		gm.loginfo("MonumentFacility Name is :" + MonumentFacilityName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddMonumentFacilityModal(MonumentFacilityName, MonumentCategoryType, isRequired);
		validateToastMessage("Created successfully", "Monument facility created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New MonumentFacility is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Monument Facilities",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 3 -1
		 */

		gm.StartTest("Validate the Options in the Monument Category type dropdown", "");
		navigateToMonumentFacilityection();
		gm.click(master.AddMonumentFacility, "AddMonumentFacility");
		gm.waitforElementVisible(master.nameField, 5, "MonumentFacilityName");
		gm.click(master.categoryDropdownComboField, "categoryDropdownComboField");
		gm.verifyElementVisible(master.selectFromComboOptions_Text("General"),
				"selectFromComboOptions_Text(\"General\")");
		gm.verifyElementVisible(master.selectFromComboOptions_Text("Disable Friendly"),
				"selectFromComboOptions_Text(\"Disable Friendly\")");

		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added MonumentFacility",
				"As a User I search for the MonumentFacility details added Newly");
		navigateToMonumentFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, MonumentFacilityName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.MonumentFacilityNameList("position()"), "List of MonumentFacility");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		validateTheTabledata(MonumentFacilityName, MonumentCategoryType, isRequired);
		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing MonumentFacility Code",
				"As a User I need to add a MonumentFacility with Existing county code");
		navigateToMonumentFacilityection();
		openAddMonumentFacilityModal(MonumentFacilityName, MonumentCategoryType, isRequired);
		validateToastMessage("Bad Request", "Monument facility with same name already exists");
		gm.EndTest();

		/*
		 * Test 5 - 1
		 */

		gm.StartTest("Edit the newly added MonumentFacility Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String MonumentFacilityNameUpdated = MonumentFacilityName + " Updated";
		navigateToMonumentFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, MonumentFacilityName, "searchBar_input");
		gm.hold(5);
		gm.click(master.MonumentFacilityEditButton(MonumentFacilityName), "editButton(" + MonumentFacilityName + ")");
		gm.waitforElementVisible(master.nameField, 5, "MonumentFacilityName");
		gm.clearbyBackspace(master.nameField, "MonumentFacilityName");
		gm.setText(master.nameField, MonumentFacilityNameUpdated, "MonumentFacilityName");
		gm.click(master.EditMonumentFacilityButton, "EditMonumentFacilityButton");
		validateToastMessage("Updated successfully", "Monument facility updated");
		gm.EndTest();

		/*
		 * Test 5 - 2
		 */

		gm.StartTest("Edit the newly added MonumentFacility Category Type", "");

		String MonumentCategoryTypeUpdated = "Disable Friendly";
		navigateToMonumentFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, MonumentFacilityNameUpdated, "searchBar_input");
		gm.hold(5);
		gm.click(master.MonumentFacilityEditButton(MonumentFacilityNameUpdated),
				"editButton(" + MonumentFacilityNameUpdated + ")");
		gm.waitforElementVisible(master.nameField, 5, "MonumentFacilityName");
		selectCategoryType(MonumentCategoryTypeUpdated);
		gm.click(master.EditMonumentFacilityButton, "EditMonumentFacilityButton");
		validateToastMessage("Updated successfully", "Monument facility updated");
		gm.EndTest();

		/*
		 * Test 5 - 3
		 */

		gm.StartTest("Edit the newly added MonumentFacility Facility isRequired", "");

		boolean MonumentFacilityisRequiredUpdated = false;
		navigateToMonumentFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, MonumentFacilityNameUpdated, "searchBar_input");
		gm.hold(5);
		gm.click(master.MonumentFacilityEditButton(MonumentFacilityNameUpdated),
				"editButton(" + MonumentFacilityNameUpdated + ")");
		gm.waitforElementVisible(master.nameField, 5, "MonumentFacilityName");
		selectisMandatory(MonumentFacilityisRequiredUpdated);
		gm.click(master.EditMonumentFacilityButton, "EditMonumentFacilityButton");
		validateToastMessage("Updated successfully", "Monument facility updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New MonumentFacility is Edited", "");
		navigateToMonumentFacilityection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Monument Facilities",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated MonumentFacility name", "");
		navigateToMonumentFacilityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, MonumentFacilityNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.MonumentFacilityNameList("position()"), "List of roup");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		validateTheTabledata(MonumentFacilityNameUpdated, MonumentCategoryTypeUpdated,
				MonumentFacilityisRequiredUpdated);

		gm.EndTest();

	}

}
