package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionCurrency extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToCurrencySection() {
		gm.refresh("Currency", 5);
		gm.click(master.entities_currencies, "entities_currencies");
		gm.hold(5);
	}

	public void openAddCurrencyModal(String CurrencyName, String CurrencyCode, String CurrencySymbol) {
		gm.click(master.AddCurrency, "AddCurrency");
		gm.waitforElementVisible(master.nameField, 5, "CurrencyName");
		gm.setText(master.nameField, CurrencyName, "CurrencyName");
		gm.setText(master.codeField, CurrencyCode, "CurrencycodeField");
		gm.setText(master.currencySymbol, CurrencySymbol, "currencySymbol");

		gm.click(master.AddCurrencyButton, "AddCurrencyButton");
	}

	public void validateTheTabledata(String CurrencyName, String CurrencyCode, String CurrencySymbol) {

		int count = gm.getSizeofWebelements(master.currencyNameList("position()"), "List of currencyNameList");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.currencyNameList("position()"), CurrencyName, "List of currencyNameList");

		gm.verifyListofElementText(master.currencyCodeList("position()"), CurrencyCode, "List of currencyCodeList");
		gm.verifyListofElementText(master.currencySymbolList("position()"), CurrencySymbol,
				"List of currencySymbolList");

	}

	public void searchAndOpenEditTheModaleWindow(String CurrencyName) {
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, CurrencyName, "searchBar_input");
		gm.hold(5);
		gm.click(master.CurrencyEditButton(CurrencyName), "editButton(" + CurrencyName + ")");
		gm.waitforElementVisible(master.nameField, 5, "CurrencyName");
		gm.verifyElementNotPresent(master.codeField, 1, "codeField");
	}

	@Test
	public void Currencys() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique Currency", "As a User I need to add a New Currency");

		navigateToCurrencySection();

		String CurrencyName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String CurrencyCode = RandomStringUtils.randomAlphabetic(3);
		String CurrencySymbol = RandomStringUtils.randomAlphabetic(3);

		gm.loginfo("Currency Name is :" + CurrencyName);
		gm.loginfo("Currency Code is :" + CurrencyCode);
		gm.loginfo("Currency Symbol is :" + CurrencySymbol);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddCurrencyModal(CurrencyName, CurrencyCode, CurrencySymbol);
		validateToastMessage("Created successfully", "Currency created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Currency is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Currency", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Currency", "As a User I search for the Currency details added Newly");
		navigateToCurrencySection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, CurrencyName, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(CurrencyName, CurrencyCode.toUpperCase(), CurrencySymbol);

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Currency Name",
				"As a User I need to add a Currency with Existing Currency code");
		navigateToCurrencySection();
		openAddCurrencyModal(CurrencyName, CurrencyCode, CurrencySymbol);
		validateToastMessage("Bad Request", "Currency with this Id : " + CurrencyCode.toUpperCase() + " already exits");
		gm.EndTest();

		/*
		 * Test 5 part 1
		 */

		String CurrencyNameUpdated = CurrencyName + " Updated";

		gm.StartTest("Edit the Currency Name in newly added Currency", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		gm.loginfo("Currency Name is :" + CurrencyNameUpdated);

		navigateToCurrencySection();
		searchAndOpenEditTheModaleWindow(CurrencyName);
		gm.clearbyBackspace(master.nameField, "nameField");
		gm.setText(master.nameField, CurrencyNameUpdated, "Currency nameField");
		gm.click(master.EditCurrencyButton, "EditCurrencyButton");
		validateToastMessage("Updated successfully", "Currency updated");
		gm.EndTest();

		/*
		 * Test 5 part 2
		 */
		String CurrencySymbolUpdated = CurrencySymbol + "Up";

		gm.StartTest("Edit the Currency Symbol in newly added Currency", "");

		navigateToCurrencySection();
		searchAndOpenEditTheModaleWindow(CurrencyNameUpdated);

		gm.clearbyBackspace(master.currencySymbol, "currencySymbol");
		gm.setText(master.currencySymbol, CurrencySymbolUpdated, "currencySymbol");

		gm.click(master.EditCurrencyButton, "EditCurrencyButton");
		validateToastMessage("Updated successfully", "Currency updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Currency is Edited", "");
		navigateToCurrencySection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Currency", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Currency name", "");
		navigateToCurrencySection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, CurrencyNameUpdated, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(CurrencyNameUpdated, CurrencyCode.toUpperCase(), CurrencySymbolUpdated);
		gm.EndTest();

	}

}
