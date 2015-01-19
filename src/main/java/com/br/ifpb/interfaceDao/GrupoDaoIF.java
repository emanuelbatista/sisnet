package com.br.ifpb.interfaceDao;

import java.util.List;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Grupo;
import com.br.ifpb.valueObject.Usuario;

public interface GrupoDaoIF {
  public void criar(Grupo grupo) throws PersistenciaException;
  
  public List<Usuario> listaDeParticipantes(int idGrupo) throws PersistenciaException ;
  
  public Usuario fundador(int idGrupo) throws PersistenciaException;
  
  public void atualizar(Grupo grupo) throws PersistenciaException;
  
  public void excluir(int idGrupo) throws PersistenciaException;
  
  public List<Grupo> listarGrupos(int login) throws PersistenciaException;
  
  
  
  
  
}
