
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="campo" required="true" type="com.br.ifpb.valueObject.Usuario"  rtexprvalue="true"%>

<form action="salvar-informacao" method="post">
    <b>Nome: </b>
    <br>
    <input type="text" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" required value="${campo.nome}" id="nome" class="campo-texto" name="nome" placeholder="Digite seu Nome">
    <br>
    <b>Sobrenome: </b>
    <br>
    <input type="text" value="${campo.sobrenome}" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" id="sobrenome" class="campo-texto" name="sobrenome" placeholder="Digite seu Sobrenome">
    <br>
    <b>Apelido: </b>
    <br>
    <input type="text" value="${campo.apelido}" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" id="apelido" class="campo-texto" name="apelido" placeholder="Digite seu Apelido">
    <br>
    <b>Data de Nascimento: </b>
    <br>
    <input type="date" value="${campo.data_nascimento}"  id="data_nascimento" name="data_nascimento" class="campo-texto">
    <br>
    <b>Cidade: </b>
    <br>
    <input type="text" value="${campo.cidade}" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" id="cidade" placeholder="Digite sua Cidade" class="campo-texto" name="cidade">
    <br>
    <b>E-mail: </b>
    <br>
    <input type="email" value="${campo.email}" id="email" required placeholder="Digite seu E-mail" class="campo-texto" name="email">
    <br>
    <b>Senha: </b>
    <br>
    <input type="password" value="${campo.senha}" id="senha" required placeholder="Digite sua senha" class="campo-texto" name="senha"/>
    <br>
    <b>Profissão: </b>
    <br>
    <input type="text" value="${campo.profissao}" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" id="profissao" placeholder="Digite sua Profissão" class="campo-texto" name="profissao">
    <br>
    <b>Status: </b>
    <br>
    <input type="text" value="${campo.status}" pattern="[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*" id="status" placeholder="Digite seu Status" class="campo-texto" name="status">
    <div class="botoes">
        <input type="submit" class="btn btn-success" value="Salvar Informações">
    </div>
</form>