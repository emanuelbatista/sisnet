package com.br.ifpb.daoFactory;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.AmizadeDaoIF;
import com.br.ifpb.interfaceDao.MensagemDaoIF;
import com.br.ifpb.jdbcDaoPostgreSql.UsuarioDAO;
import com.br.ifpb.interfaceDao.UsuarioDaoIF;
import com.br.ifpb.jdbcDaoPostgreSql.AmizadeDAO;
import com.br.ifpb.jdbcDaoPostgreSql.MensagemDAO;

public class DaoFactoryBD implements DaoFactoryIF {

    @Override
    public UsuarioDaoIF criarUsuarioDao() throws PersistenciaException {
        return new UsuarioDAO();
    }

    @Override
    public MensagemDaoIF criarMensagemDao() throws PersistenciaException {
        return new MensagemDAO();
    }

    @Override
    public AmizadeDaoIF criarAmizadeDao() throws PersistenciaException {
       return new AmizadeDAO();
    }

}
