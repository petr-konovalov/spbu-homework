package c1_s2.konovalov.task4_1;

public interface List<T> {
    /**
     * возвращает следущий элемент в списке
     * @return возвращает значения следущего элемента или null если весь список пройден
     * @throws ListIsEmptyException бросается в случае если список пуст
     */
    T getNext() throws ListIsEmptyException;

    /**
     * подсчитывает количество элементов в списке
     * @return возвращает количество элементов в списке
     */
    int size();

    /**
     * возвращает элемент по его позиции в списке
     * @param position позиция элемента
     * @return возвращает значение элемента стоящего в заданной позиции
     * @throws ListOutOfBoundException бросается в случае если позиция элемента
     * больше чем количество элементов в списке
     */
    T retrieve(int position) throws ListOutOfBoundException;

    /**
     * вставляет элемент в заданную позицию
     * @param position позиция которую будет занимать элемент после вставки
     * @param element значение элемента
     * @throws ListOutOfBoundException бросается в случае если позция элемента
     * больше чем количество элементов в списке
     */
    void insert(int position, T element) throws ListOutOfBoundException;

    /**
     * удаляет элемент стоящий на заданной позиции в списке
     * @param position позиция удаляемого элемента
     * @throws ListOutOfBoundException бросается в случае если позция элемента
     * больше чем количество элементов в списке
     */
    void remove(int position) throws ListOutOfBoundException;

    /** бросается при попытке получить следущий элемент из пустого списка*/
    class ListIsEmptyException extends Exception {

    }

    /** бросается при попытке добавить, удалить или получить элемент из несуществующего места в списке */
    class ListOutOfBoundException extends Exception {

    }
}
