package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class TwoSumTest {

  @Test
  public void twoSum() {
    int[] arr = {2, 5, 7, 11};
    int[] expected = {0, 1};
    Assert.assertArrayEquals(expected, TwoSum.twoSum(arr, 7));
  }

  @Test
  public void twoSumMap() {
    int[] arr = {2, 5, 7, 11};
    int[] expected = {0, 1};
    Assert.assertArrayEquals(expected, TwoSum.twoSumMap(arr, 7));
  }
}