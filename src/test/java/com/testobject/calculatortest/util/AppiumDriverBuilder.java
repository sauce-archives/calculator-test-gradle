package com.testobject.calculatortest.util;

import com.google.common.base.Optional;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testobject.appium.common.TestObjectCapabilities.*;

public abstract class AppiumDriverBuilder<SELF, DRIVER extends AppiumDriver> {

	public static AndroidDriverBuilder forAndroid() {
		return new AndroidDriverBuilder();
	}

	public static class AndroidDriverBuilder extends AppiumDriverBuilder<AndroidDriverBuilder, AndroidDriver> {

		public AndroidDriver build() {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", "AndroidTestDevice");

			if (testObjectConfig.isPresent()) {

				capabilities.setCapability(TESTOBJECT_API_KEY, testObjectConfig.get().apiKey);
				capabilities.setCapability(TESTOBJECT_APP_ID, testObjectConfig.get().appId);
				capabilities.setCapability(TESTOBJECT_DEVICE, testObjectConfig.get().deviceId);

				if (suiteName.isPresent()) {
					capabilities.setCapability(TESTOBJECT_SUITE_NAME, suiteName.get());
				}
				if (testName.isPresent()) {
					capabilities.setCapability(TESTOBJECT_TEST_NAME, testName.get());
				}

			} else {
				endpoint = "http://127.0.0.1:4723/wd/hub";
			}

			return new AndroidDriver(toURL(endpoint), capabilities);

		}

	}

	protected String endpoint;
	protected Optional<String> testName = Optional.absent();
	protected Optional<String> suiteName = Optional.absent();
	protected Optional<TestObjectConfig> testObjectConfig = Optional.absent();

	private static class TestObjectConfig {

		private final String apiKey;
		private final int appId;
		private final String deviceId;

		public TestObjectConfig(String apiKey, int appId, String deviceId) {
			this.apiKey = apiKey;
			this.appId = appId;
			this.deviceId = deviceId;
		}
	}

	public SELF againstTestobject(String apiKey, int appId, String deviceId) {
		this.testObjectConfig = Optional.of(new TestObjectConfig(apiKey, appId, deviceId));
		this.endpoint = "https://app.testobject.com:443/api/appium/wd/hub";

		return (SELF) this;
	}

	public SELF withTestName(String testName){
		this.testName = Optional.of(testName);

		return (SELF) this;
	}

	public SELF withSuiteName(String suiteName){
		this.suiteName = Optional.of(suiteName);

		return (SELF) this;
	}

	public abstract DRIVER build();

	private static URL toURL(String url) throws IllegalArgumentException {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("The given url is not valid: " + url, e);
		}
	}

}
