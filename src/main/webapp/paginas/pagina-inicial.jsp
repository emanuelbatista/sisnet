<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.sql.Timestamp"%>
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
                 <%
                      Timestamp data;
                      LocalDateTime dateTime;
                      DateTimeFormatter dateTimeFormatter;
                      DateTimeFormatter dateTimeFormatter1;
                    %>
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
                                 <%
                                    data=((Mensagem)pageContext.getAttribute("i")).getData();
                                    dateTime=data.toLocalDateTime();
                                    dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    dateTimeFormatter1=DateTimeFormatter.ofPattern("HH:mm");
                                %><%=dateTime.format(dateTimeFormatter)%> às <%=dateTime.format(dateTimeFormatter1)%>
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
                                  <%
                                    data=((Mensagem)pageContext.getAttribute("i")).getData();
                                    dateTime=data.toLocalDateTime();
                                    dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    dateTimeFormatter1=DateTimeFormatter.ofPattern("HH:mm");
                                %><%=dateTime.format(dateTimeFormatter)%> às <%=dateTime.format(dateTimeFormatter1)%>
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
