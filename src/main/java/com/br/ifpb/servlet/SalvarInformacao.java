/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarUsuario;
import com.br.ifpb.converter.ConverterInformacao;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.validacao.UsuarioInforValidacao;
import com.br.ifpb.valueObject.Usuario;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
            String senha = request.getParameter("senha");
            String status = request.getParameter("status");

            UsuarioInforValidacao usuarioInforValidacao = new UsuarioInforValidacao();
            List<String> mensagensErros = usuarioInforValidacao.validar(nome, sobrenome, apelido,
                    data_nascimento, cidade, email, profissao, senha, status);
            
            if (mensagensErros == null) {
                GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
                boolean emailExistente = false;
                try {
                    emailExistente = gerenciarUsuario.verificarExistenciaEmail(email);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(SalvarInformacao.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!usuario.getEmail().equals(email) && emailExistente) {
                    mensagensErros = new LinkedList<>();
                    mensagensErros.add("Email Existente");
                }
            }
            if (mensagensErros != null && mensagensErros.isEmpty() || mensagensErros==null) {
                try {
                    GerenciarUsuario gerenciarUsuario = new GerenciarUsuario();
                    gerenciarUsuario.atualizarConta(usuario.getId(), nome, sobrenome, apelido, cidade, email,
                            profissao, senha, ConverterInformacao.converteDate(data_nascimento), status);

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
}
