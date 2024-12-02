package backend.academy.fractalGenerator.model;

import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Pixel {
    private int red;
    private int green;
    private int blue;
    private int hitCount;
    private double normal;

    public void saturateHitCount(Color color) {
        if (hitCount == 0) {
            setRGB(color.getRed(), color.getGreen(), color.getBlue());
        } else {
            red = (red + color.getRed()) / 2;
            green = (green + color.getGreen()) / 2;
            blue = (blue + color.getBlue()) / 2;
        }

        hitCount++;
    }

    private void setRGB(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
    }
}
