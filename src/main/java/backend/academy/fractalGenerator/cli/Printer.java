package backend.academy.fractalGenerator.cli;

import java.io.PrintStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Printer {

    private final PrintStream printStream;

    public void printGreeting() {
        printStream.println("Welcome to the Fractal flame image creation app!");
    }

    public void printExceptionMessage() {
        printStream.println("Something went wrong, try again");
    }

    public void printSuccessMessage(String name) {
        printStream.println("Great! Your image is saved under the name: " + name);
    }

    public void printWaitMessage() {
        printStream.println("Wait...");
    }
}
