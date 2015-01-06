<%-- 
    Document   : barra
    Created on : 30/12/2014, 07:27:07
    Author     : Emanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/barra.css" rel="stylesheet" type="text/css">
<div class="barra">
    <h1 id="titulo">SisNet</h1>
    <div class="barra-menu">
        <ul>
            <li>
                <a href="perfil">
                    <img src="${usuario.foto}" alt="">
                    <div class="barra-nome">${usuario.nome}</div>
                </a>
            </li>
            <li>
                <hr>
            </li>
            <li>
                <a href="#">
                    Página Inicial
                </a>
            </li>
        </ul>
    </div>
</div>
