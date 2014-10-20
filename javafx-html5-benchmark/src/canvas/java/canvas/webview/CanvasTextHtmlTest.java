package canvas.webview;


import java.net.URI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class CanvasTextHtmlTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		
		BorderPane pane = new BorderPane();
		WebView browser = new WebView();
		
		URI uri = this.getClass().getResource("/canvas/html/CanvasTextParticleTest.html").toURI();
//		URI uri = this.getClass().getResource("/canvas/html/CanvasBallParticleTest.html").toURI();
//		URI uri = this.getClass().getResource("/canvas/html/CanvasImageParticleTest.html").toURI();
		
		browser.getEngine().load(uri.toURL().toString());
		pane.setCenter(browser);
		Scene scene = new Scene(pane, 800+10, 600+10);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}

