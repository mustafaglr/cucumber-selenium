package test.featurefiles;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefiniton {
    WebDriver driver;
    Actions actions;
    java.util.List<Map<String, String>> list;
    WebDriverWait wait;
    JavascriptExecutor je;
    TakesScreenshot scrShot;
    File scrFile;
    Select select;
    Process p;
    int a=0;
    String date;
    
    
    @Given("^I navigate to login \"(.*)\" page$")
    public void navigate_login_page(String page) throws Throwable{
	
	//get date
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
	date = simpleDateFormat.format(new Date());
	
	//create file to save images
	p = Runtime.getRuntime().exec("mkdir ./images");
	p.waitFor();
	p = Runtime.getRuntime().exec("mkdir ./images/"+date);
	p.waitFor();
		
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920x1080");
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        
		driver.get(page);
		
    }
	
	
    @When("^I submit username and password$")
    public void login() throws Throwable{
    	driver.findElement(By.id("username")).sendKeys("crmop.user");
    	driver.findElement(By.id("password")).sendKeys("crmop");
    	take_screenshot();
    	driver.findElement(By.name("login")).click();
    }
    
    @Then("^I should be logged in$")
    public void check_login() throws Throwable{
    	
    }
	
	
	
	
    @Given("^I navigate to \"(.*)\" page$")
    public void navigate_page(String page){

    	try {
    		//Initialize
    		driver.get(page);
    		actions=new Actions(driver);
    		wait = new WebDriverWait(driver,10);
    		je = (JavascriptExecutor) driver;
    		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    	} catch (Exception e) {
		throw new RuntimeException("Exception while filling " + page, e);
	}
		
		
	}

	@When("^I fill textboxes$")
	public void fill_textbox(Map<String, String> dataMap) throws Throwable {
	    for (Map.Entry<String, String> item : dataMap.entrySet()) {
	        try {
	            WebElement element = null;
	            if (driver.findElements(By.id(item.getKey())).size() != 0) {
	                element = driver.findElement(By.id(item.getKey()));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(item.getKey())));
	            }
	            else if (driver.findElements(By.xpath(item.getKey())).size() != 0) {
	                element = driver.findElement(By.xpath(item.getKey()));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item.getKey())));
	            }
	            else if (driver.findElements(By.name(item.getKey())).size() != 0) {
	                element = driver.findElement(By.cssSelector(item.getKey()));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(item.getKey())));
	            }
	            
	          //Code to do
	            element.clear();
	            element.sendKeys(item.getValue());
	            
	            
	        } catch (Exception e) {
	            throw new RuntimeException("Exception while filling " + item.getKey(), e);
	        }
	    }
	}
	
	@And("^I fill comboboxes$")
	public void fill_combobox(Map<String, String> dataMap) throws Throwable  {
	    for (Map.Entry<String, String> item : dataMap.entrySet()) {
	        try {
	            WebElement element = null;
	            if (driver.findElements(By.id(item.getKey())).size() != 0) {
	                element = driver.findElement(By.id(item.getKey()));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(item.getKey())));
	            }
	            else if (driver.findElements(By.xpath(item.getKey())).size() != 0) {
	                element = driver.findElement(By.xpath(item.getKey()));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item.getKey())));
	            }
	            else if (driver.findElements(By.name(item.getKey())).size() != 0) {
	                element = driver.findElement(By.cssSelector(item.getKey()));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(item.getKey())));
	            }
	            
	          //Code to do
	            select = new Select(element);
	            select.selectByValue(item.getValue());
	            
	        } catch (Exception e) {
	            throw new RuntimeException("Exception while filling " + item.getKey(), e);
	        }
	    }
	}

	
	
	@And("^I click checkboxes$")
	public void fill_checkbox(List<String> dataList) throws Throwable  {
	    for (String item : dataList) {
	        try {
	            WebElement element = null;
	            if (driver.findElements(By.id(item)).size() != 0) {
	                element = driver.findElement(By.id(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(item)));
	            }
	            else if (driver.findElements(By.xpath(item)).size() != 0) {
	                element = driver.findElement(By.xpath(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item)));
	            }
	            else if (driver.findElements(By.name(item)).size() != 0) {
	                element = driver.findElement(By.cssSelector(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(item)));
	            }
	            
	          //Code to do
	            actions.moveToElement(element).click().perform();
	            
	            
	        } catch (Exception e) {
	            throw new RuntimeException("Exception while filling " + item, e);
	        }
	    }
	}

	@And("^I click buttons$")
	public void click_button(List<String> dataList) throws Throwable  {
    	take_screenshot();
	    for (String item : dataList) {
	        try {
	            WebElement element = null;
	            if (driver.findElements(By.id(item)).size() != 0) {
	                element = driver.findElement(By.id(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(item)));
	            }
	            else if (driver.findElements(By.xpath(item)).size() != 0) {
	                element = driver.findElement(By.xpath(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item)));
	            }
	            else if (driver.findElements(By.name(item)).size() != 0) {
	                element = driver.findElement(By.cssSelector(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(item)));
	            }
	            
	          //Code to do
	            actions.moveToElement(element).click().perform();
	            
	            
	        } catch (Exception e) {
	            throw new RuntimeException("Exception while filling " + item, e);
	        }
	    }
	}
	@And("^I mouseover to$")
	public void mouseover(List<String> dataList) throws Throwable  {
    	take_screenshot();
	    for (String item : dataList) {
	        try {
	            WebElement element = null;
	            if (driver.findElements(By.id(item)).size() != 0) {
	                element = driver.findElement(By.id(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(item)));
	            }
	            else if (driver.findElements(By.xpath(item)).size() != 0) {
	                element = driver.findElement(By.xpath(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item)));
	            }
	            else if (driver.findElements(By.name(item)).size() != 0) {
	                element = driver.findElement(By.cssSelector(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(item)));
	            }
	            
	          //Code to do
	            actions.moveToElement(element).build().perform();
	            
	            
	        } catch (Exception e) {
	            throw new RuntimeException("Exception while filling " + item, e);
	        }
	    }
	}
	
	@And("^I scroll down$")
	public void scroll_down(List<String> dataList) throws Throwable  {
	    for (String item : dataList) {
	        try {
	            WebElement element = null;
	            if (driver.findElements(By.id(item)).size() != 0) {
	                element = driver.findElement(By.id(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(item)));
	            }
	            else if (driver.findElements(By.xpath(item)).size() != 0) {
	                element = driver.findElement(By.xpath(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item)));
	            }
	            else if (driver.findElements(By.name(item)).size() != 0) {
	                element = driver.findElement(By.cssSelector(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(item)));
	            }
	            
	          //Code to do
	            je.executeScript("arguments[0].scrollIntoView(true);",element);
	            
	            
	        } catch (Exception e) {
	            throw new RuntimeException("Exception while filling " + item, e);
	        }
	    }
	}
	@And("^I click radioboxes$")
	public void click_radiobox(List<String> dataList) throws Throwable  {
	    for (String item : dataList) {
	        try {
	            WebElement element = null;
	            if (driver.findElements(By.id(item)).size() != 0) {
	                element = driver.findElement(By.id(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(item)));
	            }
	            else if (driver.findElements(By.xpath(item)).size() != 0) {
	                element = driver.findElement(By.xpath(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item)));
	            }
	            else if (driver.findElements(By.name(item)).size() != 0) {
	                element = driver.findElement(By.cssSelector(item));
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(item)));
	            }
	            
	          //Code to do
	            actions.moveToElement(element).click().perform();
	            
	            
	        } catch (Exception e) {
	            throw new RuntimeException("Exception while filling " + item, e);
	        }
	    }
	}	

	
    @And("^I take screenshots$")
    public void take_screenshot() throws IOException {
    	
    	scrShot = ((TakesScreenshot)driver);
    	scrFile=scrShot.getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(scrFile, new File("./images/"+date+"/"+a+".png"));
    	a++;
    }
	
    @And("^I wait for$")
    public void wait_for(List<String> dataList) throws IOException {
		wait = new WebDriverWait(driver,15);	
	    for (String item : dataList) {
	        try {
	        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(item)));
	        } catch (Exception e) {
	            throw new RuntimeException("Exception while filling " + item, e);
	        }
	    }
    }
	
    @AfterTest
    public void afterTest() throws InterruptedException{
        try {
        	
    	    Thread.sleep(5);
            driver.quit();
	    p = Runtime.getRuntime().exec("cp -r ./target/cucumber/  ./images/"+date+"/");
	    p.waitFor();
	    System.out.println("Done!");
	        
	} catch (IOException e) {
    	    e.printStackTrace();
	}
		
    }
}
