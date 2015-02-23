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
import java.util.LinkedList;
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
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "INSERT INTO Grupo(nome,descricao,usuario) VALUES (?,?,?)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, grupo.getNome());
            stat.setString(2, grupo.getDescricao());
            stat.setInt(3, grupo.getFundador().getId());
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Usuario> listaDeParticipantes(int idGrupo) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT U.id,U.nome,U.foto FROM Usuario U JOIN participa_grupo P ON U.id=P.usuario WHERE id_grupo=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idGrupo);
            ResultSet rs = stat.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setFoto(rs.getString("foto"));
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                lista.add(usuario);
            }
            if (lista.size() > 0) {
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
    public List<Grupo> listarGrupos(int idUsuario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT G.nome,G.id,G.descricao FROM Grupo G JOIN participa_grupo P ON G.id=P.id_grupo WHERE P.usuario=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idUsuario);
            ResultSet rs = stat.executeQuery();
            List<Grupo> lista = new ArrayList<>();
            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setDescricao(rs.getString("descricao"));
                grupo.setId(rs.getInt("id"));
                grupo.setNome(rs.getString("nome"));
                lista.add(grupo);
            }
            if (lista.size() > 0) {
                return lista;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    @Override
    public void participarDoGrupo(int idGrupo, int idUsuario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "INSERT INTO participa_grupo(id_grupo,usuario) VALUES (?,?)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idGrupo);
            stat.setInt(2, idUsuario);
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public Grupo ultimoGrupo() throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT MAX(id) id FROM Grupo";
            PreparedStatement stat = con.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setId(rs.getInt("id"));
                return grupo;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        }
        return null;
    }

    @Override
    public List<Grupo> pesquisarGrupo(String pesquisa) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
           String sql="SELECT id,nome FROM Grupo WHERE nome ilike ? OR descricao ilike ?";
           PreparedStatement stat=con.prepareStatement(sql);
           stat.setString(1, "%"+pesquisa+"%");
           stat.setString(2, "%"+pesquisa+"%");
           ResultSet rs=stat.executeQuery();
           List<Grupo> grupos=new LinkedList<>();
           while(rs.next()){
               Grupo grupo=new Grupo();
               grupo.setNome(rs.getString("nome"));
               grupo.setId(rs.getInt("id"));
               grupos.add(grupo);
           }
           return grupos.isEmpty()?null:grupos;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        }
    }

}
