package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpHelperTest {

  @Test
  public void httpGet() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);

    //Create components
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    HttpResponse response = httpHelper.httpGet(
        new URI("https://api.twitter.com/2/tweets/"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void httpPost() throws Exception{
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);

    //Create components
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    HttpResponse response = httpHelper.httpPost(
        new URI("https://api.twitter.com/2/tweets"),new StringEntity("{\n"
            + "    \"text\": \"It's snowing outside!\"\n"
            + "}"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}