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
    @FindBy(xpath = "//div[@class='ghx-issue-content']")
    public WebElement backLogBox;
//    @FindBy(xpath = "//div[@class='ghx-summary']")
//    public List<WebElement> issues;
    @FindBy(xpath = "//div[@class='ghx-summary']//span[@class='ghx-inner']")
    public List<WebElement> summaries;
    @FindBy(xpath = "//a[@class='js-key-link']")
    public List<WebElement> keys;
    @FindBy(xpath = "//a[@id='header-details-user-fullname']")
    public WebElement avatar;
    @FindBy(xpath = "//a[@id='log_out']")
    public WebElement log_out;
    @FindBy(xpath = "//div[@class='ghx-backlog-container ghx-open ghx-everything-else ui-droppable']" +
            "//div[@class='ghx-issues js-issue-list ghx-has-issues']" +
            "//div[contains(@class,'js-issue js-sortable js-parent-drag ghx-issue-compact ghx-type')]" +
            "//div//div//span[@class='ghx-inner']")
    public List<WebElement> stories;
    public void setLog_out(){
        this.avatar.click();
        this.log_out.click();
    }

}
