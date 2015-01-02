package com.br.ifpb.interfaceDao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;

public interface UsuarioDaoIF {

  public void excluir(String email) throws PersistenciaException;
  
  public void atualizar(Usuario usuario) throws PersistenciaException;
  
  public boolean logar(String email, String senha) throws PersistenciaException;
  
  public void criar(Usuario usuario) throws PersistenciaException;
    
  public Usuario getUsuario(String email) throws PersistenciaException;
  
  public boolean verficarExistenciaEmail(String email) throws PersistenciaException;
  
}
