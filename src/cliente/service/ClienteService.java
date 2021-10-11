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
		//buscarNomeDosClientes();
		filtrarClientesNascidosNoAno();
		
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
	
	public void buscarNomeDosClientes() {
		buscarNomeDosClientesForma1(); System.out.println();
		buscarNomeDosClientesForma2(); System.out.println();
		buscarNomeDosClientesForma3(); System.out.println();
		buscarNomeDosClientesForma4(); System.out.println();
		buscarNomeDosClientesForma5(); System.out.println();
		buscarNomeDosClientesForma6(); System.out.println();
		
	}
	
	private void buscarNomeDosClientesForma1() {
		List<String> nomeClientes = new ArrayList<>();
		
		for(Cliente cliente : clientes) {
			nomeClientes.add(cliente.getNome());
		}
		
		nomeClientes.forEach(System.out::println);
	}
	
	private void buscarNomeDosClientesForma2() {
		Function<Cliente, String> function = cliente -> cliente.getNome();
		List<String> nomeClientes = clientes.stream().map(function).collect(Collectors.toList());
		nomeClientes.forEach(System.out::println);
	}
	
	private void buscarNomeDosClientesForma3() {
		String nomeClientes = clientes.stream().map((Cliente cliente) -> {
			return cliente.getNome();
		}).collect(Collectors.joining(", "));
		
		System.out.println(nomeClientes);
	}
	
	private void buscarNomeDosClientesForma4() {
		String nomeClientes = clientes.stream().map((Cliente cliente) -> cliente.getNome()).collect(Collectors.joining(", "));
		System.out.println(nomeClientes);
	}
	
	private void buscarNomeDosClientesForma5() {
		String nomeClientes = clientes.stream().map(cliente -> cliente.getNome()).collect(Collectors.joining(", "));
		System.out.println(nomeClientes);
	}
	
	private void buscarNomeDosClientesForma6() {
		String nomeClientes = clientes.stream().map(Cliente::getNome).collect(Collectors.joining(", "));
		System.out.println(nomeClientes);
	}
	
	/* ------------------------------------------------------------------------------- */
	
	public void filtrarClientesNascidosNoAno() {
		final int ANO = 1996;
		filtrarClientesNascidosNoAnoForma1(ANO); System.out.println();
		filtrarClientesNascidosNoAnoForma2(ANO); System.out.println();
		filtrarClientesNascidosNoAnoForma3(ANO); System.out.println();
	}
	
	private void filtrarClientesNascidosNoAnoForma1(int ano) {
		List<Cliente> clientesFiltrados = new ArrayList<>();
		
		for(int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDataNascimento().getYear() == ano) {
				clientesFiltrados.add(clientes.get(i));
			}
		}
		
		clientesFiltrados.forEach(System.out::println);
	}
	
	private void filtrarClientesNascidosNoAnoForma2(int ano) {
		Predicate<Cliente> predicate = cliente -> cliente.getDataNascimento().getYear() == ano;
		List<Cliente> clientesFiltrados = clientes.stream().filter(predicate).collect(Collectors.toList());
		
		clientesFiltrados.forEach(System.out::println);
	}
	
	private void filtrarClientesNascidosNoAnoForma3(int ano) {
		clientes.stream().filter(cliente -> cliente.getDataNascimento().getYear() == ano).forEach(System.out::println);
	}
	
	/* ------------------------------------------------------------------------------- */
}
