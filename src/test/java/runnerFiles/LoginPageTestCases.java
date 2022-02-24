package runnerFiles;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class LoginPageTestCases extends BeforeRun {

	@Test
	public void validateTheLoginHeader() {
		gm.StartTest("Validate Login Header", "");

		gm.verifyElementText(login.loginHeader, "Welcome to SITA", "loginHeader");
		gm.verifyElementCSSValue(login.loginHeader, "font-size", "32px", "loginHeader font-size");
		gm.verifyElementCSSValue(login.loginHeader, "font-weight", "700", "loginHeader font-weight");
		gm.verifyElementCSSValue(login.loginHeader, "line-height", "normal", "loginHeader line-height");
		gm.verifyElementCSSValue(login.loginHeader, "letter-spacing", "0.12px", "loginHeader letter-spacing");
		gm.verifyElementCSSValue(login.loginHeader, "color", "rgba(13, 19, 25, 1)", "loginHeader color");

		gm.EndTest();
	}

	@Test
	public void validateTheLoginDesc() {
		gm.StartTest("Validate Login Desc", "");

		gm.verifyElementText(login.loginDesc, "Please login with your email id and password to continue.", "loginDesc");
		gm.verifyElementCSSValue(login.loginDesc, "font-size", "15px", "loginDesc font-size");
		gm.verifyElementCSSValue(login.loginDesc, "font-weight", "400", "loginDesc font-weight");
		gm.verifyElementCSSValue(login.loginDesc, "line-height", "24px", "loginDesc line-height");
		gm.verifyElementCSSValue(login.loginDesc, "letter-spacing", "-0.18px", "loginDesc letter-spacing");
		gm.verifyElementCSSValue(login.loginDesc, "color", "rgba(127, 130, 133, 1)", "loginDesc color");

		gm.EndTest();
	}

	public void defaultEmailFieldCSSValuesWhenLaunched(String fieldValue) {

		gm.verifyElementAttributeValue(login.email_input, "value", fieldValue, "email_input");
		gm.verifyElementNOTVisible(login.emailAlertBox, "emailAlertBox");
		gm.verifyElementText(login.email_input_label, "Email ID", "email_input_label");
		
		gm.verifyElementCSSValue(login.email_input_label, "color", "rgba(135, 149, 162, 1)",
				"email_input_label color");
		gm.verifyElementCSSValue(login.email_input_label, "font-size", "16px",
				"email_input_label font-size");
		gm.verifyElementCSSValue(login.email_input_label, "font-weight", "500",
				"email_input_label font-weight");
		gm.verifyElementCSSValue(login.email_input_label, "opacity", "1", "email_input_label opacity");
		gm.verifyElementCSSValue(login.email_input_label, "position", "absolute", "email_input_label position");
		gm.verifyElementCSSValue(login.email_input_label, "left", "12px", "email_input_label left");
		gm.verifyElementCSSValue(login.email_input_label, "top", "12px", "email_input_label top");

		gm.verifyElementCSSValue(login.email_input, "border-top-color", "rgba(135, 149, 162, 0.4)",
				"email_input border-top-color");
		gm.verifyElementCSSValue(login.email_input, "border-right-color", "rgba(135, 149, 162, 0.4)",
				"email_input border-right-color");
		gm.verifyElementCSSValue(login.email_input, "border-bottom-color", "rgba(135, 149, 162, 0.4)",
				"email_input border-bottom-color");
		gm.verifyElementCSSValue(login.email_input, "border-left-color", "rgba(135, 149, 162, 0.4)",
				"email_input border-left-color");

	}

	public void defaultEmailFieldCSSValueWhenClicked(String fieldValue) {

		gm.verifyElementAttributeValue(login.email_input, "value", fieldValue, "email_input");
		gm.verifyElementNOTVisible(login.emailAlertBox, "emailAlertBox");
		gm.verifyElementText(login.email_input_label, "Email ID", "email_input_label");
		gm.verifyElementCSSValue(login.email_input_label, "color", "rgba(135, 149, 162, 1)",
				"email_input_label color");
		gm.verifyElementCSSValue(login.email_input_label, "font-size", "11px",
				"email_input_label font-size");
		gm.verifyElementCSSValue(login.email_input_label, "font-weight", "500",
				"email_input_label font-weight");
		gm.verifyElementCSSValue(login.email_input_label, "opacity", "1", "email_input_label opacity");
		gm.verifyElementCSSValue(login.email_input_label, "position", "absolute", "email_input_label position");
		gm.verifyElementCSSValue(login.email_input_label, "left", "12px", "email_input_label left");
		gm.verifyElementCSSValue(login.email_input_label, "top", "6px", "email_input_label top");

		gm.verifyElementCSSValue(login.email_input, "border-top-color", "rgba(135, 149, 162, 0.4)",
				"email_input border-top-color");
		gm.verifyElementCSSValue(login.email_input, "border-right-color", "rgba(135, 149, 162, 0.4)",
				"email_input border-right-color");
		gm.verifyElementCSSValue(login.email_input, "border-bottom-color", "rgba(135, 149, 162, 0.4)",
				"email_input border-bottom-color");
		gm.verifyElementCSSValue(login.email_input, "border-left-color", "rgba(135, 149, 162, 0.4)",
				"email_input border-left-color");
	}

	@Test
	public void validateTheEmailIdField() {

		gm.StartTest("Default Email Id field when launched", "");
		gm.refresh("LoginPage", 10);
		defaultEmailFieldCSSValuesWhenLaunched("");
		gm.EndTest();

		gm.StartTest("After Mouse click - Email Id field", "");
		gm.click(login.email_input, "email_input");
		defaultEmailFieldCSSValueWhenClicked("");
		gm.EndTest();

		gm.StartTest("Email Id field when User Clicks and unclicks the Field", "");
		gm.refresh("loginPage", 10);
		defaultEmailFieldCSSValuesWhenLaunched("");

		gm.click(login.email_input, "email_input");
		defaultEmailFieldCSSValueWhenClicked("");

		gm.click(login.loginHeader, "loginHeader");
		defaultEmailFieldCSSValuesWhenLaunched("");
		gm.EndTest();

		gm.StartTest("User Inputs Text to Email Id field and Validates", "");
		gm.refresh("loginPage", 10);
		gm.setText(login.email_input, "abc", "email_input");
		gm.verifyElementAttributeValue(login.email_input, "value", "abc", "email_input");
		gm.EndTest();

		gm.StartTest("User sets Text and clear the Text using Select All and backspace button", "");
		gm.refresh("loginPage", 10);
		gm.setText(login.email_input, "abc", "email_input");
		gm.verifyElementAttributeValue(login.email_input, "value", "abc", "email_input");
		gm.clearbyBackspace(login.email_input, "email_input");
		gm.verifyElementAttributeValue(login.email_input, "value", "", "email_input");
		gm.EndTest();

		gm.StartTest("User sets Text and Press backspace once", "");
		gm.refresh("loginPage", 10);
		gm.setText(login.email_input, "abc", "email_input");
		gm.verifyElementAttributeValue(login.email_input, "value", "abc", "email_input");
		gm.presskeys(login.email_input, Keys.BACK_SPACE, "Backspace", "email_input");
		gm.verifyElementAttributeValue(login.email_input, "value", "ab", "email_input");
		gm.EndTest();

		gm.StartTest("User presses backspace without entering any Text", "");
		gm.refresh("loginPage", 10);
		gm.click(login.email_input, "email_input");
		gm.presskeys(login.email_input, Keys.BACK_SPACE, "Backspace", "email_input");
		gm.verifyElementAttributeValue(login.email_input, "value", "", "email_input");
		gm.EndTest();

		gm.StartTest("User validates the Please input your Email ID! message", "");
		gm.refresh("loginPage", 10);
		gm.setText(login.email_input, "abc", "email_input");
		gm.verifyElementAttributeValue(login.email_input, "value", "abc", "email_input");
		gm.clearbyBackspace(login.email_input, "email_input");
		gm.verifyElementAttributeValue(login.email_input, "value", "", "email_input");
		gm.waitforElementVisible(login.emailAlertBox, 5, "emailAlertBox");
		gm.verifyElementVisible(login.emailAlertBox, "emailAlertBox");
		gm.verifyElementText(login.emailAlertBox, "Please input your Email ID!", "emailAlertBox");
		gm.verifyElementCSSValue(login.emailAlertBox, "color", "rgba(255, 77, 79, 1)", "emailAlertBox");

		gm.verifyElementCSSValue(login.email_input, "border-top-color", "rgba(255, 0, 0, 1)",
				"email_input border-top-color");
		gm.verifyElementCSSValue(login.email_input, "border-right-color", "rgba(255, 0, 0, 1)",
				"email_input border-right-color");
		gm.verifyElementCSSValue(login.email_input, "border-bottom-color", "rgba(255, 0, 0, 1)",
				"email_input border-bottom-color");
		gm.verifyElementCSSValue(login.email_input, "border-left-color", "rgba(255, 0, 0, 1)",
				"email_input border-left-color");
		gm.EndTest();

		gm.StartTest("User validates the input your Email ID! message is not visible when Text are typed again", "");
		gm.refresh("loginPage", 10);
		gm.setText(login.email_input, "abc", "email_input");
		gm.verifyElementAttributeValue(login.email_input, "value", "abc", "email_input");
		gm.clearbyBackspace(login.email_input, "email_input");
		gm.verifyElementAttributeValue(login.email_input, "value", "", "email_input");
		gm.waitforElementVisible(login.emailAlertBox, 5, "emailAlertBox");
		gm.verifyElementVisible(login.emailAlertBox, "emailAlertBox");
		gm.verifyElementText(login.emailAlertBox, "Please input your Email ID!", "emailAlertBox");
		gm.verifyElementCSSValue(login.emailAlertBox, "color", "rgba(255, 77, 79, 1)", "emailAlertBox");

		gm.verifyElementCSSValue(login.email_input, "border-top-color", "rgba(255, 0, 0, 1)",
				"email_input border-top-color");
		gm.verifyElementCSSValue(login.email_input, "border-right-color", "rgba(255, 0, 0, 1)",
				"email_input border-right-color");
		gm.verifyElementCSSValue(login.email_input, "border-bottom-color", "rgba(255, 0, 0, 1)",
				"email_input border-bottom-color");
		gm.verifyElementCSSValue(login.email_input, "border-left-color", "rgba(255, 0, 0, 1)",
				"email_input border-left-color");

		gm.setText(login.email_input, "aaa", "email_input");
		defaultEmailFieldCSSValueWhenClicked("aaa");
		gm.EndTest();

	}

	public void defaultPasswordFieldCSSValuesWhenLaunched(String fieldValue) {

		gm.verifyElementAttributeValue(login.password_input, "value", fieldValue, "password_input");
		gm.verifyElementNOTVisible(login.passwordAlertBox, "passwordAlertBox");
		gm.verifyElementText(login.password_input_label, "Password", "password_input_label");

		gm.verifyElementCSSValue(login.password_input_label, "color", "rgba(135, 149, 162, 1)",
				"password_input_label color");
		gm.verifyElementCSSValue(login.password_input_label, "font-size", "16px",
				"password_input_label font-size");
		gm.verifyElementCSSValue(login.password_input_label, "font-weight", "500",
				"password_input_label font-weight");
		gm.verifyElementCSSValue(login.password_input_label, "opacity", "1", "password_input_label opacity");
		gm.verifyElementCSSValue(login.password_input_label, "position", "absolute",
				"password_input_label position");
		gm.verifyElementCSSValue(login.password_input_label, "left", "12px", "password_input_label left");
		gm.verifyElementCSSValue(login.password_input_label, "top", "12px", "password_input_label top");

		gm.verifyElementCSSValue(login.password_input, "border-top-color", "rgba(135, 149, 162, 0.4)",
				"password_input border-top-color");
		gm.verifyElementCSSValue(login.password_input, "border-right-color", "rgba(135, 149, 162, 0.4)",
				"password_input border-right-color");
		gm.verifyElementCSSValue(login.password_input, "border-bottom-color", "rgba(135, 149, 162, 0.4)",
				"password_input border-bottom-color");
		gm.verifyElementCSSValue(login.password_input, "border-left-color", "rgba(135, 149, 162, 0.4)",
				"password_input border-left-color");
	}

	public void defaultPasswordFieldCSSValueWhenClicked(String fieldValue) {

		gm.verifyElementAttributeValue(login.password_input, "value", fieldValue, "password_input");
		gm.verifyElementNOTVisible(login.passwordAlertBox, "passwordAlertBox");
		gm.verifyElementText(login.password_input_label, "Password", "password_input_label");
		gm.verifyElementCSSValue(login.password_input_label, "color", "rgba(135, 149, 162, 1)",
				"password_input_label color");
		gm.verifyElementCSSValue(login.password_input_label, "font-size", "11px",
				"password_input_label font-size");
		gm.verifyElementCSSValue(login.password_input_label, "font-weight", "500",
				"password_input_label font-weight");
		gm.verifyElementCSSValue(login.password_input_label, "opacity", "1", "password_input_label opacity");
		gm.verifyElementCSSValue(login.password_input_label, "position", "absolute",
				"password_input_label position");
		gm.verifyElementCSSValue(login.password_input_label, "left", "12px", "password_input_label left");
		gm.verifyElementCSSValue(login.password_input_label, "top", "6px", "password_input_label top");

		gm.verifyElementCSSValue(login.password_input, "border-top-color", "rgba(135, 149, 162, 0.4)",
				"password_input border-top-color");
		gm.verifyElementCSSValue(login.password_input, "border-right-color", "rgba(135, 149, 162, 0.4)",
				"password_input border-right-color");
		gm.verifyElementCSSValue(login.password_input, "border-bottom-color", "rgba(135, 149, 162, 0.4)",
				"password_input border-bottom-color");
		gm.verifyElementCSSValue(login.password_input, "border-left-color", "rgba(135, 149, 162, 0.4)",
				"password_input border-left-color");
	}

	@Test
	public void validateThePasswordField() {

		gm.StartTest("Default Password field when launched", "");
		gm.refresh("LoginPage", 10);
		defaultPasswordFieldCSSValuesWhenLaunched("");
		gm.EndTest();

		gm.StartTest("After Mouse click - Password field", "");
		gm.click(login.password_input, "password_input");
		defaultPasswordFieldCSSValueWhenClicked("");
		gm.EndTest();

		gm.StartTest("Password field when User Clicks and unclicks the Field", "");
		gm.refresh("loginPage", 10);
		defaultPasswordFieldCSSValuesWhenLaunched("");

		gm.click(login.password_input, "password_input");
		defaultPasswordFieldCSSValueWhenClicked("");

		gm.click(login.loginHeader, "loginHeader");
		defaultPasswordFieldCSSValuesWhenLaunched("");
		gm.EndTest();

	}

}
