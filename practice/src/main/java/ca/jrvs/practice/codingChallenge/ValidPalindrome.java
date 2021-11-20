package ca.jrvs.practice.codingChallenge;

public class ValidPalindrome {

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