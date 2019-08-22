package com.rochatambelini.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rochatambelini.domain.Estado;


@Repository
public interface EstadosRepository extends JpaRepository<Estado, Integer>{

}
