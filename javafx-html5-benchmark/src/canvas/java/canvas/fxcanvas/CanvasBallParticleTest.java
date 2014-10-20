package canvas.fxcanvas;

import canvas.fxcanvas.render.CanvasParticle;
import canvas.fxcanvas.render.CanvasParticleAnimater;
import canvas.fxcanvas.render.CanvasParticleRenderer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class CanvasBallParticleTest extends Application {

	static class BallParticleRenderer implements CanvasParticleRenderer {

		@Override
		public int getNumberOfParticles() {
			return 1000;
		}

		@Override
		public void init(final Canvas canvas) {
			canvas.getGraphicsContext2D().setGlobalAlpha(0.7);
//			final GaussianBlur blur = new GaussianBlur();
//			blur.setRadius(4d);
//			canvas.setEffect(blur);
		}

		@Override
		public void render(final GraphicsContext gc, final CanvasParticle p) {
			gc.setFill(p.color);
			gc.fillOval(p.x, p.y, p.r, p.r);
		}
	}
	
	public static void main(final String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(final Stage stage) {

		stage.setTitle("JavaFX Canvas Ball Particle Test");

		final CanvasParticleAnimater animater = new CanvasParticleAnimater(800, 600, new BallParticleRenderer());

		final BorderPane pane = new BorderPane();
		pane.setCenter(animater.getCanvas());

		final Scene scene = new Scene(pane);

		stage.setScene(scene);
		stage.show();
	}
}
