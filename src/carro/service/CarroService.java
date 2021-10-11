package carro.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		//agruparCarrosPeloAno();
		agruparCarrosPeloAnoEPrecoMaior();
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
		agruparCarrosPeloAnoForma2();
	}
	
	private void agruparCarrosPeloAnoForma1() {
		Map<Integer, List<Carro>> carrosAgrupadosPeloAno = new HashMap<>();
		
		for(int i = 0; i < carros.size(); i++) {
			Carro carro = carros.get(i);
			List<Carro> carrosPeloAno = carrosAgrupadosPeloAno.containsKey(carro.getAno()) 
					? carrosAgrupadosPeloAno.get(carro.getAno()) : new ArrayList<>();
			
			carrosPeloAno.add(carro);
			carrosAgrupadosPeloAno.put(carro.getAno(), carrosPeloAno);
		}
		
		carrosAgrupadosPeloAno.forEach((ano, carros) -> System.out.println("Ano: " + ano + " - " + carros));
	}
	
	private void agruparCarrosPeloAnoForma2() {
		Map<Integer, List<Carro>> carrosAgrupadosPeloAno = carros.stream()
				.collect(Collectors.groupingBy(Carro::getAno, Collectors.toList()));
		
		carrosAgrupadosPeloAno.forEach((ano, carros) -> System.out.println("Ano: " + ano + " - " + carros));
	}
	
	/* ------------------------------------------------------------------------------- */
	public void agruparCarrosPeloAnoEPrecoMaior() {
		final int PRECO = 70000;
		agruparCarrosPeloAnoEPrecoMaiorForma1(PRECO); System.out.println();
		agruparCarrosPeloAnoEPrecoMaiorForma2(PRECO); System.out.println();
	}
	
	private void agruparCarrosPeloAnoEPrecoMaiorForma1(int preco) {
		Map<Integer, Map<Boolean, List<Carro>>> carrosAgrupadosPeloAnoEPrecoMaior = new HashMap<>();
		
		for(int i = 0; i < carros.size(); i++) {
			Carro carro = carros.get(i);
			Map<Boolean, List<Carro>> carrosAgrupadosPeloPreco;
			
			carrosAgrupadosPeloPreco = carrosAgrupadosPeloAnoEPrecoMaior.containsKey(carro.getAno()) 
					? carrosAgrupadosPeloAnoEPrecoMaior.get(carro.getAno()) : new HashMap<>();
			
			boolean precoCarroMaior = carro.getPreco() > preco;
			List<Carro> carrosConformePreco = carrosAgrupadosPeloPreco.containsKey(precoCarroMaior)
					? carrosAgrupadosPeloPreco.get(precoCarroMaior) : new ArrayList<>();
			
			carrosConformePreco.add(carro);
			carrosAgrupadosPeloPreco.put(precoCarroMaior, carrosConformePreco);
			carrosAgrupadosPeloAnoEPrecoMaior.put(carro.getAno(), carrosAgrupadosPeloPreco);
		}
		
		carrosAgrupadosPeloAnoEPrecoMaior.forEach((ano, carrosAgrupadosPeloPreco) ->
			carrosAgrupadosPeloPreco.forEach((precoMaior, carros) -> 
				System.out.println("Ano: " + ano + " - Preço maior que R$ " + preco + ": " 
						+ precoMaior + " - " + carros)));
	}
	
	private void agruparCarrosPeloAnoEPrecoMaiorForma2(int preco) {
		Map<Integer, Map<Boolean, List<Carro>>> carrosAgrupadosPeloAnoEPrecoMaior = carros.stream()
				.collect(Collectors.groupingBy(Carro::getAno, 
						Collectors.partitioningBy(carro -> carro.getPreco() > preco, Collectors.toList())));
		
		carrosAgrupadosPeloAnoEPrecoMaior.forEach((ano, carrosAgrupadosPeloPreco) ->
			carrosAgrupadosPeloPreco.forEach((precoMaior, carros) -> 
				System.out.println("Ano: " + ano + " - Preço maior que R$ " + preco + ": " 
						+ precoMaior + " - " + carros)));
	}
	
	/* ------------------------------------------------------------------------------- */
}
