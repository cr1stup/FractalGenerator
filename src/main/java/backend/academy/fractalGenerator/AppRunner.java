package backend.academy.fractalGenerator;

import backend.academy.fractalGenerator.cli.InputProcessor;
import backend.academy.fractalGenerator.cli.Printer;
import backend.academy.fractalGenerator.model.Parameters;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class AppRunner {

    private final InputProcessor input;
    private final Printer printer;

    public AppRunner(InputStream inputStream, PrintStream printStream) {
        input = new InputProcessor(inputStream, printStream);
        printer = new Printer(printStream);
    }

    public void run() {
        printer.printGreeting();
        FractalFlameGenerator generator = new FractalFlameGenerator();

        while (true) {
            try {
                Parameters parameters = input.inputParameters();
                printer.printWaitMessage();

                generator.generate(parameters);
                printer.printSuccessMessage(String.valueOf(parameters.path()));

                return;
            } catch (IOException | InterruptedException e) {
                printer.printExceptionMessage();
            }
        }
    }
}
