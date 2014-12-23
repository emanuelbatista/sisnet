package com.br.ifpb.businessobject;

import com.br.ifpb.interfaceDao.UsuarioDaoIF;
import java.util.List;
import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import java.sql.Date;
import com.br.ifpb.valueobject.Usuario;

public class GerenciarUsuario {

	public void cadastrarConta(String nome,String apelido, String cidade,String email,String profissao,
			String senha,Date data_nascimento,String status,String foto,List<String> locais_estudou,List<String> locais_trabalhou) throws PersistenciaException{
		Usuario usuario=new Usuario();
		usuario.setNome(nome);
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
        
		DaoFactoryIF fabrica=DaoFactory.createFactory();
		UsuarioDaoIF usuarioDao=fabrica.criarUsuarioDao();
		usuarioDao.criar(usuario);
	}
	
	public void excluirConta(String email) throws PersistenciaException{
		
		DaoFactoryIF fabrica=DaoFactory.createFactory();
		UsuarioDaoIF usuarioDao=fabrica.criarUsuarioDao();
		usuarioDao.excluir(email);
	}
	
	public void atualizarConta(String nome,String apelido, String cidade,String email,String profissao,
			String senha,Date data_nascimento,String status,String foto,List<String> locais_estudou,List<String> locais_trabalhou) throws PersistenciaException{
		Usuario usuario=new Usuario();
		usuario.setNome(nome);
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
		
		DaoFactoryIF fabrica=DaoFactory.createFactory();
		UsuarioDaoIF usuarioDao=fabrica.criarUsuarioDao();
		usuarioDao.atualizar(usuario);
	}
	
	public boolean logar(String email,String senha) throws PersistenciaException{
		DaoFactoryIF fabrica=DaoFactory.createFactory();
		UsuarioDaoIF usuarioDao=fabrica.criarUsuarioDao();
		return usuarioDao.logar(email, senha);
	}
	
	public Usuario getUsuario(String email) throws PersistenciaException{
		DaoFactoryIF fabrica=DaoFactory.createFactory();
		UsuarioDaoIF usuario=fabrica.criarUsuarioDao();
		return usuario.getUsuario(email);
	}
        
        public boolean verificarExistenciaEmail(String email) throws PersistenciaException{
            DaoFactoryIF fabrica=DaoFactory.createFactory();
            UsuarioDaoIF usuario=fabrica.criarUsuarioDao();
            return usuario.verficarExistenciaEmail(email);
        }
}
