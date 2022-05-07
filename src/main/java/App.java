public class App {
    public static void main(String[] args) {
        var input = Input.get(args);
        var quicksorter = new Quicksorter<>(input);

        var start_time = System.nanoTime();
        var sortResult = quicksorter.sort();
        var stop_time = System.nanoTime();

        System.out.println(quicksorter.sort());
        System.out.println("Проверка сортировки:   " + (quicksorter.isSorted() ? "+" : "-"));
        System.out.println("Время сортировки:      " +
                (double)(stop_time - start_time) / 1_000_000_000D + " с.");
    }
}
