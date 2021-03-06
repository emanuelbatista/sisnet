/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.interfaceDao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Comentario;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public interface ComentarioDaoIF {
    
    public List<Comentario> comentarioTopico(int idTopico) throws PersistenciaException;
    
    public void criarComentario(String texto,Timestamp data,int idTopico,int idUsuario) throws PersistenciaException;
            
}
