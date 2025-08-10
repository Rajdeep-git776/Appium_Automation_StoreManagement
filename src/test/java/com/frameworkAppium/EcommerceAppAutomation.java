package com.frameworkAppium;
import com.frameworkAppium.testUtils.BaseTest;
import org.appium.pageobjects.android.CartPage;
import org.appium.pageobjects.android.FormPage;

import org.appium.pageobjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class EcommerceAppAutomation extends BaseTest {

//    @BeforeMethod
//    public void preSetup()  {
//        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
//
//        ((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
//
//    }

    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void fillForm(HashMap<String,String> input) throws InterruptedException {

        FormPage form = new FormPage(driver);
        Thread.sleep(2000);
        form.setNameField(input.get("name"));
        form.setGender(input.get("gender"));
        form.setCountrySelection(input.get("country"));
        ProductCatalogue productCatalogue= form.submitButton();
        productCatalogue.addProductToCartByIndex(0);
        productCatalogue.addProductToCartByIndex(0);
        CartPage cartPage = productCatalogue.goToCartPage();
        Double totalSum =cartPage.getProductSum();
        Double displayFormaattedSum=cartPage.getTotalAmountDisplayed();
        Assert.assertEquals(totalSum,displayFormaattedSum);
        cartPage.acceptTermsAndConditions();
        cartPage.clickCheckBox();
        cartPage.submitOrder();



    }
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir")+"/src/test/java/com/frameworkAppium/testData/eCommerce.json");
        return new Object [][]{{data.get(0)}};
    }

   /* @Test
    public void validateErrorToastMessage() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage=driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your nam");
    }
    @Test
    public void addingItemToCart() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Rajdeep");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
        int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
        for(int i =0; i < productCount; i++){
           String productName= driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
            if(productName.equalsIgnoreCase("Jordan 6 Rings")){
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        String cartProduct=driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(cartProduct, "Jordan 6 Rings");



    }
    @Test
    public void validateTotalAmountInCart() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Rajdeep");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        List<WebElement> productPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        int countOfProduct = productPrices.size();
        double sum=0;
        for(int i=0;i<countOfProduct;i++){
            String amount = productPrices.get(i).getText();
            double price =getFormattedValue(amount);
            sum+=price;
        }
        String cartSum=driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double formattedCarAmount = getFormattedValue(cartSum);
        Assert.assertEquals(formattedCarAmount, sum);
        WebElement termsOfCondition=driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
        LongPress(termsOfCondition);
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='CLOSE']")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();


        }
        @Test
        public void handlingHybridApp() throws InterruptedException {
            Thread.sleep(4000);
            driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Rajdeep");
            driver.hideKeyboard();
            driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
            driver.findElement(AppiumBy.id("android:id/text1")).click();
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
            driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
            driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
            Thread.sleep(2000);
            driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
            Thread.sleep(2000);
            driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
            driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
            List<WebElement> productPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
            int countOfProduct = productPrices.size();
            double sum=0;
            for(int i=0;i<countOfProduct;i++){
                String amount = productPrices.get(i).getText();
                double price =getFormattedValue(amount);
                sum+=price;
            }
            String cartSum=driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
            double formattedCarAmount = getFormattedValue(cartSum);
            Assert.assertEquals(formattedCarAmount, sum);
            WebElement termsOfCondition=driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
            LongPress(termsOfCondition);
            driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='CLOSE']")).click();
            driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
            driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
            Thread.sleep(6000);
           Set<String> contextNames =driver.getContextHandles();
           for(String contextName : contextNames){
               System.out.println(contextName);
           }
           driver.context("WEBVIEW_com.androidsample.generalstore");
           driver.findElement(By.name("q")).sendKeys("Rahul shetty academy");
           driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
           driver.pressKey(new KeyEvent(AndroidKey.BACK));
           driver.context("NATIVE_APP");

        }

     */

    }

