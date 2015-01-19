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
}
