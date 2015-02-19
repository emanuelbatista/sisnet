/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.jdbcDaoPostgreSql;

import com.br.ifpb.conexaoBanco.ConexaoBanco;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.interfaceDao.TopicoDaoIF;
import com.br.ifpb.valueObject.Topico;
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
public class TopicoDAO implements TopicoDaoIF {

    @Override
    public List<Topico> topicoGrupo(int idGrupo) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT * FROM Topico WHERE id_grupo=? ORDER BY data DESC";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, idGrupo);
            ResultSet rs = stat.executeQuery();
            List<Topico> lista=new ArrayList<>();
            while(rs.next()){
                Topico topico=new Topico();
                topico.setId(rs.getInt("id"));
                topico.setTexto(rs.getString("texto"));
                topico.setData(rs.getTimestamp("data"));
                UsuarioDAO usuario=new UsuarioDAO();
                topico.setUsuario(usuario.getUsuario(rs.getInt("usuario")));
                ComentarioDAO comentario=new ComentarioDAO();
                topico.setComentarios(comentario.comentarioTopico(rs.getInt("id")));
                topico.setGrupo(null);
                lista.add(topico);
            }
            if(lista.size()>0){
                return lista;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TopicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void criarTopico(int idUsuario,int idGrupo,String texto,Timestamp data) throws PersistenciaException {
        try(Connection con=ConexaoBanco.getInstance()){
            String sql="INSERT INTO Topico(texto,data,usuario,id_grupo) VALUES (?,?,?,?)";
            PreparedStatement stat=con.prepareStatement(sql);
            stat.setString(1, texto);
            stat.setTimestamp(2, data);
            stat.setInt(3, idUsuario);
            stat.setInt(4, idGrupo);
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
           throw new PersistenciaException(ex);
        }
    }

}
