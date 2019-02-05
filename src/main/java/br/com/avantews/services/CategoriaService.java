package br.com.avantews.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avantews.domain.Categoria;
import br.com.avantews.repositories.CategoriaRepository;
import br.com.avantews.services.exception.ObjetoNaoEncontradoException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	//tratamento de objetos da base de dados
	public Categoria buscar(Integer id) {
				Optional<Categoria> objeto = categoriaRepository.findById(id);
				return objeto.orElseThrow(() -> new ObjetoNaoEncontradoException
						("Objeto não encontrado na base de dados. Tipo:  s" + Categoria.class.getName()));
	}

    public Categoria insert(Categoria objetoCategoria) {
		objetoCategoria.setId(null);
		return categoriaRepository.save(objetoCategoria);
	}
}
