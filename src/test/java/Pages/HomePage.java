package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    public HomePage(WebDriver driver){PageFactory.initElements(driver,this);}
    @FindBy(xpath = "//a[@accesskey='p']")
    public WebElement projects;
    @FindBy(xpath = "//a[@href='/browse/TEC' and .='TECHTORIAL (TEC)']")
    public WebElement currentProject;
    @FindBy(xpath = "//span[@class='aui-icon aui-icon-large agile-icon-plan aui-iconfont-backlog']")
    public WebElement backlog;
    @FindBy(xpath = "//div[@class='ghx-backlog-column']")
    public WebElement backLogBox;
//    @FindBy(xpath = "//div[@class='ghx-summary']")
//    public List<WebElement> issues;
    @FindBy(xpath = "//div[@class='ghx-summary']//span[@class='ghx-inner']")
    public List<WebElement> summaries;
    @FindBy(xpath = "//a[@class='js-key-link']")
    public List<WebElement> keys;

}
