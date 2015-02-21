package com.br.ifpb.interfaceDao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;
import java.util.List;

public interface UsuarioDaoIF {

  public void excluir(int id) throws PersistenciaException;
  
  public void atualizarInformacoes(Usuario usuario) throws PersistenciaException;
  
  public boolean logar(String email, String senha) throws PersistenciaException;
  
  public void criar(Usuario usuario) throws PersistenciaException;
    
  public Usuario getUsuario(int id) throws PersistenciaException;
  
  public Usuario getUsuario(String email) throws PersistenciaException;
  
  public boolean verficarExistenciaEmail(String email) throws PersistenciaException;
  
  public void atualizarImagemPerfil(String path,int idUsuario) throws PersistenciaException;
  
  public void adicionarLocalTrabalho(String local,int idUsuario) throws PersistenciaException;
  
  public void adicionarLocalEstudou(String local,int idUsuario) throws PersistenciaException;
  
  public void removerLocalTrabalho(String local,int idUsuario) throws PersistenciaException;
  
  public void removerLocalEstudou(String local,int idUsuario) throws PersistenciaException;
  
  public List<Usuario> pesquisarUsuario(String pesquisa) throws PersistenciaException;
  
}
