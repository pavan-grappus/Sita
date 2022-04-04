package runnerFiles;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resource.EnvironmentDetails;

public class Hotels extends BeforeRun {

	@BeforeTest
	public void navigateToHotels() {
		gm.StartTest("Login to application and Navigate to Hotels section", "");

//		gm.runindebugmode("E:\\eclipseWorkSpace\\Sita\\Driver\\" + "chromedriver.exe");

		gm.waitforElementVisible(hp.resourceHeader, 10, "Resource Header Name");
		gm.hold(5);
		gm.click(hp.resourceHeader, "Resource Header Name");
		gm.hold(5);

		gm.mouseHowerOnElement(prod.productsMenuTitle, "Product Menu Title");
		gm.click(prod.HotelSubMenuTitle, "HotelSubMenuTitle");
		gm.hold(5);

		gm.EndTest();
	}

	@Test(enabled = false, priority = 1)
	public void validateHotelsModalWindow() {

		gm.StartTest("Add New Hotels modal window", "");

		gm.verifyElementVisible(hotels.addNewHotels_Button, "addNewHotels_Button");

		gm.verifyElementText(hotels.addNewHotels_Button, "Add New Hotels", "addNewHotels_Button");

		gm.click(hotels.addNewHotels_Button, "addNewHotels_Button");
		gm.waitforElementVisible(hotels.languageDropdown, 10, "languageDropdown");

		gm.loginfo("Verify the Language dropdown is not a selectable field");
		gm.verifyElementCSSValue(hotels.languageDropdown, "cursor", "not-allowed", "languageDropdown");
		gm.verifyElementText(hotels.languageDropdown, "English", "languageDropdown");

		gm.loginfo("Verify the Hotel Name Text field default view");

		gm.verifyElementVisible(hotels.hotelName_label(), "hotelName");
		gm.verifyElementClickable(hotels.hotelName_label(), "hotelName");
		gm.verifyElementText(hotels.hotelName_label(), "Hotel Name *", "hotelName");
		gm.verifyElementNOTVisible(hotels.hotelName_alert(), "Hotel Name Alert");

		gm.loginfo("Validating the Hotel name alert by Clicking On Text field and clciking on Out of focus");

		gm.click(hotels.hotelName_input(), "Hotel Name Input");
		gm.click(hotels.modalHeader, "modalHeader");
		gm.waitforElementVisible(hotels.hotelName_alert(), 4, "Hotel Name Alert");
		gm.verifyElementVisible(hotels.hotelName_alert(), "Hotel Name Alert");
		gm.verifyElementText(hotels.hotelName_alert(), "Hotel name is required", "Hotel Name Alert");
		gm.click(hotels.modelCloseButton, "Close Button");

		gm.loginfo("Validating the Hotel name alert by providing only one character");

		gm.click(hotels.addNewHotels_Button, "addNewHotels_Button");
		gm.verifyElementNOTVisible(hotels.hotelName_alert(), "Hotel Name Alert");
		gm.setText(hotels.hotelName_input(), "a", "HotelName Input");
		gm.presskeys(hotels.hotelName_input(), Keys.TAB, "Tab", "Hotel Name");
		gm.waitforElementVisible(hotels.hotelName_alert(), 4, "Hotel Name Alert");
		gm.verifyElementVisible(hotels.hotelName_alert(), "Hotel Name Alert");
		gm.verifyElementText(hotels.hotelName_alert(), "Hotel name must be at least 2 characters long.",
				"Hotel Name Alert");
		gm.click(hotels.modelCloseButton, "Close Button");

		gm.loginfo("Validating the Hotel name alert by CLEARING tEXT");

		gm.click(hotels.addNewHotels_Button, "addNewHotels_Button");
		gm.verifyElementNOTVisible(hotels.hotelName_alert(), "Hotel Name Alert");
		gm.setText(hotels.hotelName_input(), "abcd", "HotelName Input");
		gm.clearbyBackspace(hotels.hotelName_input(), "Hotel Name");
		gm.hold(5);
		gm.verifyElementNOTVisible(hotels.hotelName_alert(), "Hotel Name Alert");
		gm.click(hotels.modelCloseButton, "Close Button");

		gm.loginfo("Validating the Hotel name alert by CLEARING tEXT and having one char");

		gm.click(hotels.addNewHotels_Button, "addNewHotels_Button");
		gm.verifyElementNOTVisible(hotels.hotelName_alert(), "Hotel Name Alert");
		gm.setText(hotels.hotelName_input(), "ab", "HotelName Input");
		gm.presskeys(hotels.hotelName_input(), Keys.BACK_SPACE, "BACK_SPACE", "Hotel Name");
		gm.hold(5);
		gm.verifyElementNOTVisible(hotels.hotelName_alert(), "Hotel Name Alert");
		gm.click(hotels.modelCloseButton, "Close Button");

		gm.loginfo("Hotel Name should not be more than 500 characters");

		gm.click(hotels.addNewHotels_Button, "addNewHotels_Button");
		gm.verifyElementNOTVisible(hotels.hotelName_alert(), "Hotel Name Alert");
		gm.setText(hotels.hotelName_input(), RandomStringUtils.randomAlphabetic(200), "HotelName Input");
		gm.presskeys(hotels.hotelName_input(), Keys.TAB, "Tab", "Hotel Name");
		gm.verifyElementVisible(hotels.hotelName_alert(), "Hotel Name Alert");
		gm.verifyElementText(hotels.hotelName_alert(), "Hotel name must be at least 2 characters long.",
				"Hotel Name Alert");
		gm.click(hotels.modelCloseButton, "Close Button");

		gm.loginfo("Validating the Hotel name providing two characters");

		gm.click(hotels.addNewHotels_Button, "addNewHotels_Button");
		gm.setText(hotels.hotelName_input(), "Test Hotel Name", "Hotel Name");
		gm.setText(hotels.shortDescription, "Short Description", "shortDescription");
		gm.setText(hotels.longDescription, "Long Description", "longDescription");
		gm.setText(hotels.highlights, "Highlights", "highlights");
		gm.click(hotels.createHotel_button, "createHotel_button");

		gm.waitforElementVisible(toastMessage.toastTitle, 10, "Toast Message bar");
		gm.verifyElementText(toastMessage.toastTitle, "Created Successfully.", "Toast Title");
		gm.verifyElementText(toastMessage.toastdesc, "Hotel Created", "Toast Desc");

		gm.EndTest();

	}

