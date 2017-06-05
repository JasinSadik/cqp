package testsInUse;

import common.CommonMethods;
import common.sqlMethods.Sql_UserPreferences;
import org.testng.annotations.*;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.mainPages.TopMenu;
import scenarios.BaseScenario;

import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2017-06-01.
 */
public class ChangeLsuTest extends BaseScenario {

    protected ChangeLsuTest() throws Exception {
    }

    @BeforeTest
    public void before() throws Exception {
        driver = new CommonMethods(driver).browserSetup();
        driver.get(new CommonMethods(driver).getPropertyFromConfigurationFile("environment_url"));
    }


    @Test(priority = 1)
    public void shouldLogInToCqp() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        LsuDashboard lsuDashboard = loginPage.logInToCqp(USERNAME, PASSWORD);
        assertTrue(lsuDashboard.getCurrentlyLoggedUser().contains(USERNAME));
        assertTrue(lsuDashboard.getCurrentlyLoggedUsersLsu().contains( new Sql_UserPreferences(driver).getDefaultUnit(USERNAME)));
    }


    @Test(priority = 2)
    public void shouldChangeLsu() throws InterruptedException {
        TopMenu topMenu = new TopMenu(driver);
        topMenu.changeLsu("SUSweden");
        assertTrue(topMenu.getCurrentlyLoggedUsersLsu().contains("SUSweden"));
    }

    @Test(priority = 3)
    public void shouldChangeLsuToLsu() throws InterruptedException {
        TopMenu topMenu = new TopMenu(driver);
        topMenu.changeLsu("SUESMOT");
        assertTrue(topMenu.getCurrentlyLoggedUsersLsu().contains("SUESMOT"));
    }

    @Test(priority = 4)
    public void shouldChangeLsuToPu() throws InterruptedException {
        TopMenu topMenu = new TopMenu(driver);
        topMenu.changeLsu("PUFIDRI");
        assertTrue(topMenu.getCurrentlyLoggedUsersLsu().contains("PUFIDRI"));
    }

       @AfterTest
    public void after(){
        driver.close();
        driver.quit();

    }


}
