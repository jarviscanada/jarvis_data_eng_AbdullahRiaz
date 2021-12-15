package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

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
    return null;
  }

  @Override
  public Tweet showTweet(String[] args) {
    return null;
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    return null;
  }
}
