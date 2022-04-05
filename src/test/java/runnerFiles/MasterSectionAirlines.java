package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionAirlines extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}

	public void navigateToAirlineSection() {
		gm.refresh("AirlineSection", 5);
		gm.hold(5);
		gm.click(master.entities_Airlines, "entities_Airlines");
		gm.hold(5);
	}

	public void openAddAirlineModal(String AirlineName, String AirlineCode) {

		gm.click(master.AddAirlines, "AddAirlines");
		gm.waitforElementVisible(master.nameField, 5, "AirlineName");
		gm.setText(master.nameField, AirlineName, "AirlineName");
		gm.setText(master.codeField, AirlineCode, "AirlineCode");
		gm.click(master.AddAirlinesButton, "AddAirlinesButton");
	}

	@Test
	public void airlines() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique Airline", "As a User I need to add a New Airline");

		navigateToAirlineSection();

		String AirlineName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String AirlineCode = RandomStringUtils.randomAlphabetic(10);

		gm.loginfo("Airline Name is :" + AirlineName);
		gm.loginfo("Airline code is :" + AirlineCode);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddAirlineModal(AirlineName, AirlineCode);
		validateToastMessage("Created successfully", "Airline created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Airline is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Airlines", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Airline", "As a User I search for the Airline details added Newly");
		navigateToAirlineSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, AirlineName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.AirlinesNameList("position()"), "List of Airlines");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.AirlinesNameList("position()"), AirlineName, "List of Airlines");

		gm.verifyListofElementText(master.AirlinesCodeList("position()"), AirlineCode.toUpperCase(),
				"List of Airlines code");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Airline Code",
				"As a User I need to add a Airline with Existing county code");
		navigateToAirlineSection();
		openAddAirlineModal(AirlineName, AirlineCode);
		validateToastMessage("Bad Request", "Airline with this Id : " + AirlineCode.toUpperCase() + " already exits");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added Airline Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String AirlineNameUpdated = AirlineName + " Updated";
		navigateToAirlineSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, AirlineName, "searchBar_input");
		gm.hold(5);
		gm.click(master.AirlinesEditButton(AirlineName), "editButton(" + AirlineName + ")");
		gm.waitforElementVisible(master.nameField, 5, "AirlineName");
		gm.clearbyBackspace(master.nameField, "AirlineName");
		gm.setText(master.nameField, AirlineNameUpdated, "AirlineName");
		gm.verifyElementNotPresent(master.codeField, 1, "AirlineCode");
		gm.click(master.EditAirlinesButton, "EditAirlineButton");
		validateToastMessage("Updated successfully", "Airline updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Airline is Edited", "");
		navigateToAirlineSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Airlines", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated Airline name", "");
		navigateToAirlineSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, AirlineNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.AirlinesNameList("position()"), "List of Airlines");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.AirlinesNameList("position()"), AirlineNameUpdated, "List of Airlines");

		gm.verifyListofElementText(master.AirlinesCodeList("position()"), AirlineCode.toUpperCase(),
				"List of Airlines code");
		gm.EndTest();

	}

}
