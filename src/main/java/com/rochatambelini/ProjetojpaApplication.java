package com.rochatambelini;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rochatambelini.domain.Categoria;
import com.rochatambelini.domain.Cidades;
import com.rochatambelini.domain.Estados;
import com.rochatambelini.domain.Produto;
import com.rochatambelini.repositores.CategoriaRepository;
import com.rochatambelini.repositores.CidadeRepository;
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
			
	
	}
	

}
