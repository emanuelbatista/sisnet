<%-- 
    Document   : pagina-inicial
    Created on : 13/01/2015, 08:16:21
    Author     : Emanuel
--%>

<%@page import="com.br.ifpb.valueObject.Usuario"%>
<%@page import="com.br.ifpb.valueObject.Mensagem"%>
<%@page import="com.br.ifpb.businessObject.GerenciarUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="paginas/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/barra.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/pagina-inicial.css" rel="stylesheet" type="text/css">
        <title>Sisnet - ${usuario.nome}</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"/>
            <header>
                <div class="info-usuario">
                    <div class="cabecalho-info">
                        <img src="${usuario.foto}" alt="">
                        <div class="cabecalho-info-nome">
                            ${usuario.nome}
                        </div>
                    </div>
                    <div class="grupos">
                        <h6>GRUPOS</h6>
                        <ul>
                            <li>
                                <a href="#">
                                    Meu Grupo
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    Meu Grupo
                                </a>
                            </li>
                        </ul>
                    </div>
                </div> 
            </header>
            <article>
                 <%GerenciarUsuario gerenciarUsuario=new  GerenciarUsuario();
                   Usuario usuario=null;
                 %>
                <div class="noticias-esquerda">
                    <c:forEach var="i" items="${mensagens}" step="2">
                       <%
                        usuario = gerenciarUsuario.getUsuario(((Mensagem)pageContext.getAttribute("i")).getUsuario());
                        pageContext.setAttribute("usuario1", usuario);
                        %>
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${usuario1.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="sobre?email=${usuario1.email}">${usuario1.nome}</a></div>
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
                    <c:forEach var="i" begin="1" items="${mensagens}" step="2">
                        <%
                        usuario = gerenciarUsuario.getUsuario(((Mensagem)pageContext.getAttribute("i")).getUsuario());
                        pageContext.setAttribute("usuario1", usuario);
                        %>
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${usuario1.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="sobre?email=${usuario1.email}">${usuario1.nome}</a></div>
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
