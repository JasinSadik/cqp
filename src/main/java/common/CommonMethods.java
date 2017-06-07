package common;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class CommonMethods extends Page {

    protected WebElement element;
    protected List<WebElement> elements;
    protected int loopGoThroughCounter = 0;


    public CommonMethods(WebDriver driver) {
        super(driver);
    }


    //..........................Waiters......................
    protected void waitOnButton(WebElement element) {
        waitOnElementToBeVisible(element);
        waitOnElementToBeClickable(element);
    }

    protected void waitOnButton(By by) {
        waitOnElementToBeClickable(by);
    }

    protected void waitOnPresenceOfElement(By by) {
        boolean elementStatus = true;
         while (elementStatus && loopGoThroughCounter < 30) {
            try {
                new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
                elementStatus = false;
                loopGoThroughCounter = 0;
            } catch (TimeoutException e) {
                System.out.println("Timeout - Element not found " + by.toString());
                loopGoThroughCounter++;
            }
        }
    }

    protected void waitOnElementToBeVisible(WebElement element) {
        boolean elementStatus = true;
        while (elementStatus && loopGoThroughCounter < 30) {
            try {
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
                elementStatus = false;
                loopGoThroughCounter = 0;
            } catch (TimeoutException e) {
                System.out.println("Timeout - Element not found");
                loopGoThroughCounter++;
            }
        }
    }

    protected void waitOnElementToBeVisible(By by) {
        boolean elementStatus = true;
        while (elementStatus && loopGoThroughCounter < 30) {
            try {
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(by));
                elementStatus = false;
                loopGoThroughCounter = 0;
            } catch (TimeoutException e) {
                System.out.println("Timeout - Element not found " + by.toString());
                loopGoThroughCounter++;

            }
        }
    }

    protected void waitOnElementToBeClickable(WebElement element) {
        boolean elementStatus = true;
        while (elementStatus && loopGoThroughCounter < 30) {
            try {
                new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
                elementStatus = false;
                loopGoThroughCounter = 0;
            } catch (TimeoutException e) {
                System.out.println("Timeout - Element not found");
                loopGoThroughCounter++;
            }
        }
    }

    protected void waitOnElementToBeClickable(By by) {
        boolean elementStatus = true;
        while (elementStatus && loopGoThroughCounter < 30) {
            try {
                new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(by));
                elementStatus = false;
                loopGoThroughCounter = 0;
            } catch (TimeoutException e) {
                System.out.println("Timeout - Element not found " + by.toString());
                loopGoThroughCounter++;
            }
        }
    }

    protected void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    //..........................SeleniumMethods......................
    protected WebElement findElement(By by) {
        boolean elementStatus = true;
        while (elementStatus && loopGoThroughCounter < 6) {
            try {
                element = driver.findElement(by);
                elementStatus = false;
            } catch (ElementNotFoundException e) {
                System.out.println("Timeout - Element not found " + by.toString());
                loopGoThroughCounter++;
                scrollToElement(by);
            }
        }
        return element;
    }

    protected List<WebElement> findElements(By by) {
        boolean elementStatus = true;
        while (elementStatus && loopGoThroughCounter < 6) {
            try {
                elements = driver.findElements(by);
                elementStatus = false;
            } catch (ElementNotFoundException e) {
                System.out.println("Timeout - Element not found " + by.toString());
                loopGoThroughCounter++;
                scrollToElement(by);
            }
        }
        return elements;
    }


    protected void sendKeys(By by, String text) {
        findElement(by).sendKeys(text);
    }

    protected void setAttribute(By by, String attributeName, String key) {
        boolean elementStatus = true;
        int i = 0;
        while (elementStatus && i < 3) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + key + "')", findElement(by));
                elementStatus = false;
            } catch (WebDriverException e) {
                System.out.println("Timeout - element not found: " + by.toString());
                i++;
            }
        }
    }

    protected void click(By by) {
        boolean elementStatus = true;
        int i = 0;
        while (elementStatus && i < 3) {
            try {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", findElement(by));
                elementStatus = false;
            } catch (WebDriverException e) {
                System.out.println("Timeout - element not found: " + by.toString());
                i++;
            }
        }
    }

    protected void clear(By by) {
        driver.findElement(by).clear();
    }

    protected String getText(By by) {
        return driver.findElement(by).getText();
    }

    //..........................ConfigParser......................
    public String getPropertyFromConfigurationFile(String key) throws Exception {
        boolean check = false;
        BufferedReader configFile = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "\\src\\main\\java\\common\\configuration.txt")));
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




    //..........................BrowserSetup....................
    public WebDriver browserSetup() throws Exception {
        String browser = getPropertyFromConfigurationFile("browser");
        int timeout = Integer.parseInt(getPropertyFromConfigurationFile("timeout"));
        switch (browser) {
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

    public void setTimeout(WebDriver driver, int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    //..........................Others......................
    protected void snapshot(WebDriver driver, String testName) throws Exception {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String newFileSrc = testName + "_" + timestamp() + ".jpg";

        FileUtils.copyFile(screenshot, new File(getPropertyFromConfigurationFile("snapshot_path") + newFileSrc));
        System.out.println("New screenshot was saved: " + newFileSrc);
    }

    public String timestamp() {
        java.util.Date date = new java.util.Date();
        String timestamp = (new Timestamp(date.getTime())).toString().substring(0, 19);
        return timestamp;
    }

    protected void selectElementFromDropdownList(String comboBoxId, String value) {
        int index = 1;
        By comboBoxButton = By.xpath("//input[contains(@id , '" + comboBoxId + "')]/..//span[contains(text(), 'select')]");
        By comboBoxList = By.xpath("//ul[contains(@id , '" + comboBoxId + "_listbox')]/li");
        By comboBoxElement = By.xpath("//ul[contains(@id , '" + comboBoxId + "_listbox')]/li[" + index + "]");
        waitOnButton(comboBoxButton);
        while (driver.findElements(comboBoxList).size() == 0) {
            // to fill up
        }
        click(comboBoxButton);
        while (driver.findElements(comboBoxList).size() > index - 1) {
            waitOnElementToBeClickable(driver.findElement(comboBoxElement));
            if (driver.findElement(comboBoxElement).getText().toLowerCase().contains(value.toLowerCase())) {
                click(comboBoxElement);
                break;
            } else {
                index++;
                comboBoxElement = By.xpath("//ul[contains(@id , '" + comboBoxId + "_listbox')]/li[" + index + "]");
            }
        }
    }

    protected void selectElementFromDropdownListByHtmlElement(String value) {
        selectElementFromDropdownListByHtmlElement(value, "li");
    }

    protected void selectElementFromDropdownListByHtmlElement(String value, String htmlElementType) {
        By by;
        by = By.xpath("//" + htmlElementType + "[text() = '" + value + "']");
        waitOnPresenceOfElement(by);
        click(by);
    }


    protected String[] getElementsFromDropdownList(String id){
        elements =  findElements(By.id(id));
        String[] elementsToStringTable = null;
        int indexOfElementsCount = 0;
        for (WebElement e : elements) {
            elementsToStringTable[indexOfElementsCount] = e.getText().toString();
            indexOfElementsCount++;
        }
        return elementsToStringTable;
    }

    protected void scrollToElement(By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", findElement(by));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void moveToElement(By by){
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(by)).build().perform();
    }

}
