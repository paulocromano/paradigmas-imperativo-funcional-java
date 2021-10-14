package carro.dto;

import carro.model.Carro;

public class CarroDTO {

	private String modelo;
	private Integer ano;
	private Integer preco;
	
	
	public CarroDTO(Carro carro) {
		modelo = carro.getModelo();
		ano = carro.getAno();
		preco = carro.getPreco();
	}


	public String getModelo() {
		return modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public Integer getPreco() {
		return preco;
	}


	@Override
	public String toString() {
		return "CarroDTO [modelo=" + modelo + ", ano=" + ano + ", preco=" + preco + "]";
	}
}
