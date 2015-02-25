<%-- 
    Document   : cabecalho
    Created on : 06/01/2015, 12:11:20
    Author     : Emanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="row cabecalho">
        <div class="info-basica">
            <div class="imagem-perfil">
                <img src="${usuario1.foto}" alt=""> 
            </div>
            <div class="nome-perfil">
                ${usuario1.nome}
            </div>
        </div>
        <div class="desfazer-amizade">
            <c:if test="${!convite}">
                <a href="convidar-amizade?id=${usuario1.id}">Convidar Amizade</a>
            </c:if>
        </div>
    </div>
    <nav>
        <ul class="navegacao">
            <li id="sobre">
                <a href="sobre?id=${usuario.id}">Sobre</a>
            </li>
        </ul>
    </nav>
</header>