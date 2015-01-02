package com.br.ifpb.businessObject;

import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.MensagemDaoIF;
import com.br.ifpb.valueObject.Mensagem;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public class GerenciarMensagem {
    
    public List<Mensagem> minhasMensagens(String email) throws PersistenciaException{
        DaoFactoryIF daoFactory=DaoFactory.createFactory();
        MensagemDaoIF mensagemDao=daoFactory.criarMensagemDao();
        return mensagemDao.minhasMensagens(email);
    }
    
}
