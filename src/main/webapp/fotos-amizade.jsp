
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/fotos-usuario.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/cabecalho-amizade.css" rel="stylesheet" type="text/css">
        <title>SisNet - ${usuario.nome}</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"/>
            <jsp:include page="cabecalho-amizade.jsp"/>
            <article class="article">
                <div class="fotos">
                    <c:forEach var="i" items="${fotos}">
                    <section>
                        <a href="#">
                            <img src="${i.url}" alt="" class="imagens">
                        </a>
                    </section>
                     </c:forEach> 
                </div>
            </article>
        </div>
    </body>
</html>
