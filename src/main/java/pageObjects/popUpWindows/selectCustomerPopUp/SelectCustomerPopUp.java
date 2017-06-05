package pageObjects.popUpWindows.selectCustomerPopUp;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.mainPages.TopMenu;
import pageObjects.popUpWindows.Modals;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;

/**
 * Created by PLJAHAS on 2016-12-06.
 */
public class SelectCustomerPopUp extends Modals {

    public SelectCustomerPopUp(WebDriver driver) {
        super(driver);
    }

    private By customerSearchField = By.id("searchCustomerByName");
    private By searchButton = By.cssSelector("#customerCamSearch button[class='primaryButton searchButton']");
    private By selectButtonFirstSearchResult = By.cssSelector("#customerCamSearch table tr:nth-child(1)  button");

    public SelectCustomerPopUp insertCustomerSearchFieldSearchValue(String searchValue) {
        waitOnElementToBeVisible(customerSearchField);
        clear(customerSearchField);
        sendKeys(customerSearchField, searchValue);
        return this;
    }

    public SelectCustomerPopUp pressSearchButton() {
        waitOnButton(searchButton);
        click(searchButton);
        return this;
    }

    public CustomerDataSection pressSelectButtonForFirstSearchResult() {
        waitOnButton(selectButtonFirstSearchResult);
        click(selectButtonFirstSearchResult);;
        return new CustomerDataSection(driver);
    }

    public <T extends QuotationNavigationBar> T pressSaveAndCollapseButton(Class<T> clazz) {
        waitOnButton(selectButtonFirstSearchResult);
        click(selectButtonFirstSearchResult);;
        return PageFactory.initElements(driver, clazz);
    }



}
