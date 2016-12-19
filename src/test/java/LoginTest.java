import common.CommonMethods;
import org.testng.annotations.*;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.mainPages.UnauthorizedAccessPage;

import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class LoginTest extends BaseScenario {


    protected LoginTest() throws Exception {
    }

    @Test(priority = 1)
    public void shouldLogInToCqp() {
        LoginPage loginPage = new LoginPage(driver);
        LsuDashboard lsuDashboard = loginPage.logInToCqp(USERNAME, PASSWORD);
        assertTrue(lsuDashboard.getCurrentlyLoggedUser().contains(USERNAME));
    }

    @BeforeMethod
    public void before() throws Exception {
        driver = new CommonMethods(driver).browserSetup();
        driver.get(new CommonMethods(driver).getPropertyFromConfigurationFile("environment_url"));
    }



    @Test(priority = 2)
    public void shouldShowUnauthorizedMessageLogInWithEmptyFields() {
        LoginPage loginPage = new LoginPage(driver);
        UnauthorizedAccessPage unauthorizedAccessPage = loginPage.invalidLogInToCqp("", "");
        assertTrue(unauthorizedAccessPage.checkUnauthorizedAccessMessageShown());
    }

    @Test(priority = 3)
    public void shouldShowUnauthorizedMessageLogInWithWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        UnauthorizedAccessPage unauthorizedAccessPage = loginPage.invalidLogInToCqp("urban.boken@se.abb.com", "");
        assertTrue(unauthorizedAccessPage.checkUnauthorizedAccessMessageShown());
    }

    @Test(priority = 4)
    public void shouldShowUnauthorizedMessageLogInWithWrongUsername() {
        LoginPage loginPage = new LoginPage(driver);
        UnauthorizedAccessPage unauthorizedAccessPage = loginPage.invalidLogInToCqp("", "a");
        assertTrue(unauthorizedAccessPage.checkUnauthorizedAccessMessageShown());
    }

    @AfterMethod
    public void after(){
        driver.close();
        driver.quit();

    }

}
