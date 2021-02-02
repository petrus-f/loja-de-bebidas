package projeto.homologacao.control;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projeto.homologacao.DAO.ClienteDAO;
import projeto.homologacao.DAO.ClienteDAOImpl;
import projeto.homologacao.entity.Cliente;

public class ClienteControl {
	private ObservableList<Cliente> lista_clientes = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private StringProperty nomeProperty = new SimpleStringProperty("");
	private StringProperty enderecoProperty = new SimpleStringProperty("");
	
	private ClienteDAO clienteDAO = new ClienteDAOImpl();
	private String ultimaPesquisa;
	
	
	public ClienteControl() {
		pesquisarPorNome();
	}
	
	
	private Cliente getCliente() {
		Cliente c = new Cliente(idProperty.get(), nomeProperty.get(), enderecoProperty.get());
		return c;
	}
	
	public ObservableList<Cliente> getListaClientes(){
		return lista_clientes;
	}
	public StringProperty getNomeProperty() {
		return nomeProperty;
	}
	public void setNomeProperty(StringProperty nomeProperty) {
		this.nomeProperty = nomeProperty;
	}
	public StringProperty getEnderecoProperty() {
		return enderecoProperty;
	}
	public void setEnderecoProperty(StringProperty enderecoProperty) {
		this.enderecoProperty = enderecoProperty;
	}
	
	public void adicionar() {
		clienteDAO.adicionar(getCliente());
		atualizarListaDeClientes(ultimaPesquisa);
	}
	public void pesquisarPorNome() {
		ultimaPesquisa = nomeProperty.get();
		atualizarListaDeClientes(ultimaPesquisa);
	}
	public void alterar() {
		clienteDAO.alterar(getCliente());
		atualizarListaDeClientes(ultimaPesquisa);
	}
	public void remover() {
		clienteDAO.remover(idProperty.get());
		atualizarListaDeClientes(ultimaPesquisa);
	}
	private void atualizarListaDeClientes(String nomePesquisa) {
		lista_clientes.clear();
		lista_clientes.addAll(clienteDAO.pesquisarPorNome(nomePesquisa));
		
	}

	public void setCliente(Cliente cliente) {
		if(cliente != null) {
			idProperty.set(cliente.getId());
			nomeProperty.set(cliente.getNome());
			enderecoProperty.set(cliente.getEndereco());
		}
		
	}
	
}
