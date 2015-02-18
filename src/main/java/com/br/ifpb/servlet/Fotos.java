/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarAmizade;
import com.br.ifpb.businessObject.GerenciarFotos;
import com.br.ifpb.businessObject.GerenciarUsuario;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Foto;
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
@WebServlet(name = "Fotos", urlPatterns = {"/fotos"})
public class Fotos extends HttpServlet {

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
        Integer idParametro = null;
        try {
            idParametro = Integer.valueOf(request.getParameter("id"));
        } catch (NumberFormatException ex) {
          
        }
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario != null && idParametro != null) {
            if (usuario.getId() == idParametro) {
                GerenciarFotos gerenciarFotos = new GerenciarFotos();
                List<Foto> fotos = null;
                try {
                    fotos = gerenciarFotos.listarFotos(usuario.getId());
                } catch (PersistenciaException ex) {
                    Logger.getLogger(Fotos.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("fotos", fotos);
                getServletContext().getRequestDispatcher("/fotos-usuario.jsp").forward(request, response);
            } else {
                GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
                Usuario usuario1 = null;
                try {
                    usuario1 = gerenciarUsuario.getUsuario(idParametro);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(Fotos.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (usuario1 != null) {
                    GerenciarAmizade gerenciarAmizade = new GerenciarAmizade();
                    boolean possuiAmizade = false;
                    try {
                        possuiAmizade = gerenciarAmizade.verificarAmizade(usuario.getId(), idParametro);
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(Fotos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (possuiAmizade) {
                        GerenciarFotos gerenciarFotos = new GerenciarFotos();
                        List<Foto> fotos = null;
                        try {
                            fotos = gerenciarFotos.listarFotos(usuario1.getId());
                        } catch (PersistenciaException ex) {
                            Logger.getLogger(Fotos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        request.setAttribute("usuario1", usuario1);
                        request.setAttribute("fotos", fotos);
                        getServletContext().getRequestDispatcher("/fotos-amizade.jsp").forward(request, response);
                    }
                }
            }

        }else{response.sendRedirect("");}
    }

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
