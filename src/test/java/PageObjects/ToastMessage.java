package PageObjects;

import org.openqa.selenium.By;

public class ToastMessage {

	public By toastTitle = By.xpath("//p[@class='toster-title']");

	public By toastdesc = By.xpath("//p[@class='toster-desc']");

	public By toastClose = By.xpath("//span[@aria-label='close']");
}
