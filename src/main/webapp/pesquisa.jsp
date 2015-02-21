<%-- 
    Document   : pesquisa
    Created on : 20/02/2015, 22:09:50
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
        <link href="css/pesquisa.css" rel="stylesheet" type="text/css">
        <title>SisNet - ${usuario.nome}</title>
    </head>
    <body>
        <jsp:include page="barra.jsp"/>
        <article>
            <section>
                <h2>Pesquisa</h2>
                <h5>Pessoas</h5>
                <ul class="amigos">
                    <c:forEach var="pessoa" items="${pessoas}">
                        <li>
                            <div class="caixa">
                                <div class="imagem">
                                    <img src="${pessoa.foto}" alt="">
                                </div>
                                <div class="nome">
                                    <a href="sobre?id=${pessoa.id}">${pessoa.nome}</a>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <h5>Grupos</h5>
                <ul class="amigos">
                    <%--<c:forEach var="amigo" items="${usuario}">--%>
                        <li>
                            <div class="caixa-grupo">
                                <div class="grupo">
                                    <a href="sobre?id=${amigo.id}">emanuel</a>
                                </div>
                            </div>
                        </li>
                    <%--</c:forEach>--%>
                </ul>

            </section>
        </article>
    </body>
</html>
