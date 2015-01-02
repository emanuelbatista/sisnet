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
    public List<Mensagem> minhasMensagens(String email) throws PersistenciaException{
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT * FROM Mensagem WHERE usuario=? ORDER BY data ASC";
            PreparedStatement stat=con.prepareCall(sql);
            stat.setString(1, email);
            ResultSet rs=stat.executeQuery();
            List<Mensagem> lista=new ArrayList<>();
            while(rs.next()){
                Mensagem mensagem=new Mensagem();
                mensagem.setId(rs.getInt("id"));
                mensagem.setTexto(rs.getString("texto"));
                mensagem.setData(rs.getTimestamp("data"));
                mensagem.setUsuario(rs.getString("usuario"));
                lista.add(mensagem);
            }
            return lista;
        } catch (SQLException ex) {
            throw new PersistenciaException();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    @Override
    public List<Mensagem> mensagensAmigos(String email) throws PersistenciaException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
