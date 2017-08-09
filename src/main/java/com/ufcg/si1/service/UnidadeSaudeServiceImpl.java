package com.ufcg.si1.service;

import br.edu.ufcg.Hospital;

import com.ufcg.si1.model.PostoSaude;
import com.ufcg.si1.model.UnidadeSaude;
import com.ufcg.si1.repositories.UnidadeSaudeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("unidadeSaudeService")
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {
	
	private UnidadeSaudeRepository unidades;


	@Autowired
	public UnidadeSaudeServiceImpl(UnidadeSaudeRepository repositorioUnidades) {
		this.unidades = repositorioUnidades;

	}

	@Override
	public List<UnidadeSaude> getAll() {
		return unidades.findAll();
	}

	@Override
	public void insere(UnidadeSaude unidade) {
		if (!existe(unidade.getId())) {
			unidades.save(unidade);
		}
	}

	@Override
	public boolean existe(Long id) {
		return unidades.findById(id) != null;
	}

	public UnidadeSaude findById(long id) {
		return unidades.findById(id);
	}

	@Override
	public Object findByBairro(String bairro) {
		return unidades.findByBairro(bairro);
	}

	public Double mediaMedica(Object unidade) {
		double c = 0.0;
		if (unidade instanceof PostoSaude)
			c = ((PostoSaude) unidade).getAtendentes() / ((PostoSaude) unidade).taxaDiaria();
		else if (unidade instanceof Hospital) {
			c = ((Hospital) unidade).getNumeroMedicos() / ((Hospital) unidade).getNumeroPacientesDia();
		}
		return c;
	}
}
