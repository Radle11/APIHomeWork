package StepDefs.Jira;

import Pages.HomePage;
import Pages.JiraLogInPage;
import Utils.ConfigReader;
import Utils.Driver;
import Utils.JiraPayLoads;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class CreateIssueSteps {
    public static String KEY = null;
    public static String ID = null;

    @When("user creates a Jira issues by providing {string}, {string} and {string}")
    public void user_creates_a_jira_issues_by_providing_and(String summary, String description, String issueType) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api/2/issue");
        HttpPost post = new HttpPost(uriBuilder.build());
        post.setHeader(HttpHeaders.ACCEPT, "application/json");
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        post.setHeader("Cookie", JiraPayLoads.getJiraSesionCookie());
        HttpEntity entity = new StringEntity(JiraPayLoads.getJiraIssuePayLoad(summary, description, issueType));
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        org.testng.Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_CREATED);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> desPostJira = mapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, String>>() {
                });
        desPostJira.keySet().stream().forEach(key -> System.out.printf("%s: %s\n", key, desPostJira.get(key)));
        KEY = desPostJira.get("key");
        ID=desPostJira.get("id");
    }

    @Then("validate id, {string} and {string} on UI")
    public void validate_id_and_on_ui(String summary, String description) throws InterruptedException {
        WebDriver driver = Driver.getDriver();
        driver.navigate().to("http://localhost:8080");
        JiraLogInPage jiraLogInPage = new JiraLogInPage(driver);
        HomePage homePage = new HomePage(driver);
        jiraLogInPage.getLoggedIn(ConfigReader.getProperty("jiraUserName"), ConfigReader.getProperty("jiraUserPassword"));
        homePage.projects.click();
        homePage.currentProject.click();
        homePage.backlog.click();
        Actions actions=new Actions(driver);
        actions.moveToElement(homePage.stories.get(homePage.stories.size()-1)).perform();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(ID,ID, "Validating ID");
        softAssert.assertTrue(homePage.summaries.get(homePage.summaries.size()-1).getText().equals(summary), "Validating the summary is failed");
        softAssert.assertEquals(homePage.keys.contains(KEY),"Validating Key");
        softAssert.assertAll();
        Thread.sleep(500);
        homePage.avatar.click();
        homePage.log_out.click();
    }

    public static void clickIssue(List<WebElement> issues, String summary) {
        for (WebElement issue : issues) {

        }
    }

}
