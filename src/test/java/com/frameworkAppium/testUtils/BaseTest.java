package com.frameworkAppium.testUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.appium.utils.AppiumUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest extends AppiumUtils {
    public AndroidDriver driver;
    public AppiumDriverLocalService appiumService;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {
        // getting global values from properties file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/data.properties");
        prop.load(fis);
        //-----------------------
        appiumService= AppiumDriverLocalService.buildService(new AppiumServiceBuilder());
        appiumService.start();
        System.out.println("Appium Started");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "14");
        capabilities.setCapability("appium:automationName", "UIAutomator2");
        //capabilities.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/ApiDemos-debug.apk");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/General-Store.apk");
        capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir") + "/src/test/resources/chromedriver");

        // instead of using new URI("http://127.0.0.1:4723").toURL() for connecting to server we can use appiumService.getUrl()
         driver = new AndroidDriver(appiumService.getUrl(),capabilities);


    }
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        appiumService.stop();
        System.out.println("Appium Stopped");
    }




}
