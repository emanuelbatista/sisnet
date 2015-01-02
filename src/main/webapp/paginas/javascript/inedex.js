/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function abrirCadastro(){
    if(document.getElementById("logar-se").innerHTML==="Login"){     
       document.getElementById("cadastro_usuario").style.display="none";
       document.getElementById("cadastro_login").style.display="block";
       document.getElementById("logar-se").innerHTML='Cadastro';
       fadeIn("cadastro_login",0.6);
    }else{      
       document.getElementById("cadastro_login").style.display="none";
       document.getElementById("cadastro_usuario").style.display="block";
       document.getElementById("logar-se").innerHTML='Login';
       fadeIn("cadastro_usuario",0.6);
    }
}
function fadeOut(id, time) {
    fade(id, time, 100, 0);
}

function fadeIn(id, time) {
    fade(id, time, 0, 100);
}

function fade(id, time, ini, fin) {
    var target = document.getElementById(id);
    var alpha = ini;
    var inc;
    if (fin >= ini) { 
        inc = 2; 
    } else {
        inc = -2;
    }
    timer = (time * 1000) / 50;
    var i = setInterval(
        function() {
            if ((inc > 0 && alpha >= fin) || (inc < 0 && alpha <= fin)) {
                clearInterval(i);
            }
            setAlpha(target, alpha);
            alpha += inc;
        }, timer);
}

function setAlpha(target, alpha) {
    target.style.filter = "alpha(opacity="+ alpha +")";
    target.style.opacity = alpha/100;
}

