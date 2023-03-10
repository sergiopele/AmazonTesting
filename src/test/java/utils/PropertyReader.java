package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	private static Properties prop;
	public static Properties loadProperty(String filePath){
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(filePath);
			prop.load(fis);
		}catch(RuntimeException e){
			e.printStackTrace();
		}catch (IOException r){
			r.printStackTrace();
		}
		return prop;
	}
	public static String readProperty (String target){
		return prop.get(target).toString();
	}
}
