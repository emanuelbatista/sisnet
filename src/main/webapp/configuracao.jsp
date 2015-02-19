<%-- 
    Document   : configuracao
    Created on : 17/01/2015, 07:09:15
    Author     : Emanuel
--%>

<%@page import="com.br.ifpb.businessObject.GerenciarRelacao"%>
<%@page import="com.br.ifpb.valueObject.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="ct" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/configuracao.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        var imagem = document.getElementById('imagem');
                        imagem.src = e.target.result;
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
            function load() {
                var imprimir = "";
            <c:forEach var="i" items="${mensagensInformacao}">
                texto = String("${i}");
                switch (texto) {
                    case "nome formato errado ou vazio!":
                        document.getElementById('nome').style.backgroundColor = "#ff6666";
                        break;
                }
                imprimir += texto + "\n";
            </c:forEach>
            <c:if test="${mensagensInformacao!=null}">
                alert(imprimir);
            </c:if>

            }
            window.addEventListener('load', load);
        </script>
        <title>SisNet - ${usuario.nome}</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"/>
            <article>
                <section>
                    <h2>Configuração</h2>
                    <div class="configuracoes">
                        <form action="upload-imagem-perfil" enctype="multipart/form-data" method="post">
                            <h3>Informação Pessoal</h3>
                            <b>Foto Perfil: </b>
                            <br>
                            <img src="${usuario.foto}" alt="" id="imagem" class="imagem-perfil">
                            <br>
                            <b>Adicionar Foto:</b>
                            <div class="adicionar-foto">
                                <input type="file" required onchange="readURL(this)" accept="image/*" class="foto" name="foto" >
                            </div>
                            <div class="botoes">
                                <input type="submit" class="btn btn-success" value="Salvar Imagem">
                            </div>
                            <br> 
                        </form>
                        <c:if test="${mensagensInformacao==null}">
                            <ct:formulario-usuario-configuracao campo="${usuario}"/>
                        </c:if>
                        <c:if test="${mensagensInformacao!=null}">
                            <ct:formulario-param-configuracao />
                        </c:if>
                        <br>


                    </div>
                    <div class="configuracoes">
                        <h3>Relacionamento</h3>
                        <b>Relacionamentos: </b>
                        <ul class="relacionamentos">
                            <c:forEach var="i" items="${relacao}">
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
                                    <div class="remove-relacionamento">
                                        <a href="#">remover</a>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        <br>
                        <b>Adicionar Relacionamento: </b>
                        <form action="adicionar-relacao" method="post">
                            <b>E-mail da pessoa do relacionamento: </b>
                            <br>
                            <input type="email" name="email" class="campo-texto" placeholder="Digite o email">
                            <br>
                            <b>Tipo do relacionamento: </b>
                            <br>
                            <select name="tipo">
                                <option value="Casado(a)">Casado(a)</option>
                                <option value="Namorado(a)" selected>Namorado(a)</option>
                                <option value="Primo(a)">Primo(a)</option>
                                <option value="Mãe">Mãe</option>
                                <option value="Pai">Pai</option>
                            </select>
                            <div class="botoes">
                                <input type="submit" class="btn btn-success" value="Salvar Relacionamento">
                            </div>
                        </form>
                        <br>
                        <b>Locais de Trabalho: </b>
                        <ul class="lista">
                            <c:if test="${usuario.locais_trabalhou==null}">
                                <li>Nenhum</li>
                                </c:if>
                                <c:if test="${usuario.locais_trabalhou!=null}">
                                    <c:forEach var="i" items="${usuario.locais_trabalhou}">
                                    <li>
                                        ${i} <a href="remover-local-trabalho?local-trabalho=${i}">remover</a>
                                    </li>
                                </c:forEach>
                            </c:if>        
                            <li>
                                <form action="adicionar-local-trabalho" method="post">
                                    <input type="text" name="local-trabalho" placeholder="Digite um local de Trabalho">
                                    <br>
                                    <br>
                                    <input type="submit" value="Add"> 
                                </form>
                            </li>
                        </ul>

                        <br>
                        <b>Locais onde Estudou: </b>
                        <ul class="lista">
                            <c:if test="${usuario.locais_estudou==null}">
                                <li>Nenhum</li>
                                </c:if>
                                <c:if test="${usuario.locais_estudou!=null}">
                                    <c:forEach var="i" items="${usuario.locais_estudou}">
                                    <li>
                                        ${i} <a href="remover-local-estudou?local-estudou=${i}">remover</a>
                                    </li>
                                </c:forEach>
                            </c:if>
                            <li>
                                <form action="adicionar-local-estudou" method="post">
                                    <input type="text" name="local-estudou" placeholder="Digite onde Estudou">
                                    <br>
                                    <br>
                                    <input type="submit" value="Add">
                                </form>
                            </li>
                        </ul>
                        <div class="botoes">
                            <b>Excluir Conta:</b>
                            <form action="excluir-conta" method="post">
                                <input type="submit" class="btn btn-success" value="Excluir Conta">
                            </form>
                        </div>

                    </div>

                </section>
            </article>
        </div>
    </body>
</html>
