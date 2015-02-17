/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.interfaceDao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Foto;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public interface FotoDaoIF {
    
    public void publicarFoto(Foto foto) throws PersistenciaException;
    
    public List<Foto> listarFotos(Integer id_usuario) throws PersistenciaException;
    
}
