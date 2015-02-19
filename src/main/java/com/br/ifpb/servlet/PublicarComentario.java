
package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarComentario;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name="PublicarComentario", urlPatterns={"/publicar-comentario"})
public class PublicarComentario extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
         Integer idTopico= null;
        try {
            idTopico = Integer.valueOf(request.getParameter("id"));
        } catch (NumberFormatException ex) {

        }
        String texto = request.getParameter("texto");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario != null && idTopico != null) {
            GerenciarComentario gerenciarComentario=new GerenciarComentario();
            try {
                gerenciarComentario.criarComentario(texto, Timestamp.valueOf(LocalDateTime.now()), idTopico, usuario.getId());
            } catch (PersistenciaException ex) {
                Logger.getLogger(PublicarComentario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect(request.getHeader("referer"));

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
