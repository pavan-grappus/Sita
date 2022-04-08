package runnerFiles;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionSpeciality extends BeforeRun {

	public String SpecialityCategoryType;
	public String SpecialityCategoryTypeUpdated;

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	@BeforeClass
	public void getARandomCityDetails() {
		gm.StartTest("Navigate to Theme details page and get the Details", "");
		gm.refresh("Speciality", 5);
		gm.click(master.entities_Themes, "entities_Themes");
		gm.hold(5);
		SpecialityCategoryType = gm.getText(master.ThemesNameList(1), "ThemesNameList(1)");
		SpecialityCategoryTypeUpdated = gm.getText(master.ThemesNameList(2), "ThemesNameList(2)");
		gm.EndTest();
	}

	public void navigateToSpecialityection() {
		gm.refresh("SpecialitySection", 5);
		gm.hold(5);
		gm.click(master.entities_Speciality, "entities_Speciality");
		gm.hold(5);
	}

	public void selectCategoryType(String CategoryType) {
		gm.setText(master.categorySearchComboField, CategoryType, "categorySearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(CategoryType), 5,
				"selectFromComboOptions_Text Facility Type");
		gm.click(master.selectFromComboOptions_Text(CategoryType),
				"selectFromComboOptions_Text(" + CategoryType + ") Facility Type");

	}

	public void openAddSpecialityModal(String SpecialityName, String CategoryType) {

		gm.click(master.AddSpeciality, "AddSpeciality");
		gm.waitforElementVisible(master.nameField, 5, "SpecialityName");
		gm.setText(master.nameField, SpecialityName, "SpecialityName");
		selectCategoryType(CategoryType);

		gm.click(master.AddSpecialityButton, "AddSpecialityButton");
	}

	public void validateTheTabledata(String SpecialityName, String SpecialityCategoryType) {
		gm.verifyListofElementText(master.SpecialityNameList("position()"), SpecialityName, "List of Speciality Name");

		gm.verifyListofElementText(master.SpecialityCategoryList("position()"), SpecialityCategoryType,
				"List of SpecialityCategoryList");
	}

	@Test
	public void Speciality() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique Speciality", "As a User I need to add a New Speciality");

		navigateToSpecialityection();

		String SpecialityName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("Speciality Name is :" + SpecialityName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddSpecialityModal(SpecialityName, SpecialityCategoryType);
		validateToastMessage("Created successfully", "Speciality created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Speciality is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Speciality", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Speciality", "As a User I search for the Speciality details added Newly");
		navigateToSpecialityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SpecialityName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.SpecialityNameList("position()"), "List of Speciality");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		validateTheTabledata(SpecialityName, SpecialityCategoryType);
		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Speciality Code",
				"As a User I need to add a Speciality with Existing county code");
		navigateToSpecialityection();
		openAddSpecialityModal(SpecialityName, SpecialityCategoryType);
		validateToastMessage("Bad Request", "Speciality with same name already exists");
		gm.EndTest();

		/*
		 * Test 5 - 1
		 */

		gm.StartTest("Edit the newly added Speciality Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String SpecialityNameUpdated = SpecialityName + " Updated";
		navigateToSpecialityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SpecialityName, "searchBar_input");
		gm.hold(5);
		gm.click(master.SpecialityEditButton(SpecialityName), "editButton(" + SpecialityName + ")");
		gm.waitforElementVisible(master.nameField, 5, "SpecialityName");
		gm.clearbyBackspace(master.nameField, "SpecialityName");
		gm.setText(master.nameField, SpecialityNameUpdated, "SpecialityName");
		gm.click(master.EditSpecialityButton, "EditSpecialityButton");
		validateToastMessage("Updated successfully", "Speciality updated");
		gm.EndTest();

		/*
		 * Test 5 - 2
		 */

		gm.StartTest("Edit the newly added Speciality Category Type", "");

		navigateToSpecialityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SpecialityNameUpdated, "searchBar_input");
		gm.hold(5);
		gm.click(master.SpecialityEditButton(SpecialityNameUpdated), "editButton(" + SpecialityNameUpdated + ")");
		gm.waitforElementVisible(master.nameField, 5, "SpecialityName");
		selectCategoryType(SpecialityCategoryTypeUpdated);
		gm.click(master.EditSpecialityButton, "EditSpecialityButton");
		validateToastMessage("Updated successfully", "Speciality updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Speciality is Edited", "");
		navigateToSpecialityection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Speciality",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Speciality name", "");
		navigateToSpecialityection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SpecialityNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.SpecialityNameList("position()"), "List of roup");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		validateTheTabledata(SpecialityNameUpdated, SpecialityCategoryTypeUpdated);

		gm.EndTest();

	}

}
