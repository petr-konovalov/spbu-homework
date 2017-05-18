package c1_s2.konovalov.task3_1;

/** сортировщик */
public interface Sorter<T extends Comparable<T>> {
    /**
     * сортирует массив элементов
     * @param array сортируемый массив
     */
    void sort(T[] array);
}
