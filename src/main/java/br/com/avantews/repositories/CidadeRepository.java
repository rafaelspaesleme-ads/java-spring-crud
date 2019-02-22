/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.avantews.repositories;

import br.com.avantews.domain.Cidade;
import br.com.avantews.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * @author Name: Rafael Serdeiro Paes Leme E-mail: avante.webservices@gmail.com Corporate: AvanteWS
 */
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
