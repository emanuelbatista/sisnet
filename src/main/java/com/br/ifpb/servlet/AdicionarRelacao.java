package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarRelacao;
import com.br.ifpb.businessObject.GerenciarUsuario;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name = "AdicionarRelacao", urlPatterns = {"/adicionar-relacao"})
public class AdicionarRelacao extends HttpServlet {

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
        response.sendRedirect("configuracao");
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
        request.setCharacterEncoding("UTF-8");
        String tipoRelacao = request.getParameter("tipo");
        String email = request.getParameter("email_relacao");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        try {
            if (usuario != null) {
                GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
                GerenciarRelacao gerenciarRelacao = new GerenciarRelacao();
                Usuario destinatario = gerenciarUsuario.getUsuario(email);
                List<String> mensagensErros = new ArrayList<>();
                if (usuario.getEmail().equals(email) || destinatario == null) {
                    mensagensErros.add("email incorreto");

                } else {
                    if (gerenciarRelacao.existeRelacao(usuario.getId(), destinatario.getId())) {
                        mensagensErros.add("relacionamento já existe");
                    }

                }

                if (mensagensErros.isEmpty()) {
                    gerenciarRelacao.adicionarRelacao(usuario.getId(), tipoRelacao, destinatario.getId());
                    response.sendRedirect(request.getHeader("referer"));
                } else {
                    request.setAttribute("mensagensRelacao", mensagensErros);
                    getServletContext().getRequestDispatcher("/configuracao").forward(request, response);
                }
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(AdicionarRelacao.class.getName()).log(Level.SEVERE, null, ex);
        }
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
