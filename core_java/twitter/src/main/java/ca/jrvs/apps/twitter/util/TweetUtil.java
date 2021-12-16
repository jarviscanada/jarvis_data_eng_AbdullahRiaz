package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import java.util.ArrayList;
import java.util.List;

/**
 * Tweet utility to build tweet object.
 */
public class TweetUtil {

  private static Tweet tweet;

  /**
   * Build a tweet object to post tweet.
   *
   * @param txt       tweet to be posted on Twitter
   * @param longitude geo-location
   * @param latitude  geo-location
   * @return tweet object
   */
  public static Tweet buildTweet(String txt, Double longitude, Double latitude) {
    tweet = new Tweet();
    List<Double> list = new ArrayList<>();

    PercentEscaper percentEscaper = new PercentEscaper("", false);
    String txtPe = percentEscaper.escape(txt);
    tweet.setText(txtPe);

    list.add(latitude);
    list.add(longitude);

    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(list);
    tweet.setCoordinates(coordinates);
    return tweet;
  }
}
