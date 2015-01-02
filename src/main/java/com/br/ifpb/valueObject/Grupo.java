package com.br.ifpb.valueObject;

public class Grupo {
  private int id;
  private String nome;
  private String descricao;
  private Usuario fundador;
  
 public int getId() {
 	return id;
 }
 public void setId(int id) {
 	this.id = id;
 }
 public String getNome() {
 	return nome;
 }
 public void setNome(String nome) {
 	this.nome = nome;
 }
 public String getDescricao() {
 	return descricao;
 }
 public void setDescricao(String descricao) {
 	this.descricao = descricao;
 }
 public Usuario getFundador() {
 	return fundador;
 }
 public void setFundador(Usuario fundador) {
 	this.fundador = fundador;
 }
 
  
}
