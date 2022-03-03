package runnerFiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Hotels extends BeforeRun {

	@BeforeTest
	public void navigateToHotels() {
		gm.StartTest("Login to application and Navigate to Hotels section", "");

//		gm.runindebugmode("E:\\eclipseWorkSpace\\Sita\\Driver\\" + "chromedriver.exe");

		gm.setText(login.email_input, "Mahima@grappus.com", "email_input");
		gm.setText(login.password_input, "mahima@g", "password_input");
		gm.click(login.loginButton, "loginButton");

		gm.waitforElementVisible(hp.resourceHeader, 10, "Resource Header Name");
		gm.hold(5);
		gm.click(hp.resourceHeader, "Resource Header Name");
		gm.hold(5);

		gm.mouseHowerOnElement(prod.productsMenuTitle, "Product Menu Title");
		gm.click(prod.HotelSubMenuTitle, "HotelSubMenuTitle");
		gm.hold(5);

		gm.EndTest();
	}

	@Test
	public void validateHotelsModalWindow() {
		
		gm.StartTest("Add New Hotels modal window", "");
		
		gm.verifyElementVisible(hotels.addNewHotels_Button, "addNewHotels_Button");
		
		gm.verifyElementText(hotels.addNewHotels_Button, "Add New Hotels", "addNewHotels_Button");
		
		gm.click(hotels.addNewHotels_Button, "addNewHotels_Button");
		
		/*
		 * Verify the Language dropdowin is not a selectable field
		 */
		
		
		gm.verifyElementCSSValue(hotels.languageDropdown, "cursor", "not-allowed", "languageDropdown");
		
		gm.EndTest();

	}

}
