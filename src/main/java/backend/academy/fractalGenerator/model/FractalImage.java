package backend.academy.fractalGenerator.model;

public record FractalImage(Pixel[] data, int width, int height) {

    public static FractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];

        for (int i = 0; i < height * width; i++) {
            data[i] = new Pixel(0, 0, 0, 0);
        }

        return new FractalImage(data, width, height);
    }
}
