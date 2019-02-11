package br.com.avantews.resource;

import br.com.avantews.dto.CategoriaDTO;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.avantews.domain.Categoria;
import br.com.avantews.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objetoCategoriaDTO) {
        Categoria objetoCategoria = categoriaService.fromDTO(objetoCategoriaDTO);
        objetoCategoria= categoriaService.insert(objetoCategoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objetoCategoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //Mapeando e realizando update de dados na BD e criação de metodo PUT para Json
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objetoCategoriaDTO, @PathVariable Integer id) {
        Categoria objetoCategoria = categoriaService.fromDTO(objetoCategoriaDTO);
        objetoCategoria.setId(id);
        objetoCategoria = categoriaService.update(objetoCategoria);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/detalhes", method = RequestMethod.GET)
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> list = categoriaService.lista();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAllCategoria(){
        List<Categoria> list = categoriaService.listaCategoria();
        //Strem map percorre o objeo CategoriaDTO e verifica quais atributos precisa parear com a lista do objeto Categoria
        List<CategoriaDTO> dtoList = list.stream().map(objeto -> new CategoriaDTO(objeto)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    //Inserindo configuração de paginação de lista em lista de categoria.
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer contPage,
            @RequestParam(value = "lines", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy){
        Page<Categoria> list = categoriaService.listaPageCategoria(contPage, linesPerPage, direction, orderBy);
        //Strem map percorre o objeo CategoriaDTO e verifica quais atributos precisa parear com a lista do objeto Categoria
        Page<CategoriaDTO> dtoList = list.map(objeto -> new CategoriaDTO(objeto));
        return ResponseEntity.ok().body(dtoList);
    }
}
