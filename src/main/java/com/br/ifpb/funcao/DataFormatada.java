/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.funcao;

/**
 *
 * @author Emanuel
 */
public class DataFormatada {
    public static String formatarData(int dia,int mes,int ano){
        String date="";
        if(dia<10){
            date+="0";
        }
        date+=dia+"/";
        if(mes<10){
            date+="0";
        }
        date+=mes+"/";
        date+=ano;
        return date;
    }
}
