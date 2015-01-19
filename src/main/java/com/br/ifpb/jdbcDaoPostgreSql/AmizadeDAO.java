package com.br.ifpb.jdbcDaoPostgreSql;

import com.br.ifpb.conexaoBanco.ConexaoBanco;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.AmizadeDaoIF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.br.ifpb.valueObject.Usuario;

/**
 * Classe Responsável por <b>manipular</b> na tabela <i>Amizade</i> no Banco de
 * Dados Postgre SQL
 *
 * @author Emanuel
 * @version 1.0
 */
public class AmizadeDAO implements AmizadeDaoIF {

    @Override
    public void solicitarAmizade(int remetente, int destinatario)
            throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "INSERT INTO amizade(usuario_1,pedencia,usuario_2) VALUES (?,?,?)";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, remetente);
            stat.setBoolean(2, true);
            stat.setInt(3, destinatario);
            stat.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }

    }

    @Override
    public boolean aceitarSolicitacao(int remetente, int destinatario)
            throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "UPDATE amizade SET pedencia=false WHERE usuario_1=? AND usuario_2=?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, remetente);
            stat.setInt(2, destinatario);
            stat.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new PersistenciaException();
        } finally {
            return false;
        }

    }

    /**
     * Responsável por <b>rejeitar</b> uma solicitação de amizade
     *
     * @param remetente
     * @param destinatario
     * @return boolean
     * @throws PersistenciaException
     */
    @Override
    public boolean rejeitarSolicitacao(int remetente, int destinatario)
            throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "DELETE FROM Amizade WHERE usuario_1=? AND usuario_2=?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, remetente);
            stat.setInt(2, destinatario);
            stat.executeUpdate();
            return true;
        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {
            throw new PersistenciaException();
        } finally {
            return false;
        }
    }

    /**
     * Responsável por <b>listar</b> todos as solicitações de amizades
     *
     * @param id
     * @return List de Usuario
     * @throws PersistenciaException
     */
    @Override
    public List<Usuario> listaDeSolicitacoes(int id)
            throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "(SELECT * FROM Usuario usuario_1 NATURAL JOIN (SELECT usuario_1 as id FROM Amizade \n"
                    + "WHERE usuario_2=? AND pendencia=TRUE) amigos_1)\n"
                    + "UNION \n"
                    + "(SELECT * FROM Usuario usuario_2 NATURAL JOIN (SELECT usuario_2 as id FROM Amizade\n"
                    + "WHERE usuario_1=? AND pendencia=TRUE) amigos_2)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, id);
            stat.setInt(2, id);
            ResultSet rs = stat.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setApelido(rs.getString("apelido"));
                usuario.setId(rs.getInt("id"));
                usuario.setFoto(rs.getString("foto"));
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return null;
        }
    }

    /**
     * Responsável por <b>listar</b> todos os amigos
     *
     * @param id
     * @return Lista de Usuario
     * @throws PersistenciaException
     */
    @Override
    public List<Usuario> getAmigos(int id) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "(SELECT * FROM Usuario usuario_1 NATURAL JOIN (SELECT usuario_1 as id FROM Amizade \n"
                    + "WHERE usuario_2=? AND pendencia=FALSE) amigos_1)\n"
                    + "UNION \n"
                    + "(SELECT * FROM Usuario usuario_2 NATURAL JOIN (SELECT usuario_2 as id FROM Amizade\n"
                    + "WHERE usuario_1=? AND pendencia=FALSE) amigos_2)";
            PreparedStatement stat = con.prepareCall(sql);
            stat.setInt(1, id);
            stat.setInt(2, id);
            ResultSet res = stat.executeQuery();
            List<Usuario> lista = new ArrayList();
            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id"));
                usuario.setNome(res.getString("nome"));
                usuario.setFoto(res.getString("foto"));
                usuario.setApelido(res.getString("apelido"));
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return null;
        }

    }

    @Override
    public boolean verificarAmizade(int remetente, int destinatario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
           String sql="SELECT * FROM Amizade WHERE usuario_1=? AND usuario_2=? AND pendencia=FALSE OR"
                   +" usuario_1=? AND usuario_2=? AND pendencia=FALSE";
           PreparedStatement stat=con.prepareCall(sql);
           stat.setInt(1, remetente);
           stat.setInt(2, destinatario);
           stat.setInt(3, destinatario);
           stat.setInt(4, remetente);
           ResultSet rs=stat.executeQuery();
           return rs.next();
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AmizadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
