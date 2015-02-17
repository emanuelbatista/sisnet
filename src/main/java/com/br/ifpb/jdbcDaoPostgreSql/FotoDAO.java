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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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
           String sql="INSERT INTO Foto(url,data,usuario) VALUES (?,?,?)";
            PreparedStatement stat=con.prepareStatement(sql);
            stat.setString(1, foto.getUrl());
            stat.setTimestamp(2, foto.getData());
            stat.setInt(3, foto.getUsuario().getId());
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public List<Foto> listarFotos(Integer id_usuario) throws PersistenciaException {
        try(Connection con=ConexaoBanco.getInstance()){
            String sql="SELECT * FROM Foto WHERE usuario=?";
            PreparedStatement stat=con.prepareStatement(sql);
            stat.setInt(1, id_usuario);
            ResultSet rs=stat.executeQuery();
            List<Foto> fotos=new LinkedList<>();
            while(rs.next()){
                Foto foto=new Foto();
                foto.setData(rs.getTimestamp("data"));
                foto.setUrl(rs.getString("url"));
                foto.setUsuario(null);
                fotos.add(foto);
            }
            return fotos.isEmpty()?null:fotos;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    

}
