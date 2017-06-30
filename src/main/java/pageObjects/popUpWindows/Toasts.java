package pageObjects.popUpWindows;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by PLJAHAS on 2017-06-07.
 */
public class Toasts extends Modals {

    public Toasts(WebDriver driver) {
        super(driver);
    }

    private By toastConatiner = By.xpath("//div[@id='toast-container']");
    private String toastConatinerXpath = "//div[@id='toast-container']";


    public boolean verifyIfElementWasDisplayed(String text) {
        int counter = 0;
        boolean elementStatus = false;
        String toastElements = null;
        boolean textAppeared = false;
        setTimeout(driver, 1);
        while (!elementStatus && counter < 1000) {
            try {
                elements = driver.findElements(toastConatiner);
                if (elements != null) {
                    if (elements.size() > 0) {
                        elements = driver.findElements(toastConatiner);
                        for (int i = 0; i < elements.size(); i++) {
                            if (driver.findElement(By.xpath(toastConatinerXpath + "/div[" + i + 1 + "]/div")).getText().toLowerCase().contains(text.toLowerCase())) {
                                System.out.println(driver.findElement(By.xpath(toastConatinerXpath + "/div[" + i + 1 + "]/div")).getText());
                                textAppeared = true;
                                elementStatus = true;
                            }
                        }
                    }

                }
            } catch (NoSuchElementException | ElementNotFoundException e) {
                counter++;
            }
        }
        setTimeout(driver, 30);

        return textAppeared;
    }


    public boolean isQuotationSavedSuccessfullyToatstrDisplayed() {
        return verifyIfElementWasDisplayed("Quotation saved");
    }

    public boolean isCustomerSuccessfullyAddedToatstrDisplayed() {
        return verifyIfElementWasDisplayed("Customer added");
    }

    public boolean isRemoveRequestToastrDisplayed() {
        return verifyIfElementWasDisplayed("Request removed");
    }

    public boolean isRemoveQuotationToastrDisplayed() {
        return verifyIfElementWasDisplayed("Quotation delete correctly");
    }

    public boolean isSupportRequestSuccessfullyCreatedToatstrDisplayed() {
        return verifyIfElementWasDisplayed("Support request added");
    }

    public boolean isQuoationStatusChangedDisplayed() {
        return verifyIfElementWasDisplayed("Quotation status changed");
    }


    public boolean isPreOrderDataSectionSaved() {
        return verifyIfElementWasDisplayed("Data saved");
    }

    public boolean isOrderSentSuccessfully() {
        return verifyIfElementWasDisplayed("uccess");
    }

    public boolean isSupportRequestAccepted() {
        return verifyIfElementWasDisplayed("Response action accepted");
    }

    public boolean isDocumentIssuedToastDisplayed() {
        return verifyIfElementWasDisplayed("issued");
    }

    public boolean isDocumentCreatedToastDisplayed() {
        return verifyIfElementWasDisplayed("document");
    }

}
