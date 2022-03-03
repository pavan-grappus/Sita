package runnerFiles;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.Login;
import PageObjects.Hotels;
import PageObjects.Master;
import PageObjects.Products;
import PageObjects.ToastMessage;
import PageObjects.Suppliers;
import resource.EnvironmentDetails;
import utility.GenericMethods;

public class BeforeRun {

	GenericMethods gm;
	Login login = new Login();
	HomePage hp = new HomePage();
	Suppliers supplier = new Suppliers();
	Products prod = new Products();
	Hotels hotels = new Hotels();
	Master master = new Master();
	ToastMessage toastMessage = new ToastMessage();

	@BeforeSuite(enabled = true)
	public void readingVariablesfromFile() throws IOException {

		gm = new GenericMethods(System.getProperty("user.dir") + "\\Driver\\",
				System.getProperty("user.dir") + "\\Reports\\", "SITA_");

	}

	@BeforeTest(enabled = true)
	public void logintoApplication() {

		gm.StartTest("Launch the Application", "Launch the Application");

		gm.OpenBrowser(EnvironmentDetails.sitaURL, "chrome");
//		gm.runindebugmode("E:\\eclipseWorkSpace\\HumanityHealth\\Driver\\" + "chromedriver.exe");

		gm.EndTest();
	}

	@Test(enabled = false)
	public void asd() {

	}

	@AfterMethod(enabled = true)
	public void closeTest() {
		// gm.closeBrowser();
		gm.EndTest();
	}

	@AfterTest(enabled = true)
	public void teardownReport() {
		gm.EndTest();
		gm.EndReports();
	}

	public void validateCSSproperty(By path, String cssProperty, String note) {

		List<String> list = Arrays.asList(cssProperty.trim().split(";\r\n|;"));

		for (String s : list) {
			String[] splitArray = s.split(":");
			String Key = splitArray[0].trim();
			String value = splitArray[1].trim();

			gm.verifyElementCSSValue(path, Key, value, note + " " + Key);

		}
	}

}
