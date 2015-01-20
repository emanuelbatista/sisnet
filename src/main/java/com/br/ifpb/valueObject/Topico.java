package com.br.ifpb.valueObject;

import java.sql.Timestamp;

public class Topico {
  private int id;
  private String texto;
  private Timestamp data;
  private Usuario usuario;
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
 public Grupo getGrupo() {
 	return grupo;
 }
 public void setGrupo(Grupo grupo) {
 	this.grupo = grupo;
 }
 
  
}
