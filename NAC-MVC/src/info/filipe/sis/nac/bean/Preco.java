package info.filipe.sis.nac.bean;

import java.text.DecimalFormat;

public class Preco {
	private String descricao, valordecimal;
	private double valor;
	private int id;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
		//return new DecimalFormat("###,###.##").format(valor);
	}
	public String getValordecimal() {
		valordecimal = new DecimalFormat("###,###.00").format(valor);
		return "R$ "+valordecimal;
	}
	public void setValordecimal(String valordecimal) {
		this.valordecimal = valordecimal;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
