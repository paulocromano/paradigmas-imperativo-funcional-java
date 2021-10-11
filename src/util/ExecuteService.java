package util;

import carro.service.CarroService;
import cliente.service.ClienteService;

public enum ExecuteService {

	CLIENTE(new ClienteService()),
	CARRO(new CarroService());
	
	private Execute execute;

	
	private ExecuteService(Execute execute) {
		this.execute = execute;
	}

	
	public Execute getExecute() {
		return execute;
	}
}
