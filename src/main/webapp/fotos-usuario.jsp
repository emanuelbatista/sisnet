
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/fotos-usuario.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/cabecalho-usuario.css" rel="stylesheet" type="text/css">
        <script type="text/javascript">
            function fotos(){
                var foto=document.getElementById("foto");
                var caixa=document.getElementById("caixa-foto");
                alert(foto.value);
                caixa.style.backgroundImage='url('+foto.path+')';
            }
        </script>
        <title>SisNet - ${usuario.nome}</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"/>
            <jsp:include page="cabecalho-usuario.jsp"/>
            <article class="article">
                <div class="publica-foto">
                    <h4>Publicar Foto:</h4>
                    <form action="a" method="post">
                        <div class="caixa-foto" id="caixa-foto">
                            <input onchange="fotos()" type="file" id="foto" name="foto">
                        </div>
                        <input type="submit" value="Enviar">
                    </form>
                </div>
                <div class="fotos">
                    <%--<c:forEach var="i" items="${fotos}">--%>
                    <section>
                        <a href="#">
                            <img src="imagens/emanuel.jpg" alt="" class="imagens">
                        </a>
                    </section>
                    <section>
                        <a href="#">
                            <img src="imagens/emanuel.jpg" alt="" class="imagens">
                        </a>
                    </section>
                    <section>
                        <a href="#">
                            <img src="imagens/emanuel.jpg" alt="" class="imagens">
                        </a>
                    </section>
                    <section>
                        <a href="#">
                            <img src="imagens/emanuel.jpg" alt="" class="imagens">
                        </a>
                    </section>
                    <section>
                        <a href="#">
                            <img src="imagens/emanuel.jpg" alt="" class="imagens">
                        </a>
                    </section>
                    <section>
                        <a href="#">
                            <img src="imagens/emanuel.jpg" alt="" class="imagens">
                        </a>
                    </section>
                    <%-- </c:forEach> --%>
                </div>
            </article>
        </div>
    </body>
</html>
