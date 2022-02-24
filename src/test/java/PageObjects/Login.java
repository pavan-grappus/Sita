package PageObjects;

import org.openqa.selenium.By;

public class Login {

	public By loginLogo = By.xpath("//img[@class='login-logo']");

	public By loginHeader = By.xpath("//*[starts-with(@class,'login-heading')]");

	public By loginDesc = By.xpath("//*[starts-with(@class,'login-desc')]");

	public By email_input_label = By.xpath("//input[@id='username']//following::label[1]");

	public By email_input = By.xpath("//input[@id='username']");

	public By password_input_label = By.xpath("//input[@id='password']//following::label");

	public By password_input = By.xpath("//input[@id='password']");

	public By password_input_count = By.xpath("//*[contains(@class,'input-show-count')]");

	public By forgotPassword = By.xpath("//*[contains(@class,'ant-typography-link')]");

	public By loginButton = By.xpath("//button[@type='submit']");

	public By SITAemployee = By.xpath("//*[@class='adfs-btn']");

	public By loginViaAdfs = By.xpath("//*[@class='adfs-btn']/a");

	public By imageContainer(Object index) {
		return By.xpath("//div[@class='login-image-container']/div[" + index + "]");
	}

	public By emailAlertBox = By
			.xpath("//input[@id='username']//ancestor::div[starts-with(@class,'ant-col')]//div[@role='alert']");

	public By passwordAlertBox = By
			.xpath("//input[@id='password']//ancestor::div[starts-with(@class,'ant-col')]//div[@role='alert']");

}
