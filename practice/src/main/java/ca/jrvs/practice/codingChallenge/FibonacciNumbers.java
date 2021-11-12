package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-dee303bbe9134bb58fb9a1a3e205cc6a
 */
public class FibonacciNumbers {

  /**
   * Big O: O(2^n)
   * Justification: The amount of operations needed for each level of recursion grows exponentially as the depth
   * approaches N.
   * @param n Integer
   * @return value of fib(n)
   */
  public static int fib(int n) {
    if (n == 0 || n == 1){
      return n;
    }
    return fib(n-1) + fib(n-2);
  }

  /**
   * Big O: O(n)
   * Justification: Each number, starting at 2 up to and including N, is visited,
   * computed and then stored for O(1) access later on.
   * @param n fibonacci number in the fibonacci sequence.
   * @return a value at the specified fib. number.
   */
  public static int fibDP(int n){
    int[] memo = new int[10];
    memo[0] = 0;
    memo[1] = 1;
    int result = 0;
    if (n == 0 || n == 1){
      return n;
    }
    else if (memo[n] != 0){
      return memo[n];
    }
    else {
      result = fibDP(n-1) + fibDP(n-2);
      memo[n] = result;
      return result;
    }
  }

}