package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;

/**
 * Interface for Http client to execute HTTP requests.
 */
public interface HttpHelper {

  /**
   * Execute a HTTP Post call.
   *
   * @param uri URI for Twitter API POST tweet endpoint
   * @return HTTP post request response
   */
  HttpResponse httpPost(URI uri);

  /**
   * Execute a HTTP Get call.
   *
   * @param uri URI for Twitter API GET tweet endpoint
   * @return HTTP get request response
   */
  HttpResponse httpGet(URI uri);
}
