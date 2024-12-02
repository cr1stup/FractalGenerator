package backend.academy.fractalGenerator.saver;

import backend.academy.fractalGenerator.model.FractalImage;
import backend.academy.fractalGenerator.model.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageSaver {

    private final ImageFormat format;

    public void save(FractalImage image, Path path) throws IOException {
        BufferedImage renderedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                Color color = new Color(pixel.red(), pixel.green(), pixel.blue());
                renderedImage.setRGB(x, y, color.getRGB());
            }
        }

        ImageIO.write(renderedImage, format.formatName(), path.toFile());
    }
}
