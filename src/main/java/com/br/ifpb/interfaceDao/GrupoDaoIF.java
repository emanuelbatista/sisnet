package com.br.ifpb.interfaceDao;

import java.util.List;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueobject.Grupo;
import com.br.ifpb.valueobject.Usuario;

public interface GrupoDaoIF {
  public void criar(Grupo grupo) throws PersistenciaException;
  
  public List<Usuario> listaDeParticipantes(String idGrupo) throws PersistenciaException ;
  
  public Usuario fundador(String idGrupo) throws PersistenciaException;
  
  public void atualizar(Grupo grupo) throws PersistenciaException;
  
  public void excluir(String idGrupo) throws PersistenciaException;
  
  public List<Grupo> listarGrupos(String login) throws PersistenciaException;
  
  
  
  
  
}
