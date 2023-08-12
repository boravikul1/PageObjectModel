package com.vikul.com.testautomation.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikul.com.testautomation.PageObjectModel.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	@SuppressWarnings("static-access")
	public static Logger log = new org.apache.log4j.LogManager().getLogger(BaseTest.class);
	//public ExtentReports extent = ExtentReporterNG.getReportObject();
    //public static ExtentTest test;
	
	public WebDriver initializeDriver() throws IOException
	{
	
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//vikul//com//testautomation//Resources//GlobalProperties.properties");
		Properties prop = new Properties();
		prop.load(fis);
		log.info("WebDriver driver initialized.");
		
		
		//String browserName = prop.getProperty("browser");
		
		//Java ternary operator to use System property from Maven. In case the property in null use the property set in Properties file
		String browserName = System.getProperty("browser") !=null ? System.getProperty("browser"): prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions coptions = new ChromeOptions();
			coptions.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(coptions);
			log.info("Browser is selected as Chrome");
		}
		else if(browserName.equalsIgnoreCase("chromeheadless"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions coptions = new ChromeOptions();
			coptions.addArguments("--headless");
			coptions.addArguments("--start-maximized");
			coptions.addArguments("--window-size=1920,1080");
			coptions.addArguments("--remote-allow-origins=*");
			
			driver = new ChromeDriver(coptions);
			log.info("Browser is selected as Chrome Headless");
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().avoidBrowserDetection().setup();
			FirefoxOptions foptions = new FirefoxOptions();
			foptions.addArguments("--remote-allow-origins=*");
			foptions.addArguments("--remote-debugging-port=8888");
			driver = new FirefoxDriver(foptions);
			log.info("Browser is selected as FireFox");

//			System.setProperty("webdriver.gecko.driver", "C://drivers//geckodriver.exe");
//			driver = new FirefoxDriver();
			
			
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions eoptions = new EdgeOptions();
			eoptions.addArguments("--remote-allow-origins=*");
			eoptions.addArguments("--remote-debugging-port=8888");
			driver = new EdgeDriver(eoptions);
			log.info("Browser is selected as Microsoft Edge");
		}
		else
		{
			System.out.println("Browser is not set.");
		}


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		//Reading Json to String
		String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		
		//String to HashMap - Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		log.info("Converted Json into HashMap.");
		return data;
		
		}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		//driver.get("https://rahulshettyacademy.com/client");
		landingPage.goTo();
		log.info("Landing Page opened successfully.");
		return landingPage;
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
		driver.quit();
		log.info("Driver closed successfully");
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"//ScreenCapture//"+ testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		log.info("Screenshot captured.");
		return System.getProperty("user.dir")+"//ScreenCapture//"+ testCaseName + ".png";
	}

}
