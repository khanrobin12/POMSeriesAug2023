package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();

		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			System.out.println("----------Running Chrome in headless-----------");
			co.addArguments("--headless");
		} else if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("----------Running Chrome in incognito mode-----------");
			co.addArguments("--incognito");
		}
		return co;
	}

	// we can write like that above method
	public FirefoxOptions getFirefoxOptions() {
		System.out.println("----------Running Firefox in headless-----------");
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			fo.addArguments("--incognito");
		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			eo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			eo.addArguments("--incognito");
		return eo;
	}

}
