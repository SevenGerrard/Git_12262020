package libraries;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class P3Library extends P3Base {
	final Logger logger = Logger.getLogger(P3Library.class);
	private WebDriver D;
	private boolean isDemoMode = false;


	public void setDemoMode(boolean isDemoMode) {
		this.isDemoMode = isDemoMode;
	}

	enum Browser{
		CHROME, FIREFOX, EDGE
	}

	public P3Library() {

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver _driver) {
		try {
			if (_driver != null) {
				this.driver = _driver;
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public WebDriver startBrowser(Browser browser) {
		try {
			switch(browser) {
			case CHROME:
				driver = startChromeBrowser();
				break;

			case FIREFOX:
				driver = startFirefoxBrowser();
				break;

			case EDGE:
				driver = startEdgeBrowser();
				break;

			}

		}catch (Exception e) {
			logger.error("Error: ", e);
		}
		return driver;

	}


	public WebDriver startChromeBrowser() {
		String path = "/Users/pahriya/Desktop/EclipseWorkPlace/com.group.projectiii/src/test/resources/drivers/chromedriver";

		try {
			System.setProperty("webdriver.chrome.driver", path);

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			logger.debug("Maximize the browser window");

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return driver;
	}

	public WebDriver startFirefoxBrowser() {
		String path = "/Users/pahriya/Desktop/EclipseWorkPlace/com.group.projectiii/src/test/resources/drivers/gekodriver";

		try {
			System.setProperty("webdriver.firefox.driver", path);

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			logger.debug("Maximize the browser window");

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return driver;
	}

	public WebDriver startEdgeBrowser() {
		String path = "/Users/pahriya/Desktop/EclipseWorkPlace/com.group.projectiii/src/test/resources/drivers/msedgedriver";

		try {
			System.setProperty("webdriver.edge.driver", path);

			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			logger.debug("Maximize the browser window");

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return driver;
	}


	public void scrolldown(int pix) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(0," + pix + ")");
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void customWait(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public WebElement waitUntilElementVisibility(By by) {
		WebElement dynamicElem = null;
		try {
			dynamicElem = (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(by));

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return dynamicElem;
	}

	public WebElement waitUntilElementPresence(By by) {
		WebElement dynamicElemPresence = null;
		try {
			dynamicElemPresence = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return dynamicElemPresence;
	}

	public WebElement waitUntilElementClickable(By by) {
		WebElement dynamicElemClickable = null;
		try {
			dynamicElemClickable = (new WebDriverWait(driver,8)).until(ExpectedConditions.elementToBeClickable(by));
			dynamicElemClickable.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return dynamicElemClickable;
	}

	public void highLightElem(WebElement elem) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", elem,
					"color: red; border: 2px solid yellow;");

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void hoveroverElem(By by) {
		try {
			Actions action = new Actions(driver);
			WebElement elem = driver.findElement(by);
			action.moveToElement(elem).build().perform();
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void ClickElement(By by) {
		try {
			WebElement elem = driver.findElement(by);
			elem.click();
			highLightElem(elem);
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void EnterTxt(By by, String str) {
		try {
			WebElement elem = driver.findElement(by);
			highLightElem(elem);
			elem.clear();
			elem.sendKeys(str);
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void dragAndDropBtn(By by, int Pixel) {
		try {
			WebElement btn = driver.findElement(by);
			//highLightElem(btn);
			Actions act = new Actions(driver);
			act.dragAndDropBy(btn, -40, 0).build().perform();

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void selectDropDown(By by, String optionValue) {
		try {
			WebElement elem = driver.findElement(by);
			Select dropdown = new Select(elem);
			dropdown.selectByVisibleText(optionValue);
		}catch(Exception e) {
			logger.error("Error: ", e);
		}

	}

}
