package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpritesSheet {
	private final int width;
	private final int height;
	public final int[] pixels;

	public SpritesSheet(final String route, final int width, final int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];

		BufferedImage image;
		try {
			image = ImageIO.read(SpritesSheet.class.getResource(route));

			image.getRGB(0, 0, width, height, pixels, 0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getWidth() {
		return width;
	}
}
