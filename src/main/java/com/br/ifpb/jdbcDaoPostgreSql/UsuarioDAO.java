package com.br.ifpb.jdbcDaoPostgreSql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.br.ifpb.conexaoBanco.ConexaoBanco;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;
import com.br.ifpb.interfaceDao.UsuarioDaoIF;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements UsuarioDaoIF {

    public UsuarioDAO() throws PersistenciaException {

    }

    @Override
    public void criar(Usuario usuario) throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "INSERT INTO Usuario(nome,apelido,cidade,email,profissao,senha,data_nascimento,status,foto) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statUsuario = connection.prepareStatement(sql);
            statUsuario.setString(1, usuario.getNome());
            statUsuario.setString(2, usuario.getApelido());
            statUsuario.setString(3, usuario.getCidade());
            statUsuario.setString(4, usuario.getEmail());
            statUsuario.setString(5, usuario.getProfissao());
            statUsuario.setString(6, usuario.getSenha());
            statUsuario.setDate(7, usuario.getData_nascimento());
            statUsuario.setString(8, usuario.getStatus());
            statUsuario.setString(9, usuario.getFoto());
            statUsuario.executeUpdate();
            if (usuario.getLocais_estudou() != null) {
                sql = "INSERT INTO locais_estudou(usuario,local) VALUES (?,?)";
                for (String localEstudou : usuario.getLocais_estudou()) {
                    statUsuario = connection.prepareStatement(sql);
                    statUsuario.setString(1, usuario.getEmail());
                    statUsuario.setString(2, localEstudou);
                    statUsuario.executeUpdate();
                }
            }
            if (usuario.getLocais_trabalhou() != null) {
                sql = "INSERT INTO locais_trabalhou(usuario,local) VALUES (?,?)";
                for (String localTrabalhou : usuario.getLocais_trabalhou()) {
                    statUsuario = connection.prepareStatement(sql);
                    statUsuario.setString(1, usuario.getEmail());
                    statUsuario.setString(2, localTrabalhou);
                    statUsuario.executeUpdate();
                }
            }
            statUsuario.close();
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void excluir(int id) throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "DELETE FROM Usuario WHERE id=?";
            try (PreparedStatement stat = connection.prepareStatement(sql)) {
                stat.setInt(1, id);
                stat.executeUpdate();
            }
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void atualizar(Usuario usuario) throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "UPDATE Usuario SET ";
            sql += usuario.getNome() != null ? "nome='" + usuario.getNome() + "'," : "";
            sql += usuario.getApelido() != null ? "apelido='" + usuario.getApelido() + "'," : "";
            sql += usuario.getCidade() != null ? "cidade='" + usuario.getCidade() + "'," : "";
            sql += usuario.getEmail() != null ? "email='" + usuario.getEmail() + "'," : "";
            sql += usuario.getProfissao() != null ? "profissao='" + usuario.getProfissao() + "'," : "";
            sql += usuario.getSenha() != null ? "senha='" + usuario.getSenha() + "'," : "";
            sql += usuario.getData_nascimento() != null ? "data_nascimento='" + usuario.getData_nascimento() + "'," : "";
            sql += usuario.getStatus() != null ? "status='" + usuario.getStatus() + "'," : "";
            sql += usuario.getFoto() != null ? "foto='" + usuario.getFoto() + "'," : "";
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE id='" + usuario.getEmail() + "'";
            try (Statement stat = connection.createStatement()) {
                stat.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean logar(String email, String senha) throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "{?=call logar(?,?)}";
            boolean retorno;
            try (CallableStatement stat = connection.prepareCall(sql)) {
                stat.registerOutParameter(1, java.sql.Types.BOOLEAN);
                stat.setString(2, email);
                stat.setString(3, senha);
                stat.execute();
                retorno = stat.getBoolean(1);
                //terminar depois
            }
            return retorno;
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Usuario getUsuario(int id) throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "SELECT id,nome,apelido,cidade,email,profissao,senha,data_nascimento,status,foto FROM Usuario WHERE id=?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, id);
            ResultSet set = stat.executeQuery();
            if(set.next()){
            Usuario usuario = new Usuario();
            usuario.setNome(set.getString("nome"));
            usuario.setApelido(set.getString("apelido"));
            usuario.setCidade(set.getString("cidade"));
            usuario.setEmail(set.getString("email"));
            usuario.setProfissao(set.getString("profissao"));
            usuario.setSenha(set.getString("senha"));
            usuario.setData_nascimento(set.getDate("data_nascimento"));
            usuario.setStatus(set.getString("status"));
            usuario.setFoto(set.getString("foto"));
            sql = "SELECT local FROM locais_estudou WHERE usuario=?";
            stat = connection.prepareStatement(sql);
            stat.setInt(1, id);
            set = stat.executeQuery();
            List<String> locais_estudou = new ArrayList<>();
            while (set.next()) {
                locais_estudou.add(set.getString("local"));
            }
            
            if(locais_estudou.size()>0){
            usuario.setLocais_estudou(locais_estudou);
            }else usuario.setLocais_estudou(null);
            
            sql = "SELECT local FROM locais_trabalhou WHERE usuario=?";
            stat = connection.prepareStatement(sql);
            stat.setInt(1, id);
            set = stat.executeQuery();
            List<String> locais_trabalhou = new ArrayList<>();
            while (set.next()) {
                locais_trabalhou.add(set.getString("local"));
            }
            if(locais_trabalhou.size()>0){
            usuario.setLocais_trabalhou(locais_trabalhou);
            }else usuario.setLocais_trabalhou(null);
            set.close();
            stat.close();
            return usuario;
            }else return null;
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean verficarExistenciaEmail(String email) throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "{?=call existenciaEmail(?)}";
            boolean retorno;
            try (CallableStatement stat = connection.prepareCall(sql)) {
                stat.registerOutParameter(1, java.sql.Types.BOOLEAN);
                stat.setString(2, email);
                stat.execute();
                retorno = stat.getBoolean(1);
            }
            return retorno;
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Usuario getUsuario(String email) throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "SELECT * FROM Usuario WHERE email=?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, email);
            ResultSet set = stat.executeQuery();
            if(set.next()){
            Usuario usuario = new Usuario();
            usuario.setNome(set.getString("nome"));
            usuario.setId(set.getInt("id"));
            usuario.setApelido(set.getString("apelido"));
            usuario.setCidade(set.getString("cidade"));
            usuario.setEmail(set.getString("email"));
            usuario.setProfissao(set.getString("profissao"));
            usuario.setSenha(set.getString("senha"));
            usuario.setData_nascimento(set.getDate("data_nascimento"));
            usuario.setStatus(set.getString("status"));
            usuario.setFoto(set.getString("foto"));
            sql = "SELECT local FROM locais_estudou WHERE usuario=?";
            stat = connection.prepareStatement(sql);
            stat.setInt(1, usuario.getId());
            set = stat.executeQuery();
            List<String> locais_estudou = new ArrayList<>();
            while (set.next()) {
                locais_estudou.add(set.getString("local"));
            }
            
            if(locais_estudou.size()>0){
            usuario.setLocais_estudou(locais_estudou);
            }else usuario.setLocais_estudou(null);
            
            sql = "SELECT local FROM locais_trabalhou WHERE usuario=?";
            stat = connection.prepareStatement(sql);
            stat.setInt(1, usuario.getId());
            set = stat.executeQuery();
            List<String> locais_trabalhou = new ArrayList<>();
            while (set.next()) {
                locais_trabalhou.add(set.getString("local"));
            }
            if(locais_trabalhou.size()>0){
            usuario.setLocais_trabalhou(locais_trabalhou);
            }else usuario.setLocais_trabalhou(null);
            set.close();
            stat.close();
            return usuario;
            }else return null;
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
