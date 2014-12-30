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
import com.br.ifpb.valueobject.Usuario;

/**
 * Classe Responsável por <b>manipular</b> na tabela <i>Amizade</i> no Banco de
 * Dados Postgre SQL
 *
 * @author Emanuel
 * @version 1.0
 */
public class AmizadeDAO implements AmizadeDaoIF {

    @Override
    public void solicitarAmizade(String remetente, String destinatario)
            throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "INSERT INTO amizade(usuario_1,pedencia,usuario_2) VALUES (?,?,?)";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, remetente);
            stat.setBoolean(2, true);
            stat.setString(3, destinatario);
            stat.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }

    }

    @Override
    public boolean aceitarSolicitacao(String remetente, String destinatario)
            throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "UPDATE amizade SET pedencia=false WHERE usuario_1=? AND usuario_2=?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, remetente);
            stat.setString(2, destinatario);
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
    public boolean rejeitarSolicitacao(String remetente, String destinatario)
            throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "DELETE FROM Amizade WHERE usuario_1=? AND usuario_2=?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, remetente);
            stat.setString(2, destinatario);
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
     * @param email
     * @return List de Usuario
     * @throws PersistenciaException
     */
    @Override
    public List<Usuario> listaDeSolicitacoes(String email)
            throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "(SELECT * FROM Usuario usuario_1 NATURAL JOIN (SELECT usuario_1 as email FROM Amizade \n"
                    + "WHERE usuario_2=? AND pendencia=TRUE) amigos_1)\n"
                    + "UNION \n"
                    + "(SELECT * FROM Usuario usuario_2 NATURAL JOIN (SELECT usuario_2 as email FROM Amizade\n"
                    + "WHERE usuario_1=? AND pendencia=TRUE) amigos_2)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, email);
            stat.setString(2, email);
            ResultSet rs = stat.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setApelido(rs.getString("apelido"));
                usuario.setEmail(rs.getString("email"));
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
     * @param email
     * @return Lista de Usuario
     * @throws PersistenciaException
     */
    @Override
    public List<Usuario> getAmigos(String email) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "(SELECT * FROM Usuario usuario_1 NATURAL JOIN (SELECT usuario_1 as email FROM Amizade \n"
                    + "WHERE usuario_2=? AND pendencia=FALSE) amigos_1)\n"
                    + "UNION \n"
                    + "(SELECT * FROM Usuario usuario_2 NATURAL JOIN (SELECT usuario_2 as email FROM Amizade\n"
                    + "WHERE usuario_1=? AND pendencia=FALSE) amigos_2)";
            PreparedStatement stat = con.prepareCall(sql);
            stat.setString(1, email);
            stat.setString(2, email);
            ResultSet res = stat.executeQuery();
            List<Usuario> lista = new ArrayList();
            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setEmail(res.getString("email"));
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
}
