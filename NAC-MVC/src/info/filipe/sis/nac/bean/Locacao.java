package info.filipe.sis.nac.bean;


public class Locacao {
	private int id, idCarro, idPreco, idCliente, qtdDias;
	private String dsSituacao, dsPagamento, obs,data_loc, data_entrega;
	private Carro carro;
	private Preco preco;
	private Cliente cliente;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}

	public int getIdPreco() {
		return idPreco;
	}

	public void setIdPreco(int idPreco) {
		this.idPreco = idPreco;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(int qtdDias) {
		this.qtdDias = qtdDias;
	}

	public String getDsSituacao() {
		return dsSituacao;
	}

	public void setDsSituacao(String dsSituacao) {
		this.dsSituacao = dsSituacao;
	}

	public String getDsPagamento() {
		return dsPagamento;
	}

	public void setDsPagamento(String dsPagamento) {
		this.dsPagamento = dsPagamento;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getData_loc() {
		return data_loc;
	}

	public void setData_loc(String data_loc) {
		this.data_loc = data_loc;
	}

	public String getData_entrega() {
		return data_entrega;
	}

	public void setData_entrega(String data_entrega) {
		this.data_entrega = data_entrega;
	}

	public String getCarro() {
		return carro.getModelo();
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public String getPreco() {
		return ""+preco.getValor();
	}

	public void setPreco(Preco preco) {
		this.preco = preco;
	}

	public String getCliente() {
		return cliente.getNome();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	

}
