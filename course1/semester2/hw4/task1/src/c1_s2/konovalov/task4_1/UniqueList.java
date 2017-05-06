package c1_s2.konovalov.task4_1;

/** список неповторяющихся элементов */
public class UniqueList<T> {
    private LinkedList<T> elements = new LinkedList<>();

    /**
     * возвращает следущий элемент в списке
     * @return возвращает значения следущего элемента или null если весь список пройден
     * @throws ListIsEmptyException бросается в случае если список пуст
     */
    public T getNext() throws ListIsEmptyException {
        try {
            return elements.getNext();
        } catch(LinkedList.ListIsEmptyException e) {
            throw new ListIsEmptyException();
        }
    }

    /**
     * подсчитывает количество элементов в списке
     * @return возвращает количество элементов в списке
     */
    public int size() {
        return elements.size();
    }

    /**
     * возвращает элемент по его позиции в списке
     * @param position позиция элемента
     * @return возвращает значение элемента стоящего в заданной позиции
     * @throws ListOutOfBoundException бросается в случае если позиция элемента
     * больше чем количество элементов в списке
     */
    public T retrieve(int position) throws ListOutOfBoundException {
        try {
            return elements.retrieve(position);
        } catch (List.ListOutOfBoundException e) {
            throw new ListOutOfBoundException();
        }
    }

    /**
     * возвращает позицию элемента в списке
     * @param element значение элемента
     * @return возвращает позицию
     * @throws ElementNotFoundException бросается в случае если элемент не был найден
     */
    public int locate(T element) throws ElementNotFoundException {
        int position = searchElement(element);

        if (position == -1)
            throw new ElementNotFoundException();

        return position;
    }

    /**
     * вставляет элемент в заданную позицию
     * @param position позиция которую будет занимать элемент после вставки
     * @param element значение элемента
     * @throws ListOutOfBoundException бросается в случае если позция элемента
     * больше чем количество элементов в списке
     * @throws ReAddValueException бросается если добавляемый элемент уже присутствует в списке
     */
    public void insert(int position, T element) throws ListOutOfBoundException, ReAddValueException {
        if (searchElement(element) != -1)
            throw new ReAddValueException("the value " + element + " already added");

        try {
            elements.insert(position, element);
        } catch (List.ListOutOfBoundException e) {
            throw new ListOutOfBoundException();
        }
    }

    /**
     * удаляет элемент стоящий на заданной позиции в списке
     * @param position позиция удаляемого элемента
     * @throws ListOutOfBoundException бросается в случае если позция элемента
     * больше чем количество элементов в списке
     */
    public void removeFromPosition(int position) throws ListOutOfBoundException {
        try {
            elements.remove(position);
        } catch (List.ListOutOfBoundException e) {
            throw new ListOutOfBoundException();
        }
    }

    /**
     * удаляет элемент из списка с заданным значением
     * @param element значение удаляемого элемента
     * @throws ElementNotFoundException бросается в случае если в списке нет элемента с заданным значением
     */
    public void remove(T element) throws ElementNotFoundException {
        int position = searchElement(element);

        if (position == -1)
            throw new ElementNotFoundException();

        try {
            elements.remove(position);
        } catch (List.ListOutOfBoundException e) {

        }
    }

    private int searchElement(T element) {
        int position = 0;
        boolean isSearch = false;

        try {
            T runningElement = elements.getNext();

            while (runningElement != null) {
                if (element.equals(runningElement))
                    isSearch = true;
                if (!isSearch)
                    ++position;
                runningElement = elements.getNext();
            }
        } catch(List.ListIsEmptyException e) {
            isSearch = false;
        }

        return isSearch ? position : -1;
    }

    /** бросается при попытке получить следущий элемент из пустого списка */
    public static class ListIsEmptyException extends Exception {

    }

    /** бросается при попытке добавить или удалить элемент из несуществующего места в списке */
    public static class ListOutOfBoundException extends Exception {

    }

    /** бросается при попытке добавить существующий элемент в список */
    public static class ReAddValueException extends Exception {
        ReAddValueException(String message) {
            super(message);
        }
    }

    /** бросается при попытке найти несуществующий элемент в списке */
    public static class ElementNotFoundException extends Exception {

    }
}
