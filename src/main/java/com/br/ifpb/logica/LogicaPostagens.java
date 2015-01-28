/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.logica;

import com.br.ifpb.businessObject.GerenciarAmizade;
import com.br.ifpb.businessObject.GerenciarMensagem;
import com.br.ifpb.businessObject.GerenciarUsuario;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emanuel
 */
public class LogicaPostagens implements Logica {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer idParametro = Integer.valueOf(request.getParameter("id"));
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null || idParametro == null) {
            response.sendRedirect("");
            return null;
        } else if (usuario.getId()==idParametro) {
            return postagensUsuario(request, response);
        } else {
            return postagens(request, response, idParametro, usuario);
        }
    }

    private String postagensUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GerenciarMensagem mensagem = new GerenciarMensagem();
        int id = ((Usuario) request.getSession().getAttribute("usuario")).getId();
        try {
            request.setAttribute("mensagem", mensagem.minhasMensagens(id));
        } catch (PersistenciaException ex) {
            Logger.getLogger(com.br.ifpb.servlet.Postagens.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/postagens-usuario.jsp";
    }

    private String postagens(HttpServletRequest request, HttpServletResponse response, int idParametro,
            Usuario usuario)throws ServletException, IOException {
        GerenciarUsuario gerUsuario = new GerenciarUsuario();
        Usuario usuario1 = null;
        try {
            usuario1 = gerUsuario.getUsuario(idParametro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(com.br.ifpb.servlet.Postagens.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (usuario1 != null) {
            GerenciarAmizade amizade = new GerenciarAmizade();
            boolean isAmizade = false;
            try {
                isAmizade = amizade.verificarAmizade(usuario.getId(), idParametro);
            } catch (PersistenciaException ex) {
                Logger.getLogger(com.br.ifpb.servlet.Postagens.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isAmizade) {
                return postagensAmigos(request, response, usuario1);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private String postagensAmigos(HttpServletRequest request, HttpServletResponse response,Usuario usuario1 )
            throws ServletException, IOException {
        GerenciarMensagem mensagem = new GerenciarMensagem();
        request.setAttribute("usuario1", usuario1);
        try {
            request.setAttribute("mensagem", mensagem.minhasMensagens(usuario1.getId()));
        } catch (PersistenciaException ex) {
            Logger.getLogger(com.br.ifpb.servlet.Postagens.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "/postagens-amizade.jsp";
    }

}
