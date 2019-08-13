package com.shabs.greedy;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Given a string, encode it or compress it by converting to bit string
 * <p>
 * - count frequency of characters
 * - create BinaryTree for each char with node having the char and frequency of that char
 * - put these in priority queue (minimum char frequency)
 * - and then loop and pick 2 trees from the queue merge (with least frequencies) and insert it back
 * - keep doing till you have only one tree left -- this is the Trie that will help compress the message.
 */
public class HuffmanCode {
  private static final String CODE_SEPARATOR = "-";

  private static String compress(String message) {
    Map<String, Integer> frequencies = countFrequenciesOfEachChar(message);

    BinaryTree compressionTree = compressionTree(frequencies);

    Map<String, String> charToExpressionDictionary = new HashMap<>();
    Map<String, String> expressionToCharDictionary = new HashMap<>();

    createExpressionDictionary(frequencies.keySet(), compressionTree, charToExpressionDictionary, expressionToCharDictionary);

    String encodedMessage = encode(message, charToExpressionDictionary);
    System.out.println("\nEncoded:" + encodedMessage);

    String decodedMessage = decode(encodedMessage, expressionToCharDictionary);
    System.out.println("\nDecoded:" + decodedMessage);

    return decodedMessage;
  }

  private static Map<String, Integer> countFrequenciesOfEachChar(String message) {
    Map<String, Integer> frequencies = new HashMap<>();

    for (int i = 0; i < message.length(); i++) {
      String messageChar = String.valueOf(message.charAt(i));

      frequencies.put(messageChar, frequencies.getOrDefault(messageChar, 0) + 1);
    }

    System.out.println("Frequencies: ");
    for (Map.Entry e : frequencies.entrySet()) {
      System.out.println(e.getKey() + "=>" + e.getValue());
    }

    return frequencies;
  }

  /**
   * gets the tree that will be used for compression
   */
  private static BinaryTree compressionTree(Map<String, Integer> frequencies) {
    // to store combined trees, trees with lower combined frequency pulled out first
    PriorityQueue<BinaryTree> priorityQueue = new PriorityQueue<>();

    for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
      // create single rooted tree for each char
      BinaryTree rootBinaryTree = new BinaryTree(entry.getKey(), entry.getValue());
      priorityQueue.add(rootBinaryTree);
    }

    System.out.println("\nCompression Tree: ");
    while (priorityQueue.size() != 1) {
      BinaryTree tree1 = priorityQueue.remove();
      BinaryTree tree2 = priorityQueue.remove();

      System.out.println(tree1.toString() + " + " + tree2.toString());

      BinaryTree mergedTree = new BinaryTree(tree1, tree2);
      priorityQueue.add(mergedTree);
    }

    BinaryTree completeTreeForThisMessage = priorityQueue.remove();
    System.out.println(completeTreeForThisMessage.toString());

    return completeTreeForThisMessage;
  }

  private static void createExpressionDictionary(Set<String> chars, BinaryTree compressionTree, Map<String, String> charToExpressionDictionary, Map<String, String> expressionToCharDictionary) {
    System.out.println();
    for (String key : chars) {
      String expression = compressionTree.findExpression(key, "");
      System.out.println("Compression for " + key + "=>" + expression);

      charToExpressionDictionary.put(key, expression);
      expressionToCharDictionary.put(expression, key);
    }
  }

  private static String encode(String message, Map<String, String> charToExpressionDictionary) {
    StringBuffer buffer = new StringBuffer();

    for (int i = 0; i < message.length(); i++) {
      String stringChar = String.valueOf(message.charAt(i));

      buffer.append(charToExpressionDictionary.get(stringChar));
      buffer.append(CODE_SEPARATOR);
    }

    return buffer.toString();
  }

  private static String decode(String encodedMessage, Map<String, String> expressionToCharDictionary) {
    StringBuffer buffer = new StringBuffer();

    String[] expressions = encodedMessage.split(CODE_SEPARATOR);

    for (String expression : expressions) {
      buffer.append(expressionToCharDictionary.get(expression));
    }

    return buffer.toString();
  }

  public static class BinaryTree implements Comparable<BinaryTree> {
    public static class Node {
      public String data;
      public Integer frequency;

      public Node(String data, Integer frequency) {
        this.data = data;
        this.frequency = frequency;
      }
    }

    Node root;

    BinaryTree leftTree = null;
    BinaryTree rightTree = null;

    public BinaryTree(String data, Integer frequency) {
      root = new Node(data, frequency);
    }

    public BinaryTree(BinaryTree tree1, BinaryTree tree2) {
      String combinedRootData = tree1.root.data + tree2.root.data;
      Integer combinedRootFrequency = tree1.root.frequency + tree2.root.frequency;
      root = new Node(combinedRootData, combinedRootFrequency);

      leftTree = tree1;
      rightTree = tree2;
    }

    @Override
    public String toString() {
      String left, right;
      left = leftTree != null ? leftTree.root.data : "";
      right = rightTree != null ? rightTree.root.data : "";

      return "[" + root.data + " => " + root.frequency + "=(" + left + "," + right + ")]";
    }

    @Override
    public int compareTo(BinaryTree other) {
      return Integer.compare(root.frequency, other.root.frequency);
    }

    public String findExpression(String key, String partialExpression) {
      if (root.data.equals(key)) {
        return partialExpression;
      }

      if (leftTree != null && leftTree.root.data.contains(key)) {
        return leftTree.findExpression(key, partialExpression + "0");
      }

      if (rightTree != null && rightTree.root.data.contains(key)) {
        return rightTree.findExpression(key, partialExpression + "1");
      }

      return null; // error we didnt find the key in the tree
    }
  }

  @Test
  public void compress() {
    String message = "aaaaaaabbbbcccddefgh";

    String decodeMessage = HuffmanCode.compress(message);

    Assert.assertEquals(decodeMessage, message);
  }
}
