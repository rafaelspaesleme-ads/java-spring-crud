package br.com.avantews.services;

import java.util.List;
import java.util.Optional;

import br.com.avantews.services.exception.IntegridadeDeDadosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.avantews.domain.Categoria;
import br.com.avantews.repositories.CategoriaRepository;
import br.com.avantews.services.exception.ObjetoNaoEncontradoException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    //tratamento de objetos em forma de busca na base de dados
    public Categoria buscar(Integer id) {
        Optional<Categoria> objeto = categoriaRepository.findById(id);
        return objeto.orElseThrow(() -> new ObjetoNaoEncontradoException
                ("Objeto não encontrado na base de dados. Tipo:  s" + Categoria.class.getName()));
    }

    //tratamento de objetos em forma de insert na base de dados
    public Categoria insert(Categoria objetoCategoria) {
        //Objeto recebe Id nulo para que metodo save do JPA reconheça que é um metodo de inserção ao banco
        objetoCategoria.setId(null);
        return categoriaRepository.save(objetoCategoria);
    }

    //tratamento de objetos em forma de update na base de dados
    public Categoria update(Categoria objetoCategoria) {
        //Chamando metodo buscar para verificar qual id do objeto para que JPA reconheça que o metodo save será um metodo de atualização no banco
        buscar(objetoCategoria.getId());
        return categoriaRepository.save(objetoCategoria);
    }

    public void delete(Integer id) {
        buscar(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IntegridadeDeDadosException("Dado não pode ser excluido! Objeto " + Categoria.class + " esta relacionada com outra entidade.");
        }
    }

    public List<Categoria> lista() {
        return categoriaRepository.findAll();
    }

    public List<Categoria> listaCategoria() {
        return categoriaRepository.findAll();
    }

    //Criando controle de paginação em cima da lista de categorias.
    public Page<Categoria> listaPageCategoria(Integer contPage, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(contPage, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }
}
