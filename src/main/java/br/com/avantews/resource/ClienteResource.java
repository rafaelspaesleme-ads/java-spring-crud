package br.com.avantews.resource;

import br.com.avantews.domain.Cliente;
import br.com.avantews.dto.ClienteDTO;
import br.com.avantews.dto.ClienteNewDTO;
import br.com.avantews.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService clienteService;

    //Apresenta dados em forma de Json
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Cliente objeto = clienteService.buscar(id);
        return ResponseEntity.ok().body(objeto);
    }

    //Mapeamento e inserção de dados na DB e criação de metodo post para Json
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objetoClienteDTO) {
        Cliente objetoCliente = clienteService.fromDTO(objetoClienteDTO);
        objetoCliente= clienteService.insert(objetoCliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objetoCliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //Mapeando e realizando update de dados na BD e criação de metodo PUT para Json
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objetoClienteDTO, @PathVariable Integer id) {
        Cliente objetoCliente = clienteService.fromDTO(objetoClienteDTO);
        objetoCliente.setId(id);
        objetoCliente = clienteService.update(objetoCliente);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/detalhes", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> list = clienteService.lista();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAllCliente(){
        List<Cliente> list = clienteService.listaCliente();
        //Strem map percorre o objeo ClienteDTO e verifica quais atributos precisa parear com a lista do objeto Cliente
        List<ClienteDTO> dtoList = list.stream().map(objeto -> new ClienteDTO(objeto)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    //Inserindo configuração de paginação de lista em lista de cliente.
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer contPage,
            @RequestParam(value = "lines", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy){
        Page<Cliente> list = clienteService.listaPageCliente(contPage, linesPerPage, direction, orderBy);
        //Strem map percorre o objeo ClienteDTO e verifica quais atributos precisa parear com a lista do objeto Cliente
        Page<ClienteDTO> dtoList = list.map(objeto -> new ClienteDTO(objeto));
        return ResponseEntity.ok().body(dtoList);
    }
}
