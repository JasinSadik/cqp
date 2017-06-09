package pageObjects.quotationTabs.supportRequestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2017-05-31.
 */
public class SupportRequestPage extends QuotationNavigationBar {
    public SupportRequestPage(WebDriver driver) {
        super(driver);
    }

    private int positionLine = 1;

    private void setPositionLine(int desiredLine) {
        positionLine = desiredLine;
        removeRequestIcon = By.xpath("//th[contains(text(),'Product')]/../../..//tbody//tr["+positionLine+"]//div[@class = 'icon deleteAction']" );
        viewRequestIcon = By.xpath("//th[contains(text(),'Product')]/../../..//tbody//tr["+positionLine+"]//div[@class = 'icon watchAction']");
    }

    private By removeRequestIcon = By.xpath("//th[contains(text(),'Product')]/../../..//tbody//tr["+positionLine+"]//div[@class = 'icon deleteAction']" );
    private By viewRequestIcon = By.xpath("//th[contains(text(),'Product')]/../../..//tbody//tr["+positionLine+"]//div[@class = 'icon watchAction']");

    public void pressRemoveRequestIcon (int desiredLine){
        setPositionLine(desiredLine);
        waitOnButton(removeRequestIcon);
        click(removeRequestIcon);
    }
    public void pressViewRequestIcon (int desiredLine){
        setPositionLine(desiredLine);
        waitOnButton(viewRequestIcon);
        click(viewRequestIcon);
    }
}
