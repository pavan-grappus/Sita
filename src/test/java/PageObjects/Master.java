package PageObjects;

import org.openqa.selenium.By;

public class Master {

	public By leftpanelResources = By.xpath("//div[@data-testid='resource-box']");

	public By entities_countries = By.xpath("//div[@data-testid='resource-box']//span[text()='countries']");

	public By showingCount = By.xpath("//p[contains(@class,'count-text')]");
	public By AddCountries = By.xpath("//button//span[text()='Add countries']");

	public By countryName = By.xpath("//input[@name='name']");

	public By countryCode = By.xpath("//input[@name='code']");

	public By AddCountryButton = By.xpath("//button//span[text()='Add Country']");
	public By EditCountryButton = By.xpath("//button//span[text()='Edit Country']");

	public By searchForCountries_SearchBar = By.xpath("//input[@placeholder='Search for countries']");

	public By countryNameList(Object index) {
		return By.xpath("(//div[@class='flex flex-col'])[" + index + "]/div[1]");
	}

	public By countryCodeList(Object index) {
		return By.xpath("(//div[@class='flex flex-col'])[" + index + "]/div[2]");
	}

	public By editButton(Object name) {
		return By.xpath("//div[@class='ant-card-body']//div[text()='" + name
				+ "']/parent::div[@class='flex flex-col']/following-sibling::div/img");
	}
}
