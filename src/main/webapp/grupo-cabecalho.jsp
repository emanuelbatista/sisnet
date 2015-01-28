<%-- 
    Document   : topicos-cabecalho
    Created on : 20/01/2015, 09:17:43
    Author     : Emanuel
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <div class="row cabecalho">
        <div class="info-basica">
            <div class="nome-perfil">
                ${grupo.nome}
            </div>
             <div class="descricao">
                <c:if test="${grupo.descricao!=null}">
                    <b>Descricao: </b>${grupo.descricao}
                </c:if>
                <c:if test="${grupo.descricao==null}">
                    <b>Descricao: </b>Sem descrição
                </c:if>
            </div>
            <div class="fundador">
                <b>Fundador: </b>
                <img src="${grupo.fundador.foto}" alt="">
                <a href="sobre?id=${grupo.fundador.id}">${grupo.fundador.nome}</a>
            </div>
        </div>
        <div class="configuracoes">
            <a href="atualizar">Participar</a>
        </div>
    </div>
    <nav>
        <ul class="navegacao">        
            <li id="topicos">
                <a href="topicos?id=${grupo.id}">Topicos</a>
            </li>
            <li id="participantes">
                <a href="participantes?id=${grupo.id}">Participantes</a>
            </li>
        </ul>
    </nav>
</header>

