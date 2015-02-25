package com.br.ifpb.jdbcDaoPostgreSql;

import com.br.ifpb.conexaoBanco.ConexaoBanco;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.RelacaoDaoIF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.br.ifpb.valueObject.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe Responsável por <b>manipular</b> na tabela <i>Relacao</i> no Banco de
 * Dados Postgre SQL
 *
 * @author Emanuel
 */
public class RelacaoDao implements RelacaoDaoIF {

    @Override
    public Usuario relacaoNamoro(int id) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT * FROM Usuario WHERE id=relacao(?,'Namoro')";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            rs.next();
            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("nome"));
            usuario.setFoto(rs.getString("foto"));
            usuario.setEmail(rs.getString("email"));
            return usuario;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Usuario> getRelacao(int idUsuario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "(SELECT * FROM Usuario usuario_1 NATURAL JOIN (SELECT usuario_1 as id FROM Relacao \n"
                    + "WHERE usuario_2=? AND pendencia=FALSE) amigos_1)\n"
                    + "UNION \n"
                    + "(SELECT * FROM Usuario usuario_2 NATURAL JOIN (SELECT usuario_2 as id FROM Relacao\n"
                    + "WHERE usuario_1=? AND pendencia=FALSE) amigos_2)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idUsuario);
            stat.setInt(2, idUsuario);
            ResultSet rs = stat.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setId(rs.getInt("id"));
                usuario.setFoto(rs.getString("foto"));
                lista.add(usuario);
            }
            if (lista.size() > 0) {
                return lista;
            } else {
                return null;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }

    }

    @Override
    public String tipoRelacao(int remetente, int destinatario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT tipo FROM Relacao WHERE "
                    + "usuario_1=? AND usuario_2=? OR usuario_1=? AND usuario_2=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, remetente);
            stat.setInt(2, destinatario);
            stat.setInt(3, destinatario);
            stat.setInt(4, remetente);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getString("tipo");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        }
        return null;
    }

    @Override
    public List<Usuario> solicitacaoRelacao(int idUsuario) throws PersistenciaException {
         try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "(SELECT * FROM Usuario usuario_1 NATURAL JOIN (SELECT usuario_1 as id FROM Relacao \n"
                    + "WHERE usuario_2=? AND pendencia=TRUE) amigos_1)\n";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idUsuario);
            ResultSet rs = stat.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setId(rs.getInt("id"));
                usuario.setFoto(rs.getString("foto"));
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        } 

    }

    @Override
    public void adicionarRelacao(int remetente, int destinatario, String tipoRelcao) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "INSERT INTO Relacao(usuario_1,pendencia,tipo,usuario_2) VALUES (?,?,?,?)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, remetente);
            stat.setBoolean(2, true);
            stat.setString(3, tipoRelcao);
            stat.setInt(4, destinatario);
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean existeRelacao(int remetente, int destinatario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT * FROM Relacao WHERE "
                    + "usuario_1=? AND usuario_2=? OR usuario_1=? AND usuario_2=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, remetente);
            stat.setInt(2, destinatario);
            stat.setInt(3, destinatario);
            stat.setInt(4, remetente);
            ResultSet rs=stat.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
           throw new PersistenciaException(ex);
        }
    }

    @Override
    public void aceitarRelacao(int remetente,int destinatario) throws PersistenciaException {
         try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "UPDATE Relacao SET pendencia=false WHERE usuario_2=? AND usuario_1=?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, remetente);
            stat.setInt(2, destinatario);
            stat.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenciaException();
        }
    }

    @Override
    public void desfazerRelacao(int remetente,int destinatario) throws PersistenciaException {
          try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "DELETE FROM Relacao WHERE usuario_1=? AND usuario_2=? OR usuario_2=? AND usuario_1=?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, remetente);
            stat.setInt(2, destinatario);
            stat.setInt(3, remetente);
            stat.setInt(4, destinatario);
            stat.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenciaException();
        } 
    }

}
