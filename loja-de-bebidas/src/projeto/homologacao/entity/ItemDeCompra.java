package projeto.homologacao.entity;

public class ItemDeCompra {
	
	private Item item;
	private String nome;
	private double valor_unitario;
	private int quantidade;
	private double valor_item_de_compra;
	private Compra compra;
	
	
	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public void setValor_item_de_compra(double valor_item_de_compra) {
		this.valor_item_de_compra = valor_item_de_compra;
	}

	public ItemDeCompra(Item item, int quantidade, Compra compra) {
		this.item = item;
		this.nome = item.getNome();
		this.valor_unitario = item.getValor_unitario();
		this.quantidade = quantidade;
		this.compra = compra;
		calcularValorItem();
	}
	
	public ItemDeCompra() {
		
	}
	
	public void calcularValorItem() {
		valor_item_de_compra = quantidade*valor_unitario;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor_item_de_compra() {
		return valor_item_de_compra;
	}

}
