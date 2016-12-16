import org.testng.annotations.Test;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;

import static org.testng.Assert.assertEquals;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class LoginTest extends BaseScenario{


    protected LoginTest() throws Exception {
    }

    @Test
    public void ShouldLogInToCqp(){
        LoginPage loginPage = new LoginPage(driver);
        LsuDashboard lsuDashboard = loginPage.logInToCqp(USERNAME, PASSWORD);
        assertEquals("","");
    }


}
