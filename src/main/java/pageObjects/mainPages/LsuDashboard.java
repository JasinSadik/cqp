package pageObjects.mainPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class LsuDashboard extends TopMenu {
    public LsuDashboard(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[class='button newQuotation']")
    private WebElement newQuotationButton;



    public LsuDashboard clickNewQuotationButton() {
        newQuotationButton.click();
        return this;
    }


    public QuotationNavigationBar pressNewQuotationButton() {
        newQuotationButton.click();
        return new QuotationNavigationBar(driver);
    }




}
