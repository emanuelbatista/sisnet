/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.logica;

import com.br.ifpb.businessObject.GerenciarGrupo;
import com.br.ifpb.businessObject.GerenciarMensagem;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Grupo;
import com.br.ifpb.valueObject.Mensagem;
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
public class LogicaInicio implements Logica{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
         if(usuario==null){
             response.sendRedirect("");
             return null;
         }else{
             GerenciarMensagem mensagem=new GerenciarMensagem();
             List<Mensagem> mensagens=null;
             try {
                 mensagens=mensagem.mensagensAmigos(usuario.getId());
             } catch (PersistenciaException ex) {
                 Logger.getLogger(LogicaInicio.class.getName()).log(Level.SEVERE, null, ex);
             }
             GerenciarGrupo gerenciarGrupo=new GerenciarGrupo();
             List<Grupo> grupos=null;
             try {
                 grupos=gerenciarGrupo.listarGrupos(usuario.getId());
             } catch (PersistenciaException ex) {
                 Logger.getLogger(LogicaInicio.class.getName()).log(Level.SEVERE, null, ex);
             }
             request.setAttribute("grupos", grupos);
             request.setAttribute("mensagens", mensagens);
             return "/pagina-inicial.jsp";
             
         }
    }
    
}
