package ca.jrvs.apps.twitter.helper;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

public interface HttpHelper {

  /**
   * Execute an HTTP Post call
   * @param uri
   * @return
   */
  HttpResponse httpPost(URI uri);

  /**
   * Execute an HTTP Get call
   * @param uri
   * @return
   */
  HttpResponse httpGet(URI uri);
}
