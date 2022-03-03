package PageObjects;

import org.openqa.selenium.By;

public class Suppliers {

	public By suppliersMenuTitle = By.xpath("//span[contains(@class,'menu-title')]/a[text()='Suppliers']");

	public By addNewSupplier_Button = By.xpath("//button[contains(@class,'product-create-btn')]");

	public By directSupplier_Radio = supplierType_Radio("direct supplier");

	public By indirectSupplier_Radio = supplierType_Radio("indirect supplier");

	public By associateOffice_Radio = supplierType_Radio("associate-office");

	public By branchOffice_Radio = supplierType_Radio("branch office");

	public By hotel_Checkbox = servicesOffered_Checkbox("hotel");

	public By standAloneFnb_Checkbox = servicesOffered_Checkbox("stand alone fnb");

	public By activity_Checkbox = servicesOffered_Checkbox("activity");

	public By guide_Checkbox = servicesOffered_Checkbox("guide");

	public By transport_Checkbox = servicesOffered_Checkbox("transport");

	public By package_Checkbox = servicesOffered_Checkbox("package");

	public By locatedAt_Dropdown = By.xpath("//div[@name='city']");

	public By companyName_Input = By.xpath("//input[@name='name']");

	public By createNewSupplier_Button = By.xpath("//button[@type='submit']");

	public By closeButton_Button = By.xpath("//button[@aria-label='Close']");

	public By inExistenceSince_input = By.xpath("//input[@name='estdYear']");

	public By otherDMCSIworkWith_TextBox = By.xpath("//input[@name='otherDMCs']");

	public By comments_Textbox = By.xpath("//textarea[@name='comments']");

	public By saveAndContinue_Button = By.xpath("//button[@type='submit']");

	public By supplierType_Radio(String radioText) {
		return By.xpath("//span[text()='" + radioText + "']/preceding-sibling::label[contains(@class,'radio')]");
	}

	public By servicesOffered_Checkbox(String checkboxText) {
		return By.xpath("//span[text()='" + checkboxText + "']/preceding-sibling::label[contains(@class,'checkbox')]");
	}

	/////////////////////////////
	// Detailed Info page

	public By basicDetailed_TabName = tabName("Basic Details");

	/*
	 * kyc objects
	 */

	public By kYcVerification_TabName = tabName("KYC Verification");

	public By ownershipAndManagement = By.id("rc-tabs-18-tab-ownership_management");
	/*
	 * OwnerShip Management
	 */

	public By nameOfTheSupplier = By.xpath("//input[@name='name']");

	public By nameOfLegalEntity = By.xpath("//input[@name='legalEntity']");

	public By legalEntityType = By.xpath("//input[@name='legalEntity']");

	public By registeredAs = By.xpath("//input[@id='rc_select_94']");

	public By msmeYesRadioButton = MSMERadioButton("Yes");
	public By msmeNoRadioButton = MSMERadioButton("No");

	public By MSMERadioButton(String yesOrNo) {
		return By.xpath("//label[text()='" + yesOrNo + "']//input[@name='isMSMECertified']");
	}

	public By address = By.xpath("//input[@name='registeredAddress.addressLine1']");

	public By pincode = By.xpath("//input[@name='registeredAddress.pinCode']");

	public By communicationAddressSameAsAbove = By.xpath("//input[@name='isPostalAddressSameAsReg']");

	/*
	 * 
	 */
	public By statutoryDetails = By.id("rc-tabs-18-tab-statutory_details");

	public By gstApplicableYesRadioButton = GstApplicableRadioButton("Yes");
	public By gstApplicableNoRadioButton = GstApplicableRadioButton("No");

	public By GstApplicableRadioButton(String yesOrNo) {
		return By.xpath("//span[text()='" + yesOrNo + "']//preceding-sibling::span//input[@name='isGSTApplicable']");
	}

	public By GstinNumber = By.xpath("//input[@name='gst.idn']");

	public By uploadGSTCertificate = By.xpath("//p[text()='Upload GST Certificate']");

	public By panNumber_Input = By.xpath("//input[@name='pan.idn']");

	public By panName_Input = By.xpath("//input[@name='pan.documentHolder']");

	public By uploadPANproof = By.xpath("//p[text()='Upload PAN Proof']");

	public By uploadRelationShipProof = By.xpath("//p[text()='Upload Relationship Proof']");

	/*
	 * 
	 */

	public By bankingDetails = By.xpath("rc-tabs-18-tab-banking_details");

	public By nameOfTheBank = By.xpath("//input[@name='bank']");

	public By accountHolderName = By.xpath("//input[@name='accountHolder']");

	public By bankAccountNumber = By.xpath("//input[@name='accountNumber']");

	public By ifscCode = By.xpath("//input[@name='ifsc']");

	public By selectCountry = By.xpath("//div[@name='address.country']//input");

	public By selectState = By.xpath("//div[@name='address.state']//input");

	public By selectCity = By.xpath("//div[@name='address.city']//input");

	public By uploadCancelledCheque = By.xpath("//p[text()='Upload cancelled cheque']");

	/*
	 * 
	 */
	public By keyContacts = By.xpath("rc-tabs-18-tab-key_contacts");

	public By addCompanyOwnerDetails = By.xpath("(//*[@id='banking_details']//div//p)[1]");

	public By addFinanceHeadDetails = By.xpath("(//*[@id='banking_details']//div//p)[2]");

	public By companyOwnerDesignation = By.xpath("//input[@name='designation']");

	public By selectDepartment = By.xpath("//input[@id='rc_select_49']");

	public By salutationMr_Radio = salutationRadio("Mr");
	public By salutationMrs_Radio = salutationRadio("Mrs");
	public By salutationMs_Radio = salutationRadio("Ms");
	public By salutationDr_Radio = salutationRadio("Dr");

	public By genderMale_Radio = salutationRadio("Male");
	public By genderFemale_Radio = salutationRadio("Female");
	public By genderOther_Radio = salutationRadio("Other");

	public By FirstName_Input = By.xpath("//input[@name='firstName']");
	public By MiddleName_Input = By.xpath("//input[@name='middleName']");
	public By LastName_Input = By.xpath("//input[@name='lastName']");
	public By contactNumber_Input = By.xpath("//input[@name='telephone']");
	public By alternateNumber_Input = By.xpath("//input[@name='alternateTelephone']");
	public By email_input = By.xpath("//input[@name='email']");

	public By isPrimaryContact = By.xpath("//input[@name='isPrimary']");
	public By saveContact = By.xpath("//button[@type='submit']/span[text()='Save Contact']");

	public By salutationRadio(String salutation) {
		return By.xpath("//label[text()='" + salutation + "']//input[@type='radio']");
	}

	public By continue_Button = By.xpath("//button/span[text()='Continue']");
	/*
	 * HOTEL objects
	 */
	public By Hotel_TabName = tabName("hotel");

	public By tabName(String Tabname) {
		return By.xpath("//p[contains(@class,'tab-label') and text()='" + Tabname + "']");
	}

	public By DropdownValue(String dropDownValue) {
		return By.xpath("//div[@class='ant-select-item-option-content' and text()='" + dropDownValue + "']");
	}

	public By confirmAndSubmit = By.xpath("//button[@type='submit']//span[text()='Confirm and Submit']");
}
