package com.br.ifpb.jdbcDaoPostgreSql;

import com.br.ifpb.conexaoBanco.ConexaoBanco;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.RelacaoDaoIF;
import com.br.ifpb.valueObject.Relacao;
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
    public List<Relacao> getRelacao(int idUsuario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT * FROM Relacao "
                    + "WHERE usuario_1=? AND pendencia=FALSE";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idUsuario);
            ResultSet rs = stat.executeQuery();
            List<Relacao> lista = new ArrayList<>();
            while (rs.next()) {
                Relacao relacao = new Relacao();
                relacao.setId(rs.getInt("id"));
                UsuarioDAO usuario = new UsuarioDAO();
                relacao.setUsuario_1(usuario.getUsuario(rs.getInt("usuario_1")));
                relacao.setUsuario_2(usuario.getUsuario(rs.getInt("usuario_2")));
                relacao.setTipo(rs.getString("tipo"));
                relacao.setPendencia(rs.getBoolean("pendencia"));
                lista.add(relacao);
            }
            if (lista.size() > 0) {
                return lista;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public String tipoRelacao(int remetente, int destinatario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT tipo FROM Relacao WHERE usuario_1=? AND usuario_2=? AND pendencia=FALSE";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, remetente);
            stat.setInt(2, destinatario);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getString("tipo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Usuario> solicitacaoRelacao(int id) throws PersistenciaException {
        return null;
    }

    @Override
    public void adicionarRelacao(Relacao relacao) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql="INSERT INTO Relacao(usuario_1,pendencia,tipo,usuario_2) VALUES (?,?,?,?)";
            PreparedStatement stat=con.prepareStatement(sql);
            stat.setInt(1, relacao.getUsuario_1().getId());
            stat.setBoolean(2, false);
            stat.setString(3, relacao.getTipo());
            stat.setInt(4, relacao.getUsuario_2().getId());
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
