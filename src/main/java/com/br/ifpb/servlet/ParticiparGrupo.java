package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarGrupo;
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
@WebServlet(name = "ParticiparGrupo", urlPatterns = {"/participar-grupo"})
public class ParticiparGrupo extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        Integer idParametro = Integer.valueOf(request.getParameter("id"));
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        GerenciarGrupo gerenciarGrupo = new GerenciarGrupo();
        boolean participaGrupo = true;
        try {
            participaGrupo = gerenciarGrupo.participaGrupo(usuario.getId());
        } catch (PersistenciaException ex) {
            Logger.getLogger(ParticiparGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (usuario != null && !participaGrupo && idParametro!=null) {
            try {
                gerenciarGrupo.participarDoGrupo(idParametro, usuario.getId());
            } catch (PersistenciaException ex) {
                Logger.getLogger(ParticiparGrupo.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect(request.getHeader("referer"));
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
