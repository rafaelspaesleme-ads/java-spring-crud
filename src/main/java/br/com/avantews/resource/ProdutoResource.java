package br.com.avantews.resource;

import br.com.avantews.domain.Categoria;
import br.com.avantews.domain.Produto;
import br.com.avantews.domain.Produto;
import br.com.avantews.dto.ProdutoDTO;
import br.com.avantews.resource.util.URL;
import br.com.avantews.services.ProdutoService;
import br.com.avantews.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Optional<Produto> objeto = produtoService.find(id);
        return ResponseEntity.ok().body(objeto);
    }

    //Inserindo configuração de paginação de lista em lista de categoria.
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "")String categoria,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String direction,
            @RequestParam(value = "direction", defaultValue = "ASC") String orderBy) {

        String nomeDecodificado = URL.decodeParam(nome);
        List<Integer> listIds = URL.decodeIntList(categoria);
        Page<Produto> list = produtoService.search(nomeDecodificado, listIds, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> dtoList = list.map(ProdutoDTO::new);
        return ResponseEntity.ok().body(dtoList);
    }
}
