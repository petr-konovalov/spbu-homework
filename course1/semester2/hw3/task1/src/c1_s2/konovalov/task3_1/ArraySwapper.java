package c1_s2.konovalov.task3_1;

abstract class ArraySwapper<T extends Comparable<T>> implements Sorter<T> {
    void swap(T[] array, int firstElement, int secondElement) {
        T temporaryElement = array[firstElement];
        array[firstElement] = array[secondElement];
        array[secondElement] = temporaryElement;
    }
}
