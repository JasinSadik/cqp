package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class CommonMethods extends Page {

    protected WebElement element;

    public CommonMethods(WebDriver driver) {
        super(driver);
    }



    //..........................Waiters......................
    public  void waitOnButton(WebElement element){
        waitOnElementToBeVisible(element);
        waitOnElementToBeClickable(element);
    }

    public  void waitOnButton(By by){
        waitOnElementToBeVisible(by);
        waitOnElementToBeClickable(by);
    }

    public  void waitOnPresenceOfElement(By by){
        try{
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
        }catch(TimeoutException e){
            System.out.print("Timeout - Element not found");
        }
    }

    public  void waitOnElementToBeVisible(WebElement element){
        try{
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
        }catch(TimeoutException e){
            System.out.print("Timeout - Element not found");
        }
    }

    public  void waitOnElementToBeVisible(By by){
        try{
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch(TimeoutException e){
            System.out.print("Timeout - Element not found");
        }
    }

    public  void waitOnElementToBeClickable(WebElement element){
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
        }catch(TimeoutException e){
            System.out.print("Timeout - Element not found");
        }
    }

    public  void waitOnElementToBeClickable(By by){
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(by));
        }catch(TimeoutException e){
            System.out.print("Timeout - Element not found");
        }
    }



    //..........................SeleniumMethods......................
    public  WebElement findElement(WebElement element) {
        waitOnElementToBeVisible(element);
        return  element;
    }

    public  void sendKeys(WebDriver driver, WebElement element, String key) {
        sendKeys(driver, element, key, true);
    }

    public  void sendKeys(WebDriver driver, WebElement element, String key, boolean clearTextbox){
        boolean elementStatus = true;
        while(elementStatus == true){
            try{
                if (clearTextbox == true) {
                    element.clear();
                }
                element.sendKeys(key);
                elementStatus = false;
            }catch(WebDriverException e){
                elementStatus = true;
            }
        }
        System.out.print(timestamp()+" --> ");
        element = null;
    }

    public  void setAttribute(WebDriver driver, WebElement element, String attributeName, String key){
        boolean elementStatus = true;
        while(elementStatus == true){
            try{
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].setAttribute('+'attributeName'+', '"+key+"')",element);
                elementStatus = false;
            }catch(WebDriverException e){
                elementStatus = true;
            }
        }
        System.out.print(timestamp()+" --> ");
        element = null;
    }

    public  void click(WebDriver driver, WebElement element){
        boolean elementStatus = true;
        while(elementStatus == true){
            try{
                new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
                JavascriptExecutor executor = (JavascriptExecutor)driver;
                executor.executeScript("arguments[0].click();", element);
                elementStatus = false;
            }catch(WebDriverException e){
                System.out.println("Waiting on clickable element...");
            }
        }
        System.out.print(timestamp()+" --> ");
        element = null;
    }

    public  void clear(WebDriver driver, WebElement element){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element = null;
    }

    public  String getText(WebElement element){
        String text = element.getText();
        element = null;
        return text;
    }
    //..........................ConfigParser......................
    public String getPropertyFromConfigurationFile(String key) throws Exception {
        boolean check = false;
        BufferedReader configFile = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "\\src\\main\\java\\common\\configuration")));
        while (!check) {
            String[] line = configFile.readLine().split(":");
            if (line[0].contains(key)) {
                key = "";
                for (int i = 1; i < line.length; i++) {
                    key = (line.length - 1 == i) ? key + line[i].trim() : key + line[i].trim() + ":";
                }
                configFile.close();
                break;
            }
        }
        return key;
    }

    //.........................SqlMethods.......................

    public Statement initDB () throws Exception {
        Class.forName(getPropertyFromConfigurationFile("class_db"));
        Connection conn;
        conn = DriverManager.getConnection(getPropertyFromConfigurationFile("db_url"), "", "");
        Statement stmt = conn.createStatement();
        return stmt;
    }

    public  void updateDB(String table, String columnNameLocation, String valueLocation, String columnName, String value, String position) throws Exception {
        Statement stmt = initDB();
        String environment = getPropertyFromConfigurationFile("environment");
        stmt.executeUpdate("UPDATE ["+environment+"].[dbo].["+table+"] SET "+columnName+"=NULL WHERE "+columnNameLocation+"='"+valueLocation+"' AND Position = "+"'"+position+"'" );

    }

    public ResultSet readDB(String table, String columnNameLocation, String valueLocation, String columnName, String position) throws Exception {
        ResultSet rs;
        Statement stmt = initDB();
        String environment = getPropertyFromConfigurationFile("environment");
        rs = stmt.executeQuery("SELECT ["+columnName+"] FROM ["+environment+"].[dbo].["+table+"] WHERE "+columnNameLocation+"='"+valueLocation+"'" );
        return rs;
    }

    //..........................BrowserSetup....................
    public WebDriver browserSetup() throws Exception{
        String browser= getPropertyFromConfigurationFile("browser");
        int timeout= Integer.parseInt(getPropertyFromConfigurationFile("timeout"));
        switch (browser){
            case "IE":
                driver = new InternetExplorerDriver();
                driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                break;
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", getPropertyFromConfigurationFile("chrome_path"));
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
                break;
        }
        return driver;
    }

    public void setTimout(WebDriver driver, int timeout){
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    //..........................Others......................
    public  void snapshot(WebDriver driver, String testName) throws Exception{
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String newFileSrc = testName+"_"+timestamp()+".jpg";

        FileUtils.copyFile(screenshot,new File(getPropertyFromConfigurationFile("snapshot_path") + newFileSrc));
        System.out.println("New screenshot was saved: " + newFileSrc);
    }

    public  String timestamp(){
        java.util.Date date= new java.util.Date();
        String timestamp = (new Timestamp(date.getTime())).toString().substring(0, 19);
        return timestamp;
    }

}
