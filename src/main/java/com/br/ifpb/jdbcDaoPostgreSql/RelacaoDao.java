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

/**
 * Classe Responsável por <b>manipular</b> na tabela <i>Relacao</i> no Banco de
 * Dados Postgre SQL
 *
 * @author Emanuel
 */
public class RelacaoDao implements RelacaoDaoIF {

    @Override
    public Usuario relacaoNamoro(String email) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT * FROM Usuario WHERE email=relacao(?,'Namoro')";
            PreparedStatement stat = con.prepareCall(sql);
            stat.setString(1, email);
            ResultSet rs = stat.executeQuery();
            rs.next();
            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("nome"));
            usuario.setFoto(rs.getString("foto"));
            usuario.setEmail(rs.getString("email"));
            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Usuario> relacaoFamiliar(String email) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT * FROM Usuario NATURAL JOIN"
                    + " (SELECT usuario_2 email FROM Relacao "
                    + "WHERE usuario_1=? AND tipo<>'Namoro') relacao_2";
            PreparedStatement stat = con.prepareCall(sql);

        } catch (SQLException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public String tipoRelacao(String remetente, String destinatario) throws PersistenciaException {
        return null;
    }

    @Override
    public List<Usuario> solicitacaoRelacao(String email) throws PersistenciaException {
        return null;
    }

}
