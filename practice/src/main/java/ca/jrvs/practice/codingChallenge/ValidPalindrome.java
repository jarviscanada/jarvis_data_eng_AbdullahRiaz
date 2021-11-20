package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-a9f3ff7eadcf431a83bccd77ffd3735f
 */
public class ValidPalindrome {

  /**
   * Big O: O(n)
   * Justification: Traverse a string of characters.
   * @param s string
   * @return true if palindrome, false if not
   */
  public static boolean isPalindrome(String s) {
    //convert string to lowercase
    StringBuilder sb = new StringBuilder(s.toLowerCase());

    //remove all non-alphanumeric characters
    int idx = 0;
    while (idx < sb.length()) {
      char ch = sb.charAt(idx);
      if (Character.isLetterOrDigit(ch)) {
        idx++;
      } else {
        sb.deleteCharAt(idx);
      }
    }

    //validate string for palindrome
    for (int i = 0; i < sb.length() / 2; i++) {
      int j = sb.length() - 1 - i;
      if (sb.charAt(i) != sb.charAt(j)) {
        return false;
      }
    }
    return true;
  }
}