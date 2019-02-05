package br.com.avantews.services;

import br.com.avantews.domain.Cliente;
import br.com.avantews.repositories.ClienteRepository;
import br.com.avantews.services.exception.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //tratamento de objetos da base de dados
    public Cliente buscar(Integer id){
        Optional<Cliente> objeto = clienteRepository.findById(id);
        return objeto.orElseThrow(() -> new ObjetoNaoEncontradoException
                ("Objeto não encontrado na base de dados. Tipo: " + Cliente.class.getName()));
    }
}
