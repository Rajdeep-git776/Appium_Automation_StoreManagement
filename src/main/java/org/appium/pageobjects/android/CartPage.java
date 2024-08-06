package org.appium.pageobjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class CartPage extends AndroidActions {
    public AndroidDriver driver;
    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List <WebElement> productList;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement terms;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CLOSE']")
    private WebElement closeButton;
    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkbox;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceed;

    public double getProductSum(){
        int count =productList.size();
        double totalSum=0;
        for(int i=0; i<count;i++){
            String amount =productList.get(i).getText();
            Double price=getFormattedValue(amount);
            totalSum+=price;

        }
        return totalSum;
    }

    public Double getTotalAmountDisplayed(){
        return getFormattedValue(totalAmount.getText());
    }

    public void acceptTermsAndConditions(){
        LongPress(terms);
        closeButton.click();
    }

    public void clickCheckBox(){
        checkbox.click();
    }

    public void submitOrder(){
        proceed.click();
    }



    }



