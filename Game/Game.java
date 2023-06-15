package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import controls.Keyboard;

/*The implements Runnable allows to use threads and the run to verify*/
public class Game extends Canvas implements Runnable {

	/*
	 * ///////////////////////////// este es un identificador de de serie, esto
	 * sirve si un dia estamos guardando nuestra clase y la volvemos a utilizar en
	 * un futuro y la clase a cambiado, asi java sabra si estamos en la misma clase
	 * y a sido modificada o si es otra
	 */
	/*
	 * Thi is a ID wich allows Java to identificate if the class is the same,
	 * modificated or if it is other class
	 */
	private static final long serialVersionUID = 1L;
	/* ///////////////////////////// */
	/* ///////////// */
	/* aca determine el alto y ancho de la ventana. Tambien puse el nombre */
	/* Here i determinate the height and width of the window. Also the name */
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	/* volatile prevents the variable onTheRun from getting corrupt */
	private static volatile boolean onTheRun = false;

	private static final String NAME = "Game";

	private static int ups = 0;
	private static int fps = 0;

	/* ///////////// */
	private static JFrame window;
	private static Thread thread;
	private static Keyboard keys;

	private Game() {

		keys = new Keyboard();
		addKeyListener(keys);
		/* //////////////////////////////////////////////////////////////// */
		/*
		 * opens the window with the designated width and height and prevents the user
		 * from alter the properties of the window
		 */
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		window = new JFrame(NAME);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLayout(new BorderLayout());
		window.add(this, BorderLayout.CENTER);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		/* //////////////////////////////////////////////////////////////// */
	}

	public static void main(String[] args) {
		/* //////////////////////////////////////////////////////////////// */
		/* Starts the game */
		Game game = new Game();
		game.start();
		/* //////////////////////////////////////////////////////////////// */
	}

	/*
	 * by adding synchronized, it makes sure both methods cant alter the same
	 * variable at the same time
	 */
	private synchronized void start() {
		onTheRun = true;

		thread = new Thread(this, "Graphics");
		thread.start();
	}

	private synchronized void stop() {
		onTheRun = false;

		try {
			/* join makes sure to stop the thread when this finish */
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			/* the catch will print the error (Exception in Java) */
		}
	}

	private void update() {
		keys.update();

		/*
		 * this if is here to verify if the keys works///// if (keys.up) {
		 * System.out.println("it works up"); } if (keys.down) {
		 * System.out.println("it works down"); } if (keys.left) {
		 * System.out.println("it works left"); } if (keys.right) {
		 * System.out.println("it works right"); }
		 */
		/* here updates the variables of the game */
		ups++;

	}

	private void look() {
		/* here are all the methods need for the draw of the graphics */
		fps++;
	}

	@Override
	public void run() {
		/* this method relies on the microprocessor */
		/* System.nanoTime(); */
		final int NS_PER_SECOND = 1000000000;
		final byte UPS_OBJECTIVE = 60;
		final double NS_PER_UPDATES = NS_PER_SECOND / UPS_OBJECTIVE;

		long referenceUpdate = System.nanoTime();
		long referenceCounter = System.nanoTime();

		double timeTranscurred;
		double delta = 0;

		requestFocus();

		while (onTheRun) {
			final long startLoop = System.nanoTime();

			timeTranscurred = startLoop - referenceUpdate;
			referenceUpdate = startLoop;

			delta += timeTranscurred / NS_PER_UPDATES;

			while (delta >= 1) {
				update();
				delta--;
			}

			look();

			if (System.nanoTime() - referenceCounter > NS_PER_SECOND) {
				window.setTitle(NAME + " || UPS: " + ups + " || FPS: " + fps);
				ups = 0;
				fps = 0;
				referenceCounter = System.nanoTime();
			}
		}

	}

}
