package com.rochatambelini;


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
import com.rochatambelini.domain.Produto;
import com.rochatambelini.domain.enums.TipoCliente;
import com.rochatambelini.repositores.CategoriaRepository;
import com.rochatambelini.repositores.CidadeRepository;
import com.rochatambelini.repositores.ClienteRepository;
import com.rochatambelini.repositores.EnderecoRepository;
import com.rochatambelini.repositores.EstadosRepository;
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
	
	}
	

}
