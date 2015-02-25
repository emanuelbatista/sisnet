<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="bibliotecas" %>
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
                    <h2>Solitações de Relação</h2>
                    <div class="participantes">
                        <ul>
                            <c:forEach var="i" items="${relacoes}">
                            <li>
                                <div class="caixa-participantes">
                                    <img src="${i.foto}" alt="">
                                    <a class="usuario" href="sobre?id=${i.id}">${i.nome}</a>
                                    <br>
                                    <f:tipo-relacionamento remetente="${usuario.id}" destinatario="${i.id}"></f:tipo-relacionamento>
                                    <br>
                                    <a href="aceitar-relacao?id=${i.id}" class="btn btn-success botoes">Aceitar</a>
                                    <a href="desfazer-relacao?id=${i.id}" class="btn btn-success botoes">Cancelar</a>
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

