<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="bibliotecas"%>
<%@page import="java.util.List"%>
<%@page import="com.br.ifpb.valueObject.Mensagem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="paginas/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/postagens-usuario.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/barra.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/cabecalho-usuario.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisNet - ${usuario.nome}</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"/>
            <jsp:include page="cabecalho-usuario.jsp"/>
            <article class="article">
                <div class="noticias-esquerda">
                    <c:forEach var="i" begin="0" items="${mensagem}" step="2">
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${usuario.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="sobre?id=${usuario.id}">${usuario.nome}</a></div>
                                <div class="postagem-data">
                                ${f:formatarData(i.data)}
                                </div>
                            </div>
                        </div>
                        <div class="postagem-corpo">
                            <p>${i.texto}</p>
                        </div>
                    </section>
                    </c:forEach>
                </div>
                <div class="noticias-direita">
                    <c:forEach var="i" begin="1" items="${mensagem}" step="2"> 
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${usuario.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="sobre?id=${usuario.id}">${usuario.nome}</a></div>
                                <div class="postagem-data">
                                    ${f:formatarData(i.data)}
                                </div>
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
