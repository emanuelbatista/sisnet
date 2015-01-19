package com.br.ifpb.businessObject;

import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.MensagemDaoIF;
import com.br.ifpb.valueObject.Mensagem;
import com.br.ifpb.valueObject.Usuario;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public class GerenciarMensagem {
    
    public List<Mensagem> minhasMensagens(int id) throws PersistenciaException{
        DaoFactoryIF daoFactory=DaoFactory.createFactory();
        MensagemDaoIF mensagemDao=daoFactory.criarMensagemDao();
        return mensagemDao.minhasMensagens(id);
    }
    
    public List<Mensagem> mensagensAmigos(int id) throws PersistenciaException{
        DaoFactoryIF daoFactory=DaoFactory.createFactory();
        MensagemDaoIF mensagemDao=daoFactory.criarMensagemDao();
        return mensagemDao.mensagensAmigos(id);
    }
    
    public boolean publicarMensagem(String texto, Timestamp data, Usuario usuario) throws PersistenciaException{
        DaoFactoryIF daoFactory=DaoFactory.createFactory();
        MensagemDaoIF mensagemDao=daoFactory.criarMensagemDao();
        Mensagem mensagem=new Mensagem();
        mensagem.setTexto(texto);
        mensagem.setData(data);
        mensagem.setUsuario(usuario);
        return mensagemDao.publicarMensagem(mensagem);
    }
    
}
