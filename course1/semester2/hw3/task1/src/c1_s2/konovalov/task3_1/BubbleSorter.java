package c1_s2.konovalov.task3_1;

public class BubbleSorter<T extends Comparable<T>> extends ArraySwapper<T> {
    @Override
    public void sort(T[] array) {
        boolean sortingFinished = false;
        while (!sortingFinished){
            sortingFinished = true;
            for (int j = 1; j < array.length; ++j) {
                if (array[j - 1].compareTo(array[j]) > 0) {
                    swap(array, j - 1, j);
                    sortingFinished = false;
                }
            }
        }
    }
}
