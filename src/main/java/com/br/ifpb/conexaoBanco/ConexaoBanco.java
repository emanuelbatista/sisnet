package com.br.ifpb.conexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco{
  
  public static Connection getInstance() throws SQLException, ClassNotFoundException{
      Class.forName("org.postgresql.Driver");
      String url="jdbc:postgresql://ec2-54-243-48-204.compute-1.amazonaws.com:5432/d1g2kk3dbcmhhr?user=doefghbzfqmwdv&dEredbxsKMmQHdehY_7-x5CuyP&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
      String user="doefghbzfqmwdv";
      String password="dEredbxsKMmQHdehY_7-x5CuyP";
      return DriverManager.getConnection(url,user,password);
  }
  
}

