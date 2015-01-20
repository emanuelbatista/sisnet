<%-- 
    Document   : topicos-cabecalho-usuario
    Created on : 20/01/2015, 08:43:37
    Author     : Emanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <div class="row cabecalho">
        <div class="info-basica">
            <div class="nome-perfil">
                ${grupo.nome}
            </div>
            <div class="fundador">
                <b>Fundador: </b>
                <img src="${grupo.fundador.foto}" alt="">
                <a href="sobre?id=${grupo.fundador.id}">${grupo.fundador.nome}</a>
            </div>
        </div>
    </div>
    <nav>
        <ul class="navegacao">        
            <li id="topicos">
                <a href="topicos?id=${grupo.id}">Topicos</a>
            </li>
            <li id="sobre">
                <a href="participantes?id=${usuario1.id}">Participantes</a>
            </li>
        </ul>
    </nav>
</header>
