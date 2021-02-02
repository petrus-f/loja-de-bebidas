package projeto.homologacao.entity;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String nome;
	private long id;
	private String endereco;
	private List<ItemDeCompra> lista_de_plasticos = new ArrayList<ItemDeCompra>();
	private List<Compra> lista_de_compras = new ArrayList<Compra>();

	public void gerarListaDePlastico(Compra c) {
		List<ItemDeCompra> lista_de_itens = c.getItens(); // retorne a lista de itens da compra para a variavel local
		for (ItemDeCompra item : lista_de_itens) {// para cada item da lista, verifique se ele é plastico
			if (item.getItem().isPlastico() == true) {
				lista_de_plasticos.add(item);// se for verdadeiro adicione na lista de plasticos
			}
		}
	}
	
	public Cliente(long id, String nome, String endereco) {
		this.id=id;
		this.nome=nome;
		this.endereco=endereco;
	}

	public void adicionarNovaCompra(Compra c) {
		lista_de_compras.add(c);
		gerarListaDePlastico(c);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ItemDeCompra> getLista_de_plasticos() {
		return lista_de_plasticos;
	}

	public void setLista_de_plasticos(List<ItemDeCompra> lista_de_plasticos) {
		this.lista_de_plasticos = lista_de_plasticos;
	}

	public List<Compra> getLista_de_compras() {
		return lista_de_compras;
	}

	public void setLista_de_compras(List<Compra> lista_de_compras) {
		this.lista_de_compras = lista_de_compras;
	}

}
