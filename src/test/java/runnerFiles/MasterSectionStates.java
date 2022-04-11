package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionStates extends BeforeRun {

	public String CountryName = "";
	public String CountryCode = "";
	public String CountryNameUpdated = "";
	public String CountryCodeUpdated = "";
	public String RegionName = "";
	public String RegionNameUpdated = "";
	public String updatedCountryRegion = "";

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	@BeforeClass
	public void getARandomCountriesAndRegionsDetails() {
		gm.StartTest("Navigate to Countries and Region details page and get the Details", "");

		CountryName = "India";
		CountryCode = "IND";
		CountryNameUpdated = "France";
		CountryCodeUpdated = "FRA";
		RegionName = "North India";
		RegionNameUpdated = "South India";
		updatedCountryRegion = "FranceRegion";

		gm.EndTest();
	}

	public void navigateToStatesSection() {
		gm.refresh("States", 5);
		gm.click(master.entities_States, "entities_States");
		gm.hold(5);
	}

	public void openAddStatesModal(String StatesName, String StatesCode, String CountryName, String RegionName) {
		gm.click(master.AddStates, "AddStates");
		gm.waitforElementVisible(master.nameField, 5, "StatesName");
		gm.setText(master.nameField, StatesName, "StatesName");
		gm.setText(master.codeField, StatesCode, "StatescodeField");

		gm.setText(master.CountriesSearchComboField, CountryName, "CountriesSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(CountryName), 1,
				"CountryName selectFromComboOptions_Text" + CountryName);
		gm.click(master.selectFromComboOptions_Text(CountryName), "selectFromComboOptions_Text(" + CountryName + ")");

		gm.setText(master.RegionsSearchComboField, RegionName, "RegionsSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(RegionName), 1,
				"RegionName selectFromComboOptions_Text" + RegionName);
		gm.click(master.selectFromComboOptions_Text(RegionName), "selectFromComboOptions_Text(" + RegionName + ")");

		gm.click(master.AddStateButton, "AddStateButton");
	}

	public void validateTheTabledata(String StatesName, String StateCode, String CountryName, String CountryCode,
			String Region) {

		int count = gm.getSizeofWebelements(master.StateNameList("position()"), "List of StateNameList");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.StateNameList("position()"), StatesName, "List of StateNameList");

		gm.verifyListofElementText(master.StateCodeList("position()"), StateCode, "List of StateCodeList");

		gm.verifyListofElementText(master.StateCountryNameList("position()"), CountryName,
				"List of StateCountryNameList");
		gm.verifyListofElementText(master.StateCountryCodeList("position()"), CountryCode,
				"List of StateCountryCodeList");
		gm.verifyListofElementText(master.StateRegionList("position()"), Region, "List of StateRegionList");

	}

	public void searchAndEditTheModaleWindow(String StatesName) {
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, StatesName, "searchBar_input");
		gm.hold(5);
		gm.click(master.StateEditButton(StatesName), "editButton(" + StatesName + ")");
		gm.waitforElementVisible(master.nameField, 5, "StatesName");
		gm.verifyElementNotPresent(master.codeField, 1, "codeField");
	}

	@Test
	public void Statess() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique States", "As a User I need to add a New States");

		navigateToStatesSection();

		String StatesName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String StateCode = RandomStringUtils.randomAlphabetic(5);

		gm.loginfo("States Name is :" + StatesName);
		gm.loginfo("States Code is :" + StateCode);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddStatesModal(StatesName, StateCode, CountryName, RegionName);
		validateToastMessage("Created successfully", "State created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New States is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " States", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added States", "As a User I search for the States details added Newly");
		navigateToStatesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, StatesName, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(StatesName, StateCode.toUpperCase(), CountryName, CountryCode, RegionName);

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing States Name",
				"As a User I need to add a States with Existing States code");
		navigateToStatesSection();
		openAddStatesModal(StatesName, StateCode, CountryName, RegionName);
		validateToastMessage("Bad Request", "State with this Id : " + StateCode.toUpperCase() + " already exits");
		gm.EndTest();

		/*
		 * Test 5 part 1
		 */

		String StatesNameUpdated = StatesName + " Updated";

		gm.StartTest("Edit the State Name in newly added States", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		gm.loginfo("States Name is :" + StatesNameUpdated);

		navigateToStatesSection();
		searchAndEditTheModaleWindow(StatesName);
		gm.clearbyBackspace(master.nameField, "nameField");
		gm.setText(master.nameField, StatesNameUpdated, "State nameField");
		gm.click(master.EditStateButton, "EditStateButton");
		validateToastMessage("Updated successfully", "State updated");
		gm.EndTest();

		/*
		 * Test 5 part 2
		 */

		gm.StartTest("Edit the Region Dropdown in newly added States", "");

		navigateToStatesSection();
		searchAndEditTheModaleWindow(StatesNameUpdated);
		gm.clearbyBackspace(master.CountriesSearchComboField, "CountriesSearchComboField");

		gm.setText(master.RegionsSearchComboField, RegionNameUpdated, "RegionsSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(RegionNameUpdated), 5,
				"selectFromComboOptions_Text(" + RegionNameUpdated + ")");
		gm.click(master.selectFromComboOptions_Text(RegionNameUpdated),
				"selectFromComboOptions_Text(" + RegionNameUpdated + ")");

		gm.click(master.EditStateButton, "EditStateButton");
		validateToastMessage("Updated successfully", "State updated");
		gm.EndTest();

		/*
		 * Test 5 part 3
		 */

		gm.StartTest("Edit the Country Dropdown in newly added States", "");

		navigateToStatesSection();
		searchAndEditTheModaleWindow(StatesNameUpdated);
		gm.clearbyBackspace(master.CountriesSearchComboField, "CountriesSearchComboField");

		gm.setText(master.CountriesSearchComboField, CountryNameUpdated, "CountriesSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(CountryNameUpdated), 5,
				"selectFromComboOptions_Text(" + CountryNameUpdated + ")");
		gm.click(master.selectFromComboOptions_Text(CountryNameUpdated),
				"selectFromComboOptions_Text(" + CountryNameUpdated + ")");

		gm.setText(master.RegionsSearchComboField, updatedCountryRegion, "RegionsSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(updatedCountryRegion), 5,
				"selectFromComboOptions_Text(" + updatedCountryRegion + ")");
		gm.click(master.selectFromComboOptions_Text(updatedCountryRegion),
				"selectFromComboOptions_Text(" + updatedCountryRegion + ")");

		gm.click(master.EditStateButton, "EditStateButton");
		validateToastMessage("Updated successfully", "State updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New States is Edited", "");
		navigateToStatesSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " States",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated States name", "");
		navigateToStatesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, StatesNameUpdated, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(StatesNameUpdated, StateCode.toUpperCase(), CountryNameUpdated, CountryCodeUpdated,
				updatedCountryRegion);
		gm.EndTest();

	}

}
