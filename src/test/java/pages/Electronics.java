package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static utils.Base.driver;
@Getter

public class Electronics {
	@FindBy(xpath = "//span[@class='a-list-item']/a")
	private List<WebElement> electronicsOperatingSystemOption;
	@FindBy(xpath = "//span[@class='a-list-item']/a/span")
	private List<WebElement> electronicsOperatingSystemOptionValue;
	@FindBy(xpath = "//*[@class='a-size-base-plus a-color-base a-text-normal']")
	private List<WebElement> productsOnPage;
	@FindBy(xpath = "//select[@id='s-result-sort-select']")
	private WebElement sortByDropDown;
	@FindBy(xpath = "//span[@class='a-button-text a-declarative']")
	private WebElement triggerSortByDropDown;
	@FindBy(xpath = "//div[@id='feature-bullets']")
	private WebElement itemDescription;
	public Electronics(){
		PageFactory.initElements(driver,this);
	}
}
