package graphics;

public class Sprites {
	private final int size;

	private int x;
	private int y;

	public int[] pixels;
	private final SpritesSheet sheet;

	public Sprites(final int size, final int column, final int row, final SpritesSheet sheet) {
		this.size = size;

		pixels = new int[size * size];

		this.x = column * size;
		this.y = row * size;
		this.sheet = sheet;

		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getWidth()];
			}
		}
	}

}
