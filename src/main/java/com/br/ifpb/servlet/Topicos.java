/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarGrupo;
import com.br.ifpb.businessObject.GerenciarTopico;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Topico;
import com.br.ifpb.valueObject.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emanuel
 */
@WebServlet(name = "Topicos", urlPatterns = {"/topicos"})
public class Topicos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer idGrupo = null;
        try {
            idGrupo = Integer.valueOf(request.getParameter("id"));
        } catch (NumberFormatException ex) {

        }
        Usuario usuario = ((Usuario) request.getSession().getAttribute("usuario"));

        if (usuario == null) {
            response.sendRedirect("");
        } else if (idGrupo == null) {
             response.sendError(404);
        } else {
            GerenciarGrupo gerenciarGrupo = new GerenciarGrupo();
            com.br.ifpb.valueObject.Grupo grupo = null;
            try {
                grupo = gerenciarGrupo.getGrupo(idGrupo);
            } catch (PersistenciaException ex) {
                Logger.getLogger(Topicos.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (grupo == null) {
               response.sendError(404);
            } else {
                request.setAttribute("grupo", grupo);
                GerenciarTopico gerenciarTopico = new GerenciarTopico();
                List<Topico> topicos = null;
                try {
                    topicos = gerenciarTopico.topicoGrupo(grupo.getId());
                } catch (PersistenciaException ex) {
                    Logger.getLogger(Topicos.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("topicos", topicos);
                boolean participaGrupo = false;
                try {
                    participaGrupo = gerenciarGrupo.participaGrupo(grupo.getId(),usuario.getId());
                } catch (PersistenciaException ex) {
                    Logger.getLogger(Topicos.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (participaGrupo) {
                    getServletContext().getRequestDispatcher("/topicos-usuario.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/topicos.jsp").forward(request, response);
                }
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
