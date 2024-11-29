package backend.academy.fractalGenerator.saver;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageFormat {

    PNG("png"),
    JPEG("jpg"),
    BMP("bmp");

    private final String formatName;
}
