package com.br.ifpb.valueObject;

import java.sql.Date;
import java.util.List;

public class Usuario {
  private String nome;
  private String apelido;
  private String cidade;
  private String email;
  private String profissao;
  private String senha;
  private Date data_nascimento;
  private String status;
  private String foto;
  private List<String> locais_estudou;
  private List<String> locais_trabalhou;
  
 public String getNome() {
 	return nome;
 }
 public void setNome(String nome) {
 	this.nome = nome;
 }
 public String getApelido() {
 	return apelido;
 }
 public void setApelido(String apelido) {
 	this.apelido = apelido;
 }
 public String getCidade() {
 	return cidade;
 }
 public void setCidade(String cidade) {
 	this.cidade = cidade;
 }
 public String getEmail() {
 	return email;
 }
 public void setEmail(String email) {
 	this.email = email;
 }
 public String getSenha() {
 	return senha;
 }
 public void setSenha(String senha) {
 	this.senha = senha;
 }
 public Date getData_nascimento() {
 	return data_nascimento;
 }
 public void setData_nascimento(Date data_nascimento) {
 	this.data_nascimento = data_nascimento;
 }
 public String getStatus() {
 	return status;
 }
 public void setStatus(String status) {
 	this.status = status;
 }
 public String getFoto() {
 	return foto;
 }
 public void setFoto(String foto) {
 	this.foto = foto;
 }
 public String getProfissao() {
 	return profissao;
 }
 public void setProfissao(String profissao) {
 	this.profissao = profissao;
 }
 public List<String> getLocais_estudou() {
 	return locais_estudou;
 }
 public void setLocais_estudou(List<String> locais_estudou) {
 	this.locais_estudou = locais_estudou;
 }
 public List<String> getLocais_trabalhou() {
 	return locais_trabalhou;
 }
 public void setLocais_trabalhou(List<String> locais_trabalhou) {
 	this.locais_trabalhou = locais_trabalhou;
 }
 
  
  
  
}
