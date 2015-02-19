
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>SisNet</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" href="" type="image/x-icon">
    <!--[if lte IE 8]>
         <link href="css/indexExplore.css" rel="stylesheet" type="text/css">
    <![endif]-->
    <script type="text/javascript">
        function load() {
            var imprimir = "";
        <c:forEach var="i" items="${cadastroErros}">
            texto = String("${i}");
            imprimir += texto + "\n";
        </c:forEach>
        <c:if test="${cadastroErros!=null}">
            document.getElementById("cadastro_login").style.display = "none";
            document.getElementById("cadastro_usuario").style.display = "block";
            document.getElementById("logar-se").innerHTML = 'Login';
            alert(imprimir);
        </c:if>
         <c:forEach var="i" items="${loginErros}">
            texto = String("${i}");
            imprimir += texto + "\n";
        </c:forEach>
        <c:if test="${loginErros!=null}">
            alert(imprimir);
        </c:if>

        }
        window.addEventListener('load', load);
    </script>
<body>
    <div class="container">
        <header>
            <div id="cabecario">
                <h1 id="titulo">SisNet</h1>
                <nav>
                    <button onclick="abrirCadastro()" id="logar-se">Cadastro</button>
                </nav>              
            </div>
        </header>
        <article>
            <section>
                <div class="campos" id="cadastro_login">
                    <h3>Login</h3>
                    <div id="login">
                        <form action="login" method="post">
                            <ul>
                                <li><span class="texto"><label for="email_login">Login</label></span><input id="email_login" <c:if test="${param.email_login!=null}">value="${param.email_login}"</c:if> type="email" class="campo_texto" required placeholder="Digite o seu email" name="email_login"></li>
                                <li><span class="texto"><label for="senha_login">Senha</label></span><input type="password" <c:if test="${param.senha_login!=null}">value="${param.senha_login}"</c:if> id="senha_login" class="campo_texto" required placeholder="Digite a sua senha" name="senha_login"></li>
                                <li><input type="submit" class="submit" id="logar" value="Entrar" ></li>
                            </ul>
                        </form>
                    </div>
                </div>
            </section>
            <section>
                <div class="campos" id="cadastro_usuario">
                    <h3>Cadastro do Usuario</h3>
                    <form action="cadastro-usuario" method="post">
                        <ul>
                            <li><span class="texto"><label for="nome">Nome</label></span><input type="text" <c:if test="${param.nome!=null}">value="${param.nome}"</c:if> required  maxlength="20" placeholder="Digite seu nome" class="campo_texto" id="nome" name="nome"><br></li>
                            <li><span class="texto"><label for="sobrenome">Sobrenome</label></span><input type="text" <c:if test="${param.sobrenome!=null}">value="${param.sobrenome}"</c:if> placeholder="Digite seu sobrenome" class="campo_texto" id="sobrenome" name="sobrenome"><br></li>
                            <li><span class="texto"><label for="email">E-mail</label></span><input type="email" <c:if test="${param.email!=null}">value="${param.email}"</c:if>  id="email" required placeholder="Digite seu e-mail" class="campo_texto" name="email"><br></li>
                            <li><span class="texto"><label for="senha">Senha</label></span><input id="senha" type="password" <c:if test="${param.senha!=null}">value="${param.senha}"</c:if> required placeholder="Digite sua senha" class="campo_texto" name="senha"><br></li>
                            <li><span class="texto"><label for="data_nascimento">Data de Nascimento</label></span><input type="date" <c:if test="${param.data_nascimento!=null}">value="${param.data_nascimento}"</c:if> class="campo_texto" id="data_nascimento" name="data_nascimento"><br></li>
                            <li><input type="submit" id="botao_cadastrar" class="submit" name="cadastro" value="Cadastrar"></li>
                        </ul>
                    </form>
                </div>
            </section>
        </article>     
    </div>
    <script src="javascript/inedex.js" type="text/javascript"></script>
</body>
</html>

