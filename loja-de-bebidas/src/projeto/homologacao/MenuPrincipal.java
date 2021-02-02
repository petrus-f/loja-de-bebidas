package projeto.homologacao;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import projeto.homologacao.boudary.ClienteBoundary;
import projeto.homologacao.boudary.CompraBoundary;
import projeto.homologacao.boudary.ItemBoundary;

public class MenuPrincipal extends Application implements EventHandler<ActionEvent> {

	Button btnCompra = new Button("Nova Compra");
	Button btnDevolucao = new Button("Devolução de plástico");
	Button btnItem = new Button("Itens");
	Button btnCliente = new Button("Clientes");
	Stage stage = new Stage();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		GridPane gp = new GridPane();
		Scene scn = new Scene(gp, 300, 300);

		btnCompra.addEventFilter(ActionEvent.ACTION, this);
		btnItem.addEventFilter(ActionEvent.ACTION, this);
		btnCliente.addEventFilter(ActionEvent.ACTION, this);

		gp.add(btnCompra, 0, 0);
		gp.add(btnDevolucao, 0, 1);
		gp.add(btnItem, 0, 2);
		gp.add(btnCliente, 0, 3);

		gp.setVgap(10);

		stage.setTitle("Água Coca Latão");
		stage.setScene(scn);
		stage.show();
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			if (event.getTarget() == btnCompra) {
				System.out.println("Comprar");
				CompraBoundary bCompra = new CompraBoundary();
				bCompra.start(stage);
			} else if(event.getTarget() == btnItem) {
				ItemBoundary bItem = new ItemBoundary();
				bItem.start(stage);
			}else if(event.getTarget() == btnCliente) {
				ClienteBoundary bCliente = new ClienteBoundary(); 
				bCliente.start(stage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
