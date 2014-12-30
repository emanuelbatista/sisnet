<%-- 
    Document   : perfil
    Created on : 23/12/2014, 12:36:13
    Author     : Emanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/postagens.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/cabecalho.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"/>
            <jsp:include page="cabecalho.jsp"/>
            <article class="article">
                <div class="noticias-esquerda">
                    <section class="section">
                        <div class="postar">
                            <ul>
                                <li><textarea class="novidades" placeholder="Compartilhe suas novidades aqui..."></textarea></li>
                                <li><input class="btn btn-success" type="submit" value="Compartilhar"></li>
                            </ul>
                        </div>
                    </section>
                    <section class="section">
                        oii
                    </section>
                </div>
                <div class="noticias-direita">
                    <section class="section" >
                        <div class="postagem-cabecalho">
                            <img src="imagens/emanuel.jpg" alt="">
                            <div class="postagem-info">
                                <div class="postagem-nome-usuario"><a href="#">Emanuel</a></div>
                                <div class="postagem-data">12/04/2014</div>
                            </div>
                        </div>
                        <div class="postagem-corpo">
                            <p>Texto</p>
                        </div>
                    </section>
                    <section class="section">
                        2sdfsdf<br>
                        cdcdc<br>
                        dwsdsd<br>
                        sdfsdf<br>
                        cdcdc<br>
                        dwsdsd<br>
                        sdfsdf<br>
                        cdcdc<br>
                        dwsdsd<br>
                        sdfsdf<br>
                        cdcdc<br>
                        dwsdsd<br>
                    </section>
                    <section class="section">
                        3sdfsdf<br>
                        cdcdc<br>
                        dwsdsd<br>
                        sdfsdf<br>
                        cdcdc<br>
                        dwsdsd<br>
                        sdfsdf<br>
                        cdcdc<br>
                        dwsdsd<br>
                        sdfsdf<br>
                        cdcdc<br>
                        dwsdsd<br>
                    </section>
                </div>
            </article>
        </div>
    </body>
</html>
