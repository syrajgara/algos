package com.shabs.design.connection;

public class MyConnectionFactory {

  public enum ConnectionTypeEnum {
    ORACLE ("Oracle", "conn url"),
    MYSQL ("MySQL", "conn url");

    private String name;
    private String connURL;

    ConnectionTypeEnum(String name, String connURL) {
      this.name = name;
      this.connURL = connURL;
    }
  }

  public MyConnection getConnection(ConnectionTypeEnum type) {
    MyConnection conn = null;

    if (type == ConnectionTypeEnum.ORACLE) {
      conn = new OracleConnection(ConnectionTypeEnum.ORACLE.connURL);
    }

    return conn;
  }
}