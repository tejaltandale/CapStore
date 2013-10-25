package com.cg.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Status {

	public static Properties getMerchantStatus() {
		Properties property = new Properties();
		String source = "D:/CapStore/Integration/WebContent/WEB-INF/status.properties";
		try {
			InputStream inputStream = new FileInputStream(source);
			property.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return property;

	}

}
