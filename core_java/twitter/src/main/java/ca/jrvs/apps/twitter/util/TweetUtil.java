package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import java.util.ArrayList;
import java.util.List;

public class TweetUtil {

  private static Tweet tweet;

  public static Tweet buildTweet(String txt, Double longitude, Double latitude){
    tweet = new Tweet();
    List<Double> list = new ArrayList<>();
    PercentEscaper percentEscaper = new PercentEscaper("",false);
    String txtPE = percentEscaper.escape(txt);
    tweet.setText(txtPE);
    list.add(latitude);
    list.add(longitude);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(list);
    tweet.setCoordinates(coordinates);
    return tweet;
  }
}
