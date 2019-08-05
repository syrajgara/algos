package com.shabs.datastructures.stack;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
 * <p>
 * - Use stack to push the start bracket, pop on end-bracket and match
 */

public class ValidParentheses {

  private boolean validate(String input) {

    Stack<Character> stack = new Stack<>();

    for (char c : input.toCharArray()) {
      if (c == '(') {
        stack.push(')');
      } else if (c == '{') {
        stack.push('}');
      } else if (c == '[') {
        stack.push(']');
      } else if (c == ')' || c == '}' || c == ']') {
        Character validChar = stack.pop().getData();
        if (!validChar.equals(c)) {
          return false;
        }
      }
    }

    return true;
  }

  @Test
  public void testValid() {
    String input = "()({[]}){}[**]";

    boolean expected = true;
    boolean actual = validate(input);
    Assert.assertEquals(actual, expected);
  }

  @Test
  public void testInvalid() {
    String input = "()({[]}){}[*[}*]";

    boolean expected = false;
    boolean actual = validate(input);
    Assert.assertEquals(actual, expected);
  }
}
