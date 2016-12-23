package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.popUpWindows.selectCustomerPopUp.SelectCustomerPopUp;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-06.
 */
public class CustomerDataSection extends QuotationNavigationBar {

    public CustomerDataSection(WebDriver driver) {
        super(driver);
        waitForPageLoad(driver);
    }

    private int index = 0;    //used for iteration in lists
    private void index(int index){
        this.index = index;
    }
    private String industryUsageComboBoxOneId =  "comboIndustryUsageLevelOne";
    private String industryUsageComboBoxTwoId =  "comboIndustryUsageLevelTwo";
    private By customerSearchButton = By.id("btnSearchCustomer");
    private By favoriteCustomerButton= By.cssSelector("#quotationCustomer i.icon-star");
    private By mostUsedCustomerButton = By.cssSelector("#quotationCustomer i.icon-fire");
    private By endCustomerSearchButton = By.id("btnSearchEndCustomer");
    private By channelCombobox = By.id("comboChannels");
    private By businessLineCombobox = By.id("comboBusinessLine");
    private By cancelButton = By.xpath("//*[@id='quotationCustomer']//button[contains(text(), 'Cancel')]");
    private By saveAndCollapseButton = By.cssSelector("#quotationCustomer button.primaryButton");
    private By rfqReceivedDateField = By.id("pickerRfqTimestamp");
    private By rfqReceivedDateCalendarButton = By.xpath("//input[@id='pickerRfqTimestamp']/..//span[@role='button']");
    private By openCustomerDataSectionButton = By.xpath("//*[@id='quotationCustomer']//button[contains(text(), 'Open')]");
    private By todayRqfButton = By.xpath("//*[@id='quotationCustomer']//span[contains(text(), 'Today')]");


    public CustomerDataSection insertRfqReceivedDate (String date){
        waitOnButton(rfqReceivedDateCalendarButton);
        clear(rfqReceivedDateField);
        sendKeys(rfqReceivedDateField, date);
        return this;
    }


    public CustomerDataSection setIndustryUsageLevelOne (String industryUsage){
        selectElementFromDropdownList(industryUsageComboBoxOneId, industryUsage);
        return this;
    }

    public CustomerDataSection setIndustryUsageLevelTwo (String industryUsage){
        selectElementFromDropdownList(industryUsageComboBoxTwoId, industryUsage);
        return this;
    }

    public CustomerDataSection pressCancelButton(){
        waitOnButton(cancelButton);
        click(cancelButton);
        return this;
    }

    public CustomerDataSection pressTodayRfqButton(){
        waitOnButton(todayRqfButton);
        click(todayRqfButton);
        return this;
    }

    public CustomerDataSection pressOpenCustomerDataSectionButton(){
        click(openCustomerDataSectionButton);
        return this;
    }

    public CustomerDataSection selectCustomerFromSearch(String customer){
        SelectCustomerPopUp selectCustomerPopUp = pressCustomerSearchButton();
        selectCustomerPopUp.insertCustomerSearchFieldSearchValue(customer);
        selectCustomerPopUp.pressSearchButton();
        selectCustomerPopUp.pressSelectButtonForFirstSearchResult();
        return this;
    }

    public GeneralSection pressSaveAndCollapseButton() {
        waitOnButton(saveAndCollapseButton);
        click(saveAndCollapseButton);
        return new GeneralSection(driver);
    }

    public SelectCustomerPopUp pressCustomerSearchButton() {
        waitOnButton(customerSearchButton);
        click(customerSearchButton);
        return new SelectCustomerPopUp(driver);
    }

    public <T extends QuotationNavigationBar> T pressSaveAndCollapseButton(Class<T> clazz) {
        pressSaveAndCollapseButton();
        return PageFactory.initElements(driver, clazz);
    }

}
