<%-- 
    Document   : perfil
    Created on : 23/12/2014, 12:36:13
    Author     : Emanuel
--%>

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
                    <section class="section">
                        <div class="postar">
                            <ul>
                                <li><textarea class="novidades" placeholder="Compartilhe suas novidades aqui..."></textarea></li>
                                <li><input class="btn btn-success" type="submit" value="Compartilhar"></li>
                            </ul>
                        </div>
                    </section>
                    <%for (int i = 1; i < ((List<Mensagem>) request.getAttribute("mensagem")).size(); i = i + 2) {
                            pageContext.setAttribute("i", i);
                    %>
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${usuario.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="#">${usuario.nome}</a></div>
                                <div class="postagem-data">${mensagem[i].data}</div>
                            </div>
                        </div>
                        <div class="postagem-corpo">
                            <p>${mensagem[i].texto}</p>
                        </div>
                    </section>
                    <%}%>
                </div>
                <div class="noticias-direita">
                    <%for (int i = 0; i < ((List<Mensagem>) request.getAttribute("mensagem")).size(); i = i + 2) {
                            pageContext.setAttribute("i", i);
                    %>
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${usuario.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="#">${usuario.nome}</a></div>
                                <div class="postagem-data">${mensagem[i].data}</div>
                            </div>
                        </div>
                        <div class="postagem-corpo">
                            <p>${mensagem[i].texto}</p>
                        </div>
                    </section>
                    <%}%>
                </div>
            </article>
        </div>
    </body>
</html>
