package common.blackBox;

import common.CommonMethods;
import org.openqa.selenium.WebDriver;

/*

 */
public class BlackBox extends CommonMethods {


    public BlackBox(WebDriver driver) {
        super(driver);
    }


    private void checkOnError() {
       /*
       verifyIfErrorOccured (getErrorList)
          if(true)
             searchForKnownWorkaround (getErrorId)
                if found -> use / validate expected condition of userd workaround(verify if next action was performed)
                 else -> use default
                 if not helped -> mark as blocked due to issue

         getErrorList when exception occured -> add to list and match exception
         if match not possibile Log as new Error.

         incydentLogger -> store exception


        */
        //getRedToastrException
        //getOrangeToastrInformer
        // getConsoleErrors
        // getSystemException

        //true -> lunch verifier
        //incydent -> log







    }



}
