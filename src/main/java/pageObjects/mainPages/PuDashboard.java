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
    private By loadMoreRequestButton = By.xpath("//button[text()='Load more request']");
    private By viewSupportRequestButton = By.xpath("//td[text()='" + quotationNumber + "']/..//a");
    private By listButton = By.xpath("//*[@id='thirdPanelGroup']//span[3]//button[contains (text(), 'List')]");


    private void setQuotationNumber(String quotationNumber) {
        viewSupportRequestButton = By.xpath("//td[text()='" + quotationNumber + "']/..//a");
    }

    public void openSupportRequest(String quotationNumber) {
        waitForPageLoad(driver);
        boolean elementstatus = false;
        int count = 0;
        setTimeout(driver,1);
        while (!elementstatus && count<5){
            if (driver.findElement(loadMoreRequestButton).isDisplayed()) {
                click(loadMoreRequestButton);
                elementstatus =true;
            }
            count++;
        }
        setTimeout(driver,30);
        setQuotationNumber(quotationNumber);
        waitOnElementToBeClickable(viewSupportRequestButton);
        click(viewSupportRequestButton);
    }

    public void shouldShowListOfRequests (){
        waitOnButton(listButton);
        click(listButton);
    }


}
