<%-- 
    Document   : cabecalho
    Created on : 30/12/2014, 07:40:15
    Author     : Emanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <div class="row cabecalho">
        <div class="info-basica">
            <div class="imagem-perfil">
                <img src="${usuario.foto}" alt=""> 
            </div>
            <div class="nome-perfil">
                ${usuario.nome}
            </div>
        </div>
        <div class="configuracoes">
            <a href="configuracao">Configurações</a>
        </div>
    </div>
    <nav>
        <ul class="navegacao">
            <li id="sobre">
                <a href="sobre?id=${usuario.id}">Sobre</a>
            </li>
            <li id="topicos">
                <a href="postagens?id=${usuario.id}">Postagens</a>
            </li>
            <li id="amigos">
                <a href="amigos">Amigos</a>
            </li>
            <li id="fotos">
                <a href="fotos?id=${usuario.id}">Fotos</a>
            </li>
        </ul>
    </nav>
</header>