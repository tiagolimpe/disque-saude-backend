package com.ufcg.si1.model;

import com.ufcg.si1.model.PostoSaude;
import com.ufcg.si1.model.UnidadeSaude;
import com.ufcg.si1.service.UnidadeSaudeService;
import com.ufcg.si1.service.UnidadeSaudeServiceImpl;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;


import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestUnidadeSaude {

	private UnidadeSaude unidadeCatole;
	private UnidadeSaude unidadeTambor;
	private UnidadeSaudeService service;
	private Object [] listaUnidades;

	public final static String EnderecoCatole = "Rua Ednaldo Pereira, 666, Catolé";
	public final static String EnderecoTambor = "Rua Se Juntas ja causa imagina juntas, 598, Tambor";
	public final static String EnderecoInvalido = "Rua Sei Que Sei Que Lá, 000, Sem Bairro";

	@Before
	public void setUp() {
		unidadeCatole = new PostoSaude(EnderecoCatole, 4, 3);
		unidadeTambor = new PostoSaude(EnderecoTambor, 8, 3);
		service = new UnidadeSaudeServiceImpl();
		insereCatoleETambor();
	}

	private void insereCatoleETambor() {
		try {
			service.insere(unidadeTambor);
			service.insere(unidadeCatole);
		} catch (Rep | ObjetoJaExistenteException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void insereUnidade() {
		Assert.assertEquals(service.existe(2), true);
		Assert.assertEquals(service.existe(1), true);

		try {
			service.insere(unidadeTambor);
		} catch (Rep | ObjetoJaExistenteException e) {
			Assert.assertEquals(e.getMessage(), "ExcecaoDados: Objeto jah existe no array");
		}

	}

	@Test
	public void procuraUnidadeCodigo() {
		try {
			Assert.assertEquals(service.procura(1), unidadeTambor);
			Assert.assertEquals(service.procura(2), unidadeCatole);
		} catch (Rep | ObjetoInexistenteException e) {
			e.printStackTrace();
		}

		try {
			service.procura(3);
		} catch (Rep | ObjetoInexistenteException e) {
			Assert.assertEquals(e.getMessage(), "ExcecaoDados: Não achou unidade");

		}

	}

	@Test
	public void procuraPorBairro() {
		Assert.assertEquals(service.findByBairro(EnderecoCatole), unidadeCatole);
		Assert.assertEquals(service.findByBairro(EnderecoTambor), unidadeTambor);
		Assert.assertEquals(service.findByBairro(EnderecoInvalido), null);
	}
	
	
	@Test
	public void unidadesExistentes() {
		listaUnidades = new Object[100];
		listaUnidades[1] = unidadeCatole;
		listaUnidades[0] = unidadeTambor;
		Assert.assertEquals(Arrays.asList(listaUnidades), service.getAll());
	}

}