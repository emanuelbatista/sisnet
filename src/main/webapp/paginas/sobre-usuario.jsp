<%-- 
    Document   : sobre
    Created on : 06/01/2015, 08:10:46
    Author     : Emanuel
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.br.ifpb.valueObject.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/cabecalho-usuario.css" rel="stylesheet" type="text/css">
        <link href="css/sobre.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisNet - ${usuario.nome}</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"></jsp:include>
            <jsp:include page="cabecalho-usuario.jsp"></jsp:include>
                <article class="article">
                    <div class="noticias-esquerda">
                        <section class="section" id="dados-pessoal">
                            <div class="postagem-titulo">
                                <h3>Dados Pessoais</h3>
                            </div>
                            <div class="postagem-corpo">
                                <p>
                                <c:if test="${usuario.nome!=null}">
                                    <b>Nome: </b>${usuario.nome}
                                    <br>
                                </c:if>
                                <c:if test="${usuario.apelido!=null}">
                                    <b>Apelido: </b>${usuario.apelido}
                                    <br>
                                </c:if>
                                <c:if test="${usuario.data_nascimento!=null}">
                                    <b>Data de Nascimento: </b>
                                    <% DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");%>
                                    <%= ((Usuario) request.getSession().getAttribute("usuario")).getData_nascimento().toLocalDate().format(formatar)%>
                                    <br>
                                </c:if>
                                <c:if test="${usuario.cidade!=null}">
                                    <b>Cidade: </b>${usuario.cidade}
                                    <br>
                                </c:if>
                                <c:if test="${usuario.email!=null}">
                                    <b>E-mail: </b>${usuario.email}
                                    <br>
                                </c:if>
                                <c:if test="${usuario.profissao!=null}">
                                    <b>Profissão: </b>${usuario.profissao}
                                    <br>
                                </c:if>
                            </p>
                        </div>
                    </section>
                    <section class="section" id="locais-estudou">
                        <div class="postagem-titulo">
                            <h3>Locais onde Estudou</h3>
                        </div>
                        <div class="postagem-corpo">
                            <ul>
                                <c:forEach var="i" items="${usuario.locais_estudou}">
                                    <li>${i}</li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </section>
                </div>
                <div class="noticias-direita">
                    <section class="section" id="locais-trabalho">
                        <div class="postagem-titulo">
                            <h3>Locais onde Trabalhou</h3>
                        </div>
                        <div class="postagem-corpo">
                            <ul>
                                <c:forEach var="i" items="${usuario.locais_trabalhou}">
                                    <li>${i}</li>
                                 </c:forEach> 
                            </ul>
                        </div>
                    </section>
                </div>
            </article>
        </div>
    </body>

</html>
