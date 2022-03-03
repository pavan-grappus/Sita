package PageObjects;

import org.openqa.selenium.By;

public class Products {

	
	public By productsMenuTitle = By.xpath("//div[contains(@class,'submenu-title')]/span[text()='Products']");
	
	public By HotelSubMenuTitle = By.xpath("//span[contains(@class,'menu-title')]//a[text()='Hotel']");
	
	
}
