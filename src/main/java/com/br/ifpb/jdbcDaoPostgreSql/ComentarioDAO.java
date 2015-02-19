/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.jdbcDaoPostgreSql;

import com.br.ifpb.conexaoBanco.ConexaoBanco;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.ComentarioDaoIF;
import com.br.ifpb.valueObject.Comentario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class ComentarioDAO implements ComentarioDaoIF {

    @Override
    public List<Comentario> comentarioTopico(int idTopico) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT * FROM Comentario WHERE id_topico=? ORDER BY data DESC";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idTopico);
            ResultSet rs = stat.executeQuery();
            List<Comentario> lista = new ArrayList<>();
            while (rs.next()) {
               Comentario comentario=new Comentario();
               comentario.setId(rs.getInt("id"));
               comentario.setTexto(rs.getString("texto"));
               comentario.setData(rs.getTimestamp("data"));
               UsuarioDAO usuario=new UsuarioDAO();
               comentario.setUsuario(usuario.getUsuario(rs.getInt("usuario")));
               lista.add(comentario);
            }
            if(lista.size()>0){
                return lista;
            }
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void criarComentario(String texto, Timestamp data, int idTopico, int idUsuario) throws PersistenciaException {
      try(Connection con=ConexaoBanco.getInstance()){
          String sql="INSERT INTO comentario(texto,data,id_topico,usuario) VALUES (?,?,?,?)";
          PreparedStatement stat=con.prepareStatement(sql);
          stat.setString(1, texto);
          stat.setTimestamp(2, data);
          stat.setInt(3, idTopico);
          stat.setInt(4, idUsuario);
          stat.executeUpdate();
      } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
