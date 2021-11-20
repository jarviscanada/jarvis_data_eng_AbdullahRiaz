package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Test;

public class FibonacciNumbersTest {


  @Test
  public void fib() {
    assertEquals(55, FibonacciNumbers.fib(10));
  }


  @Test
  public void fibDP() {
    Assert.assertEquals(55, FibonacciNumbers.fibDP(10));
  }
}