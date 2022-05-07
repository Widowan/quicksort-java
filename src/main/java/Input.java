import java.util.*;
import java.util.stream.Collectors;

public class Input {
    enum InputMethod {
        stdio,
        random,
    }

    public static List<Integer> get(String[] args) {
        // Regex for -h, -help, --h, --help
        if (args.length == 0 || args[0].matches("(-)?-h(elp)?")) {
            System.out.println(
                    "Введите входные числа либо аргумент --random n, где n - кол-во чисел;");
            System.exit(1);
        }

        InputMethod inputMethod;
        var	argsList = Arrays.asList(args);
        int idx;
        int len = 0;
        // Deciding whether input is random or static
        if ((idx = argsList.indexOf("--random")) != -1) {
            inputMethod = InputMethod.random;

            try {
                len = Integer.parseInt(argsList.get(idx+1));
            } catch (Exception e) {
                System.out.println("Введите --random n");
                System.exit(1);
            }

        } else inputMethod = InputMethod.stdio;

        return (inputMethod == InputMethod.random)
                ? getRandomInput(len)
                : getStdioInput(argsList);
    }

    private static List<Integer> getRandomInput(int len) {
        return new Random()
                .ints(len, 1, 10000)
                .boxed()
                .collect(Collectors.toList());
    }

    private static List<Integer> getStdioInput(List<String> args) {
        return args.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}
