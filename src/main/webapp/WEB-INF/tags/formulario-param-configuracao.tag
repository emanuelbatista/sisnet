
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="/WEB-INF/bibliotecas.tld"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<form action="salvar-informacao" method="post">
    <b>Nome: </b>
    <br>
    <input type="text" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" required value="${param.nome}" id="nome" class="campo-texto" name="nome" placeholder="Digite seu Nome">
    <br>
    <b>Sobrenome: </b>
    <br>
    <input type="text" value="${param.sobrenome}" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" id="sobrenome" class="campo-texto" name="sobrenome" placeholder="Digite seu Sobrenome">
    <br>
    <b>Apelido: </b>
    <br>
    <input type="text" value="${param.apelido}" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" id="apelido" class="campo-texto" name="apelido" placeholder="Digite seu Apelido">
    <br>
    <b>Data de Nascimento: </b>
    <br>
    <input type="text" value="${param.data_nascimento}"  id="data_nascimento" name="data_nascimento" class="campo-texto">
    <br>
    <b>Cidade: </b>
    <br>
    <input type="text" value="${param.cidade}" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" id="cidade" placeholder="Digite sua Cidade" class="campo-texto" name="cidade">
    <br>
    <b>E-mail: </b>
    <br>
    <input type="email" value="${param.email}" id="email" required placeholder="Digite seu E-mail" class="campo-texto" name="email">
    <br>
    <b>Senha: </b>
    <br>
    <input type="password" value="${param.senha}" id="senha" required placeholder="Digite sua senha" class="campo-texto" name="senha"/>
    <br>
    <b>Profissão: </b>
    <br>
    <input type="text" value="${param.profissao}" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" id="profissao" placeholder="Digite sua Profissão" class="campo-texto" name="profissao">
    <br>
    <b>Status: </b>
    <br>
    <input type="text" value="${param.status}" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" id="status" placeholder="Digite seu Status" class="campo-texto" name="status">
    <div class="botoes">
        <input type="submit" class="btn btn-success" value="Salvar Informações">
    </div>
</form>