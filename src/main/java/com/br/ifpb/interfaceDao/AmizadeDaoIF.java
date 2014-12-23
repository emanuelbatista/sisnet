package com.br.ifpb.interfaceDao;

import com.br.ifpb.execoes.PersistenciaException;
import java.util.List;
import com.br.ifpb.valueobject.Usuario;

/**
 *
 * @author Emanuel
 */
public interface AmizadeDaoIF {
  
  /**
   * Responsável por <b>enviar</b> uma solicitação de amizade
   * @param remetente
   * @param destinatario
   * @throws PersistenciaException 
   */
  public void solicitarAmizade(String remetente,String destinatario) throws PersistenciaException;
  
  /**
   * Responsável por <b>aceitar</b> uma solicitação de amizade
   * @param remetente
   * @param destinatario
   * @return boolean
   * @throws PersistenciaException 
   */
  public boolean aceitarSolicitacao(String remetente,String destinatario) throws PersistenciaException;
  
  public boolean rejeitarSolicitacao(String remetente,String destinatario) throws PersistenciaException;
  
  public List<Usuario> listaDeSolicitacoes(String email) throws PersistenciaException;
 
  public List<Usuario> getAmigos(String email) throws PersistenciaException;
  
}
