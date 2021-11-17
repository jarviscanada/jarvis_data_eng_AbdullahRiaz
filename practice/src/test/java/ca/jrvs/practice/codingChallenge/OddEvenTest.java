package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OddEvenTest {
  private OddEven oddEven;

  @Before
  public void setUp() throws Exception {
    oddEven = new OddEven();
  }

  @Test
  public void oddEvenMod() {
    String expected = "odd";
    Assert.assertEquals(expected, oddEven.oddEvenMod(3));
    expected = "even";
    Assert.assertEquals(expected, oddEven.oddEvenMod(4));
  }

  @Test
  public void oddEvenBit(){
    String expected = "odd";
    Assert.assertEquals(expected, oddEven.oddEvenBit(3));
    expected = "even";
    Assert.assertEquals(expected, oddEven.oddEvenBit(4));
  }
}