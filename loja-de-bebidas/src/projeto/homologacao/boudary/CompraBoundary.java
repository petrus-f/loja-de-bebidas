package projeto.homologacao.boudary;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import projeto.homologacao.MenuPrincipal;
import projeto.homologacao.control.ClienteControl;
import projeto.homologacao.control.CompraControl;
import projeto.homologacao.control.ItemControl;
import projeto.homologacao.entity.Cliente;
import projeto.homologacao.entity.Item;
import projeto.homologacao.entity.ItemDeCompra;

public class CompraBoundary extends Application implements EventHandler<Event> {
	
	// parents da janela
	TextField txtCliente = new TextField();
	Label lblIDCompra = new Label();
	Button btnAdicionar = new Button("Adicionar");
	Button btnFinalizar = new Button("Finalizar Compra");
	Button btnNovaCompra = new Button("Nova Compra");
	Button btnMenu = new Button("Menu");

	// parent item de compra
	Label lblProduto = new Label();
	TextField txtQuantidade = new TextField("1");
	Label lblValor = new Label();

	// Classes control instanciadas
	ItemControl cItem = new ItemControl();
	CompraControl cCompra = new CompraControl();
	ClienteControl cCliente = new ClienteControl();

	// tableviews
	TableView<Item> table_itens = new TableView<Item>();
	TableView<ItemDeCompra> table_itens_de_compra = new TableView<ItemDeCompra>();
	TableView<Cliente> table_clientes = new TableView<Cliente>();

	Stage stage; 

	@Override
	public void handle(Event e) {
		try {
			if (e.getTarget() == btnAdicionar) {
				cCompra.adicionarItem();
			} else if (e.getTarget() == btnFinalizar) {
				cCompra.finalizarCompra();
				finalizarCompra();
			} else if (e.getTarget() == btnNovaCompra) {
				CompraBoundary compra = new CompraBoundary();
				compra.start(stage);

			} else if (e.getTarget() == btnMenu) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.start(stage);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		GridPane gpTop = new GridPane();
		GridPane gpRight = new GridPane();
		GridPane gpCenter = new GridPane();
		BorderPane bp = new BorderPane();

		bp.setTop(gpTop);
		gpTop.add(new Label("ID"), 0, 0);
		gpTop.add(lblIDCompra, 1, 0);
		gpTop.add(new Label("Cliente"), 2, 0);
		gpTop.add(txtCliente, 3, 0);
		gpTop.add(table_clientes, 3, 1);
		gpTop.setVgap(20);
		gpTop.setHgap(20);

		gpCenter.add(new Label("Produto"), 0, 0);
		gpCenter.add(lblProduto, 1, 0);
		gpCenter.add(new Label("Quantidade"), 2, 0);
		gpCenter.add(txtQuantidade, 3, 0);
		gpCenter.add(new Label("Valor R$:"), 4, 0);
		gpCenter.add(lblValor, 5, 0);
		gpCenter.add(btnAdicionar, 6, 0);
		gpCenter.add(table_itens, 0, 1);

		bp.setCenter(gpCenter);
		bp.setRight(gpRight);

		gpRight.add(table_itens_de_compra, 0, 0);
		gpRight.add(btnFinalizar, 0, 1);

		btnAdicionar.addEventHandler(ActionEvent.ACTION, this);
		btnFinalizar.addEventHandler(ActionEvent.ACTION, this);

		vincularCampos();
		definirColunasItens();
		definirColunasClientes();
		definirColunasItemDeCompra();
		definirListeners();

		Scene scn = new Scene(bp, 600, 400);
		stage.setScene(scn);
		stage.show();
	}

	public void vincularCampos() {
		StringConverter<? extends Number> valorConverterDouble = new DoubleStringConverter();
		StringConverter<? extends Number> valorConverterInteger = new IntegerStringConverter();
		StringConverter<? extends Number> valorConverterLong = new LongStringConverter();
		Bindings.bindBidirectional(txtCliente.textProperty(), cCompra.getNomeClienteProperty());
		Bindings.bindBidirectional(lblIDCompra.textProperty(), cCompra.getIdCompraProperty(),
				(StringConverter<Number>) valorConverterLong);
		Bindings.bindBidirectional(lblProduto.textProperty(), cCompra.getNomeProdutoProperty());
		Bindings.bindBidirectional(txtQuantidade.textProperty(), cCompra.getQuantidadeProperty(),
				(StringConverter<Number>) valorConverterInteger);
		Bindings.bindBidirectional(lblValor.textProperty(), cCompra.getValorProperty(),
				(StringConverter<Number>) valorConverterDouble);
	}

	private void definirColunasItemDeCompra() {
		//colunas da tabela
		TableColumn<ItemDeCompra, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<ItemDeCompra, String> colValor_Unitario = new TableColumn<>("Valor Unitário");
		colValor_Unitario.setCellValueFactory(new PropertyValueFactory<>("valor_unitario"));

		TableColumn<ItemDeCompra, String> colQuantidade = new TableColumn<>("Quantidade");
		colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

		TableColumn<ItemDeCompra, String> colValorParcial = new TableColumn<>("Valor Parcial");
		colValorParcial.setCellValueFactory(new PropertyValueFactory<>("valor_item_de_compra"));
		
		table_itens_de_compra.getColumns().addAll(colNome, colValor_Unitario, colQuantidade, colValorParcial);
		
		//adiciona os itens a coluna
		table_itens_de_compra.setItems(cCompra.getLista_de_itens_compra());

//		table_itens_de_compra.getSelectionModel().selectedItemProperty().addListener((listener, itemAntigo, itemNovo) -> {
//			cItem.setItem(itemNovo);
//		});

	}

	private void definirColunasItens() {
		TableColumn<Item, String> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Item, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Item, String> colValor_Unitario = new TableColumn<>("Valor Unitário");
		colValor_Unitario.setCellValueFactory(new PropertyValueFactory<>("valor_unitario"));

		TableColumn<Item, String> colPlastico = new TableColumn<>("É plastico ?");
		colPlastico.setCellValueFactory(new PropertyValueFactory<>("plasticoString"));

		table_itens.getColumns().addAll(colId, colNome, colValor_Unitario, colPlastico);

		table_itens.setItems(cItem.getLista_de_itens());

	}

	private void definirColunasClientes() {
		TableColumn<Cliente, String> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Cliente, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Cliente, String> colEndereco = new TableColumn<>("Endereço");
		colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

		table_clientes.getColumns().addAll(colId, colNome, colEndereco);

		table_clientes.setItems(cCliente.getListaClientes());

	}

	private void definirListeners() {
		table_itens.getSelectionModel().selectedItemProperty().addListener((listener, itemAntigo, itemNovo) -> {
			cCompra.setItem(itemNovo);
			cCompra.calcularValor();
		});

		txtQuantidade.textProperty().addListener((listener, valorAntigo, Valornovo) -> {
			cCompra.calcularValor();
		});

		table_clientes.getSelectionModel().selectedItemProperty()
				.addListener((listener, clienteAntigo, clienteNovo) -> {
					cCompra.setCliente(clienteNovo);
				});

	}

	private void finalizarCompra() {
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();

		btnNovaCompra.addEventHandler(ActionEvent.ACTION, this);
		btnMenu.addEventHandler(ActionEvent.ACTION, this);

		gp.add(new Label("Compra Finalizada"), 0, 0);
		gp.add(btnNovaCompra, 0, 1);
		gp.add(btnMenu, 1, 1);

		bp.setCenter(gp);
		Scene scnFinalizar = new Scene(bp, 500, 500);
		stage.setScene(scnFinalizar);
		stage.show();
	}
}
