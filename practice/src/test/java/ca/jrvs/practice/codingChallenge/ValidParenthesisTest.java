package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ValidParenthesisTest {

  @Test
  public void isValid() {
    String s = "[][]";
    boolean expected = true;
    Assert.assertEquals(expected,ValidParenthesis.isValid(s));
  }
}