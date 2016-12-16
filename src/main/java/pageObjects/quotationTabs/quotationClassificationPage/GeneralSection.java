package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-02.
 */
public class GeneralSection extends QuotationNavigationBar {
    public GeneralSection(WebDriver driver) {
        super(driver);

    }


    @FindBy(css ="#generalEditSection i.icon-search")
    private WebElement endUserProjectSearchButton;

    @FindBy(css ="#isRfqOriginal")
    private WebElement projectNameField;

    @FindBy(css ="#isRfqOriginal")
    private WebElement endUserProjectField;

    @FindBy(css ="#generalEditSection i.icon-search")
    private WebElement endUserProjectSecondaryNameField;

    @FindBy(xpath ="//div[id='quotationOwner']//button[contains(text(), 'Binding')]")
    private WebElement bindingButton;

    @FindBy(xpath ="//div[id='quotationOwner']//button[contains(text(), 'Non-binding')]")
    private WebElement nonBindingButton;

    @FindBy(css ="#generalEditSection i.icon-search")
    private WebElement isNegotiationCheckbox;

    @FindBy(css ="#generalEditSection i.icon-search")
    private WebElement replyEmailAddressField;

    @FindBy(css ="#generalEditSection i.icon-search")
    private WebElement replyPhoneNumberButton;

    @FindBy(css ="#generalEditSection i.icon-search")
    private WebElement saveAndCollapseButton;

    @FindBy(id ="comboQuotationType")
    private WebElement quotationTypeCombobox;



    public GeneralSection pressEndUserProjectSearchButton (){
        endUserProjectSearchButton.click();
        return this;
    }


    public GeneralSection expandQuotationTypeCombobox (){
        quotationTypeCombobox.click();
        return this;
    }


    public <T extends QuotationNavigationBar> T pressSaveAndCollapseButton(Class<T> clazz) {
        waitOnButton(saveAndCollapseButton);
        saveAndCollapseButton.click();
        return PageFactory.initElements(driver, clazz);
    }
}

