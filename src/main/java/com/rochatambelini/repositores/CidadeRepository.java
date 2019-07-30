package com.rochatambelini.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rochatambelini.domain.Cidades;

@Repository
public interface CidadeRepository extends JpaRepository<Cidades, Integer>{

}
