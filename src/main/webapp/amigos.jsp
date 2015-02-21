<%-- 
    Document   : amigos
    Created on : 20/02/2015, 11:10:23
    Author     : Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/cabecalho-usuario.css" rel="stylesheet" type="text/css">
        <link href="css/amigos.css" rel="stylesheet" type="text/css">
        <title>SisNet - ${usuario.nome}</title>
    </head>
    <body>
        <jsp:include page="barra.jsp"/>
        <jsp:include page="cabecalho-usuario.jsp"/>
        <article>
            <h2>Amigos</h2>
            <ul class="amigos">
                <c:forEach var="amigo" items="${amigos}">
                    <li>
                        <div class="caixa">
                            <div class="imagem">
                                <img src="${amigo.foto}" alt="">
                            </div>
                            <div class="nome">
                                <a href="sobre?id=${amigo.id}">${amigo.nome}</a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </article>
    </body>
</html>
