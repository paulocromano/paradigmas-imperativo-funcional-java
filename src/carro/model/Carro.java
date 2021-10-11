package carro.model;

public class Carro {
	
	private String modelo;
	private Integer ano;
	private Integer preco;
	

	public Carro(String modelo, Integer ano, Integer preco) {
		this.modelo = modelo;
		this.ano = ano;
		this.preco = preco;
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
		return "Carro [modelo=" + modelo + ", ano=" + ano + ", preco=" + preco + "]";
	}
}
