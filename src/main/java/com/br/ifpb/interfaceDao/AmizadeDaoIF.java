package com.br.ifpb.interfaceDao;

import com.br.ifpb.execoes.PersistenciaException;
import java.util.List;
import com.br.ifpb.valueObject.Usuario;

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
  public void solicitarAmizade(int remetente,int destinatario) throws PersistenciaException;
  
  /**
   * Responsável por <b>aceitar</b> uma solicitação de amizade
   * @param remetente
   * @param destinatario
   * @return boolean
   * @throws PersistenciaException 
   */
  public boolean aceitarSolicitacao(int remetente,int destinatario) throws PersistenciaException;
  
  public boolean desfazerAmizade(int remetente,int destinatario) throws PersistenciaException;
  
  public List<Usuario> listaDeSolicitacoes(int id) throws PersistenciaException;
 
  public List<Usuario> getAmigos(int id) throws PersistenciaException;
  
  public boolean verificarAmizade(int remetente,int destinatario) throws PersistenciaException;
  
}
