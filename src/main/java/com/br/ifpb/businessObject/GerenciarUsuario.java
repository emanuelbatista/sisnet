package com.br.ifpb.businessObject;

import com.br.ifpb.interfaceDao.UsuarioDaoIF;
import java.util.List;
import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import java.sql.Date;
import com.br.ifpb.valueObject.Usuario;

public class GerenciarUsuario {

    public GerenciarUsuario() {

    }

    public void cadastrarConta(String nome, String sobrenome, String apelido, String cidade, String email, String profissao,
            String senha, Date data_nascimento, String status, String foto, List<String> locais_estudou, List<String> locais_trabalhou) throws PersistenciaException {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setApelido(apelido);
        usuario.setCidade(cidade);
        usuario.setEmail(email);
        usuario.setProfissao(profissao);
        usuario.setSenha(senha);
        usuario.setData_nascimento(data_nascimento);
        usuario.setStatus(status);
        usuario.setFoto(foto);
        usuario.setLocais_estudou(locais_estudou);
        usuario.setLocais_trabalhou(locais_trabalhou);

        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuarioDao = fabrica.criarUsuarioDao();
        usuarioDao.criar(usuario);
    }

    public void excluirConta(int id) throws PersistenciaException {

        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuarioDao = fabrica.criarUsuarioDao();
        usuarioDao.excluir(id);
    }

    public void atualizarConta(int id, String nome, String sobrenome, String apelido, String cidade, String email, String profissao,
            String senha, Date data_nascimento, String status) throws PersistenciaException {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setApelido(apelido);
        usuario.setCidade(cidade);
        usuario.setEmail(email);
        usuario.setProfissao(profissao);
        usuario.setSenha(senha);
        usuario.setData_nascimento(data_nascimento);
        usuario.setStatus(status);
        usuario.setSobrenome(sobrenome);

        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuarioDao = fabrica.criarUsuarioDao();
        usuarioDao.atualizarInformacoes(usuario);
    }

    public boolean logar(String email, String senha) throws PersistenciaException {
        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuarioDao = fabrica.criarUsuarioDao();
        return usuarioDao.logar(email, senha);
    }

    public Usuario getUsuario(int id) throws PersistenciaException {
        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuario = fabrica.criarUsuarioDao();
        return usuario.getUsuario(id);
    }

    public boolean verificarExistenciaEmail(String email) throws PersistenciaException {
        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuario = fabrica.criarUsuarioDao();
        return usuario.verficarExistenciaEmail(email);
    }

    public Usuario getUsuario(String email) throws PersistenciaException {
        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuario = fabrica.criarUsuarioDao();
        return usuario.getUsuario(email);
    }

    public void atualizarFotoPerfil(String caminho, int id) throws PersistenciaException {
        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuario = fabrica.criarUsuarioDao();
        usuario.atualizarImagemPerfil(caminho, id);
    }

    public void adicionarLocalTrabalho(String local, int idUsuario) throws PersistenciaException {
        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuario = fabrica.criarUsuarioDao();
        usuario.adicionarLocalTrabalho(local, idUsuario);
    }

    public void adicionarLocalEstudou(String local, int idUsuario) throws PersistenciaException {
        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuario = fabrica.criarUsuarioDao();
        usuario.adicionarLocalEstudou(local, idUsuario);
    }

    public void removerLocalTrabalho(String local, int idUsuario) throws PersistenciaException {
        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuario = fabrica.criarUsuarioDao();
        usuario.removerLocalTrabalho(local, idUsuario);
    }

    public void removerLocalEstudou(String local, int idUsuario) throws PersistenciaException {
        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuario = fabrica.criarUsuarioDao();
        usuario.removerLocalEstudou(local, idUsuario);
    }

    public List<Usuario> pesquisarUsuario(String pesquisa) throws PersistenciaException{
        DaoFactoryIF fabrica = DaoFactory.createFactory();
        UsuarioDaoIF usuario = fabrica.criarUsuarioDao();
        return usuario.pesquisarUsuario(pesquisa);
    }
}
