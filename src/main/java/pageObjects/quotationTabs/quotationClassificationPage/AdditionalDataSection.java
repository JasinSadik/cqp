package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.confirmationPopUp.ConfirmationModal;

/**
 * Created by PLJAHAS on 2016-12-01.
 */
public class AdditionalDataSection extends QuotationClassificationPage {
    public AdditionalDataSection(WebDriver driver) {
        super(driver);

    }

    private String quotationLanguageComboBoxId = "comboQuotationLanguage";

    public void setQuotationLanguage(String language) {
        scrollToElement(By.id(quotationLanguageComboBoxId));
        selectElementFromDropdownList(quotationLanguageComboBoxId, language);
        new ConfirmationModal(driver).pressYesButton();
    }


}
