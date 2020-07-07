package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    //Singleton Design Pattern

    private static WebDriver driver;//instance variable

    private Driver() {}//constructor


    public static WebDriver getDriver() {//method
        if(driver==null) {
            switch(ConfigReader.getProperty("driverName")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                case "internet explorer":
                    WebDriverManager.iedriver().setup();
                    driver=new InternetExplorerDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver=new EdgeDriver();
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    driver=new OperaDriver();
                    break;
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

}
