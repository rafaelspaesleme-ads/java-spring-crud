/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.avantews.domain.enums;

/**
 *
 * @author Name: Rafael Serdeiro Paes Leme E-mail: avante.webservices@gmail.com Corporate: AvanteWS
 */
public enum TipoCliente {
    
    PESSSOAFISICA(1, "Pessoa Fisica"),
    PESSOAJURIDICA(2, "Pessoa Juridica");
    
    private Integer codigo;
    private String descricao;
    
    private TipoCliente(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static TipoCliente toEnum(Integer codigo){
        if(codigo == null)
            return null;
        for (TipoCliente tipoCliente : TipoCliente.values())
            if(codigo.equals(tipoCliente.getCodigo()))
                return tipoCliente;
        throw new IllegalArgumentException("Id invalido: " + codigo);
    }
    
}
