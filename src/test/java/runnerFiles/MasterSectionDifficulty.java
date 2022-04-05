package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionDifficulty extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToDifficultyection() {
		gm.refresh("DifficultySection", 5);
		gm.hold(5);
		gm.click(master.entities_Difficulty, "entities_Difficulty");
		gm.hold(5);
	}

	public void openAddDifficultyModal(String DifficultyName) {

		gm.click(master.AddDifficulty, "AddDifficulty");
		gm.waitforElementVisible(master.nameField, 5, "DifficultyName");
		gm.setText(master.nameField, DifficultyName, "DifficultyName");
		gm.click(master.AddDifficultyButton, "AddDifficultyButton");
	}

	@Test
	public void Difficulty() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique Difficulty", "As a User I need to add a New Difficulty");

		navigateToDifficultyection();

		String DifficultyName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("Difficulty Name is :" + DifficultyName);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddDifficultyModal(DifficultyName);
		validateToastMessage("Created successfully", "Difficulty created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Difficulty is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Difficulty", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Difficulty", "As a User I search for the Difficulty details added Newly");
		navigateToDifficultyection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, DifficultyName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.DifficultyNameList("position()"), "List of Difficulty");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.DifficultyNameList("position()"), DifficultyName, "List of Difficulty");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Difficulty Code",
				"As a User I need to add a Difficulty with Existing county code");
		navigateToDifficultyection();
		openAddDifficultyModal(DifficultyName);
		validateToastMessage("Bad Request", "Difficulty with same name already exists");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added Difficulty Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String DifficultyNameUpdated = DifficultyName + " Updated";
		navigateToDifficultyection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, DifficultyName, "searchBar_input");
		gm.hold(5);
		gm.click(master.DifficultyEditButton(DifficultyName), "editButton(" + DifficultyName + ")");
		gm.waitforElementVisible(master.nameField, 5, "DifficultyName");
		gm.clearbyBackspace(master.nameField, "DifficultyName");
		gm.setText(master.nameField, DifficultyNameUpdated, "DifficultyName");
		gm.verifyElementNotPresent(master.codeField, 1, "DifficultyCode");
		gm.click(master.EditDifficultyButton, "EditDifficultyButton");
		validateToastMessage("Updated successfully", "Difficulty updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Difficulty is Edited", "");
		navigateToDifficultyection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Difficulty",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Difficulty name", "");
		navigateToDifficultyection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, DifficultyNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.DifficultyNameList("position()"), "List of Difficulty");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.DifficultyNameList("position()"), DifficultyNameUpdated, "List of Difficulty");

		gm.EndTest();

	}

}
