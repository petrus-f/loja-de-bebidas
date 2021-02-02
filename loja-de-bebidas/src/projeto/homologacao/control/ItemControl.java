package projeto.homologacao.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projeto.homologacao.DAO.ItemDAO;
import projeto.homologacao.DAO.ItemDAOImpl;
import projeto.homologacao.entity.Item;

public class ItemControl {
	
	private ObservableList<Item> lista_de_itens = FXCollections.observableArrayList(); 
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private StringProperty nomeProperty = new SimpleStringProperty("");
	private DoubleProperty valor_unitarioProperty = new SimpleDoubleProperty(0.0);
	private StringProperty plasticoProperty = new SimpleStringProperty("");
	private ItemDAO itemDAO = new ItemDAOImpl();
	
	
	public ItemControl() {
		atualizarListaDeItens("");
	}
	
	public Item getItem() {
		boolean ePlastico = converterStringEmBoolean(plasticoProperty.get());
		Item item = new Item(idProperty.get(), nomeProperty.get(), valor_unitarioProperty.get(), ePlastico);
		return item;
	}
	
	private boolean converterStringEmBoolean(String info) {
		info.toLowerCase();
		if(info.equals("sim")) {
			return true;
		}
		return false;
	}

	
	
	public void setItem(Item i) {
		if(i != null) {
			idProperty.set(i.getId());
			nomeProperty.set(i.getNome());
			valor_unitarioProperty.set(i.getValor_unitario());
			String ePlastico = converterBooleanEmString(i.isPlastico());
			plasticoProperty.set(ePlastico);
		}
	}
	
	private String converterBooleanEmString(boolean info) {
		if(info) {
			return "sim";
		}
		return "não";
	}

	public ObservableList<Item> getLista_de_itens() {
		return lista_de_itens;
	}
		
	public StringProperty getNomeProperty() {
		return nomeProperty;
	}
	public DoubleProperty getValor_unitarioProperty() {
		return valor_unitarioProperty;
	}
	public StringProperty getPlasticoProperty() {
		return plasticoProperty;
	}
	
	public void adicionar() {
		itemDAO.adicionar(getItem());
		atualizarListaDeItens("");
	}

	public void pesquisar() {
		String nomePesquisa = this.getNomeProperty().get();
//		List<Item> lista_banco = itemDAO.pesquisarPorNome(nomePesquisa);
		atualizarListaDeItens(nomePesquisa);
		
	}
	
	public void remover() {
		itemDAO.remover(idProperty.get());
		atualizarListaDeItens("");
	}
	
	public void alterar() {
		itemDAO.alterar(getItem());
		atualizarListaDeItens("");
	}
	
	private void atualizarListaDeItens(String nomePesquisa) {
		lista_de_itens.clear();
		lista_de_itens.addAll(itemDAO.pesquisarPorNome(nomePesquisa));
	}
}
