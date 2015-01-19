/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.valueObject;

/**
 *
 * @author Emanuel
 */
public class Relacao {
    private int id;
    private Usuario usuario_1;
    private String tipo;
    private boolean pendencia;
    private Usuario usuario_2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario_1() {
        return usuario_1;
    }

    public void setUsuario_1(Usuario usuario_1) {
        this.usuario_1 = usuario_1;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isPendencia() {
        return pendencia;
    }

    public void setPendencia(boolean pendencia) {
        this.pendencia = pendencia;
    }

    public Usuario getUsuario_2() {
        return usuario_2;
    }

    public void setUsuario_2(Usuario usuario_2) {
        this.usuario_2 = usuario_2;
    }
    
    
}
