/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarGrupo;
import com.br.ifpb.execoes.PersistenciaException;
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
@WebServlet(name = "Participantes", urlPatterns = {"/participantes"})
public class Participantes extends HttpServlet {

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
        Integer id = Integer.valueOf(request.getParameter("id"));
        Usuario usuario=((Usuario)request.getSession().getAttribute("usuario"));
        if(id==null || usuario==null){
            response.sendRedirect("");
        }else{
            GerenciarGrupo gerenciarGrupo = new GerenciarGrupo();
            com.br.ifpb.valueObject.Grupo grupo = null;
            try {
                grupo = gerenciarGrupo.getGrupo(id);
            } catch (PersistenciaException ex) {
                
            }
            if (grupo == null) {

            } else {
                request.setAttribute("grupo", grupo);
                List<Usuario> participantes=null;
                try {
                    participantes=gerenciarGrupo.listaDeParticipantes(id);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(Participantes.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("participantes", participantes);
                boolean participaGrupo=false;
                try {
                    participaGrupo=gerenciarGrupo.participaGrupo(grupo.getId(),usuario.getId());
                } catch (PersistenciaException ex) {
                    Logger.getLogger(Topicos.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (participaGrupo) {
                    getServletContext().getRequestDispatcher("/participantes-usuario.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/participantes.jsp").forward(request, response);
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
