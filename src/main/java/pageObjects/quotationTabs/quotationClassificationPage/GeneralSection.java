package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.By;
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

    private By endUserProjectSearchButton = By.cssSelector("#generalEditSection i.icon-search");
    private By projectNameField = By.cssSelector("#isRfqOriginal");
    private By bindingButton= By.xpath("//div[@id='quotationOwner']//button[contains(text(), 'Binding')]");
    private By saveAndCollapseButton= By.xpath("//div[@id='quotationOwner']//button[contains(text(), 'Save and collapse')]");
    private String quotationTypeComboboxId = "comboQuotationType";



    public GeneralSection insertProjectName (String projectName){
        waitOnPresenceOfElement(projectNameField);
        clear(projectNameField);
        sendKeys(projectNameField, projectName);
        return this;
    }

    public GeneralSection setQuotationType(String quotationType) {
        selectElementFromDropdownList(quotationTypeComboboxId, quotationType);
        return this;
    }
    public AdditionalDataSection pressSaveAndCollapseButton() {
        waitOnButton(saveAndCollapseButton);
        click(saveAndCollapseButton);
        return new AdditionalDataSection(driver);
    }

    public <T extends QuotationNavigationBar> T pressSaveAndCollapseButton(Class<T> clazz) {
        waitOnButton(saveAndCollapseButton);
        click(saveAndCollapseButton);
        return PageFactory.initElements(driver, clazz);
    }


}

