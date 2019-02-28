/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.avantews.domain;

import br.com.avantews.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Name: Rafael Serdeiro Paes Leme E-mail: avante.webservices@gmail.com Corporate: AvanteWS
 */
@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpfOuCnpj;
    private Integer tipoCliente;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();
    
    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>();

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
        this.setId(id);
        this.setNome(nome);
        this.setEmail(email);
        this.setcpfOuCnpj(cpfOuCnpj);
        this.tipoCliente = (tipoCliente == null) ? null : tipoCliente.getCodigo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getcpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setcpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCodigo();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

     
}
