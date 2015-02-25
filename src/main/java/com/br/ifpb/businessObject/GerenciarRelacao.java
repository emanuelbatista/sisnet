/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.businessObject;

import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.RelacaoDaoIF;
import com.br.ifpb.valueObject.Relacao;
import com.br.ifpb.valueObject.Usuario;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public class GerenciarRelacao {
    
    public List<Usuario> getRelacao(int id) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        RelacaoDaoIF relacaoDao = daoFactory.criarRelacaoDao();
        return relacaoDao.getRelacao(id);
    }
    
    public String tipoRelacao(int remetente, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        RelacaoDaoIF relacaoDao = daoFactory.criarRelacaoDao();
        return relacaoDao.tipoRelacao(remetente, destinatario);
    }
    
    public void adicionarRelacao(int remetente, String tipo, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        RelacaoDaoIF relacaoDao = daoFactory.criarRelacaoDao();
        relacaoDao.adicionarRelacao(remetente, destinatario, tipo);
        
    }
    
    public boolean existeRelacao(int remetente, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        RelacaoDaoIF relacaoDao = daoFactory.criarRelacaoDao();
        return relacaoDao.existeRelacao(remetente, destinatario);
    }
    
    public List<Usuario> solicitacaoRelacao(int id) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        RelacaoDaoIF relacaoDao = daoFactory.criarRelacaoDao();
        return relacaoDao.solicitacaoRelacao(id);
    }
    
    public void aceitarRelacao(int remetente, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        RelacaoDaoIF relacaoDao = daoFactory.criarRelacaoDao();
        relacaoDao.aceitarRelacao(remetente, destinatario);
    }
    
    public void desfazerRelacao(int remetente, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        RelacaoDaoIF relacaoDao = daoFactory.criarRelacaoDao();
        relacaoDao.desfazerRelacao(remetente, destinatario);
    }
}
