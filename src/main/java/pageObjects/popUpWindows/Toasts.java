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
        boolean elementStatus = true;
        setTimeout(driver, 1);
        while (elementStatus && counter < 180) {
            try {
                Thread.sleep(500);
                elements = driver.findElements(toastConatiner);
                if (elements != null) {
                    elementStatus = elements.size() > 0? false:true;
                }
            } catch (NoSuchElementException | ElementNotFoundException e) {
                counter++;
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean textAppeared = false;
        elements = driver.findElements(toastConatiner);
        setTimeout(driver, 30);
        String toastElements = null;
        for (int i = 0; i < elements.size(); i++) {
            if(driver.findElement(By.xpath(toastConatinerXpath + "/div[" + i+1 + "]/div")).getText().toLowerCase().contains(text.toLowerCase())){
                textAppeared = true;
            }
        }
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
        return verifyIfElementWasDisplayed("Preorder Data saved");
    }

    public boolean isOrderSentSuccessfully() {
        return verifyIfElementWasDisplayed("uccess");
    }

    public boolean isSupportRequestAccepted() {
        return verifyIfElementWasDisplayed("Response action accepted");
    }
}
