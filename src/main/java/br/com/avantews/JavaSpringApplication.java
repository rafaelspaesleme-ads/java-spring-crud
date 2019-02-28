package br.com.avantews;

//My projects they are in https://bit.ly/2GglPYK

import java.text.SimpleDateFormat;
import java.util.Arrays;
import br.com.avantews.domain.*;
import br.com.avantews.domain.enums.EstadoPagamento;
import br.com.avantews.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.avantews.domain.enums.TipoCliente;


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
        Categoria catInfo = new Categoria(null, "Informática");
        Categoria catEscr = new Categoria(null, "Escritório");
        Categoria catCMeB = new Categoria(null, "Cama, mesa e banho");
        Categoria catElet = new Categoria(null, "Eletrodomestico");
        Categoria catEduc = new Categoria(null, "Educação");
        Categoria catGame = new Categoria(null, "Games");
        Categoria catAlim = new Categoria(null, "Alimentos");
        Categoria catCelu = new Categoria(null, "Celulares");

        Produto pInfo1  = new Produto(null, "Monitor", 350.00);
        Produto pInfo2  = new Produto(null, "Impressora", 200.00);
        Produto pInfo3  = new Produto(null, "Mouse Gamer", 40.00);
        Produto pInfo4  = new Produto(null, "Teclado", 70.00);

        Produto pEscr1  = new Produto(null, "Mesanino", 180.00);
        Produto pEscr2  = new Produto(null, "Gaveteiro", 90.00);
        Produto pEscr3  = new Produto(null, "Prateleira", 210.00);
        Produto pEscr4  = new Produto(null, "Cadeira Gamer", 180.00);

        Produto pCMeB1  = new Produto(null, "Couchão", 300.00);
        Produto pCMeB2 = new Produto(null, "Travesseiro", 30.00);
        Produto pCMeB3 = new Produto(null, "Toalha", 5.90);
        Produto pCMeB4 = new Produto(null, "Saboneteira", 12.99);

        Produto pElet1 = new Produto(null, "Geladeira", 480.00);
        Produto pElet2 = new Produto(null, "Smart TV", 230.00);
        Produto pElet3 = new Produto(null, "Fogão Eletrico", 100.00);
        Produto pElet4 = new Produto(null, "Ar Condicionado", 1300.00);

        Produto pEduc1 = new Produto(null, "Caderno", 20.00);
        Produto pEduc2 = new Produto(null, "Lapis", 3.00);
        Produto pEduc3 = new Produto(null, "Borracha", 0.90);
        Produto pEduc4 = new Produto(null, "Caneta", 2.99);

        Produto pGame1 = new Produto(null, "Fifa 2019", 80.00);
        Produto pGame2 = new Produto(null, "PES 2019", 80.00);
        Produto pGame3 = new Produto(null, "GTA 5", 80.00);
        Produto pGame4 = new Produto(null, "PC Gamer completo", 80.00);

        Produto pAlim1 = new Produto(null, "Maça KG", 5.00);
        Produto pAlim2 = new Produto(null, "Pera KG", 8.00);
        Produto pAlim3 = new Produto(null, "Laranja KG", 0.90);
        Produto pAlim4 = new Produto(null, "Batata doce KG", 0.90);

        Produto pCelu1 = new Produto(null, "Moto X", 900.00);
        Produto pCelu2 = new Produto(null, "Samsung S3", 1200.00);
        Produto pCelu3 = new Produto(null, "Xiaomi 2", 1800.00);
        Produto pCelu4 = new Produto(null, "iPhone X", 4000.00);

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "Rio de Janeiro");

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

        ItemPedido ipInfo = new ItemPedido(ped1, pInfo1, 0.00, 1, 350.00);
        ItemPedido ipEscr = new ItemPedido(ped1, pEscr4, 0.00, 2, 180.00);
        ItemPedido ipCelu = new ItemPedido(ped2, pCelu4, 100.00, 1, 4000.00);

        //Associando itens de pedidos ao pedido correspondente
        ped1.getItens().addAll(Arrays.asList(ipInfo,ipEscr));
        ped2.getItens().addAll(Arrays.asList(ipCelu));

        //Associando itens de pedido ao produto correspondente
        pInfo1.getItens().addAll(Arrays.asList(ipInfo));
        pCelu4.getItens().addAll(Arrays.asList(ipCelu));
        pEscr4.getItens().addAll(Arrays.asList(ipEscr));

        //Associando categorias ao tipo de produtos correspondente.
        catInfo.getProdutos().addAll(Arrays.asList(pInfo1, pInfo2, pInfo3, pInfo4));
        catEscr.getProdutos().addAll(Arrays.asList(pEscr1, pEscr2, pEscr3, pEscr4));
        catCMeB.getProdutos().addAll(Arrays.asList(pCMeB1, pCMeB2, pCMeB3, pCMeB4));
        catElet.getProdutos().addAll(Arrays.asList(pElet1, pElet2, pElet3, pElet4));
        catEduc.getProdutos().addAll(Arrays.asList(pEduc1, pEduc2, pEduc3, pEduc4));
        catGame.getProdutos().addAll(Arrays.asList(pGame1, pGame2, pGame3, pGame4));
        catAlim.getProdutos().addAll(Arrays.asList(pAlim1, pAlim2, pAlim3, pAlim4));
        catCelu.getProdutos().addAll(Arrays.asList(pCelu1, pCelu2, pCelu3, pCelu4));

        est1.getCidades().addAll(Arrays.asList(cid1));
        est2.getCidades().addAll(Arrays.asList(cid2, cid3));

        //Associando produtos ao tipo de categorias correspondente.
        pInfo1.getCategorias().addAll(Arrays.asList(catInfo));
        pInfo2.getCategorias().addAll(Arrays.asList(catInfo, catEscr));
        pInfo3.getCategorias().addAll(Arrays.asList(catInfo, catGame));
        pInfo4.getCategorias().addAll(Arrays.asList(catInfo));

        pEscr1.getCategorias().addAll(Arrays.asList(catEscr));
        pEscr2.getCategorias().addAll(Arrays.asList(catEscr));
        pEscr3.getCategorias().addAll(Arrays.asList(catEscr));
        pEscr4.getCategorias().addAll(Arrays.asList(catEscr, catGame));

        pCMeB1.getCategorias().addAll(Arrays.asList(catCMeB));
        pCMeB2.getCategorias().addAll(Arrays.asList(catCMeB));
        pCMeB3.getCategorias().addAll(Arrays.asList(catCMeB));
        pCMeB4.getCategorias().addAll(Arrays.asList(catCMeB));

        pElet1.getCategorias().addAll(Arrays.asList(catElet));
        pElet2.getCategorias().addAll(Arrays.asList(catElet, catInfo));
        pElet3.getCategorias().addAll(Arrays.asList(catElet));
        pElet4.getCategorias().addAll(Arrays.asList(catElet));

        pEduc1.getCategorias().addAll(Arrays.asList(catEduc));
        pEduc2.getCategorias().addAll(Arrays.asList(catEduc));
        pEduc3.getCategorias().addAll(Arrays.asList(catEduc));
        pEduc4.getCategorias().addAll(Arrays.asList(catEduc));

        pGame1.getCategorias().addAll(Arrays.asList(catGame));
        pGame2.getCategorias().addAll(Arrays.asList(catGame));
        pGame3.getCategorias().addAll(Arrays.asList(catGame));
        pGame4.getCategorias().addAll(Arrays.asList(catGame, catInfo));

        pAlim1.getCategorias().addAll(Arrays.asList(catAlim));
        pAlim2.getCategorias().addAll(Arrays.asList(catAlim));
        pAlim3.getCategorias().addAll(Arrays.asList(catAlim));
        pAlim4.getCategorias().addAll(Arrays.asList(catAlim));

        pCelu1.getCategorias().addAll(Arrays.asList(catCelu));
        pCelu2.getCategorias().addAll(Arrays.asList(catCelu));
        pCelu3.getCategorias().addAll(Arrays.asList(catCelu));
        pCelu4.getCategorias().addAll(Arrays.asList(catCelu));

        //Associando clientes a seus pedidos
        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        //Salvando categorias na base de dadoss
        categoriaRepository.saveAll(Arrays.asList(
                catInfo, catEscr, catCMeB, catElet, catEduc, catGame, catAlim, catCelu));
        //Salvando produtos na base de dados

        produtoRepository.saveAll(Arrays.asList(
                pInfo1, pInfo2, pInfo3, pInfo4,
                pEscr1, pEscr2, pEscr3, pEscr4,
                pCMeB1, pCMeB2, pCMeB3, pCMeB4,
                pElet1, pElet2, pElet3, pElet4,
                pEduc1, pEduc2, pEduc3, pEduc4,
                pGame1, pGame2, pGame3, pGame4,
                pAlim1, pAlim2, pAlim3, pAlim4,
                pCelu1, pCelu2, pCelu3, pCelu4));

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
        itemPedidoRepository.saveAll(Arrays.asList(ipInfo, ipEscr, ipCelu));
    }
}
