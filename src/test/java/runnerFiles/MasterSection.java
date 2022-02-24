package runnerFiles;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MasterSection extends BeforeRun {

	@BeforeTest
	public void loginToMasterSection() {
		gm.StartTest("Login to application and Navigate to Master section", "");

		gm.setText(login.email_input, "Mahima@grappus.com", "email_input");
		gm.setText(login.password_input, "mahima@g", "password_input");
		gm.click(login.loginButton, "loginButton");

		gm.waitforElementVisible(master.mastersHeader, 10, "Masters Header Name");
		gm.hold(10);
		gm.click(master.mastersHeader, "Masters Header Name");
		gm.hold(5);
		gm.EndTest();
	}

	@Test
	public void countries() {

		String countryName = "TestName_" + LocalDateTime.now();
		String countryCode = RandomStringUtils.randomAlphabetic(3);

		gm.StartTest("Add a New Unique Country", "As a User I need to add a New Country");
		gm.click(master.entities_countries, "entities_countries");
		gm.hold(5);
		gm.click(master.AddCountries, "AddCountries");
		gm.setText(master.countryName, countryName, "countryName");
		gm.setText(master.countryCode, countryCode, "countryCode");
		gm.click(master.AddCountryButton, "AddCountryButton");
		gm.waitforElementVisible(toastMessage.toastTitle, 10, "toastTitle");
		gm.verifyElementText(toastMessage.toastTitle, "Created successfully", "toastTitle");
		gm.verifyElementText(toastMessage.toastdesc, "Country created", "toastdesc");
		gm.EndTest();

		gm.StartTest("Search for the Added Company", "As a User I search for the company details added Newly");

		gm.click(master.searchForCountries_SearchBar, "searchForCountries_SearchBar')");
		gm.setText(master.searchForCountries_SearchBar, countryName, "searchForCountries_SearchBar");
		gm.hold(5);

		int count = gm.getSizeofWebelements(master.countryNameList("position()"), "List of Countries");

		if (count > 0) {
			gm.logPass("Count is greater than Zero");
		} else {
			gm.logFail("Count is not greater than Zero");
		}

		gm.verifyListofElementsContainsaText(master.countryNameList("position()"), countryName, "List of Countries");

		gm.verifyListofElementsContainsaText(master.countryCodeList("position()"), countryCode,
				"List of Countries code");
		gm.EndTest();

	}

}
