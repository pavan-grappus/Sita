package runnerFiles;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionZones extends BeforeRun {

	public String ZoneCity = "";
	public String ZoneCityCode = "";

	public String ZoneCityUpdated = "";
	public String ZoneCityCodeUpdated = "";

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	@BeforeClass
	public void getARandomCityDetails() {
		gm.StartTest("Navigate to City details page and get the Details", "");
		gm.refresh("zones", 5);
		gm.click(master.entities_cities, "entities_cities");
		gm.hold(5);
		ZoneCity = gm.getText(master.cityNameList(1), "cityNameList(1)");
		ZoneCityCode = gm.getText(master.cityCodeList(1), "cityCodeList(1)");
		ZoneCityUpdated = gm.getText(master.cityNameList(2), "cityNameList(2)");
		ZoneCityCodeUpdated = gm.getText(master.cityCodeList(2), "cityCodeList(2)");
		gm.EndTest();
	}

	public void navigateToZonesSection() {
		gm.refresh("zones", 5);
		gm.click(master.entities_zones, "entities_zones");
		gm.hold(5);
	}

	public void openAddZoneModal(String ZoneName, String ZoneCityName) {
		gm.click(master.Addzones, "Addzones");
		gm.waitforElementVisible(master.nameField, 5, "ZoneName");
		gm.setText(master.nameField, ZoneName, "ZoneName");
		gm.setText(master.CitysearchComboField, ZoneCityName, "CitysearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Index("position()"), 1,
				"City selectFromComboOptions_Index");
		gm.click(master.selectFromComboOptions_Text(ZoneCityName), "Zone City");
		gm.click(master.AddZoneButton, "AddZoneButton");
	}

	@Test
	public void zones() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique Zone", "As a User I need to add a New Zone");

		navigateToZonesSection();

		String ZoneName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");

		gm.loginfo("Zone Name is :" + ZoneName);
		gm.loginfo("Zone code is :" + ZoneCity);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddZoneModal(ZoneName, ZoneCity);
		validateToastMessage("Created successfully", "Zone created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Zone is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Zones", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Zone", "As a User I search for the Zone details added Newly");
		navigateToZonesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, ZoneName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.zoneNameList("position()"), "List of zones");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.zoneNameList("position()"), ZoneName, "List of zones");

		gm.verifyListofElementText(master.zoneCityList("position()"), ZoneCity, "List of zones code");

		gm.verifyListofElementText(master.zoneCityCodeList("position()"), ZoneCityCode, "List of zones code");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Zone Code", "As a User I need to add a Zone with Existing Zone code");
		navigateToZonesSection();
		openAddZoneModal(ZoneName, ZoneCity);
		validateToastMessage("Bad Request", "Zone with same name already exists");
		gm.EndTest();

		/*
		 * Test 5 part 1
		 */

		gm.StartTest("Edit the City Name in newly added Zone Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		navigateToZonesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, ZoneName, "searchBar_input");
		gm.hold(5);
		gm.click(master.zoneEditButton(ZoneName), "editButton(" + ZoneName + ")");
		gm.waitforElementVisible(master.nameField, 5, "ZoneName");
		gm.clearbyBackspace(master.CitysearchComboField, "CitysearchComboField");
		gm.setText(master.CitysearchComboField, ZoneCityUpdated, "ZoneName");
		gm.waitforElementVisible(master.selectFromComboOptions_Index("position()"), 1,
				"City selectFromComboOptions_Index");
		gm.click(master.selectFromComboOptions_Text(ZoneCityUpdated), "Zone City");
		gm.click(master.EditZoneButton, "EditZoneButton");
		validateToastMessage("Updated successfully", "Zone updated");
		gm.EndTest();

		/*
		 * Test 5 part 2
		 */

		gm.StartTest("Edit the newly added Zone Name", "");

		String ZoneNameUpdated = ZoneName + " Updated";
		navigateToZonesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, ZoneName, "searchBar_input");
		gm.hold(5);
		gm.click(master.zoneEditButton(ZoneName), "editButton(" + ZoneName + ")");
		gm.waitforElementVisible(master.nameField, 5, "ZoneName");
		gm.clearbyBackspace(master.nameField, "ZoneName");
		gm.setText(master.nameField, ZoneNameUpdated, "ZoneName");
		gm.verifyElementNotPresent(master.codeField, 1, "ZoneCode");
		gm.click(master.EditZoneButton, "EditZoneButton");
		validateToastMessage("Updated successfully", "Zone updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Zone is Edited", "");
		navigateToZonesSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Zones", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Zone name", "");
		navigateToZonesSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, ZoneNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.zoneNameList("position()"), "List of zones");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.zoneNameList("position()"), ZoneNameUpdated, "List of zones");

		gm.verifyListofElementText(master.zoneCityList("position()"), ZoneCityUpdated, "List of zones code");
		gm.verifyListofElementText(master.zoneCityCodeList("position()"), ZoneCityCodeUpdated, "List of zones code");
		gm.EndTest();

	}

}
