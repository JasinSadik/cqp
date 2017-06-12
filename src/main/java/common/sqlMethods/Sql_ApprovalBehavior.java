package common.sqlMethods;

import org.openqa.selenium.WebDriver;

/**
 * Created by PLJAHAS on 2017-05-26.
 */
public class Sql_ApprovalBehavior extends SqlMethods {
    public Sql_ApprovalBehavior(WebDriver driver) {
        super(driver);
    }

    public void BindingGeneralApprovalSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalBinding = 0 WHERE LsuID = '" + Lsu + "'");
    }


    public void BindingGeneralApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void BindingGeneralApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }


    public void BindingCurrencyApprovalSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyBinding = 0 WHERE LsuID = ''" + Lsu + "'" + "'");
    }

    public void BindingCurrencyApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void BindingCurrencyApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }

    public void BindingQualificationTestApprovalSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestBinding = 0 WHERE LsuID = '" + Lsu + "'");
    }

    public void BindingQualificationTestApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void BindingQualificationTestApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }


    public void NonBindingGeneralApprovalSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalNoBinding = 0 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingGeneralApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalNoBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingGeneralApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalNoBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }


    public void NonBindingCurrencyApprovalsSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyNoBinding = 0 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingCurrencyApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyNoBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingCurrencyApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyNoBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingQualificationTestApprovalSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestNoBinding = 0 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingQualificationTestApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestNoBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingQualificationTestApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestNoBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }


}
