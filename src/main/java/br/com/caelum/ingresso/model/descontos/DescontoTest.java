package br.com.caelum.ingresso.model.descontos;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.SemDesconto;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTest {

	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {

		Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());

		BigDecimal precoEsperado = new BigDecimal("32.5");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}

	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBancos() {

		Sala sala = new Sala ("Eldorado - IMAX", new BigDecimal("20.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
				"SCI-FI", new BigDecimal("12"));
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, new DescontoParaBancos());
		
		BigDecimal precoEsperado = new BigDecimal("22.75");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressosDeEstudante(){
		
		Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.05"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
				"SCI-FI", new BigDecimal("12"));
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, new DescontoParaEstudantes());
		
		BigDecimal precoEsperado = new BigDecimal("16.25");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
}
