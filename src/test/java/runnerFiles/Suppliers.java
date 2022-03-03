package runnerFiles;

import java.time.LocalDateTime;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resource.EnvironmentDetails;

public class Suppliers extends BeforeRun {

	@BeforeTest
	public void navigateToSuppliers() {
		gm.StartTest("Login to application and Navigate to Resource section", "");

		gm.runindebugmode("E:\\eclipseWorkSpace\\Sita\\Driver\\" + "chromedriver.exe");

//		gm.setText(login.email_input, "Mahima@grappus.com", "email_input");
//		gm.setText(login.password_input, "mahima@g", "password_input");
//		gm.click(login.loginButton, "loginButton");
//
//		gm.waitforElementVisible(hp.resourceHeader, 10, "Resource Header Name");
//		gm.hold(5);
//		gm.click(hp.resourceHeader, "Resource Header Name");
//		gm.hold(5);
//
//		gm.click(supplier.suppliersMenuTitle, "Suppliers Menu Title");
//		gm.hold(5);

		gm.EndTest();
	}

	@Test
	public void addNewSupplier() {
		String supplierName = "SupplierName" + LocalDateTime.now();

		gm.StartTest("Creating a New Supplier", "");

		gm.click(supplier.addNewSupplier_Button, "addNewSupplier_Button");

		gm.click(supplier.directSupplier_Radio, "directSupplier_Radio");
		gm.click(supplier.hotel_Checkbox, "hotel_Checkbox");
		gm.click(supplier.locatedAt_Dropdown, "locatedAt_Dropdown");
		gm.click(supplier.DropdownValue("Abohar"), "City DropDown");
		gm.setText(supplier.companyName_Input, supplierName, "companyName_Input");

		gm.click(supplier.createNewSupplier_Button, "createNewSupplier_Button");
		gm.waitforElementVisible(supplier.kYcVerification_TabName, 5, "kYcVerification_TabName");
		gm.waitforElementVisible(supplier.saveAndContinue_Button, 5, "saveAndContinue_Button");

		gm.setText(supplier.inExistenceSince_input, "Existance Since Text", "inExistenceSince_input");
		gm.setText(supplier.otherDMCSIworkWith_TextBox, "Other DMCs I work with", "otherDMCSIworkWith_TextBox");
		gm.setText(supplier.comments_Textbox, "Write comments eg. “We’re the biggest provider in Asia”",
				"comments_Textbox");

		/*
		 * Toast message
		 */
		gm.click(supplier.saveAndContinue_Button, "saveAndContinue_Button");
		gm.waitforElementVisible(toastMessage.toastTitle, 10, "Toast Titke");
		gm.verifyElementText(toastMessage.toastTitle, "Saved Successfully!", "toastTitle");
		gm.verifyElementText(toastMessage.toastdesc, "Basics details updated successfully.", "toastdesc");

		/*
		 * KYC Verification
		 */

		gm.click(supplier.kYcVerification_TabName, "kYcVerification_TabName");
		gm.hold(5);
		gm.click(supplier.ownershipAndManagement, "ownershipAndManagement");
		gm.setText(supplier.nameOfTheSupplier, "Name of the supplier", "nameOfTheSupplier");
		gm.setText(supplier.nameOfLegalEntity, "Name of legal entity", "nameOfLegalEntity");
		gm.setText(supplier.legalEntityType, "Legal entity type", "legalEntityType");
		gm.click(supplier.registeredAs, "Registered As");
		gm.click(supplier.DropdownValue("Travel Agency"), "Registered As dropdown");

		gm.click(supplier.msmeYesRadioButton, "msmeYesRadioButton");

		gm.setText(supplier.address, "Address", "address");
		gm.setText(supplier.pincode, "999000", "pincode");

		gm.check(supplier.communicationAddressSameAsAbove, "communicationAddressSameAsAbove");

		gm.click(supplier.saveAndContinue_Button, "saveAndContinue_Button");
		gm.waitforElementVisible(toastMessage.toastTitle, 10, "Toast Titke");
		gm.verifyElementText(toastMessage.toastTitle, "Saved Successfully!", "toastTitle");
		gm.verifyElementText(toastMessage.toastdesc, "Ownership Management updated successfully.", "toastdesc");

		/*
		 * STATUTORY DETAILS
		 */

		gm.waitforElementVisible(supplier.gstApplicableYesRadioButton, 10, "gstApplicableYesRadioButton");

		gm.click(supplier.gstApplicableYesRadioButton, "gstApplicableYesRadioButton");
		gm.setText(supplier.GstinNumber, "18AABCU9603R1ZM", "GstinNumber");
		gm.click(supplier.uploadGSTCertificate, "uploadGSTCertificate");
		gm.uploadFileUsingAutoIT(EnvironmentDetails.uploadFilesRootPath, "GSTcertificate");
		gm.setText(supplier.panNumber_Input, "AABCU9603R", "panNumber_Input");
		gm.setText(supplier.panName_Input, "PAN NAME", "panName_Input");
		gm.click(supplier.uploadPANproof, "uploadPANproof");
		gm.uploadFileUsingAutoIT(EnvironmentDetails.uploadFilesRootPath, "panCopy");

		gm.click(supplier.saveAndContinue_Button, "saveAndContinue_Button");
		gm.waitforElementVisible(toastMessage.toastTitle, 10, "Toast Titke");
		gm.verifyElementText(toastMessage.toastTitle, "Saved Successfully!", "toastTitle");
		gm.verifyElementText(toastMessage.toastdesc, "Ownership Management updated successfully.", "toastdesc");

		/*
		 * Banking details
		 */

		gm.waitforElementVisible(supplier.nameOfTheBank, 10, "nameOfTheBank");

		gm.setText(supplier.nameOfTheBank, "Name of the Bank", "nameOfTheBank");
		gm.setText(supplier.accountHolderName, "Account holder name", "accountHolderName");
		gm.setText(supplier.bankAccountNumber, "12399999988888", "bankAccountNumber");
		gm.setText(supplier.ifscCode, "IFSC9999JNJ", "ifscCode");

		gm.click(supplier.selectCountry, "selectCountry");
		gm.click(supplier.DropdownValue("Afghanistan"), "Country Dropdown value");
		gm.click(supplier.selectState, "selectState");
		gm.click(supplier.DropdownValue("Afg"), "State Dropdown Value");
		gm.click(supplier.selectCity, "selectCity");
		gm.click(supplier.DropdownValue("kabul"), "City Dropdown Value");
		gm.click(supplier.uploadCancelledCheque, "uploadCancelledCheque");
		gm.uploadFileUsingAutoIT(EnvironmentDetails.uploadFilesRootPath, "cancelledCheque");
		gm.click(supplier.uploadRelationShipProof, "uploadRelationShipProof");
		gm.uploadFileUsingAutoIT(EnvironmentDetails.uploadFilesRootPath, "relationshipProof");
		
		gm.click(supplier.saveAndContinue_Button, "saveAndContinue_Button");
		gm.waitforElementVisible(toastMessage.toastTitle, 10, "Toast Titke");
		gm.verifyElementText(toastMessage.toastTitle, "Saved Successfully!", "toastTitle");
		gm.verifyElementText(toastMessage.toastdesc, "Ownership Management updated successfully.", "toastdesc");

		/*
		 * Key Contact
		 */

		
		
		
		gm.EndTest();
	}

}
