package com.br.ifpb.propriedades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropriedadeJDBC {
    private static PropriedadeJDBC propriedadeJDBC;
    private Properties propriedade;
    
    private PropriedadeJDBC(){
      propriedade=new Properties();
    }
    
    public static PropriedadeJDBC getInstance(){
        if(propriedadeJDBC==null)
            propriedadeJDBC=new PropriedadeJDBC();
        return propriedadeJDBC;
    }
    
    public String LoadProperty(String chave){
      InputStream inputStream=null;
      try{
          inputStream=new FileInputStream("jdbc.properties");
          propriedade.load(inputStream);
      }catch(FileNotFoundException e){
          e.printStackTrace();
      }catch (IOException ex) { 
          ex.printStackTrace();
      } 
      return propriedade.getProperty(chave);
    }
    
}
