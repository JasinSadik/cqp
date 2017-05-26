import baseScenarios.BaseScenario;
import com.microsoft.sqlserver.jdbc.SQLServerMetaData;
import common.CommonMethods;
import common.SqlMethods;
import org.apache.xalan.lib.sql.SQLDocument;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.mainPages.TopMenu;
import pageObjects.popUpWindows.confirmationPopUp.SfdcSyncConfirmationModal;
import pageObjects.quotationTabs.ApprovalRequestPage;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.fullCostAndFinalizationPage.DocumentGenerationSection;
import pageObjects.quotationTabs.productsAndPricesPage.ProductLine;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesCommonActionButtons;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.GeneralSection;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationCommonActionButtonsSection;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2016-12-23.
 */
public class PositiveApprovalForNewQuotationTest extends BaseScenario {
    protected PositiveApprovalForNewQuotationTest() throws Exception {
    }

    private final String PROJECT_NAME = getClass().getName();
    private final String APPROVER_1 = "Robert Larsson";
    private final String APPROVER_2 = "Adrian Wasielewski";
    private String quotationNumber = "";
    private final String APPROVAL_MESSAGE = "OK";

    @BeforeTest
    public void before() throws Exception {
        driver = new CommonMethods(driver).browserSetup();
        driver.get(new CommonMethods(driver).getPropertyFromConfigurationFile("environment_url"));
        new SqlMethods(driver).BindingGeneralApprovalSetBlocking(LSU);
        new SqlMethods(driver).NonBindingGeneralApprovalSetBlocking(LSU);
    }

    @Test(priority = 1)
    public void shouldLogIntoToCqp() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInToCqp(USERNAME, PASSWORD);
        new TopMenu(driver).pressLogoutHyperlink();
        loginPage.logInToCqp(USERNAME, PASSWORD);
        assertTrue(true);
    }


    @Test(priority = 2)
    public void shouldCreateQuotation() {
        LsuDashboard lsuDashboard = new LsuDashboard(driver);
        CustomerDataSection customerDataSection = lsuDashboard.pressNewQuotationButton();
        customerDataSection.selectCustomerFromSearch(CUSTOMER);
        customerDataSection.pressTodayRfqButton();
        customerDataSection.setIndustryUsageLevelOne(INUDSTRY_USAGE_LEVEL1);
        customerDataSection.setIndustryUsageLevelTwo(INUDSTRY_USAGE_LEVEL2);
        GeneralSection generalSection = customerDataSection.pressSaveAndCollapseButton();
        generalSection.insertProjectName(PROJECT_NAME);
        generalSection.setQuotationType(QUOTATION_TYPE);
        AdditionalDataSection additionalDataSection = generalSection.pressSaveAndCollapseButton(AdditionalDataSection.class);
        additionalDataSection.setQuotationLanguage(LANGUAGE);
        QuotationClassificationCommonActionButtonsSection quotationClassificationCommonActionButtonsSection = new QuotationClassificationCommonActionButtonsSection(driver);
        SfdcSyncConfirmationModal sfdcSyncConfirmationModal = quotationClassificationCommonActionButtonsSection.pressCreateQuotationButton();
        sfdcSyncConfirmationModal.pressConfirmButton();

    }

    @Test(priority = 3)
    public void shouldAddProducts() throws InterruptedException {
        ProductsAndPricesCommonActionButtons productsAndPricesCommonActionButtons = new ProductsAndPricesCommonActionButtons(driver);
        productsAndPricesCommonActionButtons.addProductFromLvDrive(LV_DRIVE_PRODUCT_WITH_VC);
        ProductLine productLine = new ProductLine(driver);
        productLine.setApplication(1, LV_DRIVE_APPLICATION);
    }

    @Test(priority = 4)
    public void shouldStartApproval() throws InterruptedException {
        QuotationNavigationBar quotationNavigationBar = new QuotationNavigationBar(driver);
        DocumentGenerationSection documentGenerationSection = quotationNavigationBar.goToFullCostAndFinalizationTab();
        ApprovalRequestPage approvalRequestPage = documentGenerationSection.pressStartApprovalHyperlink();
        approvalRequestPage.startApprovalForDefaultUsers();
        quotationNumber = approvalRequestPage.getQuotationNumber();


        LoginPage loginPage = approvalRequestPage.pressLogoutHyperlink();
        LsuDashboard lsuDashboard= loginPage.logInToCqp(APPROVER_1, PASSWORD);
        quotationNavigationBar =  lsuDashboard.openQuotationFromQuickSearch(quotationNumber);
        approvalRequestPage = quotationNavigationBar.goToApprovalTab();
        approvalRequestPage.approveQuotation(APPROVAL_MESSAGE);

        approvalRequestPage.pressLogoutHyperlink();
        lsuDashboard= loginPage.logInToCqp(APPROVER_2, PASSWORD);
        quotationNavigationBar =  lsuDashboard.openQuotationFromQuickSearch(quotationNumber);
        approvalRequestPage = quotationNavigationBar.goToApprovalTab();
        approvalRequestPage.approveQuotation(APPROVAL_MESSAGE);


        Thread.sleep(10000);
    }

    @AfterTest
    public void after() {
        driver.close();
        driver.quit();

    }

}


