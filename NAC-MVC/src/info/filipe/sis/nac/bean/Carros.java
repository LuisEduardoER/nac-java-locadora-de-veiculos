package info.filipe.sis.nac.bean;

public class Carros {

	private String placa, modelo;
	private Marca marca;
	private float km;
	private int id, ano, idpreco;
	
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
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
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
	
	
	
}
