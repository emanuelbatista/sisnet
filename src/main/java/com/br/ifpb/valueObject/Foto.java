package com.br.ifpb.valueObject;

import java.sql.Timestamp;

public class Foto {
  private String url;
  private Timestamp data;
  private int usuario;
  
 public String getUrl() {
 	return url;
 }
 public void setUrl(String url) {
 	this.url = url;
 }
 public Timestamp getTimestamp() {
 	return data;
 }
 public void setTimestamp(Timestamp data) {
 	this.data = data;
 }
 public int getUsuario() {
 	return usuario;
 }
 public void setUsuario(int usuario) {
 	this.usuario = usuario;
 }
 
  
}
