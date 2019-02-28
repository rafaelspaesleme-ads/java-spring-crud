package br.com.avantews.services;

import br.com.avantews.domain.Cidade;
import br.com.avantews.domain.Cliente;
import br.com.avantews.domain.Endereco;
import br.com.avantews.domain.enums.TipoCliente;
import br.com.avantews.dto.ClienteDTO;
import br.com.avantews.dto.ClienteNewDTO;
import br.com.avantews.repositories.ClienteRepository;
import br.com.avantews.repositories.EnderecoRepository;
import br.com.avantews.services.exception.IntegridadeDeDadosException;
import br.com.avantews.services.exception.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    //tratamento de objetos da base de dados
    public Cliente buscar(Integer id) {
        Optional<Cliente> objeto = clienteRepository.findById(id);
        return objeto.orElseThrow(() -> new ObjetoNaoEncontradoException
                ("Objeto não encontrado na base de dados. Tipo: " + Cliente.class.getName()));
    }

    //tratamento de objetos em forma de insert na base de dados
    public Cliente insert(Cliente objetoCliente) {
        //Objeto recebe Id nulo para que metodo save do JPA reconheça que é um metodo de inserção ao banco
        objetoCliente.setId(null);
        objetoCliente = clienteRepository.save(objetoCliente);
        enderecoRepository.saveAll(objetoCliente.getEnderecos());
        return objetoCliente;
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

    public Cliente fromDTO(ClienteNewDTO clienteNewDTO){
        Cliente cli = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipo()));
        Cidade cid = new Cidade(clienteNewDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().addAll(
                Arrays.asList(
                        clienteNewDTO.getTelefone1(),
                        (clienteNewDTO.getTelefone2() != null) ? clienteNewDTO.getTelefone2() : null,
                        (clienteNewDTO.getTelefone3() != null) ? clienteNewDTO.getTelefone3() : null));
        return cli;
    }

    private void updateData(Cliente novoObjeto, Cliente objeto) {
        novoObjeto.setNome(objeto.getNome());
        novoObjeto.setEmail(objeto.getEmail());
    }

}
