<%-- 
    Document   : criar-grupo
    Created on : 19/02/2015, 14:40:01
    Author     : Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/criar-grupo.css" type="text/css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SisNet - Grupo</title>
    </head>
    <body>
        <jsp:include page="barra.jsp"/>
        <article>
            <section>
                <h2>Novo Grupo</h2>
                <div class="container-grupo">
                    <form action="criar-grupo" method="post">
                        <b>Nome: </b>
                        <br>
                        <input type="text" class="campo-texto" placeholder="Digite o nome do Grupo" title="Nome do Grupo" name="nome">
                        <br>
                        <b>Descrição: </b>
                        <br>
                        <input type="text" class="campo-texto" placeholder="Digite a descrição do Grupo" title="Descrição do Grupo" name="descricao">
                        <br>
                        <input type="submit" class="btn btn-success" value="Criar">
                    </form>
                </div>
            </section>
        </article>
    </body>
</html>
