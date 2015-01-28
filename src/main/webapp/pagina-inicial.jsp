<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="bibliotecas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/pagina-inicial.css" rel="stylesheet" type="text/css">
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
                    <nav>
                        <div class="opcao">
                            <h6>SOLICITAÇÕES</h6>
                            <ul>
                                <li>
                                    <a href="solicitacao-amizade">
                                        Amizades
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="opcao">
                            <h6>GRUPOS</h6>
                            <ul>
                                <c:forEach var="i" items="${grupos}">
                                    <li>
                                        <a href="topicos?id=${i.id}">
                                            ${i.nome}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </nav>

                </div> 
            </header>
            <article>
                <div class="noticias-esquerda">
                    <section class="section">
                        <div class="postar">
                            <form action="publicar-mensagem" method="post">
                                <ul>
                                    <li><textarea class="novidades" name="mensagem" placeholder="Compartilhe suas novidades aqui..."></textarea></li>
                                    <li><input class="btn btn-success" type="submit" value="Compartilhar"></li>
                                </ul>
                            </form>
                        </div>
                    </section>
                    <c:forEach var="i" begin="1" items="${mensagens}" step="2">
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
                        </section>
                    </c:forEach>
                </div>
                <div class="noticias-direita">
                    <c:forEach var="i" items="${mensagens}" step="2">
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
                        </section>
                    </c:forEach>
                </div>
            </article>
        </div>
    </body>
</html>
