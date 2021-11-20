package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Parentheses-17c45aa9cf36454a9cd7287804164390
 */
public class ValidParenthesis {

  /**
   * Big O: O(n) Justification: Simply traverse the given string one character at a time and push
   * and pop operations on a stack take O(1) time.
   *
   * @param s string to validate expression
   * @return true if expression is valid, false if not valid
   */
  public static boolean isValid(String s) {
    Map<Character, Character> map = new HashMap<>();
    Stack<Character> stack = new Stack<>();
    //bracket mappings: opening brackets -> closing brackets
    map.put(')', '(');
    map.put('}', '{');
    map.put(']', '[');

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      //If the current character is a closing bracket.
      if (map.containsKey(c)) {
        //Get the top element of the stack and pop it. If the stack is empty, set a dummy value of '#'
        char topElement = stack.isEmpty() ? '#' : stack.pop();

        // If the mapping for this bracket doesn't match the stack's top element, return false.
        if (map.get(c) != topElement) {
          return false;
        }
      } else {
        //push opening bracket to the stack
        stack.push(c);
      }
    }
    //If stack is not empty, it means not all brackets have been popped meaning expression is not valid
    return stack.isEmpty();
  }

  /**
   * Big O: O(n) Justification: iterate through string
   * @param s string to validate expression
   * @return true if expression is valid, false if not valid
   */
  public static boolean isValid2(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
      } else if (stack.empty()) {
        return false;
      } else if (c == ')' && stack.pop() != '(') {
        return false;
      } else if (c == '}' && stack.pop() != '{') {
        return false;
      } else if (c == ']' && stack.pop() != '[') {
        return false;
      }
    }
    return stack.isEmpty();
  }
}