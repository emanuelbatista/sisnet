/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.businessObject;

import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.AmizadeDaoIF;
import com.br.ifpb.valueObject.Usuario;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public class GerenciarAmizade {

    public boolean verificarAmizade(int remetente, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        AmizadeDaoIF amizadeDao = daoFactory.criarAmizadeDao();
        return amizadeDao.verificarAmizade(remetente, destinatario);
    }

    public List<Usuario> listaDeSolicitacoes(int id) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        AmizadeDaoIF amizadeDao = daoFactory.criarAmizadeDao();
        return amizadeDao.listaDeSolicitacoes(id);
    }

    public void convidarAmizade(int remetente, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        AmizadeDaoIF amizadeDao = daoFactory.criarAmizadeDao();
        amizadeDao.solicitarAmizade(remetente, destinatario);
    }

    public void aceitarSolicitacao(int remetente, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        AmizadeDaoIF amizadeDao = daoFactory.criarAmizadeDao();
        amizadeDao.aceitarSolicitacao(remetente, destinatario);
    }

    public void desfazerAmizade(int remetente, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        AmizadeDaoIF amizadeDao = daoFactory.criarAmizadeDao();
        amizadeDao.desfazerAmizade(remetente, destinatario);
    }

    public List<Usuario> getAmigos(int idUsuario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        AmizadeDaoIF amizadeDao = daoFactory.criarAmizadeDao();
        return amizadeDao.getAmigos(idUsuario);
    }

    public boolean existeSolicitacao(int remetente, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        AmizadeDaoIF amizadeDao = daoFactory.criarAmizadeDao();
        return amizadeDao.existeSolicitacao(remetente, destinatario);
    }
}
