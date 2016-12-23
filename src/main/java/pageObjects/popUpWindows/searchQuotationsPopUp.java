package pageObjects.popUpWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.mainPages.TopMenu;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationCommonActionButtonsSection;

/**
 * Created by PLJAHAS on 2016-12-21.
 */
public class SearchQuotationsPopUp extends TopMenu {
    public SearchQuotationsPopUp(WebDriver driver) {
        super(driver);
    }

    private By eyeIcon = By.xpath("//div[@id='searchAllQuotations']//div[@class='icon watchAction']");

    public QuotationNavigationBar openQuotation(){
        waitOnButton(eyeIcon);
        click(eyeIcon);
        return new QuotationNavigationBar(driver);
    }


}
