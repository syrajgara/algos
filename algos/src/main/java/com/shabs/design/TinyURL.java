package com.shabs.design;

import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * convert a given URL to http://tinyurl.com/{6 chars}
 * - need to accommodate large number of urls (~56 Billion)
 * - store URLs and their alias in a DB
 * - caching scheme for GET alias
 * - multi-threading - getting counter
 *
 * For Base 10 - encoding with {0-9}       chars, and with length of 6 will give 10^6 = 10M combinations
 * For Base 62 - encoding with {a-zA-Z0-9} chars, and with length of 6 will give 62^6 = 56B combinations
 *
 * For every URL, convert to a decimal counter - then convert this number to base62 alias.
 * To get back URL, convert alias to counter and then lookup counter to URL
 */
public class TinyURL {
  // using hashmap for this sample class, instead of a database/cache system

  // Map<base62, urlString>
  Map<String, String> cache = new HashMap<>();
  char[] chars62 = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};

  int counter = 0;

  public String getTinyAlias(String url) {
    String alias = encode62(counter);
    counter++;

    cache.put(alias, url);

    return alias;
  }

  public String getURL(String alias) {
    return cache.getOrDefault(alias, "INVALID");
  }

  private String encode62(int counter) {
    String alias = "";

    while (counter != 0) {
      int remainder = counter % 62;
      alias = chars62[remainder] + alias;

      counter /= 62;
    }

    alias = StringUtils.leftPad(alias, 6, chars62[0]);

    return alias;
  }

  private int decode62(String alias) {
    int counter = 0;
    for (char c : alias.toCharArray()) {
      int index = findIndex(c);
      counter = counter * 62 + index;
    }

    return counter;
  }

  private int findIndex(char c) {
    for (int i = 0; i < chars62.length; i++) {
      if (c == chars62[i]) {
        return i;
      }
    }

    return 0; // error
  }

  @Test
  public void test() {
    TinyURL tinyURL = new TinyURL();

    String url1 = "http://mydomain/test";
    String alias1 = tinyURL.getTinyAlias(url1);
    String url1Returned = tinyURL.getURL(alias1);

    System.out.println(alias1 + " = " + url1);

    Assert.assertEquals(url1Returned, url1);

    String url2 = "not really a url, but doesnt matter we are just converting to an alias";
    String alias2 = tinyURL.getTinyAlias(url2);
    String url2Returned = tinyURL.getURL(alias2);

    System.out.println(alias2 + " = " + url2);

    Assert.assertEquals(url2Returned, url2);
  }

  @Test
  public void testEncode() {
    int actual = 0;
    String encode = "";
    int counter = 0;

    counter = 0;
    encode = encode62(counter);
    actual = decode62(encode);
    System.out.println(counter + " => " + encode);
    Assert.assertEquals(actual, counter);

    counter = 62;
    encode = encode62(counter);
    actual = decode62(encode);
    System.out.println(counter + " => " + encode);
    Assert.assertEquals(actual, counter);

    counter = 64;
    encode = encode62(counter);
    actual = decode62(encode);
    System.out.println(counter + " => " + encode);
    Assert.assertEquals(actual, counter);

    counter = 500000000;
    encode = encode62(counter);
    actual = decode62(encode);
    System.out.println(counter + " => " + encode);
    Assert.assertEquals(actual, counter);
  }

//  @Test
  public void test2() {
    String a = "";
    for (int i=97; i < 123; i++) {
      a += "'" + (char) i + "',";
    }
    System.out.print(a);
  }
}
