package br.com.avantews.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.avantews.domain.Categoria;
import br.com.avantews.domain.Produto;
import br.com.avantews.repositories.CategoriaRepository;
import br.com.avantews.repositories.ProdutoRepository;
import br.com.avantews.services.exception.ObjetoNaoEncontradoException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Produto> find(Integer id) {

        Optional<Produto> objetoProduto = produtoRepository.findById(id);

        if (objetoProduto == null) {
            throw new ObjetoNaoEncontradoException(
                    "Objeto não encontrado.\nId: " + id + "\nTipo: " + Produto.class.getName());

        }

        return objetoProduto;

    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer contPage, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(contPage, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.search(nome, categorias, pageRequest);

    }

}
