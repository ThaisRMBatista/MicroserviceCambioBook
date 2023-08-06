package br.com.thais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thais.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long>{

	Cambio findByFromAndTo(String from, String to);
}
