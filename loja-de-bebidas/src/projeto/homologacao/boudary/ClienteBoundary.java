package projeto.homologacao.boudary;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import projeto.homologacao.control.ClienteControl;
import projeto.homologacao.entity.Cliente;

public class ClienteBoundary extends Application implements EventHandler<ActionEvent> {
	
	private TextField txtNome = new TextField();
	private TextField txtEndereco = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnAlterar = new Button("Alterar");
	private Button btnRemover = new Button("Remover");
	
	private TableView<Cliente> table = new TableView<Cliente>();
	
	private ClienteControl control = new ClienteControl();
		
	public static void main(String[] args) {
		Application.launch(args);	
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == btnAdicionar) {
			control.adicionar();
			
		}else if(e.getTarget() == btnPesquisar) {
			control.pesquisarPorNome();
			
		}else if(e.getTarget() == btnAlterar) {
			control.alterar();
			
		}else if(e.getTarget() == btnRemover) {
			control.remover();
		}
	}
				

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent painel = definirPaineis();
		
		vincularCampos();
		definirColunas();
		
		btnAdicionar.addEventHandler(ActionEvent.ACTION, this);
		btnPesquisar.addEventHandler(ActionEvent.ACTION, this);
		btnRemover.addEventHandler(ActionEvent.ACTION, this);
		btnAlterar.addEventHandler(ActionEvent.ACTION, this);
		
		Scene scn = new Scene(painel, 600, 400);
		stage.setTitle("Cliente");
		stage.setScene(scn);
		stage.show();
	}

	private void vincularCampos() {

		Bindings.bindBidirectional(txtNome.textProperty(), control.getNomeProperty());
		Bindings.bindBidirectional(txtEndereco.textProperty(), control.getEnderecoProperty());
	}

	private BorderPane definirPaineis() {
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();
		bp.setTop(gp);
		bp.setBottom(table);
		gp.setHgap(30);
		gp.setVgap(5);
		
		ColumnConstraints colLabel = new ColumnConstraints();
		ColumnConstraints colText = new ColumnConstraints();
		colLabel.setHalignment(HPos.LEFT);
		colLabel.setPercentWidth(30);	
		colText.setPercentWidth(70);
		
		gp.getColumnConstraints().addAll(colLabel, colText);
		gp.add(new Label("Nome:"), 0, 0);
		gp.add(txtNome, 1, 0);
		gp.add(new Label("Endereço:"), 0, 1);
		gp.add(txtEndereco, 1, 1);
		gp.add(btnAdicionar, 0, 2);
		gp.add(btnPesquisar, 1, 2);
		gp.add(btnAlterar, 0, 3);
		gp.add(btnRemover, 1, 3);
		
		return bp;
	}
	
	private void definirColunas() {
		TableColumn<Cliente, String> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Cliente, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Cliente, String> colEndereco = new TableColumn<>("Endereço");
		colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
			
		table.getColumns().addAll(colId, colNome, colEndereco);
		
		table.setItems(control.getListaClientes());
		
		table.getSelectionModel().selectedItemProperty().addListener((listener, clienteAntigo, clienteNovo) -> {
			control.setCliente(clienteNovo);
		});
		
	}

}
