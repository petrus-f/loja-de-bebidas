package projeto.homologacao.entity;

import java.util.ArrayList;
import java.util.List;

public class Compra {
	private long id;
	private Cliente cliente;
	private List<ItemDeCompra> itens_de_compra = new ArrayList<ItemDeCompra>();
	private double valor_da_compra;
	
	
	public Compra() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ItemDeCompra> getItens_de_compra() {
		return itens_de_compra;
	}

	public void setItens_de_compra(List<ItemDeCompra> itens_de_compra) {
		this.itens_de_compra = itens_de_compra;
	}
		
	public void adicionarItemDeCompra(ItemDeCompra i) {
		itens_de_compra.add(i);
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemDeCompra> getItens() {
		return itens_de_compra;
	}

	public void setItens(List<ItemDeCompra> itens) {
		this.itens_de_compra = itens;
	}

	public double getValor_da_compra() {
		return valor_da_compra;
	}

	public void setValor_da_compra(double valor_da_compra) {
		this.valor_da_compra = valor_da_compra;
	}
	
	public void calcularValorCompra() {
		for(ItemDeCompra i: itens_de_compra) {
			valor_da_compra += i.getValor_item_de_compra();
		}
	}
	
	public void realizarCompra() {
		// interface de compra
		double valor_pago = 0;
		if(valor_pago < valor_da_compra) {
			System.out.println("Valor insuficiente");
		}else {
			double troco = valor_da_compra - valor_pago;
			System.out.println("Pagamento efetuado\nTroco: R$"+ troco);
			cliente.adicionarNovaCompra(this);
		}
	}

}
