package com.br.ifpb.daoFactory;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.AmizadeDaoIF;
import com.br.ifpb.interfaceDao.ComentarioDaoIF;
import com.br.ifpb.interfaceDao.GrupoDaoIF;
import com.br.ifpb.interfaceDao.MensagemDaoIF;
import com.br.ifpb.interfaceDao.RelacaoDaoIF;
import com.br.ifpb.interfaceDao.TopicoDaoIF;
import com.br.ifpb.interfaceDao.UsuarioDaoIF;

public interface DaoFactoryIF {

    public UsuarioDaoIF criarUsuarioDao() throws PersistenciaException;

    public MensagemDaoIF criarMensagemDao() throws PersistenciaException;
    
    public AmizadeDaoIF criarAmizadeDao() throws PersistenciaException;
    
    public RelacaoDaoIF criarRelacaoDao() throws PersistenciaException;
    
    public GrupoDaoIF criarGrupoDao() throws PersistenciaException;
    
    public TopicoDaoIF criarTopicoDao() throws PersistenciaException;
    
    public ComentarioDaoIF criarComentarioDao() throws PersistenciaException;
    
}
