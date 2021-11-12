package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FibonacciNumbersTest {


  @Test
  public void fib() {
    assertEquals(55, FibonacciNumbers.fib(10));
  }


  @Test
  public void fibDP() {
    Assert.assertEquals(5, FibonacciNumbers.fibDP(5));
  }
}