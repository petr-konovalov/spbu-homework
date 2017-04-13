package c1_s2.konovalov.task3_1;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {
    private ArraySwapper<T> swapper = new ArraySwapper<T>();
    @Override
    public void sort(T[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(T[] array, int leftBound, int rightBound) {
        T middleElement = array[(leftBound + rightBound) / 2];
        int i = leftBound;
        int j = rightBound;

        while (i < j) {
            while (middleElement.compareTo(array[i]) > 0)
                ++i;
            while (middleElement.compareTo(array[j]) < 0)
                --j;
            if (i <= j) {
                swapper.swap(array, i, j);
                ++i;
                --j;
            }
        }

        if (i < rightBound)
            sort(array, i, rightBound);
        if (j > leftBound)
            sort(array, leftBound, j);
    }
}
