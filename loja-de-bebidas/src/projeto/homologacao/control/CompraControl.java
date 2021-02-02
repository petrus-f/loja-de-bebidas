package projeto.homologacao.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projeto.homologacao.DAO.CompraDAO;
import projeto.homologacao.DAO.CompraDAOImpl;
import projeto.homologacao.entity.Cliente;
import projeto.homologacao.entity.Compra;
import projeto.homologacao.entity.Item;
import projeto.homologacao.entity.ItemDeCompra;

public class CompraControl {

	private ObservableList<Compra> lista_de_compras = FXCollections.observableArrayList();
	
	private StringProperty nomeClienteProperty = new SimpleStringProperty();
	private LongProperty idCompraProperty = new SimpleLongProperty();
	private LongProperty idItemProperty = new SimpleLongProperty();
	private StringProperty nomeProdutoProperty = new SimpleStringProperty();
	private IntegerProperty quantidadeProperty = new SimpleIntegerProperty(1);
	private DoubleProperty valorProperty = new SimpleDoubleProperty();
	private Item item;
	private Cliente cliente;
	private ObservableList<ItemDeCompra> lista_de_itens_compra = FXCollections.observableArrayList();
	private CompraDAO compraDAO = new CompraDAOImpl();
	
	public ObservableList<ItemDeCompra> getLista_de_itens_compra() {
		return lista_de_itens_compra;
	}

	public void setLista_de_itens_compra(ObservableList<ItemDeCompra > lista_de_itens_compra) {
		this.lista_de_itens_compra = lista_de_itens_compra;
	}

	public void setItem(Item i) {
		idItemProperty.set(i.getId());
		nomeProdutoProperty.set(i.getNome());
		item = i;
	}
	
	public Compra getCompra() {
		if(cliente != null) {
			Compra c = new Compra();
			c.setCliente(cliente);
			long id = compraDAO.getMaiorID();
			id++;
			c.setId(id);
			for(ItemDeCompra ic: lista_de_itens_compra) {
				c.adicionarItemDeCompra(ic);
			}
			c.calcularValorCompra();
			return c;
		}
		return null;
	}

	public StringProperty getNomeClienteProperty() {
		return nomeClienteProperty;
	}

	public LongProperty getIdCompraProperty() {
		return idCompraProperty;
	}

	public LongProperty getIdItemProperty() {
		return idItemProperty;
	}

	public StringProperty getNomeProdutoProperty() {
		return nomeProdutoProperty;
	}

	public IntegerProperty getQuantidadeProperty() {
		return quantidadeProperty;
	}

	public DoubleProperty getValorProperty() {
		return valorProperty;
	}

	public void calcularValor() {
		if (item != null) {
			double valor_item = item.getValor_unitario();
			int quantidade = quantidadeProperty.get();
			valorProperty.set(valor_item * quantidade);
		}
	}

	public void adicionarItem() {
		if (item != null) {
			Compra compra = getCompra();
			ItemDeCompra item_de_compra = new ItemDeCompra(item, quantidadeProperty.get(), compra);
			compra.adicionarItemDeCompra(item_de_compra);
			lista_de_itens_compra.add(item_de_compra);
		}
	}

	public void finalizarCompra() {
		Compra compra = getCompra();
		compraDAO.adicionar(compra);
	
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		nomeClienteProperty.set(cliente.getNome());
	}
	
	public Cliente getCliente() {
		return cliente;
	}

}
