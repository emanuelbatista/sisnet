/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.tag.handlers;

import com.br.ifpb.businessObject.GerenciarRelacao;
import com.br.ifpb.execoes.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
public class TipoRelacionamento extends SimpleTagSupport {

    private int remetente;
    private int destinatario;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        try {

            GerenciarRelacao gerenciarRelacao = new GerenciarRelacao();
            String tipo = gerenciarRelacao.tipoRelacao(remetente, destinatario);
            if (tipo != null) {
                out.print(tipo);
            }
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in TipoRelacionamento tag", ex);
        } catch (PersistenciaException ex) {
            Logger.getLogger(TipoRelacionamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setRemetente(int remetente) {
        this.remetente = remetente;
    }

    public void setDestinatario(int destinatario) {
        this.destinatario = destinatario;
    }

}
