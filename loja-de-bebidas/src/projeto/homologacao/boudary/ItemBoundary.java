package projeto.homologacao.boudary;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import projeto.homologacao.control.ItemControl;
import projeto.homologacao.entity.Item;

public class ItemBoundary extends Application implements EventHandler<ActionEvent>{
	
	private TextField txtNome = new TextField();
	private TextField txtValorUnitario = new TextField();
	private TextField txtPlastico = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnRemover = new Button("Remover");
	private Button btnAlterar = new Button("Alterar");
	
	ItemControl control = new ItemControl();
	
	TableView<Item> table = new TableView<Item>();
	
	public static void main(String[] args) {
		Application.launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		vincularCampos();
		Parent painel = definirPaineis();
		Scene scn = new Scene(painel, 600, 400);
		
		definirColunas();
		
		btnAdicionar.addEventHandler(ActionEvent.ACTION, this);
		btnPesquisar.addEventHandler(ActionEvent.ACTION, this);
		btnRemover.addEventHandler(ActionEvent.ACTION, this);
		btnAlterar.addEventHandler(ActionEvent.ACTION, this);
		
		stage.setTitle("Item");
		stage.setScene(scn);
		stage.show();
	}
	
	private BorderPane definirPaineis() {
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();
		bp.setCenter(gp);
		bp.setBottom(table);
		
		gp.setPadding(new Insets(20));
		gp.setHgap(30);
		gp.setVgap(5);
		
		ColumnConstraints colLabel = new ColumnConstraints();
		ColumnConstraints colText = new ColumnConstraints();
		colLabel.setHalignment(HPos.LEFT);
		colLabel.setPercentWidth(30);	
		colText.setPercentWidth(70);
		
		gp.getColumnConstraints().addAll(colLabel, colText);
		gp.add(new Label("Nome do item:"), 0, 0);
		gp.add(txtNome, 1, 0);
		gp.add(new Label("Valor Unitário"), 0, 1);
		gp.add(txtValorUnitario, 1, 1);
		gp.add(new Label("É plastico ?"), 0, 2);
		gp.add(txtPlastico, 1, 2);
		gp.add(btnAdicionar, 0, 3);
		gp.add(btnPesquisar, 1, 3);
		gp.add(btnRemover, 0, 4);
		gp.add(btnAlterar, 1, 4);
		return bp;
	}

	private void definirColunas() {
		TableColumn<Item, String> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Item, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Item, String> colValor_Unitario = new TableColumn<>("Valor Unitário");
		colValor_Unitario.setCellValueFactory(new PropertyValueFactory<>("valor_unitario"));
		
		TableColumn<Item, String> colPlastico = new TableColumn<>("É plastico ?");
		colPlastico.setCellValueFactory(new PropertyValueFactory<>("plastico"));
		
		table.getColumns().addAll(colId, colNome, colValor_Unitario, colPlastico);
		
		table.setItems(control.getLista_de_itens());
		
		table.getSelectionModel().selectedItemProperty().addListener((listener, itemAntigo, itemNovo) -> {
			control.setItem(itemNovo);
		});
		
	}

	public void vincularCampos() {
		StringConverter<? extends Number> valorConverter = new DoubleStringConverter();
 		
		Bindings.bindBidirectional(txtNome.textProperty(), control.getNomeProperty());
 		Bindings.bindBidirectional(txtValorUnitario.textProperty(), control.getValor_unitarioProperty(),(StringConverter<Number>) valorConverter);
 		Bindings.bindBidirectional(txtPlastico.textProperty(), control.getPlasticoProperty());	
}
	
	
	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == btnAdicionar) {
			control.adicionar();
		}else if(e.getTarget() == btnPesquisar) {
			control.pesquisar();
		}else if(e.getTarget() == btnRemover) {
			control.remover();
		}else if(e.getTarget() == btnAlterar) {
			control.alterar();
		}
	}
	
	
	
}
