package com.shabs.zUnimplemented;

import java.util.HashMap;
import java.util.Map;

/**
 * This is an implementation of DisjointSets
 */
public class DisjointSets {
  //This the the data strore which maintains the mapping of sets and their corresponding parents
  //if a node has no parent(which means it is the root), the parent field contains the size of the three
  //with multiplied by -1
  private Map<Integer, Integer> source = new HashMap<>();

  /**
   * It is for initializing the data structure
   * It looks for unique items to be created as sets
   *
   * @param root
   */
  public void addSet(Integer root) {
    source.put(root, -1);
  }

  /**
   * @param root1 - any root of a partition1
   * @param root2 - any root of a partition2
   */
  public void union(Integer root1, Integer root2) {
    //since size stored in the root node is -ve
    //if root1 is less than root 2 that means, size wise root1 is bigger
    //hence root1 should be assigned as parent of root1 and size of root1 should get incremented too

    //find the absolute root of the passed in nodes
    Integer rootOfroot1 = find(root1);
    Integer rootOfroot2 = find(root2);

    if (rootOfroot1 == rootOfroot2) {
      //they are already in the same set, union will not do anything
      return;
    }

    //if rootodroot1 is smaller that it means its bigegr in size
    //hence append the smaller tree to the bigger one
    if (source.get(rootOfroot1) < source.get(rootOfroot2)) {

      source.put(rootOfroot1, source.get(rootOfroot1) + source.get(rootOfroot2));
      source.put(rootOfroot2, rootOfroot1);

    } else {
      source.put(rootOfroot2, source.get(rootOfroot2) + source.get(rootOfroot1));
      source.put(rootOfroot1, rootOfroot2);
    }
  }


  public Integer find(Integer root1) {
    //if root's parent is negative.. means this is the root hence return the root1 value
    if (!source.containsKey(root1)) {
      addSet(root1);
    }

    if (source.get(root1) < 0) {
      return root1;
    } else {
      //if root is not negative.. try compressing the parents..
      //compression happening through recursion
      source.put(root1, find(source.get(root1)));
      //return the root node's parent ..which will point to the original root because of compression
      return source.get(root1);
    }
  }


  public static void main(String[] args) {
    DisjointSets ds = new DisjointSets();
    ds.addSet(1);
    ds.addSet(2);
    ds.addSet(3);
    ds.addSet(4);
    ds.addSet(5);
    ds.addSet(6);
    ds.addSet(7);


    ds.union(1, 2);
    ds.union(2, 3);

    System.out.println(ds.find(3));


  }


}
