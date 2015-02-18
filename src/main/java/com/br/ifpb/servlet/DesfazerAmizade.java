
package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarAmizade;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;
import java.io.IOException;
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
@WebServlet(name="DesfazerAmizade", urlPatterns={"/desfazer-amizade"})
public class DesfazerAmizade extends HttpServlet {
   
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
       
            Integer idParametro = null;
        try {
            idParametro = Integer.valueOf(request.getParameter("id"));
        } catch (NumberFormatException ex) {

        }
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario != null && idParametro != null) {
            GerenciarAmizade gerenciarAmizade = new GerenciarAmizade();
            try {
                gerenciarAmizade.desfazerAmizade(usuario.getId(), idParametro);
            } catch (PersistenciaException ex) {
                Logger.getLogger(AceitarAmizade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect(request.getHeader("referer"));
        

    } 

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
