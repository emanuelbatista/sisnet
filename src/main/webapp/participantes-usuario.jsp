<%-- 
    Document   : participantes
    Created on : 20/01/2015, 19:37:24
    Author     : Emanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/grupo-cabecalho-usuario.css" rel="stylesheet" type="text/css">
        <link href="css/participantes-usuario.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <title>SisNet - ${grupo.nome}</title>
    </head>
    <body>
        <jsp:include page="barra.jsp"/>
        <jsp:include page="grupo-cabecalho-usuario.jsp"/>
        <article>
            <section>
                <h2>Participantes</h2>
                <div class="participantes">
                    <ul>
                        <c:forEach var="i" items="${participantes}">
                        <li>
                            <div class="caixa-participantes">
                                <img src="${i.foto}" alt="">
                                <a href="sobre?id=${i.id}">${i.nome}</a>
                            </div>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </section>
        </article>
    </body>
</html>
