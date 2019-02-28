package br.com.avantews.dto;

import br.com.avantews.domain.Cliente;
import br.com.avantews.services.validation.ClientUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientUpdate
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo obrigatório!")
    @Length(min = 5, max = 255, message = "Nome deve ter valor entre 5 a 255 caracteres.")
    private String nome;

    @NotEmpty(message = "Campo obrigatório!")
    @Email(message = "Email invalido!")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente objeto){
        this.setId(objeto.getId());
        this.setNome(objeto.getNome());
        this.setEmail(objeto.getEmail());
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
}
