/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.interfaceDao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Topico;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public interface TopicoDaoIF {
    
    public List<Topico> topicoGrupo(int idGrupo) throws PersistenciaException;
    
}
