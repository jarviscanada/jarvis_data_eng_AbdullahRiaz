package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }


  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }

    String tweet_txt = args[1];
    String coord = args[2];
    String[] coordArray = coord.split(COORD_SEP);
    if (coordArray.length != 2 || StringUtils.isEmpty(tweet_txt)) {
      throw new IllegalArgumentException(
          "Invalid location format and/or tweet is empty\nTwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }

    Double lat = null;
    Double lon = null;
    try {
      lat = Double.parseDouble(coordArray[0]);
      lon = Double.parseDouble(coordArray[1]);
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Invalid location format or tweet is empty\nTwitterCLIApp post \"tweet_text\" \"latitude:longitude\"",
          e);
    }

    Tweet postTweet = TweetUtil.buildTweet(tweet_txt, lon, lat);

    return service.postTweet(postTweet);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp show \"tweet_id\" [fields]");
    }

    String id = args[1];
    String fields = args[2];
    String[] fieldsArray = fields.split(COMMA);
    if (fieldsArray == null) {
      throw new IllegalArgumentException(
          "Fields argument is empty\nUSAGE: TwitterCLIApp show \"tweet_id\" [fields]");
    }

    return service.showTweet(id, fieldsArray);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp delete [ids]");
    }

    String tweet_ids =  args[1];
    String[] ids = tweet_ids.split(COMMA);

    return service.deleteTweets(ids);
  }
}
