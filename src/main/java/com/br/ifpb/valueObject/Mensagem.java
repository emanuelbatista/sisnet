package com.br.ifpb.valueObject;

import java.io.Serializable;
import java.sql.Timestamp;

public class Mensagem implements Serializable{
  private int id;
  private String texto;
  private Timestamp data;
  private String usuario;
  
 public int getId() {
 	return id;
 }
 public void setId(int id) {
 	this.id = id;
 }
 public String getTexto() {
 	return texto;
 }
 public void setTexto(String texto) {
 	this.texto = texto;
 }
 public Timestamp getData() {
 	return data;
 }
 public void setData(Timestamp data) {
 	this.data = data;
 }
 public String getUsuario() {
 	return usuario;
 }
 public void setUsuario(String usuario) {
 	this.usuario = usuario;
 }
  
  
}
