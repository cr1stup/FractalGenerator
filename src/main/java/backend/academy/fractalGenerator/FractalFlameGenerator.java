package backend.academy.fractalGenerator;

import backend.academy.fractalGenerator.model.FractalImage;
import backend.academy.fractalGenerator.model.Parameters;
import backend.academy.fractalGenerator.processor.ImageProcessor;
import backend.academy.fractalGenerator.saver.ImageSaver;
import java.io.IOException;

public class FractalFlameGenerator {
    public void generate(Parameters p) throws IOException, InterruptedException {
        FractalImage image = p.renderer().render(p.width(), p.height(), p.area());

        for (ImageProcessor processor : p.processors()) {
            processor.process(image);
        }

        ImageSaver saver = new ImageSaver(p.format());
        saver.save(image, p.path());
    }
}
