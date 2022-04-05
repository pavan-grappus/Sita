package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSectionTrain extends BeforeRun {

	@BeforeTest
	public void before() {
		gm.StartTest("Navigate to Master section", "");
		navigateToMasterSection();
		gm.EndTest();
	}
	
	public void navigateToTrainSection() {
		gm.refresh("TainSection", 5);
		gm.hold(5);
		gm.click(master.entities_Trains, "entities_Trains");
		gm.hold(5);
	}

	public void openAddTrainModal(String TrainName, String TrainCode) {

		gm.click(master.AddTrains, "AddTrains");
		gm.waitforElementVisible(master.nameField, 5, "TrainName");
		gm.setText(master.nameField, TrainName, "TrainName");
		gm.setText(master.codeField, TrainCode, "TrainCode");
		gm.click(master.AddTrainsButton, "AddTrainsButton");
	}

	@Test
	public void trains() {

		/*
		 * Test 1
		 */

		gm.StartTest("Add a New Unique Train", "As a User I need to add a New Train");

		navigateToTrainSection();

		String trainName = "TestName_" + gm.getCurrentTime("DDmmYYYYhhmmss", "GMT");
		String trainCode = RandomStringUtils.randomAlphabetic(10);

		gm.loginfo("Train Name is :" + trainName);
		gm.loginfo("Train code is :" + trainCode);

		int Initialvalue = Integer.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		openAddTrainModal(trainName, trainCode);
		validateToastMessage("Created successfully", "Train created");
		gm.EndTest();

		/*
		 * Test 2
		 */

		gm.StartTest("Validating if count is increasing when New Train is added", "");

		gm.verifyElementText(master.showingCount, "Showing " + (Initialvalue + 1) + " Trains", "showingCount");
		gm.EndTest();

		/*
		 * Test 3
		 */

		gm.StartTest("Search for the Added Train", "As a User I search for the train details added Newly");
		navigateToTrainSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, trainName, "searchBar_input");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.trainNameList("position()"), "List of Train");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.trainNameList("position()"), trainName, "List of Train");

		gm.verifyListofElementText(master.trainCodeList("position()"), trainCode.toUpperCase(),
				"List of Train code");

		gm.EndTest();

		/*
		 * Test 4
		 */

		gm.StartTest("Trying to add a Existing Train Code",
				"As a User I need to add a Train with Existing county code");
		navigateToTrainSection();
		openAddTrainModal(trainName, trainCode);
		validateToastMessage("Bad Request", "Train with this Id : " + trainCode.toUpperCase() + " already exits");
		gm.EndTest();

		/*
		 * Test 5
		 */

		gm.StartTest("Edit the newly added Train Name", "");

		int InitialvaluebeforeUpdate = Integer
				.parseInt(gm.getText(master.showingCount, "showingCount").replaceAll("[^0-9]", ""));

		String trainNameUpdated = trainName + " Updated";
		navigateToTrainSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, trainName, "searchBar_input");
		gm.hold(5);
		gm.click(master.trainEditButton(trainName), "editButton(" + trainName + ")");
		gm.waitforElementVisible(master.nameField, 5, "TrainName");
		gm.clearbyBackspace(master.nameField, "TrainName");
		gm.setText(master.nameField, trainNameUpdated, "TrainName");
		gm.verifyElementNotPresent(master.codeField, 1, "TrainCode");
		gm.click(master.EditTrainButton, "EditTrainButton");
		validateToastMessage("Updated successfully", "Train updated");
		gm.EndTest();

		/*
		 * Test 6
		 */

		gm.StartTest("Validating if count is Not increasing when New Train is Edited", "");
		navigateToTrainSection();
		gm.verifyElementText(master.showingCount, "Showing " + InitialvaluebeforeUpdate + " Trains", "showingCount");
		gm.EndTest();

		/*
		 * Test 7
		 */

		gm.StartTest("Searching with the Updated train name", "");
		navigateToTrainSection();
		gm.click(master.searchBar_input, "searchBar_input')");
		gm.setText(master.searchBar_input, trainNameUpdated, "searchBar_input");
		gm.hold(5);

		int countUpdated = gm.getSizeofWebelements(master.trainNameList("position()"), "List of Train");

		if (countUpdated > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementText(master.trainNameList("position()"), trainNameUpdated, "List of Train");

		gm.verifyListofElementText(master.trainCodeList("position()"), trainCode.toUpperCase(),
				"List of Train code");
		gm.EndTest();

	}

}
