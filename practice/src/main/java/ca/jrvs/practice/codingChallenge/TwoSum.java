package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/Two-Sum-b3ae11302333419a9b7c6e19895eca85
 */
public class TwoSum {

  /**
   * Big O: O(n^2) Justification:  For each element, we try to find its complement by looping
   * through the rest of the array which takes O(n) time. Therefore, the time complexity is O(n^2).
   *
   * @param nums   integer array
   * @param target target valued to be calculated
   * @return array of indexes that add to target value
   */
  public static int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          int[] result = {i, j};
          return result;
        }
      }
    }
    return null;
  }

  /**
   * Big O: O(n) Justification: We traverse the list containing n elements only once. Each lookup in
   * the table costs only O(1) time.
   *
   * @param nums   array
   * @param target target value
   * @return array of indexes that add to target value
   */
  public static int[] twoSumMap(int[] nums, int target) {
    //Map - Key:Value at index of nums array, Value:Index associated with Value in nums array
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int cur = nums[i];
      //cur + x = target
      int x = target - cur;
      if (map.containsKey(x)) {
        return new int[]{map.get(x), i};
      }
      map.put(cur, i);
    }
    return null;
  }
}