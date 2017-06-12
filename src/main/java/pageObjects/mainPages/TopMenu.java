package pageObjects.mainPages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.popUpWindows.ChangeLsuPopUp;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class TopMenu extends CommonMethods {

    //non-dashboard classic menu
    private By logoutHyperlink = By.xpath("//div[contains(@class, 'classicQuotationMenu')]//a[text()='Logout']");
    private By preferencesHyperlink = By.xpath("//div[contains(@class, 'classicQuotationMenu')]//*[@id='menuPreferences']/a");
    private By changeUnitHyperlink = By.xpath("//div[contains(@class, 'classicQuotationMenu')]//a[text()='Change unit of work']");
    private By currentlyLoggedUser = By.xpath("//div[contains(@class, 'classicQuotationMenu')]//*[@id='topMenu_C019']/span");
    private By currentlyLoggedUsersLsu = By.xpath("//div[contains(@class, 'classicQuotationMenu')]//*[@id='topMenu_C019']/span/span");
    //moja zmiana
    private By dashboardHyperLink = By.xpath("//*[@id='menuDashboard']/a");

    //dashboard classic menu
    private By logoutHyperlinkDashboard = By.xpath("//div[contains(@class, 'classicMenu')]//a[text()='Logout']");
    private By preferencesHyperlinkDashboard = By.xpath("//div[contains(@class, 'classicMenu')]//*[@id='menuPreferences']/a");
    private By changeUnitHyperlinkDashboard = By.xpath("//div[contains(@class, 'classicMenu')]//a[text()='Change unit of work']");
    private By currentlyLoggedUserDashboard = By.xpath("//div[contains(@class, 'classicMenu')]//*[@id='topMenu_C019']/span");
    private By currentlyLoggedUsersLsuDashboard = By.xpath("//div[contains(@class, 'classicMenu')]//*[@id='topMenu_C019']/span/span");
    //moja zmiana
    private By dashboardHyperLinkDashboard = By.xpath("//*[@id='menuDashboard']/a");
    private By searchQuotationHyperLinkDashboard = By.xpath("//*[@id='firstNavTile']/span");

    //non dashboard small menu elements
    private By logoutHyperlinkSmallMenu = By.xpath("//div[contains(@class, 'quotationMenu')]//a[text()='Logout']");
    private By preferencesHyperlinkSmallMenu = By.xpath("//div[contains(@class, 'quotationMenu')]//*[@id='menuPreferences']/a");
    private By changeUnitHyperlinkSmallMenu = By.xpath("//div[contains(@class, 'quotationMenu')]//a[text()='Change unit of work']");
    private By currentlyLoggedUserSmallMenu = By.xpath("//div[contains(@class, 'quotationMenu')]//*[@id='topMenu_C019']/span");
    private By currentlyLoggedUsersLsuSmallMenu = By.xpath("/div[contains(@class, 'quotationMenu')]//*[@id='topMenu_C019']/span/span");

    private By smallMenuIcon = By.xpath("//div[contains(@class, 'quotationMenu')]//*[@id='topMenu_C019']/div[1]/a");
    //moja zmiana
    private By dashboardHyperLinkSmallMenu = By.xpath("//*[@id='topMenu_C019']/div[1]/ul/li[1]/a");

    //dashboard small menu elements
    private By logoutHyperlinkSmallMenuDashboard = By.xpath("//div[contains(@class, 'dashboardMenu')]//a[text()='Logout']");
    private By preferencesHyperlinkSmallMenuDashboard = By.xpath("//div[contains(@class, 'dashboardMenu')]//*[@id='menuPreferences']/a");
    private By changeUnitHyperlinkSmallMenuDashboard = By.xpath("//div[contains(@class, 'dashboardMenu')]//a[text()='Change unit of work']");
    private By currentlyLoggedUserSmallMenuDashboard = By.xpath("//div[contains(@class, 'dashboardMenu')]//*[@id='topMenu_C019']/span");
    private By currentlyLoggedUsersLsuSmallMenuDashboard = By.xpath("//div[contains(@class, 'dashboardMenu')]//*[@id='topMenu_C019']/span/span");
    //tego uzyje
    private By smallMenuIconDashboard = By.xpath("//div[contains(@class, 'dashboardMenu')]//*[@id='topMenu_C019']/div[1]/a");


    public TopMenu(WebDriver driver) {
        super(driver);
    }


    public boolean isDashboard(){
        boolean isDashboard = true;
        waitForPageLoad(driver);
        if(!driver.getTitle().contains("Dashboard")){
            isDashboard = false;
        }
        return isDashboard;
    }

    public boolean isSmallMenuDisplayed(){
        boolean isSmallMenuDisplayed = true;
        waitForPageLoad(driver);
        setTimeout(driver, 2);
            if(isDashboard()){
                try {
                    new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(smallMenuIconDashboard));
                }catch (TimeoutException e){
                    isSmallMenuDisplayed = false;
                }
            }else {
                try {
                    new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(smallMenuIcon));
                }catch (TimeoutException e){
                    isSmallMenuDisplayed = false;
                }
            }

        setTimeout(driver, 30);
        return isSmallMenuDisplayed;
    }

    public String getCurrentlyLoggedUser() {
        waitForPageLoad(driver);
        if(isSmallMenuDisplayed()) {
            if(isDashboard()){
                waitOnPresenceOfElement(currentlyLoggedUserSmallMenuDashboard);
                return getText(currentlyLoggedUserSmallMenuDashboard);
            }else {
                waitOnPresenceOfElement(currentlyLoggedUserSmallMenu);
                return getText(currentlyLoggedUserSmallMenu);
            }
        }else{
            if(isDashboard()){
                waitOnPresenceOfElement(currentlyLoggedUserDashboard);
                return getText(currentlyLoggedUserDashboard);
            }else {
                waitOnPresenceOfElement(currentlyLoggedUser);
                return getText(currentlyLoggedUser);
            }
        }
    }

    public String getCurrentlyLoggedUsersLsu() {
        waitForPageLoad(driver);
        if(isSmallMenuDisplayed()){
            if(isDashboard()){
                waitOnPresenceOfElement(currentlyLoggedUsersLsuSmallMenuDashboard);
                return getText(currentlyLoggedUsersLsuSmallMenuDashboard);
            }else {
                waitOnPresenceOfElement(currentlyLoggedUsersLsuSmallMenu);
                return getText(currentlyLoggedUsersLsuSmallMenu);
            }
        }else{
            if(isDashboard()){
                waitOnPresenceOfElement(currentlyLoggedUsersLsuDashboard);
                return getText(currentlyLoggedUsersLsuDashboard);
            }else {
                waitOnPresenceOfElement(currentlyLoggedUsersLsu);
                return getText(currentlyLoggedUsersLsu);
            }
        }
    }

    public void pressSmallMenuIcon(){
        waitForPageLoad(driver);
        if(isDashboard()){
            waitOnElementToBeClickable(smallMenuIconDashboard);
            click(smallMenuIconDashboard);
        }else {
            waitOnElementToBeClickable(smallMenuIcon);
            click(smallMenuIcon);
        }
    }

    public void pressLogoutHyperlink(){
        waitForPageLoad(driver);
        if(isSmallMenuDisplayed()){
            pressSmallMenuIcon();
            if(isDashboard()){
                waitOnElementToBeClickable(logoutHyperlinkSmallMenuDashboard);
                click(logoutHyperlinkSmallMenuDashboard);
            }else {
                waitOnElementToBeClickable(logoutHyperlinkSmallMenu);
                click(logoutHyperlinkSmallMenu);
            }
        }else{
            if(isDashboard()){
                waitOnElementToBeClickable(logoutHyperlinkDashboard);
                click(logoutHyperlinkDashboard);
            }else {
                waitOnElementToBeClickable(logoutHyperlink);
                click(logoutHyperlink);
            }
        }
    }
    //moja zmiana, wrzucić do LsuDashBoard i tutaj dodać nową, która wchodzi bezpośrednio z Quotations->SearchQuotation
    public void pressSearchQuotation(){
        waitForPageLoad(driver);
        boolean pageStatus = false;
        do {
            if (isDashboard()) {
                waitOnElementToBeClickable(searchQuotationHyperLinkDashboard);
                click(searchQuotationHyperLinkDashboard);
                pageStatus = true;
            } else {
                if (isSmallMenuDisplayed()) {
                    pressSmallMenuIcon();
                    waitOnElementToBeClickable(dashboardHyperLinkSmallMenu);
                    click(dashboardHyperLinkSmallMenu);


                } else {
                    waitOnElementToBeClickable(dashboardHyperLink);
                    click(dashboardHyperLink);
                }
            }
        }while(!pageStatus);
    }



    public void pressChangeUnitHyperlink(){
        waitForPageLoad(driver);
        if(isSmallMenuDisplayed()){
            pressSmallMenuIcon();
            if(isDashboard()){
                waitOnElementToBeClickable(changeUnitHyperlinkSmallMenuDashboard);
                click(changeUnitHyperlinkSmallMenuDashboard);
            }else {
                waitOnElementToBeClickable(changeUnitHyperlinkSmallMenu);
                click(changeUnitHyperlinkSmallMenu);
            }
        }else{
            if(isDashboard()){
                waitOnElementToBeClickable(changeUnitHyperlinkDashboard);
                click(changeUnitHyperlinkDashboard);
            }else {
                waitOnElementToBeClickable(changeUnitHyperlink);
                click(changeUnitHyperlink);
            }
        }
    }

    public void moveToPreferencesListElement(){
        if(isDashboard()){
            moveToElement(preferencesHyperlinkDashboard);
        }else{
            moveToElement(preferencesHyperlink);
        }

    }

    public void pressPreferencesHyperlink(){
        waitForPageLoad(driver);
        if(isSmallMenuDisplayed()){
            pressSmallMenuIcon();
            if(isDashboard()){
                waitOnElementToBeClickable(preferencesHyperlinkSmallMenuDashboard);
                moveToElement(preferencesHyperlinkSmallMenuDashboard);
            }else {
                waitOnElementToBeClickable(preferencesHyperlinkSmallMenu);
                click(preferencesHyperlinkSmallMenu);
            }
        }else{
            if(isDashboard()){
                waitOnElementToBeClickable(preferencesHyperlinkDashboard);
                click(preferencesHyperlinkDashboard);
            }else {
                waitOnElementToBeClickable(preferencesHyperlink);
                click(preferencesHyperlink);
            }

        }
    }

    public void changeLsu(String LsuName){
        pressPreferencesHyperlink();
        pressChangeUnitHyperlink();
        ChangeLsuPopUp changeLsuPopUp = new ChangeLsuPopUp(driver);
        changeLsuPopUp.changeLsu(LsuName);
    }

    public void relogOnUser(String username){
        pressLogoutHyperlink();


    }

}
