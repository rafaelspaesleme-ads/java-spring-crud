/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.avantews.repositories;

import br.com.avantews.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author RPL Solutec
 */
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
    
}
