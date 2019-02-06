package br.com.avantews.resource;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.avantews.domain.Categoria;
import br.com.avantews.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    //Apresenta dados para o final user!
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria objeto = categoriaService.buscar(id);
        return ResponseEntity.ok().body(objeto);
    }

    //Mapeamento e inserção de dados na DB e criação de metodo post para Json
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria objetoCategoria) {
        objetoCategoria = categoriaService.insert(objetoCategoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objetoCategoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //Mapeando e realizando update de dados na BD e criação de metodo PUT para Json
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Categoria objetoCategoria, @PathVariable Integer id) {
        objetoCategoria.setId(id);
        objetoCategoria = categoriaService.update(objetoCategoria);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
