package br.com.avantews.services;

import br.com.avantews.domain.Cliente;
import br.com.avantews.dto.ClienteDTO;
import br.com.avantews.repositories.ClienteRepository;
import br.com.avantews.services.exception.IntegridadeDeDadosException;
import br.com.avantews.services.exception.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //tratamento de objetos da base de dados
    public Cliente buscar(Integer id) {
        Optional<Cliente> objeto = clienteRepository.findById(id);
        return objeto.orElseThrow(() -> new ObjetoNaoEncontradoException
                ("Objeto não encontrado na base de dados. Tipo: " + Cliente.class.getName()));
    }

    //tratamento de objetos em forma de update na base de dados
    public Cliente update(Cliente objetoCliente) {
        //Chamando metodo buscar para verificar qual id do objeto para que JPA reconheça que o metodo save será um metodo de atualização no banco
        Cliente novoObjeto = buscar(objetoCliente.getId());
        updateData(novoObjeto, objetoCliente);
        return clienteRepository.save(novoObjeto);
    }

    public void delete(Integer id) {
        buscar(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IntegridadeDeDadosException("Dado não pode ser excluido! Objeto " + Cliente.class + " esta relacionada com outra entidade.");
        }
    }

    public List<Cliente> lista() {
        return clienteRepository.findAll();
    }

    public List<Cliente> listaCliente() {
        return clienteRepository.findAll();
    }

    //Criando controle de paginação em cima da lista de clientes.
    public Page<Cliente> listaPageCliente(Integer contPage, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(contPage, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    private void updateData(Cliente novoObjeto, Cliente objeto) {
        novoObjeto.setNome(objeto.getNome());
        novoObjeto.setEmail(objeto.getEmail());
    }

}
