package backend.academy.fractalGenerator.cli;

import backend.academy.fractalGenerator.exceptions.InvalidInputException;
import backend.academy.fractalGenerator.renderer.MultiThreadRenderer;
import backend.academy.fractalGenerator.renderer.OneThreadRenderer;
import backend.academy.fractalGenerator.renderer.Renderer;
import backend.academy.fractalGenerator.transformations.Transformation;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InputTools {

    private final Scanner scanner;
    private final PrintStream printStream;
    private static final String TRY_AGAIN = "%nInvalid input! Try again%n";
    private static final String CHECK_RANGE = "%nInvalid! Check the input range!%n";

    public int input(int min, int max, String input) {
        String message = String.format("Enter %s (%d - %d): ", input, min, max);
        printStream.print(message);
        return getInput(min, max, message);
    }

    private int getInput(int min, int max, String message) {
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            int inputNum;

            try {
                inputNum = Integer.parseInt(input);
                if (inputNum > max || inputNum < min) {
                    throw new InvalidInputException(CHECK_RANGE);
                }
            } catch (InvalidInputException e) {
                printStream.format(e.getMessage());
                printStream.print(message);
                continue;
            } catch (NumberFormatException e) {
                printStream.format(TRY_AGAIN);
                printStream.print(message);
                continue;
            }

            return inputNum;
        }
    }

    public Renderer inputRenderer(
        int affineCount,
        int samples,
        int iterPerSample,
        int symmetry,
        List<Transformation> variations
    ) {
        String intro = """

            Choose your renderer:
            1. Single-threaded
            2. Multithreaded
            """;

        printStream.println(intro);
        int choice = input(1, 2, "renderer");

        if (choice == 1) {
            return new OneThreadRenderer(affineCount, samples, iterPerSample, symmetry, variations);
        } else {
            return new MultiThreadRenderer(affineCount, samples, iterPerSample, symmetry, variations);
        }
    }

    public List<Transformation> inputTransformations(List<Transformation> allTransformations) {
        List<Transformation> inputTransformations = new ArrayList<>();
        int min = 1;
        int max = allTransformations.size();

        printStream.format("%nEnter the desired transformations separated by a space:%n");

        for (int i = 0; i < allTransformations.size(); i++) {
            printStream.format("%d. %s%n", i + 1, allTransformations.get(i).getName());
        }

        String message = "Enter: ";
        printStream.format("%n" + message);

        while (true) {
            try {
                String[] inputNums = scanner.nextLine().split(" ");
                validateInputTrans(inputNums, min, max);
                for (String num : inputNums) {
                    inputTransformations.add(allTransformations.get(Integer.parseInt(num) - 1));
                }
                return inputTransformations;

            } catch (InvalidInputException e) {
                printStream.format(e.getMessage());
                printStream.print(message);
            } catch (NumberFormatException e) {
                printStream.format(TRY_AGAIN);
                printStream.print(message);
            }
        }
    }

    private void validateInputTrans(String[] input, int min, int max) throws InvalidInputException {
        for (String s : input) {
            if (Integer.parseInt(s) < min || Integer.parseInt(s) > max) {
                throw new InvalidInputException(CHECK_RANGE);
            }
        }
    }
}
