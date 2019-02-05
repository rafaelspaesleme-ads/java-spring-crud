package br.com.avantews;

//My projects they are in https://bit.ly/2GglPYK

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import br.com.avantews.domain.*;
import br.com.avantews.domain.enums.EstadoPagamento;
import br.com.avantews.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.avantews.domain.enums.TipoCliente;

import static java.time.LocalDate.now;

@SpringBootApplication
public class JavaSpringApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired
    private CidadeRepository cidadeRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub

        //Instanciando objetos e gerando informações
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "Rio de Janeiro");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);
        Cidade cid1 = new Cidade(null, "Uberlandia", est1);
        Cidade cid2 = new Cidade(null, "Rio de Janeiro", est2);
        Cidade cid3 = new Cidade(null, "Paraiba do Sul", est2);
    
        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12387945677", TipoCliente.PESSSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("22555522", "22559988"));
        Endereco end1 = new Endereco(null, "Rua Flores da Junqueira", "300", "Apto 203", "Jardim Botanico", "25715363", cli1, cid1);
        Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);
        cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, data.parse("31/01/2019 12:00"), cli1, end1);
        Pedido ped2 = new Pedido(null, data.parse("01/02/2019 11:00"), cli1, end2);

        Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pgto1);
        Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, data.parse("28/01/2019 14:00"), null);
        ped2.setPagamento(pgto2);

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        //Associando itens de pedidos ao pedido correspondente
        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        //Associando itens de pedido ao produto correspondente
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        //Associando categorias ao tipo de produtos correspondente.
        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));
        est1.getCidades().addAll(Arrays.asList(cid1));
        est2.getCidades().addAll(Arrays.asList(cid2, cid3));

        //Associando produtos ao tipo de categorias correspondente.
        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        //Associando clientes a seus pedidos
        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        //Salvando categorias na base de dadoss
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        //Salvando produtos na base de dados
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
        //Salvando estados na base de dados
        estadoRepository.saveAll(Arrays.asList(est1, est2));
        //Salvando cidades na base de dados
        cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
        //Salvando cliente na base de dados
        clienteRepository.saveAll(Arrays.asList(cli1));
        //Salvando endereços na base de dados
        enderecoRepository.saveAll(Arrays.asList(end1, end2));
        //Salvando pedidos na base de dados
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        //Salvando pagamentos na base de dados
        pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
        //Salvando itens de pedidos na base de dados
        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
