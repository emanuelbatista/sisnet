<%-- 
    Document   : sobre
    Created on : 06/01/2015, 08:10:46
    Author     : Emanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/cabecalho.css" rel="stylesheet" type="text/css">
        <link href="css/sobre.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisNet - Emanuel</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"></jsp:include>
            <jsp:include page="cabecalho.jsp"></jsp:include>
            <article class="article">
                <div class="noticias-esquerda">
                    <section class="section" id="dados-pessoal">
                        <div class="postagem-titulo">
                            <h3>Dados Pessoais</h3>
                        </div>
                        <div class="postagem-corpo">
                            <p><b>Nome: </b>Emanuel
                                <br>
                                <b>Apelido: </b>Manelzin
                                <br>
                                <b>Data de Nascimento: </b>26/04/1995
                                <br>
                                <b>Cidade: </b>Aparecida
                                <br>
                                <b>E-mail: </b>emanuel@gmail.com
                                <br>
                                <b>Profissão: </b>Programador
                            </p>
                        </div>
                    </section>
                    <section class="section" id="locais-estudou">
                        <div class="postagem-titulo">
                            <h3>Locais onde Estudou</h3>
                        </div>
                        <div class="postagem-corpo">
                            <ul>
                                <li>Recursive</li>
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
                                <li>Recursive</li>
                            </ul>
                        </div>
                    </section>
                </div>
            </article>
        </div>
    </body>

</html>
