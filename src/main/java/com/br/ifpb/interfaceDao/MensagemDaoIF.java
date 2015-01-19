package com.br.ifpb.interfaceDao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Mensagem;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public interface MensagemDaoIF {

    public List<Mensagem> minhasMensagens(int id) throws PersistenciaException;

    public List<Mensagem> mensagensAmigos(int id) throws PersistenciaException;
    
    public boolean publicarMensagem(Mensagem mensagem) throws PersistenciaException;
    
}
