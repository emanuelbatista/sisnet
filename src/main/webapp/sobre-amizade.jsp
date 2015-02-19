
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.br.ifpb.valueObject.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/cabecalho-amizade.css" rel="stylesheet" type="text/css">
        <link href="css/sobre.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisNet - ${usuario1.nome}</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"></jsp:include>
            <jsp:include page="cabecalho-amizade.jsp"></jsp:include>
                <article class="article">
                    <div class="noticias-esquerda">
                        <section class="section" id="dados-pessoal">
                            <div class="postagem-titulo">
                                <h3>Dados Pessoais</h3>
                            </div>
                            <div class="postagem-corpo">
                                <p>
                                <c:if test="${usuario1.nome!=null}">
                                    <b>Nome: </b>${usuario1.nome}
                                    <br>
                                </c:if>
                                <c:if test="${usuario1.apelido!=null}">
                                    <b>Apelido: </b>${usuario1.apelido}
                                    <br>
                                </c:if>
                                <c:if test="${usuario1.data_nascimento!=null}">
                                    <b>Data de Nascimento: </b>
                                    <%DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");%>
                                    <%=((Usuario) request.getAttribute("usuario1")).getData_nascimento().toLocalDate().format(formatar)%>
                                    <br>
                                </c:if>
                                <c:if test="${usuario1.cidade!=null}">
                                    <b>Cidade: </b>${usuario1.cidade}
                                    <br>
                                </c:if>
                                <c:if test="${usuario1.email!=null}">
                                    <b>E-mail: </b>${usuario1.email}
                                    <br>
                                </c:if>
                                <c:if test="${usuario1.profissao!=null}">
                                    <b>Profissão: </b>${usuario1.profissao}
                                    <br>
                                </c:if>
                            </p>
                        </div>
                    </section>
                    <section class="section" id="relacionamentos">
                        <div class="postagem-titulo">
                            <h3>Relacionamentos</h3>
                        </div>
                        <div class="postagem-corpo">
                            <ul class="relacionamentos">
                                <c:if test="${relacoes!=null}">
                                    <c:forEach var="i" items="${relacoes}">
                                        <li>
                                            <div class="info-relacionamento">
                                                <div class="imagem">
                                                    <img src="${i.usuario_2.foto}" alt="">
                                                </div>
                                                <div class="info-basica-relacionamento">
                                                    <div class="nome"><a href="sobre?id=${i.usuario_2.id}">${i.usuario_2.nome}</a></div>
                                                    <div class="tipo">${i.tipo}</div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </ul>
                            <c:if test="${relacoes==null}">
                                Nenhum
                            </c:if>
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
                                <c:if test="${usuario1.locais_trabalhou!=null}">
                                    <c:forEach var="i" items="${usuario1.locais_trabalhou}">
                                        <li>${i}</li>
                                        </c:forEach> 
                                    </c:if>
                            </ul>
                            <c:if test="${usuario1.locais_trabalhou==null}">
                                Nenhum
                            </c:if>
                        </div>
                    </section>
                    <section class="section" id="locais-estudou">
                        <div class="postagem-titulo">
                            <h3>Locais onde Estudou</h3>
                        </div>
                        <div class="postagem-corpo">
                            <ul>
                                <c:if test="${usuario1.locais_estudou!=null}">
                                    <c:forEach var="i" items="${usuario1.locais_estudou}">
                                        <li>${i}</li>
                                        </c:forEach>
                                    </c:if>
                            </ul>
                            <c:if test="${usuario1.locais_estudou==null}">
                                Nenhum
                            </c:if>
                        </div>
                    </section>
                </div>
            </article>
        </div>
    </body>

</html>

