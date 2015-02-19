/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.businessObject;

import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.RelacaoDaoIF;
import com.br.ifpb.valueObject.Relacao;
import com.br.ifpb.valueObject.Usuario;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public class GerenciarRelacao {

    public List<Relacao> getRelacao(int id) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        RelacaoDaoIF relacaoDao = daoFactory.criarRelacaoDao();
        return relacaoDao.getRelacao(id);
    }

    public String tipoRelacao(int remetente, int destinatario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        RelacaoDaoIF relacaoDao = daoFactory.criarRelacaoDao();
        return relacaoDao.tipoRelacao(remetente, destinatario);
    }

    public void adicionarRelacao(Usuario remetente,String tipo,Usuario destinatario) throws PersistenciaException{
        Relacao relacao=new Relacao();
        relacao.setUsuario_1(remetente);
        relacao.setTipo(tipo);
        relacao.setUsuario_2(destinatario);
        
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        RelacaoDaoIF relacaoDao = daoFactory.criarRelacaoDao();
        relacaoDao.adicionarRelacao(relacao);
        
    }
}
