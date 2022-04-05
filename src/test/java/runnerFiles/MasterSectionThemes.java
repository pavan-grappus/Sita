package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionThemes extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToThemesection() {
		gm.refresh("ThemesSection", 5);
		gm.hold(5);
		gm.click(master.entities_Themes, "entities_Themes");
		gm.hold(5);
	}

	public void openAddThemesModal(String ThemesName) {

		gm.click(master.AddThemes, "AddThemes");
		gm.waitforElementVisible(master.nameField, 5, "ThemesName");
		gm.setText(master.nameField, ThemesName, "ThemesName");
		gm.click(master.AddThemesButton, "AddThemesButton");
	}

	@Test
	public void Themes() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique Themes", "As a User I need to add a New Themes");

		navigateToThemesection();

		String ThemesName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("Themes Name is :" + ThemesName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddThemesModal(ThemesName);
		validateToastMessage("Created successfully", "Theme created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Themes is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Themes", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Themes", "As a User I search for the Themes details added Newly");
		navigateToThemesection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, ThemesName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.ThemesNameList("position()"), "List of Themes");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.ThemesNameList("position()"), ThemesName, "List of Themes");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Themes Code", "As a User I need to add a Themes with Existing county code");
		navigateToThemesection();
		openAddThemesModal(ThemesName);
		validateToastMessage("Bad Request", "Themes with same name already exists");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added Themes Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String ThemesNameUpdated = ThemesName + " Updated";
		navigateToThemesection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, ThemesName, "searchBar_input");
		gm.hold(5);
		gm.click(master.ThemesEditButton(ThemesName), "editButton(" + ThemesName + ")");
		gm.waitforElementVisible(master.nameField, 5, "ThemesName");
		gm.clearbyBackspace(master.nameField, "ThemesName");
		gm.setText(master.nameField, ThemesNameUpdated, "ThemesName");
		gm.verifyElementNotPresent(master.codeField, 1, "ThemesCode");
		gm.click(master.EditThemesButton, "EditThemesButton");
		validateToastMessage("Updated successfully", "Theme updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Themes is Edited", "");
		navigateToThemesection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Themes", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Themes name", "");
		navigateToThemesection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, ThemesNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.ThemesNameList("position()"), "List of Themes");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.ThemesNameList("position()"), ThemesNameUpdated, "List of Themes");

		gm.EndTest();

	}

}
