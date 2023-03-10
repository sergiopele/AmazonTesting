package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import static utils.Base.*;

public class Hooks {
	@Before
	public static void beforeTest(){
		openBrowser();
	}
	
	@After
	public static void afterTest(Scenario scenario){
		byte[] pic;
		if(scenario.isFailed()){
			//failed screenshot will be available inside failed folder
			pic = takeScreenshot("failed/" + scenario.getName());
		}else {
			pic = takeScreenshot("passed/" + scenario.getName());
		}
		
		//to attach the screenshot in our report
		scenario.attach(pic, "image/png", scenario.getName());
		quitBrowser();
	}
}
