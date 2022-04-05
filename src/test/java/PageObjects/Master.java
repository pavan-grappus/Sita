package PageObjects;

import org.openqa.selenium.By;

public class Master {

	public By leftpanelResources = By.xpath("//div[@data-testid='resource-box']");

	public By showingCount = By.xpath("//p[contains(@class,'count-text')]");

	public By nameField = By.xpath("//input[@name='name']");

	public By codeField = By.xpath("//input[@name='code']");

	public By searchBar_input = By.xpath("//input[contains(@class,'searchBar')]");

	/*
	 * Country
	 */
	public By entities_countries = By.xpath("//div[@data-testid='resource-box']//span[text()='countries']");

	public By AddCountries = By.xpath("//button//span[text()='Add countries']");

	public By AddCountryButton = By.xpath("//button//span[text()='Add Country']");
	public By EditCountryButton = By.xpath("//button//span[text()='Edit Country']");

	public By countryNameList(Object index) {
		return By.xpath("(//div[@class='flex flex-col'])[" + index + "]/div[1]");
	}

	public By countryCodeList(Object index) {
		return By.xpath("(//div[@class='flex flex-col'])[" + index + "]/div[2]");
	}

	public By countryEditButton(Object name) {
		return By.xpath("//div[@class='ant-card-body']//div[text()='" + name
				+ "']/parent::div[@class='flex flex-col']/following-sibling::div/img");
	}

	/*
	 * Trains
	 */
	public By entities_Trains = By.xpath("//div[@data-testid='resource-box']//span[text()='trains']");

	public By AddTrains = By.xpath("//button//span[text()='Add Trains']");

	public By AddTrainsButton = By.xpath("//button//span[text()='Add Train']");

	public By EditTrainButton = By.xpath("//button//span[text()='Edit Train']");

	public By trainNameList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[1]");
	}

	public By trainCodeList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[2]");
	}

	public By trainEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[1]");
	}

	/*
	 * Airlines
	 */

	public By entities_Airlines = By.xpath("//div[@data-testid='resource-box']//span[text()='airlines']");

	public By AddAirlines = By.xpath("//button//span[text()='Add airlines']");

	public By AddAirlinesButton = By.xpath("//button//span[text()='Add Airline']");

	public By EditAirlinesButton = By.xpath("//button//span[text()='Edit Airline']");

	public By AirlinesNameList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[1]");
	}

	public By AirlinesCodeList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[2]");
	}

	public By AirlinesEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[1]");
	}

	/*
	 * Hotel Group
	 */
	public By entities_HotelGroup = By.xpath("//div[@data-testid='resource-box']//span[text()='hotel group']");

	public By AddHotelGroup = By.xpath("//button//span[text()='Add hotel group']");

	public By AddHotelGroupButton = By.xpath("//button//span[text()='Add Hotel Group']");

	public By EditHotelGroupButton = By.xpath("//button//span[text()='Edit Hotel Group']");

	public By HotelGroupNameList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[1]");
	}

	public By HotelGroupEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[1]");
	}

	/*
	 * DMC Group
	 */
	public By entities_DMC = By.xpath("//div[@data-testid='resource-box']//span[text()='dmc']");

	public By AddDMC = By.xpath("//button//span[text()='Add DMC']");

	public By AddDMCButton = By.xpath("//button[@type='submit']//span[text()='Add DMC']");

	public By EditDMCButton = By.xpath("//button[@type='submit']//span[text()='Edit DMC']");

	public By DMCNameList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[1]");
	}

	public By DMCEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[1]");
	}

	/*
	 * Themes Group
	 */
	public By entities_Themes = By.xpath("//div[@data-testid='resource-box']//span[text()='themes']");

	public By AddThemes = By.xpath("//button//span[text()='Add Themes']");

	public By AddThemesButton = By.xpath("//button[@type='submit']//span[text()='Add Theme']");

	public By EditThemesButton = By.xpath("//button[@type='submit']//span[text()='Edit Theme']");

	public By ThemesNameList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[1]");
	}

	public By ThemesEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[1]");
	}

	/*
	 * Difficulty Group
	 */
	public By entities_Difficulty = By.xpath("//div[@data-testid='resource-box']//span[text()='difficulty']");

	public By AddDifficulty = By.xpath("//button//span[text()='Add Difficulty']");

	public By AddDifficultyButton = By.xpath("//button[@type='submit']//span[text()='Add Difficulty']");

	public By EditDifficultyButton = By.xpath("//button[@type='submit']//span[text()='Edit Difficulty']");

	public By DifficultyNameList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[1]");
	}

	public By DifficultyEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[1]");
	}

	/*
	 * Vehicle Categories
	 */
	public By entities_vehicleCategories = By
			.xpath("//div[@data-testid='resource-box']//span[text()='vehicle categories']");

	public By AddvehicleCategories = By.xpath("//button//span[text()='Add vehicle categories']");

	public By AddvehicleCategoriesButton = By.xpath("//button[@type='submit']//span[text()='Add Vehicle Category']");

	public By EditvehicleCategoriesButton = By.xpath("//button[@type='submit']//span[text()='Edit Vehicle Category']");

	public By vehicleCategoriesNameList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[1]");
	}

	public By vehicleCategoriesEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[1]");
	}

	/*
	 * Activity Units
	 */
	public By entities_ActivityUnits = By.xpath("//div[@data-testid='resource-box']//span[text()='activity units']");

	public By AddActivityUnits = By.xpath("//button//span[text()='Add activity units']");

	public By AddActivityUnitsButton = By.xpath("//button[@type='submit']//span[text()='Add Active Unit']");

	public By EditActivityUnitsButton = By.xpath("//button[@type='submit']//span[text()='Edit Active Unit']");

	public By ActivityUnitsNameList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[1]");
	}

	public By ActivityUnitsEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[1]");
	}

	/*
	 * Monument Extra Charges
	 */
	public By entities_MonumentsExtraCharges = By
			.xpath("//div[@data-testid='resource-box']//span[text()='monuments extra charges']");

	public By AddMonumentsExtraCharges = By.xpath("//button//span[text()='Add monument extra charges']");

	public By AddMonumentsExtraChargesButton = By.xpath("//button[@type='submit']//span[text()='Add Extra Charge']");

	public By EditMonumentsExtraChargesButton = By.xpath("//button[@type='submit']//span[text()='Edit Extra Charge']");

	public By MonumentsExtraChargesNameList(Object index) {
		return By.xpath("(//div[contains(@class,'body')]//div[contains(@style,'columns')])[" + index + "]/div[1]");
	}

	public By MonumentsExtraChargesEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[1]");
	}
	
	/*
	 * Nationalities
	 */
	public By entities_nationalities = By.xpath("//div[@data-testid='resource-box']//span[text()='nationalities']");

	public By AddNationality = By.xpath("//button//span[text()='Add nationalities']");

	public By AddNationalityButton = By.xpath("//button//span[text()='Add Nationality']");
	public By EditNationalityButton = By.xpath("//button//span[text()='Edit Nationality']");

	public By nationalityNameList(Object index) {
		return By.xpath("(//div[@class='flex flex-col'])[" + index + "]/div[1]");
	}

	public By nationalityCodeList(Object index) {
		return By.xpath("(//div[@class='flex flex-col'])[" + index + "]/div[2]");
	}

	public By nationalityEditButton(Object name) {
		return By.xpath("//div[@class='ant-card-body']//div[text()='" + name
				+ "']/parent::div[@class='flex flex-col']/following-sibling::div/img");
	}

}
