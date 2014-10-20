package canvas.fxcanvas.render;

import javafx.scene.paint.Color;

public final class CanvasParticle {

	public static final int MIN_RADIUS = 8;

	public static final int MAX_RADIUS = 120;

	public static final int MAX_DELTA = 12;

	public static final int RADIUS_DELTA = 2;

	private static final Color[] COLORS = new Color[] { Color.RED, Color.INDIGO, Color.GOLD, Color.GREEN, Color.BROWN, Color.BLUE, Color.ORANGERED, Color.YELLOW, Color.AQUA, Color.LIGHTPINK };

	public double x;

	public double y;

	public final Color color;

	public double dx;

	public double dy;

	public double r;

	public final double maxR;

	public int dr;

	public CanvasParticle(final int canvasWidth, final int canvasHeight) {

		this.x = Math.round(Math.random() * 50);
		if (Math.random() > 0.5) {
			this.x = canvasWidth - this.x;
		}
		this.y = Math.round(Math.random() * 50);
		if (Math.random() > 0.5) {
			this.y = canvasHeight - this.y;
		}

		this.color = COLORS[(int) Math.round(Math.random() * (COLORS.length - 1))];

		this.dx = Math.round(Math.random() * MAX_DELTA) - MAX_DELTA / 2;
		this.dy = Math.round(Math.random() * MAX_DELTA) - MAX_DELTA / 2;

		this.r = Math.round(Math.random() * MAX_RADIUS) - MAX_RADIUS / 2;
		this.maxR = Math.round(Math.random() * MAX_RADIUS / 2) + 10;
		this.dr = Math.random() > 0.5 ? RADIUS_DELTA : -RADIUS_DELTA;
	}
}