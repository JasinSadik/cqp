package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.sql.Timestamp;

/**
 * Created by PLJAHAS on 2016-12-15.
 */
public abstract class Page{

    protected WebDriver driver;
    public Page(WebDriver driver){
        this.driver = driver;
    }



}
