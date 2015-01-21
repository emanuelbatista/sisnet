
<%@page import="com.br.ifpb.valueObject.Topico"%>
<%@page import="java.util.List"%>
<%@page import="com.br.ifpb.valueObject.Comentario"%>
<%@page import="com.br.ifpb.businessObject.GerenciarComentario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="/WEB-INF/bibliotecas.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="paginas/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/topicos-usuario.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/barra.css" rel="stylesheet" type="text/css">
        <link href="paginas/css/grupo-cabecalho.css" rel="stylesheet" type="text/css">
        <title>SisNet - ${grupo.nome}</title>
    </head>
    <body>
        <jsp:include page="barra.jsp"/>
        <jsp:include page="grupo-cabecalho.jsp"/>
        <%
            GerenciarComentario gerenciarComentario = new GerenciarComentario();
            List<Comentario> comentarios;
        %>
        <article class="article">
            <div class="noticias-esquerda">
                <c:forEach var="i" begin="0" items="${topicos}" step="2">
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${i.usuario.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="#">${i.usuario.nome}</a></div>
                                <div class="postagem-data">
                                    ${f:formatarData(i.data)} 
                                </div>
                            </div>
                        </div>
                        <div class="postagem-corpo">
                            <p>${i.texto}</p>
                        </div>
                        <div class="comentarios">
                            <ul>
                                <%
                                comentarios=gerenciarComentario.comentarioTopico(((Topico)pageContext.getAttribute("i")).getId());
                                pageContext.setAttribute("comentarios", comentarios);
                                %>
                                <c:forEach var="f" items="${comentarios}">
                                    <li>
                                        <div class="caixa-comentario">
                                            <img src="${f.usuario.foto}" alt="">
                                            <a href="">${f.usuario.nome}</a>
                                            <div class="data">${f:formatarData(f.data)}</div>
                                            <div class="texto-comentario">
                                                ${f.texto}
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>

                        </div>
                    </section>
                </c:forEach>
            </div>
            <div class="noticias-direita">
               <c:forEach var="i" begin="1" items="${topicos}" step="2">
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${i.usuario.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="#">${i.usuario.nome}</a></div>
                                <div class="postagem-data">
                                    ${f:formatarData(i.data)} 
                                </div>
                            </div>
                        </div>
                        <div class="postagem-corpo">
                            <p>${i.texto}</p>
                        </div>
                        <div class="comentarios">
                            <ul>
                                <%
                                comentarios=gerenciarComentario.comentarioTopico(((Topico)pageContext.getAttribute("i")).getId());
                                pageContext.setAttribute("comentarios", comentarios);
                                %>
                                <c:forEach var="f" items="${comentarios}">
                                    <li>
                                        <div class="caixa-comentario">
                                            <img src="${f.usuario.foto}" alt="">
                                            <a href="">${f.usuario.nome}</a>
                                            <div class="data">${f:formatarData(f.data)}</div>
                                            <div class="texto-comentario">
                                                ${f.texto}
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </section>
                </c:forEach>
            </div>
        </article>
    </body>
</html>
