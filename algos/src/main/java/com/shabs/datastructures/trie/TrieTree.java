package com.shabs.datastructures.trie;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * == reTRIEval ==
 * <p>
 * Start with root node that is empty string
 * <p>
 * all words start as links to the root
 * mark the last node (char) as isWord
 * <p>
 * new words could continue past a word, so when printing keep going till end of links
 */
public class TrieTree {

  // root node is empty string
  private TrieNode root = new TrieNode(null);

  public class TrieNode {
    public Character wordChar;
    public boolean isWord = false;
    public Map<Character, TrieNode> links = new HashMap();

    public TrieNode(Character wordChar) {
      this.wordChar = wordChar;
    }
  }

  private void addWord(String word) {
    TrieNode previousNode = root;

    for (int i = 0; i < word.length(); i++) {
      Character currChar = word.charAt(i);
      TrieNode currentNode = null;

      if (previousNode.links.containsKey(currChar)) {
        currentNode = previousNode.links.get(currChar);
      }

      if (currentNode == null) {
        currentNode = new TrieNode(word.charAt(i));
        previousNode.links.put(currChar, currentNode);
      }

      if (i == word.length() - 1) {
        currentNode.isWord = true;
      }

      previousNode = currentNode;
    }

    previousNode.isWord = true;
  }

  public boolean searchWord(String word) {
    if (word == null) {
      return false;
    }

    TrieNode previousNode = root;

    for (int i = 0; i < word.length(); i++) {
      Character currChar = word.charAt(i);

      if (!previousNode.links.containsKey(currChar)) {
        return false;
      }

      previousNode = previousNode.links.get(currChar);
    }

    return previousNode.isWord;
  }

  private void printTrie() {
    printTrie(root, "");
  }

  private void printTrie(TrieNode node, String prefix) {
    String stringSoFar = "";
    if (node.wordChar != null) {
      stringSoFar = prefix + node.wordChar;
    }

    if (node.isWord) {
      System.out.println(stringSoFar);
    }

    for (TrieNode childNode : node.links.values()) {
      printTrie(childNode, stringSoFar);
    }
  }

  @Test
  public void createTrie() {
    String[] words = {"FAST", "FASTER", "FIN", "FINDER", "A", "APPLE"};
    TrieTree trieTree = new TrieTree();

    for (String w : words) {
      trieTree.addWord(w);
    }

    trieTree.printTrie();

    boolean expected = true;
    boolean actual = trieTree.searchWord("FAST");
    Assert.assertEquals(actual,expected);

    expected = true;
    actual = trieTree.searchWord("FASTER");
    Assert.assertEquals(actual,expected);

    expected = false;
    actual = trieTree.searchWord(null);
    Assert.assertEquals(actual,expected);

    expected = false;
    actual = trieTree.searchWord("FAS");
    Assert.assertEquals(actual,expected);

    expected = false;
    actual = trieTree.searchWord("XYZ");
    Assert.assertEquals(actual,expected);
  }
}
