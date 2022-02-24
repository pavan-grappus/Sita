package PageObjects;

import org.openqa.selenium.By;

public class Master {

	public By mastersHeader = By.xpath("//span[text()='Masters']/parent::div");

	public By leftpanelResources = By.xpath("//div[@data-testid='resource-box']");

	public By entities_countries = By.xpath("//div[@data-testid='resource-box']//span[text()='countries']");

	public By AddCountries = By.xpath("//button//span[text()='Add countries']");

	public By countryName = By.xpath("//input[@name='name']");

	public By countryCode = By.xpath("//input[@name='code']");

	public By AddCountryButton = By.xpath("//button//span[text()='Add Country']");

	public By searchForCountries_SearchBar = By.xpath("//input[@placeholder='Search for countries']");

	public By countryNameList(Object index) {
		return By.xpath("(//div[@class='flex flex-col'])[" + index + "]/div[1]");
	}
	
	public By countryCodeList(Object index) {
		return By.xpath("(//div[@class='flex flex-col'])[" + index + "]/div[2]");
	}
}
