package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarUsuario;
import com.br.ifpb.converter.ConverterInformacao;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.validacao.UsuarioInforValidacao;
import com.br.ifpb.valueObject.Usuario;
import java.io.IOException;
import java.util.LinkedList;
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
@WebServlet(name = "CadastroUsuario", urlPatterns = {"/cadastro-usuario"})
public class CadastroUsuario extends HttpServlet {

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
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String data_nascimento = request.getParameter("data_nascimento");

        UsuarioInforValidacao usuarioInforValidaca = new UsuarioInforValidacao();
        List<String> cadastroErros = usuarioInforValidaca.validar(nome, sobrenome, null, data_nascimento,
                null, email, null, senha, null);

        if (cadastroErros == null) {
            GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
            boolean emailExistente = false;
            try {
                emailExistente = gerenciarUsuario.verificarExistenciaEmail(email);
            } catch (PersistenciaException ex) {
                Logger.getLogger(SalvarInformacao.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (emailExistente) {
                cadastroErros=new LinkedList<>();
                cadastroErros.add("e-mail existente!");
            }
        }
        if (cadastroErros==null || cadastroErros.isEmpty()) {
            GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
            try {
                gerenciarUsuario.cadastrarConta(nome, sobrenome,null, null, email, null, senha, ConverterInformacao.converteDate(data_nascimento), null, "imagens/icone/perfil.png", null, null);
            } catch (PersistenciaException ex) {
                Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            Usuario usuario = null;
            try {
                usuario = gerenciarUsuario.getUsuario(email);
            } catch (PersistenciaException ex) {
                Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("inicio");
        } else {
            request.setAttribute("cadastroErros", cadastroErros);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
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
