package br.com.avantews.services;

import br.com.avantews.domain.Pedido;
import br.com.avantews.repositories.PedidoRepository;
import br.com.avantews.services.exception.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscar(Integer id){
        Optional<Pedido> objeto = pedidoRepository.findById(id);
        return objeto.orElseThrow(() -> new ObjetoNaoEncontradoException
                ("Objeto não encontrado na base de dados. Tipo: " + Pedido.class.getName()));
    }
}
