package com.br.ifpb.daoFactory;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.jdbcDaoPostgreSql.UsuarioDAO;
import com.br.ifpb.interfaceDao.UsuarioDaoIF;

public class DaoFactoryBD implements DaoFactoryIF {

	@Override
	public UsuarioDaoIF criarUsuarioDao() throws PersistenciaException {
		return new UsuarioDAO();
	}

}
