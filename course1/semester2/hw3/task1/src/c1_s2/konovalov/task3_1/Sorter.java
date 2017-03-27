package c1_s2.konovalov.task3_1;

public interface Sorter<T extends Comparable<T>> {
    void sort(T[] array);
}
