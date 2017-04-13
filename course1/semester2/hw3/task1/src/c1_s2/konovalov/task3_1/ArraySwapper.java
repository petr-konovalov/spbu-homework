package c1_s2.konovalov.task3_1;

class ArraySwapper<T> {
    void swap(T[] array, int firstElement, int secondElement) {
        T temporaryElement = array[firstElement];
        array[firstElement] = array[secondElement];
        array[secondElement] = temporaryElement;
    }
}
