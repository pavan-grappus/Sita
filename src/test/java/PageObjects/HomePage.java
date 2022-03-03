package PageObjects;

import org.openqa.selenium.By;

public class HomePage {

	public By resourceHeader = headerSection("Resources");
	public By mastersHeader = headerSection("Masters");

	public By headerSection(String headerName) {
		return By.xpath("//span[text()='" + headerName + "']/parent::div");
	}
}
