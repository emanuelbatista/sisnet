/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.jdbcDaoPostgreSql;

import com.br.ifpb.conexaoBanco.ConexaoBanco;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.MensagemDaoIF;
import com.br.ifpb.valueObject.Mensagem;
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
public class MensagemDAO implements MensagemDaoIF {

    @Override
    public List<Mensagem> minhasMensagens(int id) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT * FROM Mensagem WHERE usuario=? ORDER BY data DESC";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            List<Mensagem> lista = new ArrayList<>();
            while (rs.next()) {
                Mensagem mensagem = new Mensagem();
                mensagem.setId(rs.getInt("id"));
                mensagem.setTexto(rs.getString("texto"));
                mensagem.setData(rs.getTimestamp("data"));
                UsuarioDAO usuario=new UsuarioDAO();
                mensagem.setUsuario(usuario.getUsuario(rs.getInt("usuario")));
                lista.add(mensagem);
            }
            return lista;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Mensagem> mensagensAmigos(int id) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT M.id,texto,data,usuario FROM Mensagem M,\n"
                    + "( (SELECT id FROM Usuario usuario_1 NATURAL JOIN (SELECT usuario_1 as id FROM Amizade\n"
                    + "WHERE usuario_2=? AND pendencia=FALSE) amigos_1)\n"
                    + "UNION\n"
                    + "(SELECT id FROM Usuario usuario_2 NATURAL JOIN (SELECT usuario_2 as id FROM Amizade\n"
                    + " WHERE usuario_1=? AND pendencia=FALSE) amigos_2)"
                    + "UNION"
                    + "(SELECT id FROM Usuario WHERE id=?)) A\n"
                    + " WHERE M.usuario=A.id ORDER BY data DESC ";
            ResultSet rs;
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, id);
            stat.setInt(2, id);
            stat.setInt(3, id);
            rs = stat.executeQuery();
            List<Mensagem> lista = new ArrayList<>();
            while (rs.next()) {
                Mensagem mensagem = new Mensagem();
                mensagem.setId(rs.getInt("id"));
                mensagem.setData(rs.getTimestamp("data"));
                mensagem.setTexto(rs.getString("texto"));
                UsuarioDAO usuario=new UsuarioDAO();
                mensagem.setUsuario(usuario.getUsuario(rs.getInt("usuario")));
                lista.add(mensagem);
            }
            return lista;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean publicarMensagem(Mensagem mensagem) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "INSERT INTO Mensagem(texto,data,usuario) VALUES (?,?,?)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, mensagem.getTexto());
            stat.setTimestamp(2, mensagem.getData());
            stat.setInt(3, mensagem.getUsuario().getId());
            stat.executeUpdate();
            return true;

        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return false;
        }
    }

}
