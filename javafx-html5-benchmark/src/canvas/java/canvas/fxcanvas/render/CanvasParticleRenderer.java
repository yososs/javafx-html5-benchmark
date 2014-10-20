package canvas.fxcanvas.render;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public interface CanvasParticleRenderer {

	int getNumberOfParticles();
	void init(final Canvas canvas);
	void render(final GraphicsContext gc, final CanvasParticle p);
}
