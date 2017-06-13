package testToBeUpdated;

import common.CommonMethods;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.mainPages.PuDashboard;
import pageObjects.mainPages.TopMenu;
import pageObjects.popUpWindows.CreditLimitPopUp;
import pageObjects.popUpWindows.Toasts;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.approvalRequestPage.ApprovalRequestPuPage;
import pageObjects.quotationTabs.productsAndPricesPage.ProductLine;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesPage;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.GeneralSection;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationPage;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestCreationPage;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestMainActionPage;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestPage;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestProductAndPricesPage;
import scenarios.ScenarioSweden;

import static org.junit.Assert.assertTrue;

/**
 * Created by PLLIKOS on 2017-06-09.
 */
public class SupportWorkflowTest extends ScenarioSweden {

    private final String PROJECT_NAME = new CommonMethods(driver).timestamp();
    private final String PU_USER = "asko.hokkanen@fi.abb.com";
    private final String PU_APPROVER = "jari.toimela@fi.abb.com";
    private String quotationNumber = "";
    private final String PU_DISCOUNT_ON_WHOLE_REQUEST = "10.00%";

    protected SupportWorkflowTest() throws Exception {
    }

    @BeforeTest
    public void before() throws Exception {
        driver = new CommonMethods(driver).browserSetup();
        driver.get(new CommonMethods(driver).getPropertyFromConfigurationFile("environment_url"));
    }

    @Test(priority = 1)
    public void shouldLogIntoToCqp() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInToCqp(USERNAME, PASSWORD);
        assertTrue(true);
    }


    @Test(priority = 2)
    public void shouldCreateQuotation() throws InterruptedException {
        LsuDashboard lsuDashboard = new LsuDashboard(driver);
        CustomerDataSection customerDataSection = lsuDashboard.pressNewQuotationButton();
        customerDataSection.selectCustomerFromSearch(CUSTOMER);
        CreditLimitPopUp creditLimitPopUp = new CreditLimitPopUp(driver);
        creditLimitPopUp.pressOkButton();
        customerDataSection.pressTodayRfqButton();
        customerDataSection.setIndustryUsageLevelOne(INUDSTRY_USAGE_LEVEL1);
        //  customerDataSection.setIndustryUsageLevelTwo(INUDSTRY_USAGE_LEVEL2);
        GeneralSection generalSection = new GeneralSection(driver);
        customerDataSection.pressSaveAndCollapseButton();
        generalSection.insertProjectName(PROJECT_NAME);
        generalSection.setQuotationType(QUOTATION_TYPE);
        AdditionalDataSection additionalDataSection = new AdditionalDataSection(driver);
        generalSection.pressSaveAndCollapseButton();
      //  additionalDataSection.setQuotationLanguage("Arabic");
        QuotationClassificationPage quotationClassificationPage = new QuotationClassificationPage(driver);
        quotationClassificationPage.pressCreateQuotationButton();
        //  sfdcSyncConfirmationModal.pressConfirmButton();

    }

    @Test(priority = 3)
    public void shouldAddProducts() throws InterruptedException {
        ProductsAndPricesPage productsAndPricesPage = new ProductsAndPricesPage(driver);
        ProductLine productLine = new ProductLine(driver);
        productsAndPricesPage.addProductFromLvDrive("ACS800-01-0120-3+E202+K454");
        productLine.setApplication(1, "Calander Line");
    }

    @Test(priority = 4)
    public void shouldStartSupportRequest() throws InterruptedException {
        ProductLine productLine = new ProductLine(driver);
        SupportRequestCreationPage supportRequestCreationPage = new SupportRequestCreationPage(driver);
        productLine.createSupportRequest(1, "LV AC Drives Helsinki support");
        supportRequestCreationPage.createSupportRequestForSpecificUser("Asko Hokkanen", "First Auto Support", "Special discount");
        ProductsAndPricesPage productsAndPricesPage = new ProductsAndPricesPage(driver);
        assertTrue("The support request is not sent. Probably caused by K2 unavailability.", productsAndPricesPage.isAddProductButtonDisplayed());
        quotationNumber = supportRequestCreationPage.getQuotationNumber();
    }

    @Test(priority = 5)
    public void shouldRelogCqpUser() throws InterruptedException {
        new TopMenu(driver).relogOnUser(PU_USER);
    }

    @Test(priority = 6)
    public void shouldOpenSupportRequestFromDashboard() throws Exception {
        PuDashboard puDashboard = new PuDashboard(driver);
        puDashboard.shouldShowListOfRequests();
        puDashboard.openSupportRequest(quotationNumber);
    }
    @Test (priority = 7)
    public void shouldPuAddDiscountOnWholeRequest () {
        new QuotationNavigationBar(driver).goToProductAndPriceTab();
        SupportRequestProductAndPricesPage supportRequestProductAndPricesPage = new SupportRequestProductAndPricesPage(driver);
        supportRequestProductAndPricesPage.insertDiscountOnWholeRequest(PU_DISCOUNT_ON_WHOLE_REQUEST);
        supportRequestProductAndPricesPage.pressApplyDicountToWholeRequestButton();
        assertTrue(PU_DISCOUNT_ON_WHOLE_REQUEST.equals(supportRequestProductAndPricesPage.getProductTpDiscountField(1)));
        supportRequestProductAndPricesPage.pressBackToSupportRequestButton();
    }

        /*
    Wykonanie innych akcji
     */

    @Test(priority = 8)
    public void shouldReplyAndCloseRequest() {
        new SupportRequestMainActionPage(driver).replyAndCloseRequest("Please find my final comment");
    }

    @Test(priority = 9)
    public void shouldPuSendApproval() {
        ApprovalRequestPuPage approvalRequestPuPage = new ApprovalRequestPuPage(driver);
        approvalRequestPuPage.pressNextButton();
        approvalRequestPuPage.pressSubmitForApprovalButton();
    }

    @Test(priority = 10)
    public void shouldPuAcceptApproval() {
        TopMenu topMenu = new TopMenu(driver);
        topMenu.relogOnUser(PU_APPROVER);
        PuDashboard puDashboard = new PuDashboard(driver);
        puDashboard.shouldShowListOfRequests();
        puDashboard.openSupportRequest(quotationNumber);
        ApprovalRequestPuPage approvalRequestPuPage = new ApprovalRequestPuPage(driver);
        approvalRequestPuPage.pressApproveButton();
        approvalRequestPuPage.approveQuotation("OK");
        approvalRequestPuPage.pressSendByPuApproverButton();
    }
    /*
      Dopisać w przyszłości sprawdzenie czy jest approval, przejście przez approval
       */
    @Test(priority = 11)
    public void shouldRelogOnLsuUser() {
        new TopMenu(driver).relogOnUser(USERNAME);
    }

    @Test(priority = 12)
    public void shouldLsuAcceptFinalResponseFromPu() {
        new LsuDashboard(driver).openQuotationFromQuickSearch(quotationNumber);
        new QuotationNavigationBar(driver).goToSupportRequestTab();
        new SupportRequestPage(driver).pressViewRequestIcon(1);
        SupportRequestMainActionPage supportRequestMainActionPage = new SupportRequestMainActionPage(driver);
        supportRequestMainActionPage.setNpsScore(8);
        supportRequestMainActionPage.pressAcceptResponseButton();
        assertTrue("Unable to close support request",  new Toasts(driver).isSupportRequestAccepted());
    }

}




