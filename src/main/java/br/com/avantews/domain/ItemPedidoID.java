package br.com.avantews.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemPedidoID implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "fk_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedidoID)) return false;
        ItemPedidoID that = (ItemPedidoID) o;
        return Objects.equals(getPedido(), that.getPedido()) &&
                Objects.equals(getProduto(), that.getProduto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPedido(), getProduto());
    }
}
