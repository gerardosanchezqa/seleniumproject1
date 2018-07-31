package automation.components;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BasePage extends PagesFactory{

    private final PagesFactory _pagesFactory;
    private final WebDriver _webDriver;

    public BasePage(WebDriver _webDriver, PagesFactory pagesFactory){
        super(_webDriver);
        this._webDriver = getWebDriver();
        this._pagesFactory = pagesFactory;
    }

    public void clearFieldAndSendKeys(WebElement webElement, String text){
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void clickElement(WebElement webElement){
        webElement.click();
    }

    public LoginPage goToWebsite(String desiredWebsite){
        _webDriver.get(desiredWebsite);
        return new LoginPage(getWebDriver(), _pagesFactory);
    }

    public String getCurrentAlertText(){
            try
            {
                _webDriver.switchTo().alert();
                return _webDriver.switchTo().alert().getText();
            }
            catch (NoAlertPresentException Ex)
            {
                return "No alert thrown";
            }
    }

    public void closeCurrentAlert(){
        try
        {
            _webDriver.switchTo().alert().accept();
        }
        catch (NoAlertPresentException Ex)
        {
        }
    }

    public String getPageTitle(){
        return _webDriver.getTitle();
    }




    public static String javaIoTmpDir = System.getProperty("java.io.tmpdir");

    public void captureScreenshot() {
        String timePicture = javaIoTmpDir+"\\scr_"+String.valueOf(System.currentTimeMillis())+".png";
        File scrFile = ((TakesScreenshot) _webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(timePicture));
            System.out.println("Screenshot successfully saved in: "+timePicture);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}