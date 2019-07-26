package com.rochatambelini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.rochatambelini.domain.Categoria;
import com.rochatambelini.repositores.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	public CategoriaRepository repo;
	
	public Categoria buscar(Integer id){		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
	

}