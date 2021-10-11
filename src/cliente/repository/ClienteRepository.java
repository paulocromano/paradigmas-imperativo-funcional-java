package cliente.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import cliente.model.Cliente;

public class ClienteRepository {

	public List<Cliente> buscarClientes() {
		return Arrays.asList(
				new Cliente("João Pedro", LocalDate.of(1990, Month.JANUARY, 12)),
				new Cliente("Rebeca", LocalDate.of(1985, Month.APRIL, 20)),
				new Cliente("Pedro", LocalDate.of(1992, Month.AUGUST, 2)),
				new Cliente("Pamela", LocalDate.of(1996, Month.FEBRUARY, 5)),
				new Cliente("Paola", LocalDate.of(1996, Month.FEBRUARY, 15)),
				new Cliente("Daniel", LocalDate.of(1996, Month.MARCH, 24)),
				new Cliente("Abraão", LocalDate.of(1997, Month.SEPTEMBER, 10)),
				new Cliente("Daniela", LocalDate.of(1996, Month.MAY, 17)));
	}
}
