/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.execoes;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
public class FormatadoException extends Exception {

    /**
     * Creates a new instance of <code>FormatadoException</code> without detail
     * message.
     */
    public FormatadoException() {
    }

    /**
     * Constructs an instance of <code>FormatadoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FormatadoException(String msg) {
        super(msg);
    }
}
