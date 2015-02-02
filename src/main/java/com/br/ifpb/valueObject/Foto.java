package com.br.ifpb.valueObject;

import java.sql.Timestamp;

public class Foto {
  private String url;
  private Timestamp data;
  private Usuario usuario;
  
 public String getUrl() {
 	return url;
 }
 public void setUrl(String url) {
 	this.url = url;
 }
 public Timestamp getData() {
 	return data;
 }
 public void setData(Timestamp data) {
 	this.data = data;
 }
 public Usuario getUsuario() {
 	return usuario;
 }
 public void setUsuario(Usuario usuario) {
 	this.usuario = usuario;
 }
 
  
}
