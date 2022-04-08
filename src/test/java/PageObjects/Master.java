package PageObjects;

import org.openqa.selenium.By;

public class Master {

	public By leftpanelResources = By.xpath("//div[@data-testid='resource-box']");

	public By showingCount = By.xpath("//p[contains(@class,'count-text')]");

	public By nameField = By.xpath("//input[@name='name']");

	public By codeField = By.xpath("//input[@name='code']");

	public By searchBar_input = By.xpath("//input[contains(@class,'searchBar')]");

	public By selectFromComboOptions_Text(String objectName) {
		return By.xpath("//div[contains(@class,'select-item-option')][text()='" + objectName + "']");
	}

	public By selectFromComboOptions_Index(Object objectName) {
		return By.xpath("//div[contains(@class,'select-item-option')][" + objectName + "]");

	}

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
	 * Cities
	 */
	public By entities_cities = By.xpath("//div[@data-testid='resource-box']//span[text()='cities']");

	public By cityNameList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[@class='flex flex-col'][1]/div[1]");
	}

	public By cityCodeList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[@class='flex flex-col'][1]/div[2]");
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

	/*
	 * Zones
	 */
	public By entities_zones = By.xpath("//div[@data-testid='resource-box']//span[text()='zones']");

	public By Addzones = By.xpath("//button//span[text()='Add zones']");

	public By AddZoneButton = By.xpath("//button//span[text()='Add Zone']");
	public By EditZoneButton = By.xpath("//button//span[text()='Edit Zone']");

	public By CitysearchComboField = By.xpath("//div[@name='city']//input[@type='search'][@role='combobox']");

	public By selectedCityOption = By.xpath("//div[@name='city']//span[contains(@class,'selection-item')]");

	public By zoneNameList(Object index) {
		return By.xpath("(//div[@class='capitalize'])[" + index + "]");
	}

	public By zoneCityList(Object index) {
		return By.xpath("(//div[@class='flex flex-col'])[" + index + "]/div[1]");
	}

	public By zoneCityCodeList(Object index) {
		return By.xpath("(//div[@class='flex flex-col'])[" + index + "]/div[2]");
	}

	public By zoneEditButton(Object name) {
		return By.xpath("//div[@class='ant-card-body']//div[text()='" + name + "']/following-sibling::div/img");
	}

	/*
	 * Special Needs
	 */
	public By entities_SpecialNeeds = By
			.xpath("//div[@data-testid='resource-box']//span[text()='query special needs']");

	public By AddSpecialNeeds = By.xpath("//button//span[text()='Add special needs']");

	public By AddSpecialNeedsButton = By.xpath("//button//span[text()='Add Special Need']");
	public By EditSpecialNeedsButton = By.xpath("//button//span[text()='Edit Special Need']");

	public By HotelFacilitysearchComboField = By
			.xpath("//div[@name='hotelFacilityId']//input[@type='search'][@role='combobox']");

	public By selectedHotelFacilityOption = By
			.xpath("//div[@name='hotelFacilityId']//span[contains(@class,'selection-item')]");

	public By specialNeedsNameList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[@class='capitalize']");
	}

	public By specialNeedsHotelFacilityList(Object index) {
		return By.xpath(
				"(//div[@class='ant-card-body'])[" + index + "]//div[@class='capitalize']/following-sibling::div[1]");
	}

	public By specialNeedsEditButton(Object name) {
		return By.xpath("//div[@class='ant-card-body']//div[text()='" + name + "']/following-sibling::div/img");
	}

	/*
	 * Hotel Facility
	 */
	public By entities_hotelFacility = By.xpath("//div[@data-testid='resource-box']//span[text()='hotel facility']");

	public By AddHotelFacility = By.xpath("//button//span[text()='Add hotel facilities']");

	public By facilityTypesearchComboField = By
			.xpath("//div[@name='type']//span[contains(@class,'select-selection-item')]");

	public By inputTypesearchComboField = By
			.xpath("//div[@name='inputType']//span[contains(@class,'select-selection-item')]");

	public By isRequired_switch = By.xpath("//button[@role='switch']");

	public By AddHotelFacilityButton = By.xpath("//button//span[text()='Add Hotel Facility']");

	public By EditHotelFacilityButton = By.xpath("//button//span[text()='Edit Hotel Facility']");

	public By HotelFacilityEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[@class='cursor-pointer'][1]");
	}

	public By hotelFacilityNameList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[@class='capitalize'][1]");
	}

	public By hotelFacilityTypeList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[@class='capitalize'][2]");
	}

	public By hotelFacilityInputTypeList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[@class='capitalize'][3]");
	}

	public By hotelFacilityMandatoryList_img(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[2]/img");
	}

	/*
	 * Monument Facility
	 */

	public By entities_monumentFacility = By
			.xpath("//div[@data-testid='resource-box']//span[text()='monuments facilities']");

	public By AddMonumentFacility = By.xpath("//button//span[text()='Add monument facilities']");

	public By categoryDropdownComboField = By
			.xpath("//div[@name='category']//span[contains(@class,'select-selection-item')]");

	public By AddMonumentFacilityButton = By.xpath("//button//span[text()='Add Monument Facility']");

	public By EditMonumentFacilityButton = By.xpath("//button//span[text()='Edit Monument Facility']");

	public By MonumentFacilityEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]/div[1][text()='" + name
				+ "']/following::img[@class='cursor-pointer'][1]");
	}

	public By MonumentFacilityNameList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[@class='capitalize'][1]");
	}

	public By MonumentFacilityCategoryList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[3]");
	}

	public By MonumentFacilityMandatoryList_img(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[2]/img");
	}

	/*
	 * Terminals
	 */
	public By entities_terminals = By.xpath("//div[@data-testid='resource-box']//span[text()='terminals']");

	public By AddTerminals = By.xpath("//button//span[text()='Add terminals']");

	public By AddTerminalsButton = By.xpath("//button//span[text()='Add Terminal']");
	public By EditTerminalsButton = By.xpath("//button//span[text()='Edit Terminal']");

	public By TerminalsNameList(Object index) {
		return By.xpath("((//div[@class='ant-card-body'])[" + index + "]//div[@class='flex flex-col']/div)[1]");
	}

	public By TerminalsCodeList(Object index) {
		return By.xpath("((//div[@class='ant-card-body'])[" + index + "]//div[@class='flex flex-col']/div)[2]");
	}

	public By TerminalsCityList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[@class='flex flex-col'][2]/div");
	}

	public By TerminalsEditButton(Object name) {
		return By.xpath("//div[text()='" + name + "']/ancestor::div[@class='ant-card-body']//img");
	}

	/*
	 * Stations
	 */
	public By entities_stations = By.xpath("//div[@data-testid='resource-box']//span[text()='stations']");

	public By AddStations = By.xpath("//button//span[text()='Add stations']");

	public By AddStationsButton = By.xpath("//button//span[text()='Add Stations']");
	public By EditStationsButton = By.xpath("//button//span[text()='Edit Stations']");

	public By StationsNameList(Object index) {
		return By.xpath("((//div[@class='ant-card-body'])[" + index + "]//div[@class='flex flex-col']/div)[1]");
	}

	public By StationsCodeList(Object index) {
		return By.xpath("((//div[@class='ant-card-body'])[" + index + "]//div[@class='flex flex-col']/div)[2]");
	}

	public By StationsCityList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[@class='flex flex-col'][2]/div");
	}

	public By StationsEditButton(Object name) {
		return By.xpath("//div[text()='" + name + "']/ancestor::div[@class='ant-card-body']//img");
	}

	/*
	 * Speciality
	 */

	public By entities_Speciality = By.xpath("//div[@data-testid='resource-box']//span[text()='speciality']");

	public By AddSpeciality = By.xpath("//button//span[text()='Add speciality']");

	public By categorySearchComboField = By.xpath("//div[@name='tourCategoryId']//input");

	public By AddSpecialityButton = By.xpath("//button//span[text()='Add Speciality']");

	public By EditSpecialityButton = By.xpath("//button//span[text()='Edit Speciality']");

	public By SpecialityEditButton(Object name) {
		return By.xpath("//div[contains(@class,'body')]//div[contains(@style,'columns')]//div[1][text()='" + name
				+ "']/following::img[@class='cursor-pointer'][1]");
	}

	public By SpecialityNameList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[@class='capitalize'][1]");
	}

	public By SpecialityCategoryList(Object index) {
		return By.xpath("(//div[@class='ant-card-body'])[" + index + "]//div[3]");
	}

}
