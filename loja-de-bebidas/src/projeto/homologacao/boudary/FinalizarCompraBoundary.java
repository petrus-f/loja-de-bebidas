package projeto.homologacao.boudary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FinalizarCompraBoundary extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bp = new BorderPane(); 
		bp.setCenter(new Label("Compra Finalizada"));
		Scene scnFinalizar = new Scene(bp, 600, 600);
		stage.setScene(scnFinalizar);
		stage.show();
	}

	
}
