package com.br.ifpb.daoFactory;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.AmizadeDaoIF;
import com.br.ifpb.interfaceDao.ComentarioDaoIF;
import com.br.ifpb.interfaceDao.GrupoDaoIF;
import com.br.ifpb.interfaceDao.MensagemDaoIF;
import com.br.ifpb.interfaceDao.RelacaoDaoIF;
import com.br.ifpb.interfaceDao.TopicoDaoIF;
import com.br.ifpb.jdbcDaoPostgreSql.UsuarioDAO;
import com.br.ifpb.interfaceDao.UsuarioDaoIF;
import com.br.ifpb.jdbcDaoPostgreSql.AmizadeDAO;
import com.br.ifpb.jdbcDaoPostgreSql.ComentarioDAO;
import com.br.ifpb.jdbcDaoPostgreSql.GrupoDAO;
import com.br.ifpb.jdbcDaoPostgreSql.MensagemDAO;
import com.br.ifpb.jdbcDaoPostgreSql.RelacaoDao;
import com.br.ifpb.jdbcDaoPostgreSql.TopicoDAO;

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

    @Override
    public RelacaoDaoIF criarRelacaoDao() throws PersistenciaException {
       return new RelacaoDao();
    }

    @Override
    public GrupoDaoIF criarGrupoDao() throws PersistenciaException {
        return new GrupoDAO();
    }

    @Override
    public TopicoDaoIF criarTopicoDao() throws PersistenciaException {
       return new TopicoDAO();
    }

    @Override
    public ComentarioDaoIF criarComentarioDao() throws PersistenciaException {
        return new ComentarioDAO();
    }
    
    

}
