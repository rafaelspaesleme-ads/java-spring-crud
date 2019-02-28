package br.com.avantews.repositories;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.avantews.domain.Categoria;
import br.com.avantews.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT DISTINCT produto "+
           "FROM Produto produto "+
           "INNER_JOIN "+
           "produto.categorias categoria "+
           "WHERE "+
           "produto.nome LIKE %:nome% "+
           "AND categoria IN :categorias")
    Page<Produto> search(@Param("nome") String nome, 
                         @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
		
}
