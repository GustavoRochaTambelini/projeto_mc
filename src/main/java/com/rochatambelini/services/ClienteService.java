package com.rochatambelini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.rochatambelini.domain.Cliente;
import com.rochatambelini.repositores.ClienteRepository;
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
	

}
