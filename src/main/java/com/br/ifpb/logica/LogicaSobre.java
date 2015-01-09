/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.logica;

import com.br.ifpb.businessObject.GerenciarAmizade;
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
public class LogicaSobre implements Logica{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailParametro = request.getParameter("email");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        if (usuario == null || emailParametro == null) {
            response.sendRedirect("");
            return null;
        } else if (usuario.getEmail().equals(emailParametro)) {
            return "/paginas/sobre-usuario.jsp";
        } else {
            return sobre(request, response, emailParametro, usuario);
        }
    }
    
    public String sobre(HttpServletRequest request, HttpServletResponse response,
            String emailParametro,Usuario usuario)throws IOException,ServletException{
         GerenciarUsuario gerUsuario = new GerenciarUsuario();
        Usuario usuario1 = null;
        try {
            usuario1 = gerUsuario.getUsuario(emailParametro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(com.br.ifpb.servlet.Postagens.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (usuario1 != null) {
            request.setAttribute("usuario1", usuario1);
            GerenciarAmizade amizade = new GerenciarAmizade();
            boolean isAmizade = false;
            try {
                isAmizade = amizade.verificarAmizade(usuario.getEmail(), emailParametro);
            } catch (PersistenciaException ex) {
                Logger.getLogger(com.br.ifpb.servlet.Postagens.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isAmizade) {
                return "/paginas/sobre-amizade.jsp";
            } else {
                return "/paginas/sobre.jsp";
            }
        } else {
            response.sendRedirect("");
            return null;
        }
    }
    
}
