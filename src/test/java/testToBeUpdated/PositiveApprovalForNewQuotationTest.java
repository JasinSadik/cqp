package testToBeUpdated;

import common.CommonMethods;
import common.sqlMethods.Sql_ApprovalBehavior;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.mainPages.TopMenu;
import pageObjects.popUpWindows.CreditLimitPopUp;
import pageObjects.popUpWindows.Toasts;
import pageObjects.popUpWindows.confirmationPopUp.SfdcSyncConfirmationModal;
import pageObjects.quotationTabs.approvalRequestPage.ApprovalRequestPage;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.fullCostAndFinalizationPage.DocumentGenerationSection;
import pageObjects.quotationTabs.productsAndPricesPage.ProductLine;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesPage;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.GeneralSection;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationPage;
import scenarios.ScenarioSweden;

import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2016-12-23.
 */
public class PositiveApprovalForNewQuotationTest extends ScenarioSweden {


    private final String PROJECT_NAME = getClass().getName();
    private final String APPROVER_1 = "Robert Larsson";
    private final String APPROVER_2 = "Adrian Wasielewski";
    private String quotationNumber = "";
    private final String APPROVAL_MESSAGE = "OK";

    protected PositiveApprovalForNewQuotationTest() throws Exception {
    }

    @BeforeTest
    public void before() throws Exception {
        driver = new CommonMethods(driver).browserSetup();
        driver.get(new CommonMethods(driver).getPropertyFromConfigurationFile("environment_url"));
        new Sql_ApprovalBehavior(driver).BindingGeneralApprovalSetBlocking(LSU);
        new Sql_ApprovalBehavior(driver).NonBindingGeneralApprovalSetBlocking(LSU);
    }

    @Test(priority = 1)
    public void shouldLogIntoToCqp() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInToCqp(USERNAME, PASSWORD);
        assertTrue(true);
    }


    @Test(priority = 2)
    public void shouldCreateQuotation() {
        LsuDashboard lsuDashboard = new LsuDashboard(driver);
        CustomerDataSection customerDataSection = lsuDashboard.pressNewQuotationButton();
        customerDataSection.selectCustomerFromSearch(CUSTOMER);
        CreditLimitPopUp creditLimitPopUp = new CreditLimitPopUp(driver);
        creditLimitPopUp.pressOkButton();
        customerDataSection.pressTodayRfqButton();
        customerDataSection.setIndustryUsageLevelOne(INUDSTRY_USAGE_LEVEL1);
        customerDataSection.setIndustryUsageLevelTwo(INUDSTRY_USAGE_LEVEL2);
        GeneralSection generalSection = new GeneralSection(driver);
        customerDataSection.pressSaveAndCollapseButton();
        generalSection.insertProjectName(PROJECT_NAME);
        generalSection.setQuotationType(QUOTATION_TYPE);
        AdditionalDataSection additionalDataSection = new AdditionalDataSection(driver);
        generalSection.pressSaveAndCollapseButton();
        additionalDataSection.setQuotationLanguage(LANGUAGE);
        QuotationClassificationPage quotationClassificationPage = new QuotationClassificationPage(driver);
        quotationClassificationPage.pressCreateQuotationButton();
        assertTrue(new Toasts(driver).isQuotationSavedSuccessfullyToatstrDisplayed());
    }

    @Test(priority = 3)
    public void shouldAddProducts() throws InterruptedException {
        ProductsAndPricesPage productsAndPricesPage = new ProductsAndPricesPage(driver);
        productsAndPricesPage.addProductFromLvDrive(LV_DRIVE_PRODUCT_WITH_VC);
        ProductLine productLine = new ProductLine(driver);
        productLine.setApplication(1, LV_DRIVE_APPLICATION);
    }

    @Test(priority = 4)
    public void shouldStartApproval() throws InterruptedException {
        QuotationNavigationBar quotationNavigationBar = new QuotationNavigationBar(driver);
        DocumentGenerationSection documentGenerationSection = new DocumentGenerationSection(driver);
        quotationNavigationBar.goToFullCostAndFinalizationTab();
        ApprovalRequestPage approvalRequestPage = new ApprovalRequestPage(driver);
        documentGenerationSection.pressStartApprovalHyperlink();
        approvalRequestPage.startApprovalForDefaultUsers();
        quotationNumber = approvalRequestPage.getQuotationNumber();


        LoginPage loginPage = new LoginPage(driver);
        approvalRequestPage.pressLogoutHyperlink();
        LsuDashboard lsuDashboard= new LsuDashboard(driver);
        loginPage.logInToCqp(APPROVER_1, PASSWORD);
        lsuDashboard.openQuotationFromQuickSearch(quotationNumber);
        quotationNavigationBar.goToApprovalTab();
        approvalRequestPage.approveQuotation(APPROVAL_MESSAGE);

        approvalRequestPage.pressLogoutHyperlink();
        loginPage.logInToCqp(APPROVER_2, PASSWORD);
        lsuDashboard.openQuotationFromQuickSearch(quotationNumber);
        quotationNavigationBar.goToApprovalTab();
        approvalRequestPage.approveQuotation(APPROVAL_MESSAGE);
        Thread.sleep(10000);
    }

    @AfterTest
    public void after() {
        driver.close();
        driver.quit();

    }

}


