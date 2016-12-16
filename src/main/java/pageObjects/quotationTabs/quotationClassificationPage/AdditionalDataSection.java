package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-01.
 */
public class AdditionalDataSection extends QuotationNavigationBar {
    public AdditionalDataSection(WebDriver driver) {
        super(driver);

    }

    @FindBy(css = "#quotationGeneral > div.editingPart.isEditing > div:nth-child(3) > div:nth-child(15) > div > span:nth-child(2) > button")
    private WebElement saveAndCollapseButton;

    public AdditionalDataSection clickSaveAndCollapseButton() {
        saveAndCollapseButton.click();
        return this;
    }

}
