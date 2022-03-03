package PageObjects;

import org.openqa.selenium.By;

public class Hotels {

	public By addNewHotels_Button = By.xpath("//button//span[text()='Add New hotels']");

	public By basicDetails = By.xpath("//p[text()='Basic Details']");

	public By configurations = By.xpath("//p[text()='Configurations']");

	public By servicesAndTariffs = By.xpath("//p[text()='Services & Tariffs']");

	public By profilling = By.xpath("//p[text()='Profiling']");

	public By modalHeader = By.className("ant-modal-title");
	/*
	 * About the HOTEL
	 */
	public By languageDropdown = By.xpath("//div[@name='language']");

	private String hotelFloatConatiner = "//input[@name='name']//ancestor::div[@id='float-label']";

	public By hotelName_input() {
		return By.xpath(hotelFloatConatiner + "//input");
	}

	public By hotelName_label() {
		return By.xpath(hotelFloatConatiner + "//label");
	}

	public By hotelName_alert() {
		return By.xpath(hotelFloatConatiner + "//div[@role='alert']");
	}

	public By shortDescription = By.xpath("//input[@name='shortDescription']");

	public By longDescription = By.xpath("//textarea[@name='longDescription']");

	public By highlights = By.xpath("//input[@name='highlights']");

	public By createHotel_button = By.xpath("//button//span[text()='Create Hotel']");

	public By addInOtherLanguages = By.xpath("//p[text()='Add In Other Languages']");

	public By createDescription = By.xpath("//button//span[text()='Create Description']");

	public By modelCloseButton = By.xpath("//span[@class='ant-modal-close-x']");
	/*
	 * HOTEL DETAILS
	 */

	public By addDetails = By.xpath("//p[text()='Add Details']");

	public By selectSupplierType = By.xpath("//div[@name='supplierType']");

	public By selectSupplier = By.xpath("//div[@name='supplierCode']");

	public By selectCountry = By.xpath("//div[@name='address.country']");

	public By selectState = By.xpath("//div[@name='address.state']");

	public By selectCity = By.xpath("//div[@name='address.city']");

	public By address = By.xpath("//input[@name='address.addressLine1']");

	public By pinCode = By.xpath("//input[@name='address.pinCode']");

	public By selectZone = By.xpath("//div[@name='address.zone']");

	public By healthAndSafetyCertificate_Yes = By
			.xpath("//span[text()='Yes']/preceding-sibling::span//input[@name='healthAndSafetyCertificate']");

	public By healthAndSafetyCertificate_No = By
			.xpath("//span[text()='No']/preceding-sibling::span//input[@name='healthAndSafetyCertificate']");

	public By healthAndSafetyCertificate_FileUpload = By.xpath("//p[text()='Upload Health & Safety Certificate']");

	public By fireSafetyCertificate_Yes = By
			.xpath("//span[text()='Yes']/preceding-sibling::span//input[@name='fireSafetyCertificate']");

	public By fireSafetyCertificate_No = By
			.xpath("//span[text()='No']/preceding-sibling::span//input[@name='fireSafetyCertificate']");

	public By fireSafetyCertificate_FileUpload = By.xpath("//p[text()='Upload Fire Safety Certificate']");

	public By liquorLicense_Yes = By
			.xpath("//span[text()='Yes']/preceding-sibling::span//input[@name='liquorLicense']");

	public By liquorLicense_No = By.xpath("//span[text()='No']/preceding-sibling::span//input[@name='liquorLicense']");

	public By liquorLicenseCertificate_FileUpload = By.xpath("//p[text()='Upload Liquor License']");

	public By officialClassification = By.xpath("//div[@name='officialClassification']");

	public By tciClassification = By.xpath("//div[@name='tciClassification']");

	public By checkInTime = By.xpath("//input[@name='checkInTime']");

	public By checkInOkbutton = By.xpath("(//span[text()='Ok']/parent::button)[1]");

	public By checkOutTime = By.xpath("//input[@name='checkOutTime']");

	public By checkOutOkbutton = By.xpath("(//span[text()='Ok']/parent::button)[2]");

	public By saveDetails_button = By.xpath("//button//span[text()='Save Details']");

	public By inTimeHours(String hour) {
		return By.xpath("(//ul[@class='ant-picker-time-panel-column'])[1]//div[text()='" + hour + "']");
	}

	public By inTimeMin(String hour) {
		return By.xpath("(//ul[@class='ant-picker-time-panel-column'])[2]//div[text()='" + hour + "']");
	}

	public By inTimeFormat(String hour) {
		return By.xpath("(//ul[@class='ant-picker-time-panel-column'])[3]//div[text()='" + hour + "']");
	}

	public By outTimeHours(String hour) {
		return By.xpath("(//ul[@class='ant-picker-time-panel-column'])[4]//div[text()='" + hour + "']");
	}

	public By outTimeMin(String hour) {
		return By.xpath("(//ul[@class='ant-picker-time-panel-column'])[5]//div[text()='" + hour + "']");
	}

	public By outTimeFormat(String hour) {
		return By.xpath("(//ul[@class='ant-picker-time-panel-column'])[6]//div[text()='" + hour + "']");
	}

	/*
	 * FACT SHEET
	 */

	public By mandatory_FactSheet = By.xpath("//div[@id='rc-tabs-0-tab-product-hotel-factsheet-mandatory']");

	public By optional_FactSheet = By.xpath("//div[@id='rc-tabs-0-tab-product-hotel-factsheet-optional']");

	public By checkBoxUsingLabel(By String) {
		return By.xpath("//span[text()='" + String + "']/preceding-sibling::label//input[@type='checkbox']");
	}

}
