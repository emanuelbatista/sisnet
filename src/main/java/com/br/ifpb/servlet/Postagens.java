/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarAmizade;
import com.br.ifpb.businessObject.GerenciarMensagem;
import com.br.ifpb.businessObject.GerenciarUsuario;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Postagens", urlPatterns = {"/postagens"})
public class Postagens extends HttpServlet {

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
        String emailParametro = request.getParameter("email");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null || emailParametro == null) {
            response.sendRedirect("index.html");
        } else if (usuario.getEmail().equals(emailParametro)) {
            GerenciarMensagem mensagem = new GerenciarMensagem();
            String emailUsuario = ((Usuario) request.getSession().getAttribute("usuario")).getEmail();
            try {
                request.setAttribute("mensagem", mensagem.minhasMensagens(emailUsuario));
            } catch (PersistenciaException ex) {
                Logger.getLogger(Postagens.class.getName()).log(Level.SEVERE, null, ex);
            }
            getServletContext().getRequestDispatcher("/paginas/postagens-usuario.jsp").forward(request, response);
        } else {
            GerenciarUsuario gerUsuario = new GerenciarUsuario();
            Usuario usuario1 = null;
            try {
                usuario1 = gerUsuario.getUsuario(emailParametro);
            } catch (PersistenciaException ex) {
                Logger.getLogger(Postagens.class.getName()).log(Level.SEVERE, null, ex);
            }
            PrintWriter out = response.getWriter();
            if (usuario1 != null) {
                GerenciarAmizade amizade = new GerenciarAmizade();
                boolean isAmizade = false;
                try {
                    isAmizade = amizade.verificarAmizade(usuario.getEmail(), emailParametro);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(Postagens.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (isAmizade) {
                    GerenciarMensagem mensagem = new GerenciarMensagem();
                    request.setAttribute("usuario1", usuario1);
                    try {
                        request.setAttribute("mensagem", mensagem.minhasMensagens(usuario1.getEmail()));
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(Postagens.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    getServletContext().getRequestDispatcher("/paginas/postagens.jsp").forward(request, response);
                }else{
                    out.print("erro1");
                }
            }else{
                out.print("erro0");
            }

        }
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

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
