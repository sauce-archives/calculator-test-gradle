package com.testobject.calculatortest.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testobject.appium.common.TestObjectCapabilities.TESTOBJECT_API_KEY;
import static org.testobject.appium.common.TestObjectCapabilities.TESTOBJECT_TEST_REPORT_ID;

public abstract class AppiumDriverBuilder<SELF, DRIVER extends AppiumDriver> {

	protected String apiKey;
	protected String testReportId;

	public static AndroidDriverBuilder forAndroid() {
		return new AndroidDriverBuilder();
	}

	public static class AndroidDriverBuilder extends AppiumDriverBuilder<AndroidDriverBuilder, AndroidDriver> {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		public AndroidDriver build() {

			capabilities.setCapability(TESTOBJECT_API_KEY, apiKey);
			capabilities.setCapability(TESTOBJECT_TEST_REPORT_ID, testReportId);

			capabilities.setCapability("deviceName", "testDevice");

			return new AndroidDriver(endpoint, capabilities);

		}

	}

	protected URL endpoint;

	public SELF withEndpoint(URL endpoint) {
		this.endpoint = endpoint;

		return (SELF) this;
	}

	public SELF withApiKey(String apiKey) {
		this.apiKey = apiKey;

		return (SELF) this;
	}

	public SELF withTestReportId(String testReportId) {
		this.testReportId = testReportId;

		return (SELF) this;
	}

	public abstract DRIVER build();

}
