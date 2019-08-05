package com.shabs.datastructures.stack;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * operators => * / + -
 * "1 2 3 + - 5 *"
 * <p>
 * - stream of operands and operators, or split the given String
 * <p>
 * - add operands to stack
 * - if you encounter operator - pop 2 operands from the stack
 * - calculate the result and add result back to the stack
 * - continue till stack is empty
 */
public class PostFixReversePolishCalculator {
  public int calculate(String postFix) {
    Stack<String> operands = new Stack<>();

    String[] postFixSplitOperands = postFix.split(" ");

    for (String postFixSplitOperand : postFixSplitOperands) {
      int operand1;
      int operand2;

      if (!isOperator(postFixSplitOperand)) {
        operands.push(postFixSplitOperand);
      } else {
        operand2 = Integer.parseInt(operands.pop().getData());
        operand1 = Integer.parseInt(operands.pop().getData());

        int result = calculate(operand1, operand2, postFixSplitOperand);
        operands.push(Integer.toString(result));
      }
    }

    return Integer.parseInt(operands.pop().getData());
  }

  private boolean isOperator(String postFixSplitOperand) {
    return postFixSplitOperand.equals("+") || postFixSplitOperand.equals("-")
        || postFixSplitOperand.equals("*") || postFixSplitOperand.equals("/");

  }

  private int calculate(int operand1, int operand2, String postFixSplitOperand) {
    int result = 0;

    if (postFixSplitOperand.equals("+")) {
      result = operand1 + operand2;
    } else if (postFixSplitOperand.equals("-")) {
      result = operand1 - operand2;
    } else if (postFixSplitOperand.equals("*")) {
      result = operand1 * operand2;
    } else if (postFixSplitOperand.equals("/")) {
      result = operand1 / operand2;
    }

    return result;
  }

  @Test
  public void calculatePlus() {
    String operation = "1 2 +";
    int expectedResults = 3;

    PostFixReversePolishCalculator postFixReversePolishCalculator = new PostFixReversePolishCalculator();
    int results = postFixReversePolishCalculator.calculate(operation);
    Assert.assertEquals(results, expectedResults);
  }

  @Test
  public void calculateComplex() {
    String operation = "1 2 3 + - 5 *";
    int expectedResults = -20;

    PostFixReversePolishCalculator postFixReversePolishCalculator = new PostFixReversePolishCalculator();
    int results = postFixReversePolishCalculator.calculate(operation);
    Assert.assertEquals(results, expectedResults);
  }
}
