/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.jdbcDaoPostgreSql;

import com.br.ifpb.conexaoBanco.ConexaoBanco;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.FotoDaoIF;
import com.br.ifpb.valueObject.Foto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class FotoDAO implements FotoDaoIF {

    @Override
    public void publicarFoto(Foto foto) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
           String sql="INSERT INTO(url,data,usuario) VALUES (?,?,?)";
            PreparedStatement stat=con.prepareStatement(sql);
            stat.setString(1, foto.getUrl());
            stat.setTimestamp(2, foto.getData());
            stat.setInt(3, foto.getUsuario().getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
