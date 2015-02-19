package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarUsuario;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        List<String> cadastroErros = new ArrayList<>();

        if (nome != null && !nome.matches("[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*")) {
            cadastroErros.add("nome formato errado ou vazio!");
        }
        if (sobrenome != null) {
            if (sobrenome.equals("")) {
                sobrenome = null;
            } else if (!sobrenome.matches("[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*")) {
                cadastroErros.add("sobrenome formato errado!");
            }
        }
        Date data = null;
        if (data_nascimento != null) {
            if (data_nascimento.equals("")) {
                data_nascimento = null;
            } else {
                if (data_nascimento.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = null;
                    try {
                        date = LocalDate.parse(data_nascimento, dateTimeFormatter);
                        data = Date.valueOf(date);
                    } catch (DateTimeParseException ex) {
                        cadastroErros.add("data de nascimento formato errado!");
                    }

                } else {
                    try {
                        data = Date.valueOf(data_nascimento);
                    } catch (IllegalArgumentException ex) {
                        cadastroErros.add("data de nascimento formato errado!");
                    }
                }
            }
        }
        if (senha == null || !senha.matches("\\w+")) {
            cadastroErros.add("senha contém caracteres especiais ou esta vazia!");
        }
        if (email == null || !email.matches("\\w+@\\w+\\.\\w{2,3}")) {
            cadastroErros.add("e-mail formato errado ou vazio!");
        } else {
            GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
            boolean emailExistente = false;
            try {
                emailExistente = gerenciarUsuario.verificarExistenciaEmail(email);
            } catch (PersistenciaException ex) {
                Logger.getLogger(SalvarInformacao.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (emailExistente) {
                cadastroErros.add("e-mail existente!");
            }
        }
        if(cadastroErros.isEmpty()){
            GerenciarUsuario gerenciarUsuario=new GerenciarUsuario();
            try {
                gerenciarUsuario.cadastrarConta(nome,null,null,email,null,senha,data,null,null,null,null);
            } catch (PersistenciaException ex) {
                Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            Usuario usuario=null;
            try {
                usuario=gerenciarUsuario.getUsuario(email);
            } catch (PersistenciaException ex) {
                Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("inicio");
        }else{
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
