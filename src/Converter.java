import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Converter {

	public static void convertToCSV(String inputImageName, String outputFileName) throws IOException {

		BufferedImage image = ImageIO.read(new File(inputImageName)); // load the image
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFileName)));

		for (int y = 0; y < image.getHeight(); y++) { // looping left to right top to bottom

			StringBuilder red = new StringBuilder(); // all red values per row in one string
			StringBuilder green = new StringBuilder(); // string builders to increase speed
			StringBuilder blue = new StringBuilder();

			for (int x = 0; x < image.getWidth(); x++) {

				int clr = image.getRGB(x, y); // get rgb info of every pixel

				red.append((clr & 0x00ff0000) >> 16); // isolating red component of rgb
				red.append(';'); // semi colon seperator for csv format

				green.append((clr & 0x0000ff00) >> 8);
				green.append(';');

				blue.append(clr & 0x000000ff);
				blue.append(';');
			}
			red.append('\n'); // putting everthing on seperate lines
			green.append('\n');
			blue.append('\n');

			writer.write(red.toString()); // writing the color values to output file
			writer.write(green.toString());
			writer.write(blue.toString());
		}
		writer.close();
	}
}
