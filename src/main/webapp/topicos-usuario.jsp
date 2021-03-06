
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
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/topicos-usuario.css" rel="stylesheet" type="text/css">
        <link href="css/grupo-cabecalho-usuario.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <title>SisNet - ${grupo.nome}</title>
    </head>
    <body>
        <jsp:include page="barra.jsp"/>
        <jsp:include page="grupo-cabecalho-usuario.jsp"/>
        <%
            GerenciarComentario gerenciarComentario = new GerenciarComentario();
            List<Comentario> comentarios;
        %>
        <article class="article">
            <div class="noticias-esquerda">
                <section class="section">
                    <div class="postar">
                        <form action="publicar-topico" method="post">
                            <ul>
                                <li><textarea name="texto" required class="novidades" placeholder="Compartilhe suas novidades aqui..."></textarea></li>
                                <li><input type="hidden" name="id" value="${grupo.id}"></li>
                                <li><input class="btn btn-success" type="submit" value="Compartilhar"></li>
                            </ul>
                        </form>
                    </div>
                </section>
                <c:forEach var="i" begin="1" items="${topicos}" step="2">
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${i.usuario.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="sobre?id=${i.usuario.id}">${i.usuario.nome}</a></div>
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
                                <c:forEach var="f" items="${i.comentarios}">
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
                            <div class="botoes">
                                <form action="publicar-comentario" method="post">
                                    <textarea rows="1" name="texto"></textarea>
                                    <input type="hidden" value="${i.id}" name="id">
                                    <input type="submit" value="Comentar">
                                </form>
                            </div>

                        </div>
                    </section>
                </c:forEach>
            </div>
            <div class="noticias-direita">
                <c:forEach var="i" begin="0" items="${topicos}" step="2">
                    <section class="section">
                        <div class="postagem-cabecalho">
                            <img src="${i.usuario.foto}" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="sobre?id=${i.usuario.id}">${i.usuario.nome}</a></div>
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
                                <c:forEach var="f" items="${i.comentarios}">
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
                            <div class="botoes">
                                <form action="publicar-comentario" method="post">
                                    <textarea rows="1" name="texto"></textarea>
                                    <input type="hidden" value="${i.id}" name="id">
                                    <input type="submit" value="Comentar">
                                </form>
                            </div>

                        </div>
                    </section>
                </c:forEach>
            </div>
        </article>
    </body>
</html>
