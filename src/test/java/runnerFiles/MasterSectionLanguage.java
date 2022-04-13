package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionLanguage extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToLanguageSection() {
		gm.refresh("Language", 5);
		gm.click(master.entities_Language, "entities_Language");
		gm.hold(5);
	}

	public void openAddLanguageModal(String LanguageName, String LanguageCode) {
		gm.click(master.AddLanguage, "AddLanguage");
		gm.waitforElementVisible(master.nameField, 5, "LanguageName");
		gm.setText(master.nameField, LanguageName, "LanguageName");
		gm.setText(master.codeField, LanguageCode, "LanguagecodeField");
		
		gm.click(master.AddLanguageButton, "AddLanguageButton");
	}

	public void validateTheTabledata(String LanguageName, String LanguageCode) {

		int count = gm.getSizeofWebelements(master.languageNameList("position()"), "List of languageNameList");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.languageNameList("position()"), LanguageName, "List of languageNameList");

		gm.verifyListofElementText(master.languageCodeList("position()"), LanguageCode, "List of languageCodeList");

	}

	public void searchAndOpenEditTheModaleWindow(String LanguageName) {
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, LanguageName, "searchBar_input");
		gm.hold(5);
		gm.click(master.LanguageEditButton(LanguageName), "editButton(" + LanguageName + ")");
		gm.waitforElementVisible(master.nameField, 5, "LanguageName");
		gm.verifyElementNotPresent(master.codeField, 1, "codeField");
	}

	@Test
	public void Languages() {

		/*
		 * Test 1
		 */
		gm.StartTest("Add a New Unique Language", "As a User I need to add a New Language");

		navigateToLanguageSection();

		String LanguageName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String LanguageCode = RandomStringUtils.randomAlphabetic(3);
		
		gm.loginfo("Language Name is :" + LanguageName);
		gm.loginfo("Language Code is :" + LanguageCode);
		
		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddLanguageModal(LanguageName, LanguageCode);
		validateToastMessage("Created successfully", "Language created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Language is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Languages", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Language", "As a User I search for the Language details added Newly");
		navigateToLanguageSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, LanguageName, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(LanguageName, LanguageCode.toUpperCase());

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Language Name",
				"As a User I need to add a Language with Existing Language code");
		navigateToLanguageSection();
		openAddLanguageModal(LanguageName, LanguageCode);
		validateToastMessage("Bad Request", "Language with this Id : " + LanguageCode.toUpperCase() + " already exits");
		gm.EndTest();

		/*
		 * Test 5 part 1
		 */

		String LanguageNameUpdated = LanguageName + " Updated";

		gm.StartTest("Edit the Language Name in newly added Language", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		gm.loginfo("Language Name is :" + LanguageNameUpdated);

		navigateToLanguageSection();
		searchAndOpenEditTheModaleWindow(LanguageName);
		gm.clearbyBackspace(master.nameField, "nameField");
		gm.setText(master.nameField, LanguageNameUpdated, "Language nameField");
		gm.click(master.EditLanguageButton, "EditLanguageButton");
		validateToastMessage("Updated successfully", "Language updated");
		gm.EndTest();

		
		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Language is Edited", "");
		navigateToLanguageSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Languages", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Language name", "");
		navigateToLanguageSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, LanguageNameUpdated, "searchBar_input");
		gm.hold(5);

		validateTheTabledata(LanguageNameUpdated, LanguageCode.toUpperCase());
		gm.EndTest();

	}

}
