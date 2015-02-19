/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.businessObject;

import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.ComentarioDaoIF;
import com.br.ifpb.valueObject.Comentario;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public class GerenciarComentario {
    
    public List<Comentario> comentarioTopico(int idTopico) throws PersistenciaException{
        DaoFactoryIF daoFactory=DaoFactory.createFactory();
        ComentarioDaoIF comentarioDao=daoFactory.criarComentarioDao();
        return comentarioDao.comentarioTopico(idTopico);
    }
    
    public void criarComentario(String texto,Timestamp data,int idTopico,int idUsuario) throws PersistenciaException{
        DaoFactoryIF daoFactory=DaoFactory.createFactory();
        ComentarioDaoIF comentarioDao=daoFactory.criarComentarioDao();
        comentarioDao.criarComentario(texto, data, idTopico, idUsuario);
    }
}
