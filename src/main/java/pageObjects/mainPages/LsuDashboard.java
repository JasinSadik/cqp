package pageObjects.mainPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class LsuDashboard extends TopMenu {
    public LsuDashboard(WebDriver driver) {
        super(driver);
    }

    private By newQuotationButton = By.cssSelector("a[class='button newQuotation']");
    private By currentlyLoggedUser = By.cssSelector("#topMenu_C019 > span");


    public CustomerDataSection pressNewQuotationButton() {
        waitOnButton(newQuotationButton);
        click(newQuotationButton);
        return new CustomerDataSection(driver);
    }

    public String getCurrentlyLoggedUser() {
        waitOnPresenceOfElement(currentlyLoggedUser);
        return getText(currentlyLoggedUser);
    }



}
