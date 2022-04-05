package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionTerminals extends BeforeRun {

	public String TerminalCity = "";
	public String TerminalCityUpdated = "";

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	@BeforeClass
	public void getARandomCityDetails() {
		gm.StartTest("Navigate to City details page and get the Details", "");
		gm.refresh("Terminals", 5);
		gm.click(master.entities_cities, "entities_cities");
		gm.hold(5);
		TerminalCity = gm.getText(master.cityNameList(1), "cityNameList(1)");
		TerminalCityUpdated = gm.getText(master.cityNameList(2), "cityNameList(2)");
		gm.EndTest();
	}

	public void navigateToTerminalsSection() {
		gm.refresh("Terminals", 5);
		gm.click(master.entities_terminals, "entities_terminals");
		gm.hold(5);
	}

	public void openAddTerminalModal(String TerminalName, String TerminalCode, String TerminalCityName) {
		gm.click(master.AddTerminals, "AddTerminals");
		gm.waitforElementVisible(master.nameField, 5, "TerminalName");
		gm.setText(master.nameField, TerminalName, "TerminalName");
		gm.setText(master.codeField, TerminalCode, "codeField");
		gm.setText(master.CitysearchComboField, TerminalCityName, "CitysearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Index("position()"), 1,
				"City selectFromComboOptions_Index");
		gm.click(master.selectFromComboOptions_Text(TerminalCityName), "Terminal City");
		gm.click(master.AddTerminalsButton, "AddTerminalsButton");
	}

	@Test
	public void Terminals() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique Terminal", "As a User I need to add a New Terminal");

		navigateToTerminalsSection();

		String TerminalName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String TerminalCode = RandomStringUtils.randomAlphabetic(5);

		gm.loginfo("Terminal Name is :" + TerminalName);
		gm.loginfo("Terminal code is :" + TerminalCode);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddTerminalModal(TerminalName, TerminalCode, TerminalCity);
		validateToastMessage("Created successfully", "Terminal created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Terminal is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Terminals", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Terminal", "As a User I search for the Terminal details added Newly");
		navigateToTerminalsSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, TerminalName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.TerminalsNameList("position()"), "List of Terminals");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.TerminalsNameList("position()"), TerminalName, "List of Terminals Name");

		gm.verifyListofElementText(master.TerminalsCodeList("position()"), TerminalCode, "List of Terminals Code");

		gm.verifyListofElementText(master.TerminalsCityList("position()"), TerminalCity, "List of Terminals City");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Terminal Code",
				"As a User I need to add a Terminal with Existing Terminal code");
		navigateToTerminalsSection();
		openAddTerminalModal(TerminalName, TerminalCode, TerminalCity);
		validateToastMessage("Bad Request", "Terminal with this Id : " + TerminalCode + " already exits");
		gm.EndTest();

		/*
		 * Test 5 part 1
		 */

		gm.StartTest("Edit the City Name in newly added Terminal Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		navigateToTerminalsSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, TerminalName, "searchBar_input");
		gm.hold(5);
		gm.click(master.TerminalsEditButton(TerminalName), "editButton(" + TerminalName + ")");
		gm.waitforElementVisible(master.nameField, 5, "TerminalName");
		gm.clearbyBackspace(master.CitysearchComboField, "CitysearchComboField");
		gm.setText(master.CitysearchComboField, TerminalCityUpdated, "TerminalName");
		gm.waitforElementVisible(master.selectFromComboOptions_Index("position()"), 1,
				"City selectFromComboOptions_Index");
		gm.click(master.selectFromComboOptions_Text(TerminalCityUpdated), "Terminal City");
		gm.verifyElementNotPresent(master.codeField, 1, "codeField");
		gm.click(master.EditTerminalsButton, "EditTerminalsButton");
		validateToastMessage("Updated successfully", "Terminal updated");
		gm.EndTest();

		/*
		 * Test 5 part 2
		 */

		gm.StartTest("Edit the newly added Terminal Name", "");

		String TerminalNameUpdated = TerminalName + " Updated";
		navigateToTerminalsSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, TerminalName, "searchBar_input");
		gm.hold(5);
		gm.click(master.TerminalsEditButton(TerminalName), "editButton(" + TerminalName + ")");
		gm.waitforElementVisible(master.nameField, 5, "TerminalName");
		gm.clearbyBackspace(master.nameField, "TerminalName");
		gm.setText(master.nameField, TerminalNameUpdated, "TerminalName");
		gm.verifyElementNotPresent(master.codeField, 1, "TerminalCode");
		gm.click(master.EditTerminalsButton, "EditTerminalButton");
		validateToastMessage("Updated successfully", "Terminal updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Terminal is Edited", "");
		navigateToTerminalsSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Terminals", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Terminal name", "");
		navigateToTerminalsSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, TerminalNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.TerminalsNameList("position()"), "List of Terminals");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.TerminalsNameList("position()"), TerminalNameUpdated,
				"List of Terminals Name");

		gm.verifyListofElementText(master.TerminalsCodeList("position()"), TerminalCode, "List of Terminals code");

		gm.verifyListofElementText(master.TerminalsCityList("position()"), TerminalCityUpdated,
				"List of Terminals City");

		gm.EndTest();

	}

}
