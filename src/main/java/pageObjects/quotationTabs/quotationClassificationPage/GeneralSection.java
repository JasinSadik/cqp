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
public class GeneralSection extends QuotationClassificationPage {
    public GeneralSection(WebDriver driver) {
        super(driver);

    }

    private By endUserProjectSearchButton = By.cssSelector("#generalEditSection i.icon-search");
    private By projectNameField = By.cssSelector("#isRfqOriginal");
    private By bindingButton= By.xpath("//div[@id='quotationOwner']//button[contains(text(), 'Binding')]");
    private By saveAndCollapseButton= By.xpath("//div[@id='quotationOwner']//button[contains(text(), 'Save and collapse')]");
    private By BindingQuotationCategoryButton= By.xpath("//div[@id='generalEditSection']//button[text()='Binding']");

    private String quotationTypeComboboxId = "comboQuotationType";



    public void insertProjectName (String projectName){
        waitOnPresenceOfElement(projectNameField);
        clear(projectNameField);
        sendKeys(projectNameField, projectName);
    }

    public void setQuotationType(String quotationType) {
        scrollToElement(By.id(quotationTypeComboboxId));
        selectElementFromDropdownList(quotationTypeComboboxId, quotationType);
    }
    public void pressSaveAndCollapseButton() {
        scrollToElement(saveAndCollapseButton);
        waitOnButton(saveAndCollapseButton);
        click(saveAndCollapseButton);
    }

    public void pressBindingQuotationCategoryButton() {
        scrollToElement(BindingQuotationCategoryButton);
        waitOnButton(BindingQuotationCategoryButton);
        click(BindingQuotationCategoryButton);
    }


}

