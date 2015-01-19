package com.br.ifpb.valueObject;

import java.sql.Timestamp;

public class Topico {
  private int id;
  private String texto;
  private Timestamp data;
  private int usuario;
  private Grupo grupo;
  
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
 public Grupo getGrupo() {
 	return grupo;
 }
 public void setGrupo(Grupo grupo) {
 	this.grupo = grupo;
 }
 
  
}
