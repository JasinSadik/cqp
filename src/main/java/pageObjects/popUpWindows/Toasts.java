package pageObjects.popUpWindows;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by PLJAHAS on 2017-06-07.
 */
public class Toasts extends Modals {

    public Toasts(WebDriver driver) {
        super(driver);
    }

    private By toastPath = By.xpath("//div[@class='toast-message']");


    private boolean verifyIfElementWasDisplayed(String text) {
        int counter = 0;
        boolean elementStatus = false;
        setTimeout(driver, 1);
        while (!elementStatus && counter < 30) {
            try {
                element = driver.findElement(toastPath);
                elementStatus = true;
            } catch (NoSuchElementException | ElementNotFoundException e) {
                counter++;
            }
        }
        setTimeout(driver, 30);

        if (elementStatus && element.getText().contains(text)) {
            return true;
        } else {
            return false;
        }

    }

    private boolean verifyIfElementWasDisplayedNotUsedForNow(String text) {
        int counter = 0;
        boolean elementStatus = true;
        setTimeout(driver, 1);
        while (elementStatus && counter < 60) {
            try {

                elements = driver.findElements(toastPath);
                if (elements != null) {
                    elementStatus = false;
                }
            } catch (NoSuchElementException | ElementNotFoundException e) {
                counter++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        boolean correctTextFound = false;
        setTimeout(driver, 30);

        for (WebElement e : elements) {
            if (element.getText().contains(text)) {
                correctTextFound = true;
                break;
            }
        }

        if (elementStatus) {
            return false;
        } else {
            if (correctTextFound) {
                return true;
            } else {
                return false;
            }
        }
    }


    public boolean isQuotationSavedSuccessfullyToatstrDisplayed() {
        return verifyIfElementWasDisplayed("Quotation saved");
    }

    public boolean isCustomerSuccessfullyAddedToatstrDisplayed() {
        return verifyIfElementWasDisplayed("Customer added");
    }

    public boolean isSupportRequestSuccessfullyCreatedToatstrDisplayed() {
        return verifyIfElementWasDisplayed("Support request added");
    }

    public boolean isQuoationStatusChangedDisplayed() {
        return verifyIfElementWasDisplayed("Quotation status changed");
    }


    public boolean isPreOrderDataSectionSaved() {
        return verifyIfElementWasDisplayed("Preorder Data saved");
    }

    public boolean isOrderSentSuccessfully() {
        return verifyIfElementWasDisplayed("uccess");
    }
}
