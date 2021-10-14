package carro.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import carro.dto.CarroDTO;
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
		//converterListaCarroParaCarroDTO();
		calcularPrecoTotalCarros();
		//agruparCarrosPeloAno();
		//agruparCarrosPeloAnoEConformePreco();
	}
	
	/* ------------------------------------------------------------------------------- */
	
	public void converterListaCarroParaCarroDTO() {
		System.out.println("\t Conversão de lista de Carro para CarroDTO");
		converterListaCarroParaCarroDTOForma1();
		converterListaCarroParaCarroDTOForma2();
		converterListaCarroParaCarroDTOForma3();
	}
	
	private void converterListaCarroParaCarroDTOForma1() {
		System.out.println("Forma 1 - Paradigma Imperativo");
		
		List<CarroDTO> carrosDTO = new ArrayList<>();
		
		for (Carro carro : carros) {
			carrosDTO.add(new CarroDTO(carro));
		}
		
		carrosDTO.forEach(System.out::println);
	}
	
	private void converterListaCarroParaCarroDTOForma2() {
		System.out.println("\nForma 2 - Paradigma Funcional");
		
		List<CarroDTO> carrosDTO = carros.stream().map(carro -> new CarroDTO(carro)).collect(Collectors.toList());
		carrosDTO.forEach(System.out::println);
	}
	
	private void converterListaCarroParaCarroDTOForma3() {
		System.out.println("\nForma 3 - Paradigma Funcional");
		
		List<CarroDTO> carrosDTO = carros.stream().map(CarroDTO::new).collect(Collectors.toList());
		carrosDTO.forEach(System.out::println);
	}
	
	/* ------------------------------------------------------------------------------- */
	
	public void calcularPrecoTotalCarros() {
		System.out.println("\t Cálculo total do preço dos Carros");
		calcularPrecoTotalCarrosForma1();
		calcularPrecoTotalCarrosForma2();
		calcularPrecoTotalCarrosForma3();
	}
	
	private void calcularPrecoTotalCarrosForma1() {
		System.out.println("Forma 1 - Paradigma Imperativo");
		
		int precoTotal = 0;
		
		for(int i = 0; i < carros.size(); i++) {
			precoTotal += carros.get(i).getPreco();
		}
		
		System.out.println("Preço total dos carros R$: " + precoTotal);
	}
	
	private void calcularPrecoTotalCarrosForma2() {
		System.out.println("\nForma 2 - Paradigma Funcional");
		
		int precoTotal = carros.stream().mapToInt(Carro::getPreco)
				.reduce(0, (int subtotal, int proximoElemento) -> subtotal + proximoElemento);
		
		System.out.println("Preço total dos carros R$: " + precoTotal);
	}
	
	private void calcularPrecoTotalCarrosForma3() {
		System.out.println("\nForma 3 - Paradigma Funcional");
		
		int precoTotal = carros.stream().mapToInt(Carro::getPreco).reduce(0, Integer::sum);
		System.out.println("Preço total dos carros R$: " + precoTotal);
	}
	
	/* ------------------------------------------------------------------------------- */
	
	public void agruparCarrosPeloAno() {
		System.out.println("\t Carros agrupados pelo ano");
		agruparCarrosPeloAnoForma1(); 
		agruparCarrosPeloAnoForma2();
	}
	
	private void agruparCarrosPeloAnoForma1() {
		System.out.println("Forma 1 - Paradigma Imperativo");
		
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
		System.out.println("\nForma 2 - Paradigma Funcional");
		
		Map<Integer, List<Carro>> carrosAgrupadosPeloAno = carros.stream()
				.collect(Collectors.groupingBy(Carro::getAno, Collectors.toList()));
		
		carrosAgrupadosPeloAno.forEach((ano, carros) -> System.out.println("Ano: " + ano + " - " + carros));
	}
	
	/* ------------------------------------------------------------------------------- */
	public void agruparCarrosPeloAnoEConformePreco() {
		final int PRECO = 70000;
		System.out.println("\t Carros agrupados pelo ano e pelo preço maior ou menor ou igual a R$ " + PRECO);
		agruparCarrosPeloAnoEConformePrecoForma1(PRECO);
		agruparCarrosPeloAnoEConformePrecoForma2(PRECO);
	}
	
	private void agruparCarrosPeloAnoEConformePrecoForma1(int preco) {
		System.out.println("Forma 1 - Paradigma Imperativo");
		
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
	
	private void agruparCarrosPeloAnoEConformePrecoForma2(int preco) {
		System.out.println("\nForma 2 - Paradigma Funcional");
		
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
