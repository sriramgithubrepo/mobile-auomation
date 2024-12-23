package pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;


public class BaseClass {
	
	 protected static AndroidDriver driver; // Shared across all tests

	    @BeforeTest
	    public void setUp() throws MalformedURLException {
	            try {
	                // Set DesiredCapabilities
	                DesiredCapabilities caps = new DesiredCapabilities();
	                caps.setCapability("appium:platformName", "Android");
	                caps.setCapability("appium:platformVersion", "14");
	                caps.setCapability("appium:automationName", "UiAutomator2");
	                caps.setCapability("appium:udid", "emulator-5554");
	                //caps.setCapability("appium:app", System.getProperty("user.dir") + "/src/test/resources/apps/app.apk");
	                caps.setCapability("appium:appActivity", "com.shopapotheke.activities.splash.AliasSplashActivityDE");
	                caps.setCapability("appium:appPackage", "shop.shop_apotheke.com.shopapotheke.debug");
	                caps.setCapability("appium:newCommandTimeout", 300);
	                caps.setCapability("appium:logLevel", "debug");
	                caps.setCapability("appium:appWaitActivity", "*");
	               
	              

	                // Initialize Appium driver
	                URL url = new URL("http://127.0.0.1:4723");
	                driver = new AndroidDriver(url, caps);

	                // Set global timeouts
	                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));

	                System.out.println("Driver initialized successfully.");
	            } catch (Exception exp) {
	                System.err.println("Failed to initialize driver.");
	                System.err.println("Cause: " + exp.getCause());
	                System.err.println("Message: " + exp.getMessage());
	                exp.printStackTrace();
	                throw new MalformedURLException("Driver initialization failed: " + exp.getMessage());
	            }
	        }

	    @AfterTest
	    public void tearDown() {
	        if (driver != null) {
	            try {
	                driver.quit();
	                System.out.println("Driver quit successfully.");
	            } catch (Exception e) {
	                System.err.println("Error while quitting the driver: " + e.getMessage());
	            }
	        }
	    }
	       
	    public void clickElement(By locator) {
	        driver.findElement(locator).click();
	    }

	    public void sendKeysToElement(By locator, String keys) {
	    	driver.findElement(locator).sendKeys(keys);
	    }

	    public String getElementText(By locator) {
	        return driver.findElement(locator).getText();
	    }
	    public boolean isElementDisplayed(By locator) {
	        return driver.findElement(locator).isDisplayed();
	    }
	    
}