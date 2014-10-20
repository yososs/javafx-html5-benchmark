package canvas.fxcanvas;

import canvas.fxcanvas.render.CanvasParticle;
import canvas.fxcanvas.render.CanvasParticleAnimater;
import canvas.fxcanvas.render.CanvasParticleRenderer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;


public class CanvasTextParticleTest extends Application {

	static class TextParticleRenderer implements CanvasParticleRenderer {

		@Override
		public int getNumberOfParticles() {
			return 1000;
		}

		@Override
		public void init(final Canvas canvas) {
			canvas.getGraphicsContext2D().setGlobalAlpha(0.7);
		}

		@Override
		public void render(final GraphicsContext gc, final CanvasParticle p) {
			gc.setFont(Font.font("Arial", FontPosture.REGULAR, p.r / 2));
			gc.setFill(p.color);
			gc.fillText("JavaFX", p.x, p.y); //$NON-NLS-1$
		}
	}

	public static void main(final String[] args) {
		Application.launch(args);
	}


	@Override
	public void start(final Stage stage) {

		stage.setTitle("JavaFX Canvas Text Particle Test");

		final CanvasParticleAnimater animater = new CanvasParticleAnimater(800, 600, new TextParticleRenderer());

		final BorderPane pane = new BorderPane();
		pane.setCenter(animater.getCanvas());

		final Scene scene = new Scene(pane);

		stage.setScene(scene);
		stage.show();
	}
}
