package com.shabs.design.connection;

/**
 * Create a class structure that your client can use to get connections,
 * the connection could be for Oracle, MySQL, etc based on some key passed in
 */
public interface MyConnection {

  void openConnection();
  void closeConnection();
}
