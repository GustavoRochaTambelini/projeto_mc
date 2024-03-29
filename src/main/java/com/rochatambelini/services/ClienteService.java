package com.rochatambelini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.rochatambelini.domain.Cliente;
import com.rochatambelini.domain.Cliente;
import com.rochatambelini.domain.Cliente;
import com.rochatambelini.dto.ClienteDTO;
import com.rochatambelini.repositores.ClienteRepository;
import com.rochatambelini.services.exceptions.DateIntegrityExeption;
import com.rochatambelini.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	public ClienteRepository repo;
	
	public Cliente buscar(Integer id){		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj); 
	}

	public void delete(Integer id) {
		buscar(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DateIntegrityExeption("Não é possivel exclir por que há entidades relacionadas");
		}
	}

	public List <Cliente> findAll() {
		List <Cliente> list = repo.findAll();
		return list;
	}
	
	public Page<Cliente> findPage(Integer page,Integer linesPerPage,String orderBy,String direction ){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(@Valid ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(),objDto.getEmail(),null,null,null);
	}
	
	
	public void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
