
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <div class="row cabecalho">
        <div class="info-basica-relacionamento">
            <div class="imagem-perfil">
                <img src="${usuario1.foto}" alt=""> 
            </div>
            <div class="nome-perfil">
                ${usuario1.nome}
            </div>
        </div>
        <div class="desfazer-amizade">
            <a href="desfazer-amizade?id=${usuario1.id}">Desfazer Amizade</a>
        </div>
    </div>
    <nav>
        <ul class="navegacao">
            <li id="sobre">
                <a href="sobre?id=${usuario1.id}">Sobre</a>
            </li>
            <li id="topicos">
                <a href="postagens?id=${usuario1.id}">Postagens</a>
            </li>
            <li id="fotos">
                <a href="fotos?id=${usuario1.id}">Fotos</a>
            </li>
        </ul>
    </nav>
</header>