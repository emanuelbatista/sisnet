
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <div class="row cabecalho">
        <div class="info-basica">
            <div class="imagem-perfil">
                <img src="${usuario1.foto}" alt=""> 
            </div>
            <div class="nome-perfil">
                ${usuario1.nome}
            </div>
        </div>
        <div class="desfazer-amizade">
            <a href="#">Desfazer Amizade</a>
        </div>
    </div>
    <nav>
        <ul class="navegacao">
            <li id="sobre">
                <a href="sobre?email=${usuario1.email}">Sobre</a>
            </li>
            <li id="postagens">
                <a href="postagens?email=${usuario1.email}">Postagens</a>
            </li>
            <li id="fotos">
                <a href="#">Fotos</a>
            </li>
        </ul>
    </nav>
</header>