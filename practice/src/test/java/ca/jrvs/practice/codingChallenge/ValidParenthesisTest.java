package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ValidParenthesisTest {

  @Test
  public void isValid() {
    String s = "[]{}[])";
    boolean expected = false;
    Assert.assertEquals(expected,ValidParenthesis.isValid(s));
  }

  @Test
  public void isValid2() {
    String s = "[]{}[])";
    boolean expected = false;
    Assert.assertEquals(expected,ValidParenthesis.isValid2(s));
  }
}