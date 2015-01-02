package com.br.ifpb.daoFactory;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.MensagemDaoIF;
import com.br.ifpb.interfaceDao.UsuarioDaoIF;

public interface DaoFactoryIF {

    public UsuarioDaoIF criarUsuarioDao() throws PersistenciaException;

    public MensagemDaoIF criarMensagemDao() throws PersistenciaException;
}
