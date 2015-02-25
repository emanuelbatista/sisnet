package com.br.ifpb.validacao;

import com.br.ifpb.execoes.FormatadoException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 25/02/2015 ás 07:17:18
 */
public class UsuarioInforValidacao {

    public List<String> validar(String nome, String sobrenome, String apelido,
            String data_nascimento, String cidade, String email, String profissao, String senha, String status) {
        List<String> mensagensErros = new LinkedList<>();
        try {
            validarTextoObrigatorio(nome, "Nome");
        } catch (FormatadoException ex) {
            mensagensErros.add(ex.getMessage());
        }
        try {
            validarTextoNaoObrigatorio(sobrenome, "Sobrenome");
        } catch (FormatadoException ex) {
            mensagensErros.add(ex.getMessage());
        }
        try {
            validarTextoNaoObrigatorio(apelido, "Apelido");
        } catch (FormatadoException ex) {
            mensagensErros.add(ex.getMessage());
        }
        try {
            validarTextoNaoObrigatorio(profissao, "Profissão");
        } catch (FormatadoException ex) {
            mensagensErros.add(ex.getMessage());
        }
        try {
            validarData(data_nascimento, "Data de Nascimento");
        } catch (FormatadoException ex) {
            mensagensErros.add(ex.getMessage());
        }
        try {
            validarTextoNaoObrigatorio(cidade, "Cidade");
        } catch (FormatadoException ex) {
            mensagensErros.add(ex.getMessage());
        }
        try {
            validarEmail(email, "Email");
        } catch (FormatadoException ex) {
            mensagensErros.add(ex.getMessage());
        }
        try {
            validarTextoSemCaracteresEspecial(senha, "Senha");
        } catch (FormatadoException ex) {
            mensagensErros.add(ex.getMessage());
        }
        try {
            validarTextoNaoObrigatorio(status, "Status");
        } catch (FormatadoException ex) {
            mensagensErros.add(ex.getMessage());
        }

        return mensagensErros.isEmpty() ? null : mensagensErros;

    }

    private void validarTextoObrigatorio(String texto, String campo) throws FormatadoException {
        if (texto == null || !texto.matches("[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*")) {
            throw new FormatadoException(campo + " formato errado ou vazio!");
        }
    }

    private void validarTextoNaoObrigatorio(String texto, String campo) throws FormatadoException {

        if (texto != null && texto.isEmpty()) {
            texto = null;
        } else if (!texto.matches("[A-ZÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ].*")) {
            throw new FormatadoException(campo + " formato errado!");
        }

    }

    private void validarTextoSemCaracteresEspecial(String texto, String campo) throws FormatadoException {
        if (texto == null || !texto.matches("\\w+")) {
            throw new FormatadoException("senha contém caracteres especiais ou esta vazia!");
        }
    }

    private void validarData(String data, String campo) throws FormatadoException {
        if (data != null) {
            if (data.equals("")) {
                data = null;
            } else {
                if (data.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    try {
                        LocalDate date = LocalDate.parse(data, dateTimeFormatter);
                    } catch (DateTimeParseException ex) {
                        throw new FormatadoException(campo + " formato errado!");
                    }

                } else {
                    try {
                        Date.valueOf(data);
                    } catch (IllegalArgumentException ex) {
                        throw new FormatadoException(campo + " formato errado!");
                    }
                }
            }
        }
    }

    private void validarEmail(String email, String campo) throws FormatadoException {
        if (email == null || !email.matches("\\w+@\\w+\\.\\w{2,3}")) {
            throw new FormatadoException("e-mail formato errado ou vazio!");
        }
    }

}
