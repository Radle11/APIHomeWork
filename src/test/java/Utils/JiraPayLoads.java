package Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

public class JiraPayLoads {
    public static String getCookieAuthPayLoad(String username,String password){
        return "{\n" +
                "    \"username\":\""+username+"\",\n" +
                "    \"password\":\""+password+"\"\n" +
                "}";
    }
    public static String getJiraIssuePayLoad(String summary,String description,String issueName){
        return "{\n" +
                "    \"fields\": {\n" +
                "        \"project\": {\n" +
                "            \"key\": \"TEC\"\n" +
                "        },\n" +
                "        \"summary\": \""+summary+"\",\n" +
                "        \"description\": \""+description+"\",\n" +
                "        \"issuetype\": {\n" +
                "            \"name\": \""+issueName+"\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }
    public static String getJiraSesionCookie() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        //http://localhost:8080/rest/auth/1/session
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/auth/1/session");
        HttpPost post = new HttpPost(uriBuilder.build());
        post.setHeader(HttpHeaders.ACCEPT, "application/json");
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity entity = null;
        try {
            entity = new StringEntity(JiraPayLoads.getCookieAuthPayLoad(ConfigReader.getProperty("jiraUserName"),ConfigReader.getProperty("jiraUserPassword")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        ObjectMapper mapper=new ObjectMapper();
        Map<String, Object> desJira = mapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });
        Map<String,String>session= (Map<String,String>)desJira.get("session");
        String name=session.get("name");
       // System.out.println(name);
        String value=session.get("value");
        //System.out.println(value);
        return String.format("%s=%s",name,value) ;
    }
}
