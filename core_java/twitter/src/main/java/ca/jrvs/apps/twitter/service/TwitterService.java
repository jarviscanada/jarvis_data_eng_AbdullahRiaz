package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service implementation for Twitter.
 */
@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  private static boolean validatePostTweet(Tweet tweet) {
    if (tweet.getText().length() <= 280) {
      if (tweet.getCoordinates().getCoordinates().get(0) >= -90
          && tweet.getCoordinates().getCoordinates().get(0) <= 90) {
        if (tweet.getCoordinates().getCoordinates().get(1) >= -180
            && tweet.getCoordinates().getCoordinates().get(1) <= 180) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean validateShowTweet(String id) {
    return id.length() == 19;
  }

  private static boolean validateDelTweets(String[] ids) {
    for (String id : ids) {
      if (id.length() != 19) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    //Business logic:
    //ex: text length, lat/long range, id format
    if (!validatePostTweet(tweet)) {
      throw new IllegalArgumentException(
          "Number of characters exceed 280 and/or latitude/longitude value exceeds range");
    }

    //Create tweet via dao
    return (Tweet) dao.create(tweet);
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    if (!validateShowTweet(id)) {
      throw new IllegalArgumentException("Incorrect id");
    }

    //Show tweet via dao
    Tweet tweet = (Tweet) dao.findById(id);

    //Set fields
    List<String> fieldList = Arrays.asList(fields);
    if (!fieldList.contains("created_at")) {
      tweet.setCreated_at(null);
    }
    if (!fieldList.contains("id")) {
      tweet.setId(null);
    }
    if (!fieldList.contains("id_str")) {
      tweet.setId_str(null);
    }
    if (!fieldList.contains("text")) {
      tweet.setText(null);
    }
    if (!fieldList.contains("entities")) {
      tweet.setEntities(null);
    }
    if (!fieldList.contains("coordinates")) {
      tweet.setCoordinates(null);
    }
    if (!fieldList.contains("retweet_count")) {
      tweet.setRetweet_count(null);
    }
    if (!fieldList.contains("favorite_count")) {
      tweet.setFavorite_count(null);
    }
    if (!fieldList.contains("favorited")) {
      tweet.setFavorited(null);
    }
    if (!fieldList.contains("retweeted")) {
      tweet.setRetweeted(null);
    }
    return tweet;
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    if (!validateDelTweets(ids)) {
      throw new IllegalArgumentException("Invalid id/ids length, should be 19 characters");
    }
    List<Tweet> tweets = new ArrayList<>();
    for (String id : ids) {
      tweets.add((Tweet) dao.deleteById(id));
    }
    return tweets;
  }
}