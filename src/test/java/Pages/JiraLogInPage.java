package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiraLogInPage {
    public JiraLogInPage(WebDriver driver){PageFactory.initElements(driver,this);}
    @FindBy(xpath = "//input[@name='os_username']")
    public WebElement username;
    @FindBy(xpath = "//input[@name='os_password']")
    public WebElement password;
    @FindBy(xpath = "//input[@name='login']")
    public WebElement login;

    public void getLoggedIn(String username,String password){
       this.username.sendKeys(username);
       this.password.sendKeys(password);
       this.login.click();
    }
}
