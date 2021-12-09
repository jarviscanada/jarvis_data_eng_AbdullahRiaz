package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;

public class TweetUtil {

  public static Tweet buildTweet(String txt, Double longitude, Double latitude){
    Tweet tweet = new Tweet();
    List<Double> list = new ArrayList<>();
    tweet.setText(txt);
    list.add(longitude);
    list.add(latitude);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(list);
    tweet.setCoordinates(coordinates);
    return tweet;
  }
}
