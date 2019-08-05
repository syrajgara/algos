package com.shabs.recurssion;

import org.testng.annotations.Test;

public class GCD {
  public static int compute(int a, int b) {
    int min = Math.min(a, b);
    int max = Math.max(a, b);

    if (min <= 0) {
      return -1;
    }

    if (a == b) {
      return a;
    }

    return compute(min, max - min);
  }

  @Test
  public void test() {
    System.out.println("GCD -1,2 = " + compute(-1, 2)); // -1
    System.out.println("GCD 0,0 = " + compute(0, 0)); // -1
    System.out.println("GCD 0,2 = " + compute(0, 2)); // -1
    System.out.println("GCD 10,10 = " + compute(10, 10)); // 10
    System.out.println("GCD 6,8 = " + compute(6, 8)); // 2
    System.out.println("GCD 4,16 = " + compute(4, 12)); // 4
    System.out.println("GCD 20,50 = " + compute(20, 50)); // 10
  }
}
