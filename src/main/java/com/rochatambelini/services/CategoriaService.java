package com.rochatambelini.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rochatambelini.domain.Categoria;
import com.rochatambelini.dto.CategoriaDTO;
import com.rochatambelini.repositores.CategoriaRepository;
import com.rochatambelini.services.exceptions.DateIntegrityExeption;
import com.rochatambelini.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	public CategoriaRepository repo;
	
	public Categoria buscar(Integer id){		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj); 
	}

	public void delete(Integer id) {
		buscar(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DateIntegrityExeption("Não é possivel exclir uma categoria que possui produtos");
		}
	}

	public List <Categoria> findAll() {
		List <Categoria> list = repo.findAll();
		return list;
	}
	
	public Page<Categoria> findPage(Integer page,Integer linesPerPage,String orderBy,String direction ){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}
