package com.shabs.design.connection;

public class OracleConnection implements MyConnection {
  private String c;

  public OracleConnection(String connURL) {
    c = connURL;
  }

  public void openConnection() {
  }

  public void closeConnection() {
  }
}
