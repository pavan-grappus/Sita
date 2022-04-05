package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionStation extends BeforeRun {

	public String StationCity = "";
	public String StationCityUpdated = "";

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	@BeforeClass
	public void getARandomCityDetails() {
		gm.StartTest("Navigate to City details page and get the Details", "");
		gm.refresh("Stations", 5);
		gm.click(master.entities_cities, "entities_cities");
		gm.hold(5);
		StationCity = gm.getText(master.cityNameList(1), "cityNameList(1)");
		StationCityUpdated = gm.getText(master.cityNameList(2), "cityNameList(2)");
		gm.EndTest();
	}

	public void navigateToStationsSection() {
		gm.refresh("Stations", 5);
		gm.click(master.entities_stations, "entities_Stations");
		gm.hold(5);
	}

	public void openAddStationModal(String StationName, String StationCode, String StationCityName) {
		gm.click(master.AddStations, "AddStations");
		gm.waitforElementVisible(master.nameField, 5, "StationName");
		gm.setText(master.nameField, StationName, "StationName");
		gm.setText(master.codeField, StationCode, "codeField");
		gm.setText(master.CitysearchComboField, StationCityName, "CitysearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Index("position()"), 1,
				"City selectFromComboOptions_Index");
		gm.click(master.selectFromComboOptions_Text(StationCityName), "Station City");
		gm.click(master.AddStationsButton, "AddStationsButton");
	}

	@Test
	public void Stations() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique Station", "As a User I need to add a New Station");

		navigateToStationsSection();

		String StationName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String StationCode = RandomStringUtils.randomAlphabetic(5);

		gm.loginfo("Station Name is :" + StationName);
		gm.loginfo("Station code is :" + StationCode);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddStationModal(StationName, StationCode, StationCity);
		validateToastMessage("Created successfully", "Station created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Station is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Stations", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Station", "As a User I search for the Station details added Newly");
		navigateToStationsSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, StationName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.StationsNameList("position()"), "List of Stations");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.StationsNameList("position()"), StationName, "List of Stations Name");

		gm.verifyListofElementText(master.StationsCodeList("position()"), StationCode, "List of Stations Code");

		gm.verifyListofElementText(master.StationsCityList("position()"), StationCity, "List of Stations City");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Station Code",
				"As a User I need to add a Station with Existing Station code");
		navigateToStationsSection();
		openAddStationModal(StationName, StationCode, StationCity);
		validateToastMessage("Bad Request", "Station with this Id : " + StationCode + " already exits");
		gm.EndTest();

		/*
		 * Test 5 part 1
		 */

		gm.StartTest("Edit the City Name in newly added Station Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		navigateToStationsSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, StationName, "searchBar_input");
		gm.hold(5);
		gm.click(master.StationsEditButton(StationName), "editButton(" + StationName + ")");
		gm.waitforElementVisible(master.nameField, 5, "StationName");
		gm.clearbyBackspace(master.CitysearchComboField, "CitysearchComboField");
		gm.setText(master.CitysearchComboField, StationCityUpdated, "StationName");
		gm.waitforElementVisible(master.selectFromComboOptions_Index("position()"), 1,
				"City selectFromComboOptions_Index");
		gm.click(master.selectFromComboOptions_Text(StationCityUpdated), "Station City");
		gm.verifyElementNotPresent(master.codeField, 1, "codeField");
		gm.click(master.EditStationsButton, "EditStationsButton");
		validateToastMessage("Updated successfully", "Station updated");
		gm.EndTest();

		/*
		 * Test 5 part 2
		 */

		gm.StartTest("Edit the newly added Station Name", "");

		String StationNameUpdated = StationName + " Updated";
		navigateToStationsSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, StationName, "searchBar_input");
		gm.hold(5);
		gm.click(master.StationsEditButton(StationName), "editButton(" + StationName + ")");
		gm.waitforElementVisible(master.nameField, 5, "StationName");
		gm.clearbyBackspace(master.nameField, "StationName");
		gm.setText(master.nameField, StationNameUpdated, "StationName");
		gm.verifyElementNotPresent(master.codeField, 1, "StationCode");
		gm.click(master.EditStationsButton, "EditStationButton");
		validateToastMessage("Updated successfully", "Station updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Station is Edited", "");
		navigateToStationsSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Stations", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Station name", "");
		navigateToStationsSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, StationNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.StationsNameList("position()"), "List of Stations");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.StationsNameList("position()"), StationNameUpdated,
				"List of Stations Name");

		gm.verifyListofElementText(master.StationsCodeList("position()"), StationCode, "List of Stations code");

		gm.verifyListofElementText(master.StationsCityList("position()"), StationCityUpdated,
				"List of Stations City");

		gm.EndTest();

	}

}
