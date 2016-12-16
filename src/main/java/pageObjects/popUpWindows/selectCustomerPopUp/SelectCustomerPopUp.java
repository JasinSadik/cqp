package pageObjects.popUpWindows.selectCustomerPopUp;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.mainPages.TopMenu;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;

/**
 * Created by PLJAHAS on 2016-12-06.
 */
public class SelectCustomerPopUp extends TopMenu {

    public SelectCustomerPopUp(WebDriver driver) {
        super(driver);


    }

    @FindBy(id = "searchCustomerByName")
    private WebElement customerSearchField;

    @FindBy(css = "#customerCamSearch button[class='primaryButton searchButton']")
    private WebElement searchButton;


    @FindBy(css = "#customerCamSearch table tr:nth-child(1)  button")
    private WebElement selectButtonFirstSearchResult;

    public SelectCustomerPopUp setCustomerSearchFieldSearchValue(String searchValue) {
        customerSearchField.clear();
        customerSearchField.sendKeys(searchValue);
        return this;
    }

    public SelectCustomerPopUp pressSearchButton() {
        searchButton.click();
        return this;
    }

    public CustomerDataSection pressSelectButtonForFirstSearchResult() {
        selectButtonFirstSearchResult.click();
        return new CustomerDataSection(driver);
    }


}
