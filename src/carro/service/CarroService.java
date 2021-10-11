package carro.service;

import java.util.ArrayList;
import java.util.List;

import carro.model.Carro;
import carro.repository.CarroRepository;
import util.Execute;

public class CarroService implements Execute {
	
	private List<Carro> carros = new ArrayList<>();
	
	
	public CarroService() {
		carros = new CarroRepository().buscarCarros();
	}

	@Override
	public void execute() {
		//calcularPrecoTotalCarros();
		agruparCarrosPeloAno();
	}
	
	/* ------------------------------------------------------------------------------- */
	
	public void calcularPrecoTotalCarros() {
		calcularPrecoTotalCarrosForma1();
		calcularPrecoTotalCarrosForma2();
		calcularPrecoTotalCarrosForma3();
	}
	
	private void calcularPrecoTotalCarrosForma1() {
		int precoTotal = 0;
		
		for(int i = 0; i < carros.size(); i++) {
			precoTotal += carros.get(i).getPreco();
		}
		
		System.out.println("Preço total dos carros R$: " + precoTotal);
	}
	
	private void calcularPrecoTotalCarrosForma2() {
		int precoTotal = carros.stream().mapToInt(Carro::getPreco)
				.reduce(0, (int subtotal, int proximoElemento) -> subtotal + proximoElemento);
		
		System.out.println("Preço total dos carros R$: " + precoTotal);
	}
	
	private void calcularPrecoTotalCarrosForma3() {
		int precoTotal = carros.stream().mapToInt(Carro::getPreco).reduce(0, Integer::sum);
		System.out.println("Preço total dos carros R$: " + precoTotal);
	}
	
	/* ------------------------------------------------------------------------------- */
	
	public void agruparCarrosPeloAno() {
		agruparCarrosPeloAnoForma1(); System.out.println();
	}
	
	private void agruparCarrosPeloAnoForma1() {
		
	}
}
