package cliente.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import cliente.model.Cliente;
import cliente.repository.ClienteRepository;
import util.Execute;

public class ClienteService implements Execute {
	
	private List<Cliente> clientes = new ArrayList<>();
	
	
	public ClienteService() {
		clientes = new ClienteRepository().buscarClientes();
	}

	@Override
	public void execute() {
		//ordenarClientesPeloNome();
		//gerarListaComNomeDosClientes();
		filtrarClientesNascidosNoAno();
		
	}

	/* ------------------------------------------------------------------------------- */
	
	public void ordenarClientesPeloNome() {
		System.out.println("\t Ordenação de Clientes pelo nome");
		ordenarClientesPeloNomeForma1();
		ordenarClientesPeloNomeForma2();
		ordenarClientesPeloNomeForma3();
	}
	
	
	private void ordenarClientesPeloNomeForma1() {
		System.out.println("Forma 1 - Paradigma Imperativo");
		
		Comparator<Cliente> comparator = new Comparator<>() {

			@Override
			public int compare(Cliente cliente, Cliente cliente2) {
				return cliente.getNome().compareTo(cliente2.getNome());
			}
		};
		
		Collections.sort(clientes, comparator);
		
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i));
		}
	}
	
	private void ordenarClientesPeloNomeForma2() {
		System.out.println("\nForma 2 - Paradigma Funcional");
		
		Comparator<Cliente> comparator = (Cliente cliente, Cliente cliente2) -> cliente.getNome().compareTo(cliente2.getNome());
		clientes.stream().sorted(comparator).forEach((Cliente cliente) -> System.out.println(cliente));
	}
	
	private void ordenarClientesPeloNomeForma3() {
		System.out.println("\nForma 3 - Paradigma Funcional");
		
		clientes.stream().sorted(Comparator.comparing(Cliente::getNome)).forEach(System.out::println);
	}
	
	/* ------------------------------------------------------------------------------- */
	
	public void gerarListaComNomeDosClientes() {
		System.out.println("\t Gerar lista com o nome dos Clientes");
		gerarListaComNomeDosClientesForma1();
		gerarListaComNomeDosClientesForma2();
		gerarListaComNomeDosClientesForma3();
		gerarListaComNomeDosClientesForma4();
		gerarListaComNomeDosClientesForma5();
		gerarListaComNomeDosClientesForma6();
		
	}
	
	private void gerarListaComNomeDosClientesForma1() {
		System.out.println("Forma 1 - Paradigma Imperativo");
		
		List<String> nomeClientes = new ArrayList<>();
		
		for(Cliente cliente : clientes) {
			nomeClientes.add(cliente.getNome());
		}
		
		nomeClientes.forEach(System.out::println);
	}
	
	private void gerarListaComNomeDosClientesForma2() {
		System.out.println("\nForma 2 - Paradigma Funcional");
		
		Function<Cliente, String> function = cliente -> cliente.getNome();
		List<String> nomeClientes = clientes.stream().map(function).collect(Collectors.toList());
		nomeClientes.forEach(System.out::println);
	}
	
	private void gerarListaComNomeDosClientesForma3() {
		System.out.println("\nForma 3 - Paradigma Funcional");
		
		String nomeClientes = clientes.stream().map((Cliente cliente) -> {
			return cliente.getNome();
		}).collect(Collectors.joining(", "));
		
		System.out.println(nomeClientes);
	}
	
	private void gerarListaComNomeDosClientesForma4() {
		System.out.println("\nForma 4 - Paradigma Funcional");
		
		String nomeClientes = clientes.stream().map((Cliente cliente) -> cliente.getNome()).collect(Collectors.joining(", "));
		System.out.println(nomeClientes);
	}
	
	private void gerarListaComNomeDosClientesForma5() {
		System.out.println("\nForma 5 - Paradigma Funcional");
		
		String nomeClientes = clientes.stream().map(cliente -> cliente.getNome()).collect(Collectors.joining(", "));
		System.out.println(nomeClientes);
	}
	
	private void gerarListaComNomeDosClientesForma6() {
		System.out.println("\nForma 6 - Paradigma Funcional");
		
		String nomeClientes = clientes.stream().map(Cliente::getNome).collect(Collectors.joining(", "));
		System.out.println(nomeClientes);
	}
	
	/* ------------------------------------------------------------------------------- */
	
	public void filtrarClientesNascidosNoAno() {
		final int ANO = 1996;
		System.out.println("\t Filtrar Clientes nascidos no ano de " + ANO);
		filtrarClientesNascidosNoAnoForma1(ANO);
		filtrarClientesNascidosNoAnoForma2(ANO);
		filtrarClientesNascidosNoAnoForma3(ANO);
	}
	
	private void filtrarClientesNascidosNoAnoForma1(int ano) {
		System.out.println("Forma 1 - Paradigma Imperativo");
		
		List<Cliente> clientesFiltrados = new ArrayList<>();
		
		for(int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDataNascimento().getYear() == ano) {
				clientesFiltrados.add(clientes.get(i));
			}
		}
		
		clientesFiltrados.forEach(System.out::println);
	}
	
	private void filtrarClientesNascidosNoAnoForma2(int ano) {
		System.out.println("\nForma 2 - Paradigma Funcional");
		
		Predicate<Cliente> predicate = cliente -> cliente.getDataNascimento().getYear() == ano;
		List<Cliente> clientesFiltrados = clientes.stream().filter(predicate).collect(Collectors.toList());
		
		clientesFiltrados.forEach(System.out::println);
	}
	
	private void filtrarClientesNascidosNoAnoForma3(int ano) {
		System.out.println("\nForma 3 - Paradigma Funcional");
		
		clientes.stream().filter(cliente -> cliente.getDataNascimento().getYear() == ano).forEach(System.out::println);
	}
	
	/* ------------------------------------------------------------------------------- */
}
