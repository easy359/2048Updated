package game;

public class GameLoop implements Runnable {

	private Main main;
	private Thread thread;
	private boolean isRunning;

	private int fps;

	public GameLoop(int fps) {
		this.fps = fps;
	}

	public synchronized void start() {
		if (thread == null) {
			thread = new Thread(this);
		}
		if (isRunning) {
			return;
		} else {
			isRunning = true;
			thread.start();
		}
	}

	public synchronized void stop() {
		if (thread == null) {
			return;
		}

		if (isRunning == false) {
			return;
		} else {
			isRunning = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void run() {
		final double ns = 1000000000.0;
		final double wait = 1.0 / fps;

		long start = System.nanoTime();
		long last = start;
		long now = start;

		while (isRunning) {
			now = System.nanoTime();
			if (((now / ns) - (last / ns)) > wait) {
				update();
				render();
				last = now;
			}
			if ((now / ns) - (start / ns) > 1) {
				start = now;
			}
		}
	}

	private void update() {
		main.update();
	}

	private void render() {
		main.render();
	}

	public void addMain(Main main) {
		this.main = main;
	}

	public static void main(String[] args) {
	    GameLoop gl = new GameLoop(20);
		gl.addMain(new Main(4, 4));
		gl.start();
	}
}
