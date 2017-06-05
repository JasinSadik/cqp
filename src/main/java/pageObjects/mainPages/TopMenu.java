package pageObjects.mainPages;

import common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.ChangeLsuPopUp;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class TopMenu extends CommonMethods {
    public TopMenu(WebDriver driver) {
        super(driver);
    }

    private By logoutHyperlink = By.xpath("//a[text()='Logout']");
    private By preferencesHyperlink = By.xpath("//div[@class='classicMenu']//*[@id='menuPreferences']/a");
    private By changeUnitHyperlink = By.xpath("//div[@class='classicMenu']//a[text()='Change unit of work']");
    private By currentlyLoggedUser = By.xpath("//div[contains(@class, 'classicMenu')]//*[@id='topMenu_C019']/span");
    private By currentlyLoggedUsersLsu = By.xpath("//div[contains(@class, 'classicMenu')]//*[@id='topMenu_C019']/span/span");

    public String getCurrentlyLoggedUser() {
        waitForPageLoad(driver);
        waitOnPresenceOfElement(currentlyLoggedUser);
        return getText(currentlyLoggedUser);
    }

    public String getCurrentlyLoggedUsersLsu() {
        waitForPageLoad(driver);
        waitOnPresenceOfElement(currentlyLoggedUsersLsu);
        return getText(currentlyLoggedUsersLsu);
    }

    public LoginPage pressLogoutHyperlink(){
        waitForPageLoad(driver);
        waitOnElementToBeClickable(logoutHyperlink);
        click(logoutHyperlink);
        return new LoginPage(driver);
    }


    public LoginPage pressChangeUnitHyperlink(){
        waitForPageLoad(driver);
        waitOnElementToBeClickable(changeUnitHyperlink);
        click(changeUnitHyperlink);
        return new LoginPage(driver);
    }

    public LoginPage pressPreferencesHyperlink(){
        waitOnElementToBeClickable(preferencesHyperlink);
        click(preferencesHyperlink);
        return new LoginPage(driver);
    }

    public ChangeLsuPopUp changeLsu(String LsuName){
        pressPreferencesHyperlink();
        pressChangeUnitHyperlink();
        ChangeLsuPopUp changeLsuPopUp = new ChangeLsuPopUp(driver);
        changeLsuPopUp.changeLsu(LsuName);
        return new ChangeLsuPopUp(driver);
    }

}
