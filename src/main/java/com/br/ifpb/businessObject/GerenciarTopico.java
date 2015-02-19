/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.businessObject;

import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.TopicoDaoIF;
import com.br.ifpb.valueObject.Topico;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public class GerenciarTopico {
    public List<Topico> topicoGrupo(int idGrupo) throws PersistenciaException{
        DaoFactoryIF daoFactory=DaoFactory.createFactory();
        TopicoDaoIF topicoDao=daoFactory.criarTopicoDao();
        return topicoDao.topicoGrupo(idGrupo);
    }
    
    public void criarTopico(int idUsuario,int idGrupo,String texto,Timestamp data) throws PersistenciaException{
        DaoFactoryIF daoFactory=DaoFactory.createFactory();
        TopicoDaoIF topicoDao=daoFactory.criarTopicoDao();
        topicoDao.criarTopico(idUsuario, idGrupo, texto, data);
    }
}
