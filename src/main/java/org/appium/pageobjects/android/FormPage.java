package org.appium.pageobjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidActions {
    public AndroidDriver driver;
    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    //driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter name here']"))
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter name here']")
    private WebElement nameField;

    //driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement femaleButton;
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    private WebElement maleButton;
    @AndroidFindBy(id= "android:id/text1")
    private WebElement countrySelection;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    public void setNameField(String name){
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender){
        if(gender.contains("female")){
            femaleButton.click();
        }else {
            maleButton.click();
        }
    }

    public void setCountrySelection(String countryName){
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    public ProductCatalogue submitButton(){
        shopButton.click();
        return new ProductCatalogue(driver);

    }


}
