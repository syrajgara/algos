package com.shabs.datastructures.linkedlist;

import com.shabs.datastructures.node.Node;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Priority Queue
 * <p>
 * K sorted linked list of numbers, merge it to a single linked list
 * <p>
 * - take first node on each linked list and add it to a priority queue
 * - take one node out from the pq, this is the sorted among all the list.
 * - take next of this node and add to the pq as replacement.
 * - continue till pq is empty
 */
public class MergeKSortedLists {

  public LinkedList<Integer> sort(List<LinkedList<Integer>> list) {

    PriorityQueue<Node<Integer>> pq = new PriorityQueue<>((o1, o2) -> o1.getData().compareTo(o2.getData()));

    for (LinkedList<Integer> ll : list) {
      pq.add(ll.root);
    }

    LinkedList<Integer> sortedLL = new LinkedList<>();

    while (!pq.isEmpty()) {
      Node<Integer> n = pq.remove();
      sortedLL.addLast(n.getData());

      if (n.hasNext()) {
        pq.add(n.getNext());
      }
    }

    return sortedLL;
  }

  @Test
  public void test() {
    LinkedList<Integer> list1 = new LinkedList<>();
    LinkedList<Integer> list2 = new LinkedList<>();
    LinkedList<Integer> list3 = new LinkedList<>();
    LinkedList<Integer> list4 = new LinkedList<>();

    list1.addLast(1);
    list1.addLast(2);
    list1.addLast(5);
    list1.addLast(10);

    list2.addLast(4);
    list2.addLast(8);

    list3.addLast(3);
    list3.addLast(7);

    list4.addLast(6);
    list4.addLast(9);

    List<LinkedList<Integer>> list = new ArrayList<>();
    list.add(list1);
    list.add(list2);
    list.add(list3);
    list.add(list4);

    LinkedList<Integer> sortedLL = sort(list);
    sortedLL.print();
  }
}
