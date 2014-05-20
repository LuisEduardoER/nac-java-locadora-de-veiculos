package info.filipe.sis.nac.bean;

public class Carro {

	private String placa, modelo;
	private float km;
	private int id, ano, idpreco, idmarca;
	private Marca marca;
	private Preco preco;
	
	public String getPreco() {
		return preco.getDescricao();
	}
	public void setPreco(Preco preco) {
		this.preco = preco;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getIdmarca() {
		return idmarca;
	}
	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
	}
	public float getKm() {
		return km;
	}
	public void setKm(float km) {
		this.km = km;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getIdpreco() {
		return idpreco;
	}
	public void setIdpreco(int idpreco) {
		this.idpreco = idpreco;
	}
	public String getMarca() {
		return marca.getDescricao();
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	
	
}
