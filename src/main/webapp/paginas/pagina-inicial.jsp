<%-- 
    Document   : pagina-inicial
    Created on : 13/01/2015, 08:16:21
    Author     : Emanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/barra.css" rel="stylesheet" type="text/css">
        <link href="css/pagina-inicial.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="barra.jsp"/>
            <div class="info-usuario">
                <div class="cabecalho-info">
                    <img src="imagens/aynara.jpg" alt="">
                    <div class="cabecalho-info-nome">
                        Emanuel
                    </div>
                </div>
                <div class="grupos">
                    <h6>GRUPOS</h6>
                </div>
            </div> 

        </div>
    </body>
</html>
