/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.avantews.repositories;

import br.com.avantews.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * @author Name: Rafael Serdeiro Paes Leme E-mail: avante.webservices@gmail.com Corporate: AvanteWS
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    @Transactional(readOnly = true)
    Optional<Cliente> findByEmail(String email);

    @Transactional(readOnly = true)
    Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
