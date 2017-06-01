package pageObjects.mainPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestMainActionPage;

/**
 * Created by PLJAHAS on 2016-12-28.
 */
public class PuDashboard extends TopMenu {
    public PuDashboard(WebDriver driver) {
        super(driver);
    }

    private String quotationNumber = "";

    private void setQuotationNumber(String quotationNumber) {
        viewSupportRequestButton = By.xpath("//td[text()='" + quotationNumber + "']/..//a");
    }

    private By viewSupportRequestButton = By.xpath("//td[text()='" + quotationNumber + "']/..//a");

    public SupportRequestMainActionPage openSupportRequest(String quotationNumber) {
        setQuotationNumber(quotationNumber);
        waitOnElementToBeClickable(viewSupportRequestButton);
        click(viewSupportRequestButton);
        return new SupportRequestMainActionPage(driver);
    }


}
