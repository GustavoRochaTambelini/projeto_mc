package com.rochatambelini;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rochatambelini.domain.Categoria;
import com.rochatambelini.domain.Cidades;
import com.rochatambelini.domain.Cliente;
import com.rochatambelini.domain.Endereco;
import com.rochatambelini.domain.Estados;
import com.rochatambelini.domain.PagamentoComBoleto;
import com.rochatambelini.domain.PagamentoComCartao;
import com.rochatambelini.domain.Pedido;
import com.rochatambelini.domain.Produto;
import com.rochatambelini.domain.enums.EstadoPagamento;
import com.rochatambelini.domain.enums.TipoCliente;
import com.rochatambelini.repositores.CategoriaRepository;
import com.rochatambelini.repositores.CidadeRepository;
import com.rochatambelini.repositores.ClienteRepository;
import com.rochatambelini.repositores.EnderecoRepository;
import com.rochatambelini.repositores.EstadosRepository;
import com.rochatambelini.repositores.PagamentoRepository;
import com.rochatambelini.repositores.PedidoRepository;
import com.rochatambelini.repositores.ProdutoRepository;


@SpringBootApplication
public class ProjetojpaApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadosRepository estadoRepository;
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
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetojpaApplication.class, args);
	}
	
	@Override
	public void run(String... args)throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estados est1 = new Estados(null,"Minas Gerais");
		Estados est2 = new Estados(null,"São Paulo");
		
		Cidades cid1 = new Cidades(null, "Uberlandia",est1);
		Cidades cid2 = new Cidades(null, "São Paulo",est2);
		Cidades cid3 = new Cidades(null, "Campinas",est2);
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("23456271", "65432163"));
		
		Endereco e1 = new Endereco(null,"Rua Flores", "300", "Apto 303", "Jardin", "38220834", cli1, cid1);
		Endereco e2 = new Endereco(null,"Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1, e1, itens);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"),cli1, e2, itens);
		
		PagamentoComCartao pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,ped1,6);
	
		ped1.setPagamento(pag1);
		
		PagamentoComBoleto pag2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("21/10/2017"),null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
	}
	

}
