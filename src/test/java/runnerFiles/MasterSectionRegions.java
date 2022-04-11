package runnerFiles;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionRegions extends BeforeRun {

	public String RegionsCountriesName;
	public String RegionsCountriesNameUpdated;

	public String RegionsCountriesCode;
	public String RegionsCountriesCodeUpdated;

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	@BeforeClass
	public void getARandomCountryDetails() {
		gm.StartTest("Navigate to Countries details page and get the Details", "");
		gm.refresh("Master Section", 5);
		gm.click(master.entities_countries, "entities_countries");
		gm.hold(5);
		RegionsCountriesName = gm.getText(master.countryNameList(1), "countryNameList(1)");
		RegionsCountriesCode = gm.getText(master.countryCodeList(1), "countryCodeList(1)");
		RegionsCountriesNameUpdated = gm.getText(master.countryNameList(2), "countryNameList(2)");
		RegionsCountriesCodeUpdated = gm.getText(master.countryCodeList(2), "countryCodeList(2)");
		gm.EndTest();
	}

	public void navigateToRegionsection() {
		gm.refresh("RegionsSection", 5);
		gm.hold(5);
		gm.click(master.entities_Regions, "entities_Regions");
		gm.hold(5);
	}

	public void selectCountriesType(String CountriesType) {
		gm.setText(master.CountriesSearchComboField, CountriesType, "CountriesSearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(CountriesType), 5,
				"selectFromComboOptions_Text Facility Type");
		gm.click(master.selectFromComboOptions_Text(CountriesType),
				"selectFromComboOptions_Text(" + CountriesType + ") Facility Type");

	}

	public void openAddRegionsModal(String RegionsName, String CountriesName) {

		gm.click(master.AddRegions, "AddRegions");
		gm.waitforElementVisible(master.nameField, 5, "RegionsName");
		gm.setText(master.nameField, RegionsName, "RegionsName");
		selectCountriesType(CountriesName);

		gm.click(master.AddRegionButton, "AddRegionButton");
	}

	public void validateTheTabledata(String RegionsName, String RegionsCountriesName, String RegionsCountriesCode) {
		gm.verifyListofElementText(master.RegionsNameList("position()"), RegionsName, "List of Regions Name");

		gm.verifyListofElementText(master.RegionsCountryNameList("position()"), RegionsCountriesName,
				"List of RegionsCountryNameList");

		gm.verifyListofElementText(master.RegionsCountryCodeList("position()"), RegionsCountriesCode,
				"List of RegionsCountryCodeList");
	}

	@Test
	public void Regions() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique Regions", "As a User I need to add a New Regions");

		navigateToRegionsection();

		String RegionsName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("Regions Name is :" + RegionsName);
		gm.loginfo("Regions Country Name : " + RegionsCountriesName);
		gm.loginfo("Regions Country Code : " + RegionsCountriesCode);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddRegionsModal(RegionsName, RegionsCountriesName);
		validateToastMessage("Created successfully", "Region created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Regions is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Regions", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Regions", "As a User I search for the Regions details added Newly");
		navigateToRegionsection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, RegionsName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.RegionsNameList("position()"), "List of Regions");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		validateTheTabledata(RegionsName, RegionsCountriesName, RegionsCountriesCode);
		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Regions Code",
				"As a User I need to add a Regions with Existing county code");
		navigateToRegionsection();
		openAddRegionsModal(RegionsName, RegionsCountriesName);
		validateToastMessage("Bad Request", "Region with same name already exists");
		gm.EndTest();

		/*
		 * Test 5 - 1
		 */

		gm.StartTest("Edit the newly added Regions Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String RegionsNameUpdated = RegionsName + " Updated";

		gm.loginfo("Regions Name Updated is :" + RegionsNameUpdated);
		gm.loginfo("Regions Country Name Updated : " + RegionsCountriesNameUpdated);
		gm.loginfo("Regions Country Code Updated : " + RegionsCountriesCodeUpdated);

		navigateToRegionsection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, RegionsName, "searchBar_input");
		gm.hold(5);
		gm.click(master.RegionsEditButton(RegionsName), "editButton(" + RegionsName + ")");
		gm.waitforElementVisible(master.nameField, 5, "RegionsName");
		gm.clearbyBackspace(master.nameField, "RegionsName");
		gm.setText(master.nameField, RegionsNameUpdated, "RegionsName");
		gm.click(master.EditRegionButton, "EditRegionButton");
		validateToastMessage("Updated successfully", "Region updated");
		gm.EndTest();

		/*
		 * Test 5 - 2
		 */

		gm.StartTest("Edit the newly added Regions Countries Name", "");

		navigateToRegionsection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, RegionsNameUpdated, "searchBar_input");
		gm.hold(5);
		gm.click(master.RegionsEditButton(RegionsNameUpdated), "editButton(" + RegionsNameUpdated + ")");
		gm.waitforElementVisible(master.nameField, 5, "RegionsName");
		selectCountriesType(RegionsCountriesNameUpdated);
		gm.click(master.EditRegionButton, "EditRegionButton");
		validateToastMessage("Updated successfully", "Region updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Regions is Edited", "");
		navigateToRegionsection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Regions", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Regions name", "");
		navigateToRegionsection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, RegionsNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.RegionsNameList("position()"), "List of roup");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		validateTheTabledata(RegionsNameUpdated, RegionsCountriesNameUpdated, RegionsCountriesCodeUpdated);

		gm.EndTest();

	}

}
