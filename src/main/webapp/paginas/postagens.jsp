<%-- 
    Document   : postagens
    Created on : 06/01/2015, 12:10:02
    Author     : Emanuel
--%>

<%@page import="java.util.List"%>
<%@page import="com.br.ifpb.valueObject.Mensagem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="paginas/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/postagens.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/barra.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/cabecalho.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisNet - ${usuario1.nome}</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"/>
            <jsp:include page="cabecalho.jsp"/>
            <article class="article">
                <div class="noticias-esquerda">
                    <section class="section">
                        <div class="postar">
                            <ul>
                                <li><textarea class="novidades" placeholder="Compartilhe suas novidades aqui..."></textarea></li>
                                <li><input class="btn btn-success" type="submit" value="Compartilhar"></li>
                            </ul>
                        </div>
                    </section>
                    <c:forEach var="i" begin="1" items="${mensagem}" step="2">
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${usuario1.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="#">${usuario1.nome}</a></div>
                                <div class="postagem-data">${i.data}</div>
                            </div>
                        </div>
                        <div class="postagem-corpo">
                            <p>${i.texto}</p>
                        </div>
                    </section>
                    </c:forEach>
                </div>
                <div class="noticias-direita">
                    <c:forEach var="i" items="${mensagem}" step="2">
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${usuario1.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="#">${usuario1.nome}</a></div>
                                <div class="postagem-data">${i.data}</div>
                            </div>
                        </div>
                        <div class="postagem-corpo">
                            <p>${i.texto}</p>
                        </div>
                    </section>
                    </c:forEach>
                </div>
            </article>
        </div>
    </body>
</html>

