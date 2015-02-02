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
                        <ul class="relcionamentos">
                            <c:forEach var="i" items="${relacao}">
                                <li>
                                    <div class="info-relacionamento">
                                        <div class="imagem">
                                            <img src="${i.usuario_2.foto}" alt="">
                                        </div>
                                        <div class="info-basica">
                                            <div class="nome">${i.usuario_2.nome}</div>
                                            <div class="tipo">${i.tipo}</div>
                                        </div>
                                    </div>
                                    <div class="remove-relacionamento">
                                        <a href="#">remove</a>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        <b>Adicionar Relacionamento: </b>
                        <form action="" method="post">
                            <br>
                            <b>E-mail da pessoa do relacionamento: </b>
                            <br>
                            <input type="email" class="campo-texto" placeholder="Digite o email">
                            <br>
                            <b>Tipo do relacionamento: </b>
                            <br>
                            <select>
                                <option>Casado(a)</option>
                                <option selected>Namorado(a)</option>
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
                                    <li>${i} <a href="#">remover</a></li>
                                    </c:forEach>
                                </c:if>
                            <li><input type="text" placeholder="Digite um local de Trabalho"></li>
                            <li><input type="button" value="Add"></li>
                        </ul>
                        <br>
                        <b>Locais onde Estudou: </b>
                        <ul class="lista">
                            <c:if test="${usuario.locais_trabalhou==null}">
                                <li>Nenhum</li>
                                </c:if>
                                <c:if test="${usuario.locais_trabalhou!=null}">
                                    <c:forEach var="i" items="${usuario.locais_estudou}">
                                    <li>oii <a href="#">remover</a></li>

                                </c:forEach>
                            </c:if>
                            <li><input type="text" placeholder="Digite onde Estudou"></li>
                            <li><input type="button" value="Add"></li>
                        </ul>                          

                    </div>

                </section>
            </article>
        </div>
    </body>
</html>
