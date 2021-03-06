package com.br.ifpb.jdbcDaoPostgreSql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.br.ifpb.conexaoBanco.ConexaoBanco;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;
import com.br.ifpb.interfaceDao.UsuarioDaoIF;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements UsuarioDaoIF {

    @Override
    public void criar(Usuario usuario) throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "INSERT INTO Usuario(nome,apelido,cidade,email,profissao,senha,data_nascimento,status,foto,sobrenome) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
            statUsuario.setString(10, usuario.getSobrenome());
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
    public void atualizarInformacoes(Usuario usuario) throws PersistenciaException {
        try (Connection connection = ConexaoBanco.getInstance()) {
            String sql = "UPDATE Usuario SET nome=?,sobrenome=?,apelido=?,data_nascimento=?,"
                    + "cidade=?,email=?,profissao=?,status=?,senha=? WHERE id=?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, usuario.getNome().isEmpty()?null:usuario.getNome());
            stat.setString(2, usuario.getSobrenome().isEmpty()?null:usuario.getSobrenome());
            stat.setString(3, usuario.getApelido().isEmpty()?null:usuario.getApelido());
            stat.setDate(4, usuario.getData_nascimento());
            stat.setString(5, usuario.getCidade().isEmpty()?null:usuario.getCidade());
            stat.setString(6, usuario.getEmail().isEmpty()?null:usuario.getEmail());
            stat.setString(7, usuario.getProfissao().isEmpty()?null:usuario.getProfissao());
            stat.setString(8, usuario.getStatus().isEmpty()?null:usuario.getStatus());
            stat.setString(9, usuario.getSenha().isEmpty()?null:usuario.getSenha());
            stat.setInt(10, usuario.getId());
            stat.executeUpdate();
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
            String sql = "SELECT id,nome,sobrenome,apelido,cidade,email,profissao,senha,data_nascimento,status,foto FROM Usuario WHERE id=?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, id);
            ResultSet set = stat.executeQuery();
            if (set.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(set.getInt("id"));
                usuario.setNome(set.getString("nome"));
                usuario.setApelido(set.getString("apelido"));
                usuario.setCidade(set.getString("cidade"));
                usuario.setEmail(set.getString("email"));
                usuario.setProfissao(set.getString("profissao"));
                usuario.setSenha(set.getString("senha"));
                usuario.setData_nascimento(set.getDate("data_nascimento"));
                usuario.setStatus(set.getString("status"));
                usuario.setFoto(set.getString("foto"));
                usuario.setSobrenome(set.getString("sobrenome"));
                sql = "SELECT local FROM locais_estudou WHERE usuario=?";
                stat = connection.prepareStatement(sql);
                stat.setInt(1, id);
                set = stat.executeQuery();
                List<String> locais_estudou = new ArrayList<>();
                while (set.next()) {
                    locais_estudou.add(set.getString("local"));
                }

                if (locais_estudou.size() > 0) {
                    usuario.setLocais_estudou(locais_estudou);
                } else {
                    usuario.setLocais_estudou(null);
                }

                sql = "SELECT local FROM locais_trabalhou WHERE usuario=?";
                stat = connection.prepareStatement(sql);
                stat.setInt(1, id);
                set = stat.executeQuery();
                List<String> locais_trabalhou = new ArrayList<>();
                while (set.next()) {
                    locais_trabalhou.add(set.getString("local"));
                }
                if (locais_trabalhou.size() > 0) {
                    usuario.setLocais_trabalhou(locais_trabalhou);
                } else {
                    usuario.setLocais_trabalhou(null);
                }
                set.close();
                stat.close();
                return usuario;
            } else {
                return null;
            }
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
            if (set.next()) {
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
                usuario.setSobrenome(set.getString("sobrenome"));
                sql = "SELECT local FROM locais_estudou WHERE usuario=?";
                stat = connection.prepareStatement(sql);
                stat.setInt(1, usuario.getId());
                set = stat.executeQuery();
                List<String> locais_estudou = new ArrayList<>();
                while (set.next()) {
                    locais_estudou.add(set.getString("local"));
                }

                if (locais_estudou.size() > 0) {
                    usuario.setLocais_estudou(locais_estudou);
                } else {
                    usuario.setLocais_estudou(null);
                }

                sql = "SELECT local FROM locais_trabalhou WHERE usuario=?";
                stat = connection.prepareStatement(sql);
                stat.setInt(1, usuario.getId());
                set = stat.executeQuery();
                List<String> locais_trabalhou = new ArrayList<>();
                while (set.next()) {
                    locais_trabalhou.add(set.getString("local"));
                }
                if (locais_trabalhou.size() > 0) {
                    usuario.setLocais_trabalhou(locais_trabalhou);
                } else {
                    usuario.setLocais_trabalhou(null);
                }
                set.close();
                stat.close();
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void atualizarImagemPerfil(String path, int idUsuario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "UPDATE Usuario SET foto=? WHERE id=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, path);
            stat.setInt(2, idUsuario);
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionarLocalTrabalho(String local, int idUsuario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "INSERT INTO locais_trabalhou(local,usuario) VALUES (?,?)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, local);
            stat.setInt(2, idUsuario);
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public void adicionarLocalEstudou(String local, int idUsuario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "INSERT INTO locais_estudou(local,usuario) VALUES (?,?)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, local);
            stat.setInt(2, idUsuario);
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public void removerLocalTrabalho(String local, int idUsuario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "DELETE FROM locais_trabalhou WHERE local=? AND usuario=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, local);
            stat.setInt(2, idUsuario);
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public void removerLocalEstudou(String local, int idUsuario) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "DELETE FROM locais_estudou WHERE local=? AND usuario=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, local);
            stat.setInt(2, idUsuario);
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public List<Usuario> pesquisarUsuario(String pesquisa) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getInstance()) {
            String sql = "SELECT id,nome,foto FROM Usuario WHERE nome ilike ? OR sobrenome ilike ?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, "%"+pesquisa+"%");
            stat.setString(2, "%"+pesquisa+"%");
            ResultSet rs = stat.executeQuery();
            List<Usuario> usuarios = new LinkedList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setId(rs.getInt("id"));
                usuario.setFoto(rs.getString("foto"));
                usuarios.add(usuario);
            }
            return usuarios.isEmpty() ? null : usuarios;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new PersistenciaException(ex);
        }
    }

}
