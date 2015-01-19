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
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Usuario> relacao(int id) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT nome,foto,id FROM Usuario NATURAL JOIN"
                    + " (SELECT usuario_2 id FROM Relacao "
                    + "WHERE usuario_1=? AND pendencia=FALSE) relacao_2";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, id);
            ResultSet rs=stat.executeQuery();
            List<Usuario> lista=new ArrayList<>();
            while(rs.next()){
                Usuario usuario=new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setFoto(rs.getString("foto"));
                usuario.setNome(rs.getString("nome"));
                lista.add(usuario);
            }
            if(lista.size()>0){
                return lista;
            }else return null;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public String tipoRelacao(int remetente, int destinatario) throws PersistenciaException {
        try(Connection con=ConexaoBanco.getInstance()){
            String sql="SELECT tipo FROM Relacao WHERE usuario_1=? AND usuario_2=? AND pendencia=FALSE";
            PreparedStatement stat=con.prepareStatement(sql);
            stat.setInt(1, remetente);
            stat.setInt(2, destinatario);
            ResultSet rs=stat.executeQuery();
            if(rs.next()){
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

}
