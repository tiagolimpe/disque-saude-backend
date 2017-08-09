package com.ufcg.si1.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.si1.model.UnidadeSaude;

public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude,  Long>{

	
	
	/**
	 * Serviço que retorna uma única unidade de saude a partir do id.
	 * 
	 * @param id
	 * @return unidade de saude com id especificado
	 */
	UnidadeSaude findById(Long id);
	
	/**
	 * Serviço que retorna uma única unidade de saude a partir do bairro.
	 * 
	 * @param bairro
	 * @return unidade de saude com bairro especificado
	 */
	UnidadeSaude findByBairro(String bairro);
	
	
	
}
