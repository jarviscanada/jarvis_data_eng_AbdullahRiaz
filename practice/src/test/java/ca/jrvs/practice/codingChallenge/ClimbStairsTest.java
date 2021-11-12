package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ClimbStairsTest {

  @Test
  public void climbStairs() {
    Assert.assertEquals(8, ClimbStairs.climbStairs(5));
  }
}