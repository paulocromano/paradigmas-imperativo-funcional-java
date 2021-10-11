package carro.repository;

import java.util.Arrays;
import java.util.List;

import carro.model.Carro;

public class CarroRepository {

	public List<Carro> buscarCarros() {
		return Arrays.asList(
			new Carro("HB 20", 2018, 75000),
			new Carro("Corolla", 2020, 120000),
			new Carro("Civic", 2021, 100000),
			new Carro("S10", 2016, 80000),
			new Carro("Gol", 2015, 25000),
			new Carro("Santana", 2006, 20000),
			new Carro("Camaro", 2021, 240000));
	}
}
