<%-- 
    Document   : barra
    Created on : 30/12/2014, 07:27:07
    Author     : Emanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
   function menu(){
       var menu=document.getElementById("barra-menu");
       if(menu.style==="" || menu.style.display==="none"){
           menu.style.display="inline";
       }else{
           menu.style.display="none";
       }
   }
   function tela(){
       var menu=document.getElementById("barra-menu");
       if(window.innerWidth>900){
           menu.style.display="inline";
       }else{
           menu.style.display="none";
       }
   }
   window.addEventListener('resize',tela);
   window.addEventListener('load',tela);
</script>
<div class="barra">
    <h1 id="titulo">SisNet</h1>
    <button class="botao-menu" id="menu" onclick="menu()">menu</button>
    <div class="barra-menu" id="barra-menu"  style="display: inline">
        <ul>
            <li>
                <a href="sobre?id=${usuario.id}">
                    <img src="${usuario.foto}" alt="">
                    <div class="barra-nome">${usuario.nome}</div>
                </a>
            </li>
            <li>
                <hr>
            </li>
            <li>
                <a href="inicio">
                    Página Inicial
                </a>
            </li>
            <li>
                <hr>
            </li>
            <li>
                <a href="sair">
                    Sair
                </a>
            </li>
        </ul>
    </div>
</div>
