package backend.academy;

import backend.academy.fractalGenerator.AppRunner;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        AppRunner appRunner = new AppRunner(System.in, System.out);
        appRunner.run();
    }
}
