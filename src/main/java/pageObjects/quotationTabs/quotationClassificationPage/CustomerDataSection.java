package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-06.
 */
public class CustomerDataSection extends QuotationNavigationBar {

    public CustomerDataSection(WebDriver driver) {
        super(driver);
    }

    @FindBy(css ="#quotationCustomer button.searchCustomerButton")
    private WebElement customerSearchButton;

    @FindBy(css ="#quotationCustomer i.icon-star")
    private WebElement favoriteCustomerButton;

    @FindBy(css ="#quotationCustomer i.icon-fire")
    private WebElement mostUsedCustomerButton;

    @FindBy(css ="#editingCustomer  div:nth-child(5) i.icon-search")     //do poprawy
    private WebElement endCustomerSearchButton;

    @FindBy(css ="#editingCustomer > div:nth-child(11) > div:nth-child(1) > span.k-widget.k-combobox.k-header > span > input")     //do poprawy
    private WebElement industryUsageDropdownList;

    @FindBy(xpath = "//*[@id='quotationCustomer']//button[contains(text(), 'Cancel')]")
    private WebElement cancelButton;

    @FindBy(css = "#quotationCustomer button.primaryButton")
    private WebElement saveAndCollapseButton;

    @FindBy(id = "rfQtimestamp")
    private WebElement rfqReceivedDateField;

    public CustomerDataSection setRfqReceivedDate (String date){
        rfqReceivedDateField.clear();
        rfqReceivedDateField.sendKeys(date);
        return this;
    }

    public CustomerDataSection pressSaveAndCollapseButton (){
        saveAndCollapseButton.click();
        return this;
    }

    public CustomerDataSection setIndustryUsage (String industryUsage){
        industryUsageDropdownList.clear();
        industryUsageDropdownList.sendKeys(industryUsage);
        return this;
    }

    public CustomerDataSection pressCustomerSearchButton (){
        customerSearchButton.click();
        return this;
    }

    public CustomerDataSection pressEndCustomerSearchButton (){
        endCustomerSearchButton.click();
        return this;
    }

    public CustomerDataSection pressFavoriteCustomerButton (){
        favoriteCustomerButton.click();
        return this;
    }

    public CustomerDataSection pressCancelButton(){
        cancelButton.click();
        return this;
    }

    public CustomerDataSection pressMostUsedCustomerButton (){
        mostUsedCustomerButton.click();
        return this;
    }

    public <T extends QuotationNavigationBar> T pressCustomerSearchButton(Class<T> clazz) {
        customerSearchButton.click();
        return PageFactory.initElements(driver, clazz);
    }


}
