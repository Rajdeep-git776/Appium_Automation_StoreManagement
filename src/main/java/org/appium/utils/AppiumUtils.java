package org.appium.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils {



    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        // System.getProperty("user.dir")+"src//test//java//com//frameworkAppium//testData//eCommerce.json"
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath));
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String >>data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });

        return data;
    }

    public Double getFormattedValue(String amount) {
        double value = Double.parseDouble(amount.substring(1));
        return value;
    }
        public void waitForElementToAppear(WebElement ele, AndroidDriver driver){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
        }

    public static String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("usr.dir")+"//reports"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destination));
        return destination;
    }

}
