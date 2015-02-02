/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Emanuel
 */
@WebServlet(name = "SalvarInformacao", urlPatterns = {"/salvar-informacao"})
public class SalvarInformacao extends HttpServlet {

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
        Usuario usuario = ((Usuario) request.getSession().getAttribute("usuario"));
        if (usuario == null) {
            response.sendRedirect("");
        } else {
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String apelido = request.getParameter("apelido");
            String data_nascimento = request.getParameter("data_nascimento");
            String cidade = request.getParameter("cidade");
            String email = request.getParameter("email");
            String profissao = request.getParameter("profissao");
            String senha=request.getParameter("senha");
            String status = request.getParameter("status");

            List<String> mensagensErros = new ArrayList<>();

            if (nome != null && !nome.matches("[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*")) {
                mensagensErros.add("nome formato errado ou vazio!");
            }
            if (sobrenome != null) {
                if (sobrenome.equals("")) {
                    sobrenome = null;
                } else if (!sobrenome.matches("[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*")) {
                    mensagensErros.add("sobrenome formato errado!");
                }
            }
            if (apelido != null) {
                if (apelido.equals("")) {
                    apelido = null;
                } else if (!apelido.matches("[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*")) {
                    mensagensErros.add("apelido formato errado!");
                }
            }
            Date data = null;
            if (data_nascimento != null) {
                if (data_nascimento.equals("")) {
                    data_nascimento=null;
                } else {
                    if (data_nascimento.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date=null;
                        try {
                            date = LocalDate.parse(data_nascimento, dateTimeFormatter);
                            data = Date.valueOf(date);
                        } catch (DateTimeParseException ex) {
                            mensagensErros.add("data de nascimento formato errado!");
                        }

                    } else {
                        try {
                            data = Date.valueOf(data_nascimento);
                        } catch (IllegalArgumentException ex) {
                            mensagensErros.add("data de nascimento formato errado!");
                        }
                    }
                }
            }
            if (cidade != null) {
                if (cidade.equals("")) {
                    cidade = null;
                } else if (!cidade.matches("[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*")) {
                    mensagensErros.add("cidade formato errado!");
                }
            }

            if (profissao != null) {
                if (profissao.equals("")) {
                    profissao = null;
                } else if (!profissao.matches("[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*")) {
                    mensagensErros.add("profissao formato errado!");
                }
            }
            if (status != null) {
                if (status.equals("")) {
                    status = null;
                } else if (!status.matches("[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*")) {
                    mensagensErros.add("status formato errado!");
                }
            }
            if(senha==null || !senha.matches("\\w+")){
                mensagensErros.add("senha contém caracteres especiais ou esta vazia!");
            }
            if(email==null || !email.matches("\\w+@\\w+\\.\\w{2,3}")){
                mensagensErros.add("e-mail formato errado ou vazio!");
            }else{
                GerenciarUsuario gerenciarUsuario=new GerenciarUsuario();
                 boolean emailExistente=false;
                try {
                    emailExistente=gerenciarUsuario.verificarExistenciaEmail(email);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(SalvarInformacao.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(!usuario.getEmail().equals(email) && emailExistente){
                    mensagensErros.add("e-mail existente!");
                }
            }
            if (mensagensErros.isEmpty()) {
                GerenciarUsuario gerenciarUsuario=new GerenciarUsuario();
                try {
                    gerenciarUsuario.atualizarConta(usuario.getId(), nome, sobrenome, apelido, cidade, email, profissao, senha, data, status);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(SalvarInformacao.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    usuario = gerenciarUsuario.getUsuario(usuario.getId());
                } catch (PersistenciaException ex) {
                    Logger.getLogger(SalvarInformacao.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.getSession().setAttribute("usuario", usuario);
                response.sendRedirect("configuracao");
            } else {
                request.setAttribute("mensagensInformacao", mensagensErros);
                getServletContext().getRequestDispatcher("/configuracao").forward(request, response);
            }

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
