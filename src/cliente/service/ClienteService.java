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
		//visualizarClientes();
		//ordenarClientesPeloNome();
		//buscarNomeClientes();
		filtrarClientesNascidosNoAno();
		
	}
	
	/* ------------------------------------------------------------------------------- */
	public void visualizarClientes() {
		visualizarClientesForma1(); System.out.println();
		visualizarClientesForma2(); System.out.println();
		visualizarClientesForma3(); System.out.println();
	}
	
	
	private void visualizarClientesForma1() {
		for(int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i));
		}
	}
	
	private void visualizarClientesForma2() {
		clientes.forEach(cliente -> System.out.println(cliente));
	}
	
	private void visualizarClientesForma3() {
		clientes.forEach(System.out::println);
	}
	/* ------------------------------------------------------------------------------- */
	
	public void ordenarClientesPeloNome() {
		ordenarClientesPeloNomeForma1(); System.out.println();
		ordenarClientesPeloNomeForma2(); System.out.println();
		ordenarClientesPeloNomeForma3(); System.out.println();
	}
	
	
	private void ordenarClientesPeloNomeForma1() {
		Comparator<Cliente> comparator = new Comparator<>() {

			@Override
			public int compare(Cliente cliente, Cliente cliente2) {
				return cliente.getNome().compareTo(cliente2.getNome());
			}
		};
		
		Collections.sort(clientes, comparator);
		clientes.forEach(System.out::println);
	}
	
	private void ordenarClientesPeloNomeForma2() {
		Comparator<Cliente> comparator = (Cliente cliente, Cliente cliente2) -> cliente.getNome().compareTo(cliente2.getNome());
		clientes.stream().sorted(comparator).forEach(System.out::println);
	}
	
	private void ordenarClientesPeloNomeForma3() {
		clientes.stream().sorted(Comparator.comparing(Cliente::getNome)).forEach(System.out::println);
	}
	
	/* ------------------------------------------------------------------------------- */
	
	public void buscarNomeClientes() {
		buscarNomesClientesForma1(); System.out.println();
		buscarNomesClientesForma2(); System.out.println();
		buscarNomesClientesForma3(); System.out.println();
		buscarNomesClientesForma4(); System.out.println();
		buscarNomesClientesForma5(); System.out.println();
		buscarNomesClientesForma6(); System.out.println();
		
	}
	
	private void buscarNomesClientesForma1() {
		List<String> nomeClientes = new ArrayList<>();
		
		for(Cliente cliente : clientes) {
			nomeClientes.add(cliente.getNome());
		}
		
		nomeClientes.forEach(System.out::println);
	}
	
	private void buscarNomesClientesForma2() {
		Function<Cliente, String> function = cliente -> cliente.getNome();
		List<String> nomeClientes = clientes.stream().map(function).collect(Collectors.toList());
		nomeClientes.forEach(System.out::println);
	}
	
	private void buscarNomesClientesForma3() {
		String nomeClientes = clientes.stream().map((Cliente cliente) -> {
			return cliente.getNome();
		}).collect(Collectors.joining(", "));
		
		System.out.println(nomeClientes);
	}
	
	private void buscarNomesClientesForma4() {
		String nomeClientes = clientes.stream().map((Cliente cliente) -> cliente.getNome()).collect(Collectors.joining(", "));
		System.out.println(nomeClientes);
	}
	
	private void buscarNomesClientesForma5() {
		String nomeClientes = clientes.stream().map(cliente -> cliente.getNome()).collect(Collectors.joining(", "));
		System.out.println(nomeClientes);
	}
	
	private void buscarNomesClientesForma6() {
		String nomeClientes = clientes.stream().map(Cliente::getNome).collect(Collectors.joining(", "));
		System.out.println(nomeClientes);
	}
	
	/* ------------------------------------------------------------------------------- */
	
	public void filtrarClientesNascidosNoAno() {
		final int ano = 1996;
		filtrarClientesNascidosNoAnoForma1(ano); System.out.println();
		filtrarClientesNascidosNoAnoForma2(ano); System.out.println();
		filtrarClientesNascidosNoAnoForma3(ano); System.out.println();
	}
	
	public void filtrarClientesNascidosNoAnoForma1(int ano) {
		List<Cliente> clientesFiltrados = new ArrayList<>();
		
		for(int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDataNascimento().getYear() == ano) {
				clientesFiltrados.add(clientes.get(i));
			}
		}
		
		clientesFiltrados.forEach(System.out::println);
	}
	
	public void filtrarClientesNascidosNoAnoForma2(int ano) {
		Predicate<Cliente> predicate = cliente -> cliente.getDataNascimento().getYear() == ano;
		List<Cliente> clientesFiltrados = clientes.stream().filter(predicate).collect(ArrayList::new, List::add, List::addAll);
		
		clientesFiltrados.forEach(System.out::println);
	}
	
	public void filtrarClientesNascidosNoAnoForma3(int ano) {
		clientes.stream().filter(cliente -> cliente.getDataNascimento().getYear() == ano).forEach(System.out::println);
	}
	
	/* ------------------------------------------------------------------------------- */
}
