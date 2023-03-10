package steps;

import pages.Electronics;
import pages.HomePage;

public class PageInitializer {
	public static HomePage homePage;
	public static Electronics electronics;
	
	public static void initializePages(){
		homePage = new HomePage();
		electronics = new Electronics();
	}

}
