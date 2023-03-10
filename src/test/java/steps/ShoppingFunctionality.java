package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

import static steps.PageInitializer.electronics;
import static steps.PageInitializer.homePage;
import static utils.Base.*;

public class ShoppingFunctionality {
	@When("user clicks on hunburger menu")
	public void user_clicks_on_hunburger_menu() {
		elementBeVisible(homePage.getHamburgerManu());
		click(homePage.getHamburgerManu());
	}
	
	@When("user click on {string} option")
	public void user_click_on_option(String category) {
		implicitWait();
		click(homePage.getSideMenu()
				.stream()
				.filter(s -> scrollIntoView(s).getText().equals(category))
				.findFirst()
				.orElseThrow(NoSuchElementException::new));
	}
	
	@When("user clicks on {string} option")
	public void user_clicks_on_option(String option) {
		implicitWait();
		click(homePage.getElectronicsCategory()
				.stream()
				.filter(s -> scrollIntoView(s).getText().equals(option))
				.findFirst()
				.orElseThrow(NoSuchElementException::new));
	}
	
	@When("user select {string} as Operating system")
	public void user_select_as_operating_system(String option) {
		//here implemented function to retrieve element that contains target text
		WebElement element = electronics.getElectronicsOperatingSystemOptionValue()
				.stream()
				.filter(s -> s.getText().equals(option))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
		
		/*here is methods to find linkedLink of target element that located below in DOM,
		 -2 -> correction to get proper click result*/
		click(scrollIntoView(
				electronics.getElectronicsOperatingSystemOption()
				.get((electronics).getElectronicsOperatingSystemOptionValue()
						.indexOf(element) - 2)));
	}
	
	@When("user set sort price by {string}")
	public void user_set_sort_price_by(String option) {
		elementBeVisible(electronics.getSortByDropDown());
		click(electronics.getTriggerSortByDropDown());
		selectDropdownByText(electronics.getSortByDropDown(), option);
	}
	
	@When("user click on {string} item from a list")
	public void user_click_on_item_from_a_list(String elementNO) {
		click(
				scrollIntoView(
				electronics.getProductsOnPage()
						.get(Integer.parseInt(elementNO) - 1)));
	}
	
	@Then("user must see description of product")
	public void user_must_see_description_of_product() {
		assertTrue(scrollIntoView(
				electronics.getItemDescription())
				.isDisplayed());
	}
}
