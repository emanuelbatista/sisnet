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
        <div class="atualizar">
            <a href="atualizar">Configurações</a>
        </div>
    </div>
    <nav>
        <ul class="navegacao">
            <li id="sobre">
                <a href="#">Sobre</a>
            </li>
            <li id="postagens">
                <a href="postagem">Postagens</a>
            </li>
            <li id="fotos">
                <a href="#">Fotos</a>
            </li>
        </ul>
    </nav>
</header>