public class App {
    public static void main(String[] args) {
        var input = Input.get(args);
        var quicksorterAvg = new Quicksorter<>(input);

        var startTimeAvg = System.nanoTime();
        var sortResultsAvg = quicksorterAvg.sort();
        var stopTimeAvg = System.nanoTime();

        System.out.println("Средний случай:");
        System.out.println(sortResultsAvg);
        System.out.println("Проверка сортировки:   " + (quicksorterAvg.isSorted() ? "+" : "-"));
        System.out.printf( "Время сортировки:      %f с.%n",
                (stopTimeAvg - startTimeAvg) / 1_000_000_000D);

        var quicksorterBest = new Quicksorter<>(sortResultsAvg.nums());

        var startTimeBest = System.nanoTime();
        var sortResultsBest = quicksorterBest.sort();
        var stopTimeBest = System.nanoTime();

        System.out.println("\nЛучший случай:");
        System.out.println(sortResultsBest);
        System.out.println("Проверка сортировки:   " + (quicksorterBest.isSorted() ? "+" : "-"));
        System.out.printf( "Время сортировки:      %f с.%n",
                (stopTimeBest - startTimeBest) / 1_000_000_000D);
    }
}
