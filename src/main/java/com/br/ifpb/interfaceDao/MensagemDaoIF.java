package com.br.ifpb.interfaceDao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Mensagem;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public interface MensagemDaoIF {

    public List<Mensagem> minhasMensagens(String email) throws PersistenciaException;

    public List<Mensagem> mensagensAmigos(String email) throws PersistenciaException;
    
}
