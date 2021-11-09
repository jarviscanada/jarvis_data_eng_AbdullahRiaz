package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-337cd73891914c25a048c17d2a06cadd
 */
public class CompareTwoMaps {

  /**
   * Use Collections Framework .equals method.
   *
   * Big O - O(1)
   * Justification: Time complexity will always be the same regardless of the number of elements.
   * @param m1 Map 1
   * @param m2 Map 2
   * @param <K> Key
   * @param <V> Value of Key
   * @return true if maps match, false if not
   */
  public static <K, V> boolean compareMaps(Map<K, V> m1, Map<K, V> m2) {
    return m1.equals(m2);
  }

}