	@Test(enabled = false)
	public void addNewHotelDetails() {
		gm.StartTest("Add new Hotel Details", "Add new Hotel Details");

		gm.waitforElementVisible(hotels.addDetails, 5, "addDetails");

		gm.click(hotels.addDetails, "addDetails");
		gm.waitforElementVisible(hotels.selectSupplierType, 2, "selectSupplierType");

		gm.click(hotels.selectSupplierType, "selectSupplierType");
		gm.click(supplier.DropdownValue("Direct Supplier"), "Supplier Type Dropdown");
		gm.click(hotels.selectSupplier, "selectSupplier");
		gm.hold(2);
		gm.click(supplier.DropdownValue("ICIC"), "Supplier Dropdown");
		gm.click(hotels.selectCountry, "Country Dropdown");
		gm.click(supplier.DropdownValue("Afghanistan"), "Country dropdown");
		gm.hold(2);
		gm.click(hotels.selectState, "selectState");
		gm.click(supplier.DropdownValue("Afg"), "Select State");
		gm.hold(2);
		gm.click(hotels.selectCity, "selectCity");
		gm.click(supplier.DropdownValue("kabul"), "select City");
		gm.hold(2);
		
		gm.setText(hotels.address, "Address", "Address");
		gm.setText(hotels.pinCode, "999999", "Pincode");
		gm.click(hotels.selectZone, "Select Zone");
		gm.click(supplier.DropdownValue("KabulZone"), "Zone Dropdown");
		gm.hold(2);
		
		gm.scrollToElement(gm.driver, gm.driver.findElement(hotels.healthAndSafetyCertificate_Yes));
		
		gm.clickNormal(hotels.healthAndSafetyCertificate_Yes, "healthAndSafetyCertificate_Yes");
		gm.waitforElementVisible(hotels.healthAndSafetyCertificate_FileUpload, 5, "healthAndSafetyFileUpload");
		gm.click(hotels.healthAndSafetyCertificate_FileUpload, "healthAndSafetyFileUpload");
		gm.uploadFileUsingAutoIT(EnvironmentDetails.uploadFilesRootPath, "healthAndSafety.pdf");

		gm.clickNormal(hotels.fireSafetyCertificate_Yes, "fireSafetyCertificate_Yes");
		gm.waitforElementVisible(hotels.fireSafetyCertificate_FileUpload, 5, "fireSafetyCertificate_FileUpload");
		gm.click(hotels.fireSafetyCertificate_FileUpload, "fireSafetyCertificate_FileUpload");
		gm.uploadFileUsingAutoIT(EnvironmentDetails.uploadFilesRootPath, "fireSafetyCertificate.pdf");

		gm.clickNormal(hotels.liquorLicense_Yes, "liquorLicense_Yes");
		gm.waitforElementVisible(hotels.liquorLicenseCertificate_FileUpload, 5, "liquorLicenseCertificate_FileUpload");
		gm.click(hotels.liquorLicenseCertificate_FileUpload, "liquorLicenseCertificate_FileUpload");
		gm.uploadFileUsingAutoIT(EnvironmentDetails.uploadFilesRootPath, "liquorLicence.pdf");

		gm.click(hotels.officialClassification, "officialClassification");
		gm.click(supplier.DropdownValue("5 Star"), "Classification dropdown");
		gm.hold(2);
		
		gm.click(hotels.tciClassification, "tciClassification");
		gm.click(supplier.DropdownValue("Luxury"), "TCI values");
		gm.hold(2);
		
		gm.clickNormal(hotels.checkInTime, "checkIn");
		gm.hold(2);
		gm.click(hotels.inTimeHours("01"), "inTimeHour");
		gm.click(hotels.inTimeMin("05"), "inTimeMin");
		gm.click(hotels.inTimeFormat("AM"), "inTimeFormat");
		gm.hold(2);
		gm.click(hotels.checkInOkbutton, "checkIn Ok");

		gm.clickNormal(hotels.checkOutTime, "checkOut");
		gm.hold(2);
		gm.click(hotels.outTimeHours("05"), "inTimeHour");
		gm.click(hotels.outTimeMin("04"), "inTimeMin");
		gm.click(hotels.outTimeFormat("PM"), "inTimeFormat");
		gm.hold(2);
		gm.click(hotels.checkOutOkbutton, "checkOut Ok");

		gm.click(hotels.saveDetails_button, "saveDetails_button");

		gm.waitforElementVisible(toastMessage.toastTitle, 10, "Toast Message bar");
		gm.verifyElementText(toastMessage.toastTitle, "Saved Successfully!", "Toast Title");
		gm.verifyElementText(toastMessage.toastdesc, "Details updated", "Toast Desc");

		gm.EndTest();
	}

}
