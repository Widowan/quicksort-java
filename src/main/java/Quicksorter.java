import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quicksorter<T extends Comparable<? super T>> {
    private List<T> nums;
    private long swapCnt  = 0;
    private long cmpCnt   = 0;
    private long maxDepth = 0;

    public Quicksorter(List<T> nums) {
        this.nums = nums;
    }

    public SortResults<T> sort() {
        if (nums.size() == 0)
            return new SortResults<>(nums, swapCnt, cmpCnt, maxDepth);
        nums = rawSort(nums, 0, nums.size() - 1);
        return new SortResults<>(nums, swapCnt, cmpCnt, maxDepth);
    }

    private List<T> rawSort(List<T> list, int from, int to) {
        maxDepth += 1;
        if (from >= to) return list;
        var pivotIdx = partition(list, from, to);
        rawSort(list, from, pivotIdx);
        rawSort(list, pivotIdx + 1, to);
        return nums;
    }

    private int partition(List<T> list, int from, int to) {
        var pivot = median3(list, from, to);
        var i = from;
        var j = to;

        while (true) {
            while (list.get(i).compareTo(pivot) < 0) { i++; cmpCnt++; }
            while (list.get(j).compareTo(pivot) > 0) { j--; cmpCnt++; }

            // For false conditions above,
            // comparison still been made
            cmpCnt += 2;

            if (i >= j) return j;
            Collections.swap(list, i++, j--);
            swapCnt += 1;
        }
    }

    private List<T> rawPrimitiveSort(List<T> list) {
        if (list.size() == 0) return list;
        var pivot = list.remove(list.size()/2);

        var leftArr = list.stream()
                .filter(x -> x.compareTo(pivot) <= 0)
                .collect(Collectors.toList());
        var rightArr = list.stream()
                .filter(x -> x.compareTo(pivot) >= 0)
                .collect(Collectors.toList());

        return concatLists(
                rawPrimitiveSort(leftArr),
                List.of(pivot),
                rawPrimitiveSort(rightArr));
    }

    @SafeVarargs
    private List<T> concatLists(List<T>... lists) {
        var resultList = new ArrayList<T>();
        for (var list : lists)
            resultList.addAll(list);
        return resultList;
    }

    private T median3(List<T> list, int from, int to) {
        return Stream.of(
                list.get(from),
                list.get((from+to)/2),
                list.get(to))
            .sorted()
            .toList()
            .get(1);
    }

    public boolean isSorted() {
        boolean sorted = true;
        var last = nums.get(0);
        for (var curr : nums.subList(1, nums.size())) {
            if (curr.compareTo(last) < 0) return false;
            last = curr;
        }
        return sorted;
    }

    public record SortResults<T>(
            List<T> nums,
            long swapCnt,
            long cmpCnt,
            long maxDepth)
    {
        @Override
        public String toString() {
            return String.format("""
                    ????????????: %s
                    ??????-???? ??????????????????:      %d
                    ??????-???? ????????????????????????:   %d
                    ????????. ?????????????? ??????????????: %d""",
                    (nums.size() > 10 ? "????????????" : nums.toString()),
                    cmpCnt, swapCnt, maxDepth);
        }
    }
}
