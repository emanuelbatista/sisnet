package com.br.ifpb.conexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco{
  
  public static Connection getInstance() throws SQLException, ClassNotFoundException{
      Class.forName("org.postgresql.Driver");
      String url="jdbc:postgresql://localhost:5432/sisnet";
      String user="postgres";
      String password="yadirf";
      return DriverManager.getConnection(url,user,password);
  }
  
}

