package br.com.avantews.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avantews.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
		
}
