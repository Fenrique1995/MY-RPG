package Game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

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

	/* ///////////// */
	private static JFrame window;
	private static Thread thread;

	private Game() {
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

	@Override
	public void run() {
		while (onTheRun) {

		}

	}

}
