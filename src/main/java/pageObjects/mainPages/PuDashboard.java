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

    private By loadMoreRequestButton = By.xpath("//button[text()='Load more request']");

    private String quotationNumber = "";

    private void setQuotationNumber(String quotationNumber) {
        viewSupportRequestButton = By.xpath("//td[text()='" + quotationNumber + "']/..//a");
    }

    private By viewSupportRequestButton = By.xpath("//td[text()='" + quotationNumber + "']/..//a");

    public void openSupportRequest(String quotationNumber) {
        waitForPageLoad(driver);
        if (findElement(loadMoreRequestButton).isDisplayed()){
            click(loadMoreRequestButton);
        }
        setQuotationNumber(quotationNumber);
        waitOnElementToBeClickable(viewSupportRequestButton);
        click(viewSupportRequestButton);
    }


}
