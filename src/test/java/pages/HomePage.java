package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static utils.Base.driver;

@Getter
public class HomePage {
	@FindBy(xpath = "//a[@id='nav-hamburger-menu']")
	private WebElement hamburgerManu;
	
	@FindBy(xpath = "//ul[@class='hmenu hmenu-visible']/li")
	private List<WebElement> sideMenu;
	@FindBy(xpath = "//ul[@class='hmenu hmenu-visible hmenu-translateX']/li")
	private List<WebElement> electronicsCategory;
	public HomePage(){
		PageFactory.initElements(driver,this);
	}
}
