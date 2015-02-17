package com.br.ifpb.businessObject;

import com.br.ifpb.daoFactory.DaoFactory;
import com.br.ifpb.daoFactory.DaoFactoryIF;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.FotoDaoIF;
import com.br.ifpb.valueObject.Foto;
import com.br.ifpb.valueObject.Usuario;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 16/02/2015 ás 21:36:10
 */
public class GerenciarFotos {

    public void publicarFoto(String url, Timestamp data, Usuario usuario) throws PersistenciaException {
        Foto foto = new Foto();
        foto.setData(data);
        foto.setUrl(url);
        foto.setUsuario(usuario);
        DaoFactoryIF daoFactory =DaoFactory.createFactory();
        FotoDaoIF fotoDao=daoFactory.criarFotoDao();
        fotoDao.publicarFoto(foto);
    }
    
    public List<Foto> listarFotos(Integer id_usuario) throws PersistenciaException{
        DaoFactoryIF daoFactory =DaoFactory.createFactory();
        FotoDaoIF fotoDao=daoFactory.criarFotoDao();
        return fotoDao.listarFotos(id_usuario);
    }
}
