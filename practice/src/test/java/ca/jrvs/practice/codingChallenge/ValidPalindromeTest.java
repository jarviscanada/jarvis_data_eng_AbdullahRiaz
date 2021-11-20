package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ValidPalindromeTest {

  @Test
  public void isPalindrome() {
    String s = "racecar";
    boolean expected = true;
    Assert.assertEquals(expected,ValidPalindrome.isPalindrome(s));
  }
}