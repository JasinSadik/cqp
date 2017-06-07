package pageObjects.mainPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageObjects.popUpWindows.SearchQuotationsPopUp;
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
     private By searchQuotationField = By.cssSelector("#firstClonePanelGroup input");
    private By searchAllQuotationsButton = By.xpath("//*[@id = 'firstClonePanelGroup']//input/../a");

    public CustomerDataSection pressNewQuotationButton() {
        waitOnButton(newQuotationButton);
        click(newQuotationButton);
        return new CustomerDataSection(driver);
    }
    

    public void pressSearchAllQuotationsButton(){
        waitOnButton(searchAllQuotationsButton);
        click(searchAllQuotationsButton);
    }

    public void insertQuotationNumberToSearchField(String quoteNumber){
        waitOnPresenceOfElement(searchQuotationField);
        clear(searchQuotationField);
        sendKeys(searchQuotationField, quoteNumber);
    }

    public void openQuotationFromQuickSearch(String quoteNumber) {
        waitOnPresenceOfElement(searchQuotationField);
        insertQuotationNumberToSearchField(quoteNumber);
        pressSearchAllQuotationsButton();
        SearchQuotationsPopUp searchQuotationsPopUp = new SearchQuotationsPopUp(driver);
        searchQuotationsPopUp.openQuotation();
    }
}
