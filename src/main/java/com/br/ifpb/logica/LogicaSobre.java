/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.logica;

import com.br.ifpb.businessObject.GerenciarAmizade;
import com.br.ifpb.businessObject.GerenciarRelacao;
import com.br.ifpb.businessObject.GerenciarUsuario;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Relacao;
import com.br.ifpb.valueObject.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emanuel
 */
public class LogicaSobre implements Logica {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idParametro;
        try {
            idParametro = Integer.valueOf(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect("");
            return null;
        }
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null || idParametro == null) {
            response.sendRedirect("");
            return null;
        } else if (usuario.getId() == idParametro) {
            GerenciarRelacao gerenciarRelacao = new GerenciarRelacao();
            List<Usuario> relacoes = null;
            try {
                relacoes = gerenciarRelacao.getRelacao(usuario.getId());
            } catch (PersistenciaException ex) {
                Logger.getLogger(LogicaSobre.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("relacoes", relacoes);
            return "/sobre-usuario.jsp";
        } else {
            return sobreRelacao(request, response, idParametro, usuario);
        }
    }

    public String sobreRelacao(HttpServletRequest request, HttpServletResponse response,
            int idParametro, Usuario usuario) throws IOException, ServletException {
        GerenciarUsuario gerUsuario = new GerenciarUsuario();
        Usuario usuario1 = null;
        try {
            usuario1 = gerUsuario.getUsuario(idParametro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(com.br.ifpb.servlet.Postagens.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (usuario1 != null) {
            request.setAttribute("usuario1", usuario1);
            GerenciarAmizade amizade = new GerenciarAmizade();
            boolean isAmizade = false;
            try {
                isAmizade = amizade.verificarAmizade(usuario.getId(), idParametro);
            } catch (PersistenciaException ex) {
                Logger.getLogger(com.br.ifpb.servlet.Postagens.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isAmizade) {
                GerenciarRelacao gerenciarRelacao = new GerenciarRelacao();
                List<Usuario> relacoes = null;
                try {
                    relacoes = gerenciarRelacao.getRelacao(usuario1.getId());
                } catch (PersistenciaException ex) {
                    Logger.getLogger(LogicaSobre.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("relacoes", relacoes);
                return "/sobre-amizade.jsp";
            } else {
                try {
                    boolean convite=amizade.existeSolicitacao(usuario.getId(), idParametro);
                    request.setAttribute("convite", convite);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(LogicaSobre.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return "/sobre.jsp";
            }
        } else {
            response.sendRedirect("");
            return null;
        }
    }

}
