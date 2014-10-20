var canvas = document.querySelector('canvas'), ctx = canvas.getContext('2d'), particles = [], particlesNum, w = 800, h = 600, COLORS = [
		'#ff0000', '#4b0082', '#ffd700', '#008000', '#a52a2a', '#0000ff',
		'#ff4500', '#ffff00', '#00ffff', '#ffb6c1' ], MIN_RADIUS = 8, MAX_RADIUS = 120, MAX_DELTA = 12, RADIUS_DELTA = 2;

canvas.width = w;
canvas.height = h;
canvas.style.left = (window.innerWidth - w) / 2 + 'px';

if (window.innerHeight > w)
	canvas.style.top = (window.innerHeight - h) / 2 + 'px';

function Particle() {
	this.x = Math.round(Math.random() * 50);
	if (Math.random() > 0.5) {
		this.x = canvas.width - this.x;
	}
	this.y = Math.round(Math.random() * 50);
	if (Math.random() > 0.5) {
		this.y = canvas.height - this.y;
	}
	this.color = COLORS[Math.round(Math.random() * (COLORS.length - 1))];
	this.dx = Math.round(Math.random() * MAX_DELTA) - MAX_DELTA / 2;
	this.dy = Math.round(Math.random() * MAX_DELTA) - MAX_DELTA / 2;
	this.r = Math.abs(Math.round(Math.random() * MAX_RADIUS) - MAX_RADIUS / 2);
	this.maxR = Math.round(Math.random() * MAX_RADIUS / 2) + 10;
	this.dr = Math.random() > 0.5 ? RADIUS_DELTA : -RADIUS_DELTA;
}

function draw(renderer) {
	ctx.fillStyle = "#2f4f4f";
	ctx.fillRect(0, 0, w, h);
	for (var i = 0; i < particlesNum; i++) {
		var p = particles[i];
		renderer.render(p);

		if (p.r <= MIN_RADIUS) {
			p.dr = RADIUS_DELTA;
		} else if (p.r > p.maxR) {
			p.dr = -RADIUS_DELTA;
		}

		p.r += p.dr;

		p.x += p.dx;
		p.y += p.dy;

		if (p.x > w) {
			p.x = w;
			p.dx = -p.dx;
		} else if (p.x < 0) {
			p.x = 0;
			p.dx = -p.dx;
		}

		if (p.y > h) {
			p.y = h;
			p.dy = -p.dy;
		} else if (p.y < 0) {
			p.y = 0;
			p.dy = -p.dy;
		}
	}

}

function drawEllipse(centerX, centerY, width, height, color) {
	ctx.beginPath();
	ctx.moveTo(centerX, centerY - height / 2);

	ctx.bezierCurveTo(
			centerX + width / 2, centerY - height / 2,
			centerX + width / 2, centerY + height / 2,
			centerX, centerY + height / 2);

	ctx.bezierCurveTo(
			centerX - width / 2, centerY + height / 2,
			centerX - width / 2, centerY - height / 2,
			centerX, centerY - height / 2);

	ctx.fillStyle = color;
	ctx.fill();
	ctx.closePath();
}

function drawArc(centerX, centerY, radius, color) {
	ctx.beginPath();
	ctx.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
	ctx.fillStyle = color;
	ctx.fill();
	ctx.closePath();
}

var lastLoop = new Date();
function drawFps() {
	var thisLoop = new Date();
	var fps = 1000 / (thisLoop - lastLoop);
	lastLoop = thisLoop;
	ctx.fillStyle = "#ffffff";
	ctx.font = "20pt Arial";
	ctx.fillText("fps:" + fps, 600, 600);
	// console.log("fps:"+fps);
}

function animate(renderer) {
	particlesNum = renderer.recommendedParticles;
	ctx.globalAlpha = 0.7;

	if (renderer.blur) {
		canvas.style.webkitFilter = 'blur(1.5px)';
	}

	window.requestAnimFrame = (function() {
		return window.requestAnimationFrame
				|| window.webkitRequestAnimationFrame
				|| window.mozRequestAnimationFrame || function(callback) {
					window.setTimeout(callback, 1000 / 60);
				};
	})();

	(function init() {
		for (var i = 0; i < particlesNum; i++) {
			particles.push(new Particle);
		}
	})();

	(function loop() {
		draw(renderer);
		drawFps();
		requestAnimFrame(loop);
	})();
}