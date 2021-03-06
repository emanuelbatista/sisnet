/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.interfaceDao;

import com.br.ifpb.execoes.PersistenciaException;
import java.util.List;
import com.br.ifpb.valueObject.Usuario;

/**
 *
 * @author Emanuel
 */
public interface RelacaoDaoIF {
  /**
   * Responsável por <b>retornar</b> o namorado(a) do usuario passado por parametro
   * @param id
   * @return Usuario que é namorado(a)
   * @throws PersistenciaException 
   */
  public Usuario relacaoNamoro(int id) throws PersistenciaException;
  
  public List<Usuario> getRelacao(int idUsuario) throws PersistenciaException;
  
  public String tipoRelacao(int remetente,int destinatario) throws PersistenciaException;
  
  public List<Usuario> solicitacaoRelacao(int id) throws PersistenciaException;
  
  public void adicionarRelacao(int remetente,int destinatario,String tipoRelacao) throws PersistenciaException;
  
  public boolean existeRelacao(int remetente,int destinatario) throws PersistenciaException;
  
  public void aceitarRelacao(int remetente,int destinatario) throws PersistenciaException;
  
  public void desfazerRelacao(int remetente,int destinatario) throws PersistenciaException;
  
}
