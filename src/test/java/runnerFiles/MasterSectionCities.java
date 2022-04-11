package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionCities extends BeforeRun {

	public String CountryName = "";
	public String CountryCode = "";
	public String CountryNameUpdated = "";
	public String CountryCodeUpdated = "";
	public String StateName = "";
	public String StateCode = "";
	public String StateNameUpdated = "";
	public String StateCodeUpdated = "";
	public String updatedCountryStateName = "";
	public String updatedCountryStateCode = "";

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
		StateName = "Delhi";
		StateCode = "IN-DL";
		StateNameUpdated = "Haryana";
		StateCodeUpdated = "IN-HR";
		updatedCountryStateName = "FranceState";
		updatedCountryStateCode = "EDFRG";

		gm.EndTest();
	}

	public void navigateToCitiesSection() {
		gm.refresh("Cities", 5);
		gm.click(master.entities_cities, "entities_cities");
		gm.hold(5);
	}

	public void openAddCitiesModal(String CitiesName, String CitiesCode, String CountryName, String StateName) {
		gm.click(master.AddCities, "AddCities");
		gm.waitforElementVisible(master.nameField, 5, "CitiesName");
		gm.setText(master.nameField, CitiesName, "CitiesName");
		gm.setText(master.codeField, CitiesCode, "CitiescodeField");

		gm.setText(master.CountriesSearchComboField, CountryName, "CountriesSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(CountryName), 5,
				"CountryName selectFromComboOptions_Text" + CountryName);
		gm.click(master.selectFromComboOptions_Text(CountryName), "selectFromComboOptions_Text(" + CountryName + ")");

		gm.setText(master.StateSearchComboField, StateName, "StateSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(StateName), 5,
				"RegionName selectFromComboOptions_Text" + StateName);
		gm.click(master.selectFromComboOptions_Text(StateName), "selectFromComboOptions_Text(" + StateName + ")");

		gm.click(master.AddCityButton, "AddCityButton");
	}

	public void validateTheTabledata(String CityName, String CityCode, String StateName, String StateCode,
			String CountryName, String CountryCode) {

		int count = gm.getSizeofWebelements(master.cityNameList("position()"), "List of cityNameList");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.cityNameList("position()"), CityName, "List of cityNameList");

		gm.verifyListofElementText(master.cityCodeList("position()"), CityCode, "List of cityCodeList");
		gm.verifyListofElementText(master.CitiesStateNameList("position()"), StateName, "List of CitiesStateNameList");
		gm.verifyListofElementText(master.CitiesStateCodeList("position()"), StateCode, "List of CitiesStateCodeList");
		gm.verifyListofElementText(master.CitiesCountryNameList("position()"), CountryName,
				"List of CitiesCountryNameList");
		gm.verifyListofElementText(master.CitiesCountryCodeList("position()"), CountryCode,
				"List of CitiesCountryCodeList");

	}

	public void searchAndEditTheModaleWindow(String CitiesName) {
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, CitiesName, "searchBar_input");
		gm.hold(5);
		gm.click(master.CitiesEditButton(CitiesName), "editButton(" + CitiesName + ")");
		gm.waitforElementVisible(master.nameField, 5, "CitiesName");
		gm.verifyElementNotPresent(master.codeField, 1, "codeField");
	}

	@Test
	public void Citiess() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique Cities", "As a User I need to add a New Cities");

		navigateToCitiesSection();

		String CityName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String CityCode = RandomStringUtils.randomAlphabetic(5);

		gm.loginfo("Cities Name is :" + CityName);
		gm.loginfo("Cities Code is :" + CityCode);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddCitiesModal(CityName, CityCode, CountryName, StateName);
		validateToastMessage("Created successfully", "City created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Cities is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Cities", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Cities", "As a User I search for the Cities details added Newly");
		navigateToCitiesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, CityName, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(CityName, CityCode.toUpperCase(), StateName, StateCode, CountryName, CountryCode);

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Cities Name",
				"As a User I need to add a Cities with Existing Cities code");
		navigateToCitiesSection();
		openAddCitiesModal(CityName, CityCode, CountryName, StateName);
		validateToastMessage("Bad Request", "City with this Id : " + CityCode.toUpperCase() + " already exits");
		gm.EndTest();

		/*
		 * Test 5 part 1
		 */

		String CityNameUpdated = CityName + " Updated";

		gm.StartTest("Edit the City Name in newly added Cities", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		gm.loginfo("City Name is :" + CityNameUpdated);

		navigateToCitiesSection();
		searchAndEditTheModaleWindow(CityName);
		gm.clearbyBackspace(master.nameField, "nameField");
		gm.setText(master.nameField, CityNameUpdated, "City nameField");
		gm.click(master.EditCityButton, "EditCityButton");
		validateToastMessage("Updated successfully", "City updated");
		gm.EndTest();

		/*
		 * Test 5 part 2
		 */

		gm.StartTest("Edit the State Dropdown in newly added City", "");

		navigateToCitiesSection();
		searchAndEditTheModaleWindow(CityNameUpdated);

		gm.clearbyBackspace(master.StateSearchComboField, "StateSearchComboField");
		gm.setText(master.StateSearchComboField, StateNameUpdated, "StateSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(StateNameUpdated), 5,
				"selectFromComboOptions_Text(" + StateNameUpdated + ")");
		gm.click(master.selectFromComboOptions_Text(StateNameUpdated),
				"selectFromComboOptions_Text(" + StateNameUpdated + ")");

		gm.click(master.EditCityButton, "EditCityButton");
		validateToastMessage("Updated successfully", "City updated");
		gm.EndTest();

		/*
		 * Test 5 part 3
		 */

		gm.StartTest("Edit the Country Dropdown in newly added Cities", "");

		navigateToCitiesSection();
		searchAndEditTheModaleWindow(CityNameUpdated);

		gm.clearbyBackspace(master.CountriesSearchComboField, "CountriesSearchComboField");
		gm.setText(master.CountriesSearchComboField, CountryNameUpdated, "CountriesSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(CountryNameUpdated), 5,
				"selectFromComboOptions_Text(" + CountryNameUpdated + ")");
		gm.click(master.selectFromComboOptions_Text(CountryNameUpdated),
				"selectFromComboOptions_Text(" + CountryNameUpdated + ")");

		gm.clearbyBackspace(master.StateSearchComboField, "StateSearchComboField");
		gm.setText(master.StateSearchComboField, updatedCountryStateName, "StateSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(updatedCountryStateName), 5,
				"selectFromComboOptions_Text(" + updatedCountryStateName + ")");
		gm.click(master.selectFromComboOptions_Text(updatedCountryStateName),
				"selectFromComboOptions_Text(" + updatedCountryStateName + ")");

		gm.click(master.EditCityButton, "EditCityButton");
		validateToastMessage("Updated successfully", "City updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Cities is Edited", "");
		navigateToCitiesSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Cities", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Cities name", "");
		navigateToCitiesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, CityNameUpdated, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(CityNameUpdated, CityCode.toUpperCase(), updatedCountryStateName, updatedCountryStateCode,
				CountryNameUpdated, CountryCodeUpdated);
		gm.EndTest();

	}

}
