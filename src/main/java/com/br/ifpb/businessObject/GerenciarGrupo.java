/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.businessObject;

import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.GrupoDaoIF;
import com.br.ifpb.valueObject.Grupo;
import com.br.ifpb.valueObject.Usuario;

/**
 *
 * @author Emanuel
 */
public class GerenciarGrupo {
    public Usuario fundador(int idGrupo) throws PersistenciaException{
        DaoFactoryIF daoFactory=DaoFactory.createFactory();
        GrupoDaoIF grupoDao=daoFactory.criarGrupoDao();
        return grupoDao.fundador(idGrupo);
    }
    
    public Grupo getGrupo(int id) throws PersistenciaException{
        DaoFactoryIF daoFactory=DaoFactory.createFactory();
        GrupoDaoIF grupoDao=daoFactory.criarGrupoDao();
        return grupoDao.getGrupo(id);
    }
}
