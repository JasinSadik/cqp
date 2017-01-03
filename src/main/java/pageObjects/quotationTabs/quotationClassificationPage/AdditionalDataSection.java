package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.popUpWindows.confirmationPopUp.ConfirmationModal;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-01.
 */
public class AdditionalDataSection extends QuotationNavigationBar {
    public AdditionalDataSection(WebDriver driver) {
        super(driver);

    }

    private String quotationLanguageComboBoxId = "comboQuotationLanguage";

    public ConfirmationModal setQuotationLanguage(String language) {
        scrollToElement(By.id(quotationLanguageComboBoxId));
        selectElementFromDropdownList(quotationLanguageComboBoxId, language);
        new ConfirmationModal(driver).pressYesButton();
        return new ConfirmationModal(driver);
    }


}
