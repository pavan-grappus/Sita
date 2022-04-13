package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionSourceMarkets extends BeforeRun {

	public String SourceMarketsNationalityName;
	public String SourceMarketsNationalityNameUpdated;

	public String SourceMarketsNationalityCode;
	public String SourceMarketsNationalityCodeUpdated;

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	@BeforeClass
	public void getARandomNationalityDetails() {
		gm.StartTest("Navigate to Nationality details page and get the Details", "");
		gm.refresh("Master Section", 5);
		gm.click(master.entities_nationalities, "entities_nationalities");
		gm.hold(5);
		SourceMarketsNationalityName = gm.getText(master.nationalityNameList(1), "nationalityNameList(1)");
		SourceMarketsNationalityCode = gm.getText(master.nationalityCodeList(1), "nationalityCodeList(1)");
		SourceMarketsNationalityNameUpdated = gm.getText(master.nationalityNameList(2), "nationalityNameList(2)");
		SourceMarketsNationalityCodeUpdated = gm.getText(master.nationalityCodeList(2), "nationalityCodeList(2)");
		gm.EndTest();
	}

	public void navigateToSourceMarketsection() {
		gm.refresh("SourceMarketsSection", 5);
		gm.hold(5);
		gm.click(master.entities_SourceMarkets, "entities_SourceMarkets");
		gm.hold(5);
	}

	public void selectNationalityType(String NationalityType) {
		gm.setText(master.NationalitySearchComboField, NationalityType, "NationalitySearchComboField");
		gm.waitforElementVisible(master.selectFromComboOptions_Text(NationalityType), 5,
				"selectFromComboOptions_Text Facility Type");
		gm.click(master.selectFromComboOptions_Text(NationalityType),
				"selectFromComboOptions_Text(" + NationalityType + ") Facility Type");

	}

	public void openAddSourceMarketsModal(String SourceMarketsName, String SourceMarketsCode, String NationalityName) {

		gm.click(master.AddSourceMarkets, "AddSourceMarkets");
		gm.waitforElementVisible(master.nameField, 5, "SourceMarketsName");
		gm.setText(master.nameField, SourceMarketsName, "SourceMarketsName");
		gm.setText(master.codeField, SourceMarketsCode, "SourceMarketsCode");
		selectNationalityType(NationalityName);

		gm.click(master.AddSourceMarketButton, "AddSourceMarketButton");
	}

	public void openEditSourceMarketModal(String SourceMarketsName) {
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SourceMarketsName, "searchBar_input");
		gm.hold(5);
		gm.click(master.SourceMarketEditButton(SourceMarketsName), "SourceMarketEditButton(" + SourceMarketsName + ")");
		gm.waitforElementVisible(master.nameField, 5, "SourceMarketsName");
		gm.verifyElementNotPresent(master.codeField, 1, "codeField");
	}

	public void validateTheTabledata(String SourceMarketsName, String SourceMarketsCode,
			String SourceMarketsNationalityName, String SourceMarketsNationalityCode) {

		int count = gm.getSizeofWebelements(master.SourceMarketsNameList("position()"), "List of SourceMarkets");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.SourceMarketsNameList("position()"), SourceMarketsName,
				"List of SourceMarkets Name");

		gm.verifyListofElementText(master.SourceMarketCodeList("position()"), SourceMarketsCode,
				"List of SourceMarketCodeList");

		gm.verifyListofElementText(master.SourceMarketNationalityNameList("position()"), SourceMarketsNationalityName,
				"List of SourceMarketNationalityNameList");

		gm.verifyListofElementText(master.SourceMarketNationalityCodeList("position()"), SourceMarketsNationalityCode,
				"List of SourceMarketNationalityCodeList");
	}

	@Test
	public void SourceMarkets() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique SourceMarkets", "As a User I need to add a New SourceMarkets");

		navigateToSourceMarketsection();

		String SourceMarketsName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String SourceMarketsCode = RandomStringUtils.randomAlphabetic(3);

		gm.loginfo("SourceMarkets Name is :" + SourceMarketsName);
		gm.loginfo("SourceMarkets Nationality Name : " + SourceMarketsNationalityName);
		gm.loginfo("SourceMarkets Nationality Code : " + SourceMarketsNationalityCode);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		gm.loginfo("Initila Value as displayed is :" + Initialvalue);
		openAddSourceMarketsModal(SourceMarketsName, SourceMarketsCode, SourceMarketsNationalityName);
		validateToastMessage("Created successfully", "Source market created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New SourceMarkets is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Source Market", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added SourceMarkets",
				"As a User I search for the SourceMarkets details added Newly");
		navigateToSourceMarketsection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SourceMarketsName, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(SourceMarketsName, SourceMarketsCode.toUpperCase(), SourceMarketsNationalityName,
				SourceMarketsNationalityCode);
		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing SourceMarkets Code",
				"As a User I need to add a SourceMarkets with Existing county code");
		navigateToSourceMarketsection();
		openAddSourceMarketsModal(SourceMarketsName, SourceMarketsCode, SourceMarketsNationalityName);
		validateToastMessage("Bad Request", "Source market with code : "+SourceMarketsCode.toUpperCase()+" already exits");
		gm.EndTest();

		/*
		 * Test 5 - 1
		 */

		gm.StartTest("Edit the newly added SourceMarkets Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String SourceMarketsNameUpdated = SourceMarketsName + " Updated";

		gm.loginfo("SourceMarkets Name Updated is :" + SourceMarketsNameUpdated);
		gm.loginfo("SourceMarkets Nationality Name Updated : " + SourceMarketsNationalityNameUpdated);
		gm.loginfo("SourceMarkets Nationality Code Updated : " + SourceMarketsNationalityCodeUpdated);

		navigateToSourceMarketsection();
		openEditSourceMarketModal(SourceMarketsName);
		gm.clearbyBackspace(master.nameField, "SourceMarketsName");
		gm.setText(master.nameField, SourceMarketsNameUpdated, "SourceMarketsName");
		gm.click(master.EditSourceMarketButton, "EditSourceMarketButton");
		validateToastMessage("Updated successfully", "Source market updated");
		gm.EndTest();

		/*
		 * Test 5 - 2
		 */

		gm.StartTest("Edit the newly added SourceMarkets Nationality Name", "");

		navigateToSourceMarketsection();
		openEditSourceMarketModal(SourceMarketsNameUpdated);
		gm.clearbyBackspace(master.NationalitySearchComboField, "NationalitySearchComboField");
		selectNationalityType(SourceMarketsNationalityNameUpdated);
		gm.click(master.EditSourceMarketButton, "EditSourceMarketButton");
		validateToastMessage("Updated successfully", "Source market updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New SourceMarkets is Edited", "");
		navigateToSourceMarketsection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Source Market",
				"showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated SourceMarkets name", "");
		navigateToSourceMarketsection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, SourceMarketsNameUpdated, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(SourceMarketsNameUpdated, SourceMarketsCode.toUpperCase(), SourceMarketsNationalityNameUpdated,
				SourceMarketsNationalityCodeUpdated);

		gm.EndTest();

	}

}
