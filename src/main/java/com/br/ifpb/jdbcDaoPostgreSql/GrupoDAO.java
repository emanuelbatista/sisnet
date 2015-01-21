/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.jdbcDaoPostgreSql;

import com.br.ifpb.conexaoBanco.ConexaoBanco;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.GrupoDaoIF;
import com.br.ifpb.valueObject.Grupo;
import com.br.ifpb.valueObject.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class GrupoDAO implements GrupoDaoIF {

    @Override
    public void criar(Grupo grupo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listaDeParticipantes(int idGrupo) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT U.id,U.nome,U.foto FROM Usuario U JOIN participa_grupo P ON U.id=P.usuario WHERE id_grupo=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idGrupo);
            ResultSet rs = stat.executeQuery();
            List<Usuario> lista=new ArrayList<>();
            while(rs.next()){
                Usuario usuario=new Usuario();
                usuario.setFoto(rs.getString("foto"));
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                lista.add(usuario);
            }
            if(lista.size()>0){
                return lista;
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Usuario fundador(int idGrupo) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT U.id,U.nome,U.foto FROM Usuario U JOIN Grupo G ON U.id=G.usuario WHERE G.usuario=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idGrupo);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setFoto(rs.getString("foto"));
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void atualizar(Grupo grupo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(int idGrupo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Grupo> listarGrupos(int id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Grupo getGrupo(int id) throws PersistenciaException {
        try {
            Connection con = ConexaoBanco.getInstance();
            String sql = "SELECT * FROM Grupo WHERE id=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setDescricao(rs.getString("descricao"));
                grupo.setNome(rs.getString("nome"));
                UsuarioDAO usuario = new UsuarioDAO();
                grupo.setFundador(usuario.getUsuario(rs.getInt("usuario")));
                return grupo;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean participaGrupo(int idUsuario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT COUNT(*) quantidade FROM participa_grupo WHERE usuario=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idUsuario);
            ResultSet rs = stat.executeQuery();
            rs.next();
            if (rs.getInt("quantidade") > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
