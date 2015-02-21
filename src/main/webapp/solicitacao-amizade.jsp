<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/solicitacao-amizade.css" rel="stylesheet" type="text/css">
        <title>Sisnet - ${usuario.nome}</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"/>
            <article>
                <section>
                    <h2>Solitações de Amizade</h2>
                    <div class="participantes">
                        <ul>
                            <c:forEach var="i" items="${solicitacoes}">
                            <li>
                                <div class="caixa-participantes">
                                    <img src="${i.foto}" alt="">
                                    <a class="usuario" href="sobre?id=${i.id}">${i.nome}</a>
                                    <br>
                                    <a href="aceitar-amizade?id=${i.id}" class="btn btn-success botoes">Aceitar</a>
                                    <a href="desfazer-amizade?id=${i.id}" class="btn btn-success botoes">Cancelar</a>
                                </div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </section>
            </article>
        </div>
    </body>
</html>
