/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.businessObject;

import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.GrupoDaoIF;
import com.br.ifpb.valueObject.Grupo;
import com.br.ifpb.valueObject.Usuario;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public class GerenciarGrupo {

    public Usuario fundador(int idGrupo) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        GrupoDaoIF grupoDao = daoFactory.criarGrupoDao();
        return grupoDao.fundador(idGrupo);
    }

    public Grupo getGrupo(int id) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        GrupoDaoIF grupoDao = daoFactory.criarGrupoDao();
        return grupoDao.getGrupo(id);
    }

    public boolean participaGrupo(int idGrupo,int idUsuario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        GrupoDaoIF grupoDao = daoFactory.criarGrupoDao();
        return grupoDao.participaGrupo(idGrupo,idUsuario);
    }

    public List<Usuario> listaDeParticipantes(int idGrupo) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        GrupoDaoIF grupoDao = daoFactory.criarGrupoDao();
        return grupoDao.listaDeParticipantes(idGrupo);
    }

    public List<Grupo> listarGrupos(int idUsuario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        GrupoDaoIF grupoDao = daoFactory.criarGrupoDao();
        return grupoDao.listarGrupos(idUsuario);
    }

    public void participarDoGrupo(int idGrupo, int idUsuario) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        GrupoDaoIF grupoDao = daoFactory.criarGrupoDao();
        grupoDao.participarDoGrupo(idGrupo, idUsuario);
    }

    public void criar(String nome, String descricao, Usuario usuario) throws PersistenciaException {
        Grupo grupo = new Grupo();
        grupo.setNome(nome);
        grupo.setDescricao(descricao);
        grupo.setFundador(usuario);

        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        GrupoDaoIF grupoDao = daoFactory.criarGrupoDao();
        grupoDao.criar(grupo);

    }

    public Grupo ultimoGrupo() throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        GrupoDaoIF grupoDao = daoFactory.criarGrupoDao();
        return grupoDao.ultimoGrupo();
    }

    public List<Grupo> pesquisarGrupo(String pesquisa) throws PersistenciaException {
        DaoFactoryIF daoFactory = DaoFactory.createFactory();
        GrupoDaoIF grupoDao = daoFactory.criarGrupoDao();
        return grupoDao.pesquisarGrupo(pesquisa);
    }
}
