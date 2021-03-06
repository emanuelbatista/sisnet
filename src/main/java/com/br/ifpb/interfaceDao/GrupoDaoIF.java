package com.br.ifpb.interfaceDao;

import java.util.List;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Grupo;
import com.br.ifpb.valueObject.Usuario;

public interface GrupoDaoIF {
    
  public Grupo getGrupo(int id) throws PersistenciaException;
  
  public void criar(Grupo grupo) throws PersistenciaException;
  
  public List<Usuario> listaDeParticipantes(int idGrupo) throws PersistenciaException ;
  
  public Usuario fundador(int idGrupo) throws PersistenciaException;
  
  public void atualizar(Grupo grupo) throws PersistenciaException;
  
  public void excluir(int idGrupo) throws PersistenciaException;
  
  public List<Grupo> listarGrupos(int idUsuario) throws PersistenciaException;
  
  public boolean participaGrupo(int idGrupo,int idUsuario) throws PersistenciaException;
  
  public void participarDoGrupo(int idGrupo,int idUsuario) throws PersistenciaException;
  
  public Grupo ultimoGrupo() throws PersistenciaException;
  
  public List<Grupo> pesquisarGrupo(String pesquisa) throws PersistenciaException;
  
}
