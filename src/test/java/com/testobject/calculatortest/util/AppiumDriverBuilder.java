package com.testobject.calculatortest.util;

import com.google.common.base.Optional;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testobject.appium.common.TestObjectCapabilities.*;

public abstract class AppiumDriverBuilder<SELF, DRIVER extends AppiumDriver> {

	public static AndroidDriverBuilder forAndroid() {
		return new AndroidDriverBuilder();
	}

	public static class AndroidDriverBuilder extends AppiumDriverBuilder<AndroidDriverBuilder, AndroidDriver> {

		private Optional<String> bundleId = Optional.absent();
		private Optional<String> udid = Optional.absent();

		public AndroidDriverBuilder app(String bundleId) {

			this.bundleId = Optional.of(bundleId);
			return this;

		}

		public AndroidDriver build() {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", "AndroidTestDevice");
			capabilities.setCapability("platformName", "Android");

			if (bundleId.isPresent()) {
				capabilities.setCapability("bundleId", bundleId.get());
			}

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
			}

			System.out.println(toURL(endpoint));

			return new AndroidDriver(toURL("http://192.168.0.231:8080/api/appium/wd/hub"), capabilities);
		}

	}

	public static IOSDriverBuilder forIOS() {
		return new IOSDriverBuilder();
	}

	public static class IOSDriverBuilder extends AppiumDriverBuilder<IOSDriverBuilder, IOSDriver> {

		private Optional<String> bundleId = Optional.absent();

		public IOSDriverBuilder app(String bundleId) {
			this.bundleId = Optional.of(bundleId);

			return this;
		}

		public IOSDriver build() {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
//            capabilities.setCapability("autoAcceptAlerts", "true");

			if (bundleId.isPresent()) {
				capabilities.setCapability("bundleId", bundleId.get());
			}

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
			}

			this.endpoint = Optional.fromNullable(endpoint)
					.or(Optional.fromNullable(System.getenv("APPIUM_ENDPOINT")).or("http://0.0.0.0:4723/wd/hub"));

			return new IOSDriver(toURL("http://192.168.0.231:8080/api/appium/wd/hub"), capabilities);
		}
	}

	protected String endpoint;
	protected Optional<String> testName = Optional.absent();
	protected Optional<String> suiteName = Optional.absent();
	protected Optional<TestObjectConfig> testObjectConfig = Optional.absent();

	public SELF againstHost(String host, int port) {
		this.endpoint = "http://" + host + ":" + port + "/wd/hub";

		return (SELF) this;
	}

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

	/**
	 * Configure AppiumDriver to talk to the TestObject device cloud.
	 * Set TestObjectCapabilities.TESTOBJECT_API_KEY, TestObjectCapabilities.TESTOBJECT_APP_ID and TESTOBJECT_DEVICE by providing
	 * the following Enviroment Variables (eg. by calling java.util.System.setCa variables:
	 *
	 * TESTOBJECT_API_KEY
	 * TESTOBJECT_APP_ID
	 * TESTOBJECT_DEVICE
	 *
	 * @return this
	 */
	public SELF againstTestobject() {
		String apiKey = System.getenv("TESTOBJECT_API_KEY");
		int appId = Integer.parseInt(System.getenv("TESTOBJECT_APP_ID"));
		String deviceId = System.getenv("TESTOBJECT_DEVICE");

		return againstTestobject(apiKey, appId, deviceId);
	}

	public SELF againstTestobject(String apiKey, int appId, String deviceId) {
		this.testObjectConfig = Optional.of(new TestObjectConfig(apiKey, appId, deviceId));
		this.endpoint = "http://192.168.0.231:8080/api/appium/wd/hub"; //"http://branches.testobject.org:80/api/appium/wd/hub";

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
