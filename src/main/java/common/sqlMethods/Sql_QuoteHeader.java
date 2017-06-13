package common.sqlMethods;

import org.openqa.selenium.WebDriver;

/**
 * Created by PLJAHAS on 2017-06-13.
 */
public class Sql_QuoteHeader extends SqlMethods {
    public Sql_QuoteHeader(WebDriver driver) {
        super(driver);
    }


    public boolean getIsQuotationDeleted(String quotationNumber) throws Exception {
        String isDeleted = readDB("QuoteHeader", "isDelete", "WHERE QuotationNumber = '" + quotationNumber + "'");
        if(isDeleted.equals("0") || isDeleted ==null){
            return false;
        }else{
            return true;
        }


    }


}
