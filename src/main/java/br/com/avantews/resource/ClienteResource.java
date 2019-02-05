package br.com.avantews.resource;

import br.com.avantews.domain.Cliente;
import br.com.avantews.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
}
