package automation.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ManagerHomePage extends BasePage{

    @FindBy(xpath = "//*[contains(text(), 'Manger Id')]")
    @CacheLookup
    WebElement userIdDisplay;

    public ManagerHomePage(WebDriver webDriver, PagesFactory pagesFactory){ super(webDriver, pagesFactory);}

    public String getDisplayedUserID() {
        String userID = userIdDisplay.getText();
        userID = userID.substring(userID.indexOf(":")+1).replaceAll("\\s+","");
        return userID;
    }
}
