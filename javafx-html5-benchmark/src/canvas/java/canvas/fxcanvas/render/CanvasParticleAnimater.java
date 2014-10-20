package canvas.fxcanvas.render;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public final class CanvasParticleAnimater {

	private final int width;

	private final int height;

	private final Canvas canvas;

	private final GraphicsContext gc;

	private CanvasParticle[] particles;

	private final CanvasParticleRenderer renderer;

	public CanvasParticleAnimater(final int width, final int height, final CanvasParticleRenderer renderer) {
		this.width = width;
		this.height = height;
		this.canvas = new Canvas(width, height);
		this.gc = this.canvas.getGraphicsContext2D();
		this.renderer = renderer;

		this.init();

		this.animate();
	}

	private void animate() {
		new AnimationTimer() {

			@Override
			public void handle(final long now) {
				CanvasParticleAnimater.this.renderFrame();
				double fps =  com.sun.javafx.perf.PerformanceTracker.getSceneTracker(canvas.getScene()).getInstantFPS();
				gc.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
				gc.setFill(Color.WHITE);
				gc.fillText("fps:"+fps, 600, 600);
			}
		}.start();
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	private void init() {

		this.particles = new CanvasParticle[this.renderer.getNumberOfParticles()];
		for (int i = 0; i < this.renderer.getNumberOfParticles(); i++) {
			this.particles[i] = new CanvasParticle(this.width, this.height);
		}

		this.renderer.init(this.canvas);
	}

	private void renderFrame() {
		this.gc.setFill(Color.DARKSLATEGRAY);
		this.gc.fillRect(0, 0, this.width, this.height);

		for (final CanvasParticle p : this.particles) {

			this.renderer.render(this.gc, p);

			if (p.r <= CanvasParticle.MIN_RADIUS) {
				p.dr = CanvasParticle.RADIUS_DELTA;
			} else if (p.r > p.maxR) {
				p.dr = -CanvasParticle.RADIUS_DELTA;
			}

			p.r = p.r + p.dr;
			p.x = p.x + p.dx;
			p.y = p.y + p.dy;

			if (p.x > this.width) {
				p.x = this.width;
				p.dx = -p.dx;
			} else if (p.x < 0) {
				p.x = 0;
				p.dx = -p.dx;
			}

			if (p.y > this.height) {
				p.y = this.height;
				p.dy = -p.dy;
			} else if (p.y < 0) {
				p.y = 0;
				p.dy = -p.dy;
			}
		}
	}
}