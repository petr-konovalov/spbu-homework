package c1_s2.konovalov.task4_1;

/** список неповторяющихся элементов */
public class UniqueList<T extends Comparable<T>> {
    private Node head = null;
    private Node current = null;

    private class Node {
        private T value;
        private Node next;

        Node(T initialValue, Node initialNext) {
            value = initialValue;
            next = initialNext;
        }
    }

    /**
     * возвращает следущий элемент в списке
     * @return возвращает значения следущего элемента или null если весь список пройден
     * @throws ListIsEmptyException бросается в случае если список пуст
     */
    public T getNext() throws ListIsEmptyException {
        if (isEmpty())
            throw new ListIsEmptyException();

        if (current == null)
            current = head;
        else
            current = current.next;

        if (current == null)
            return null;

        return current.value;
    }

    /**
     * подсчитывает количество элементов в списке
     * @return возвращает количество элементов в списке
     */
    public int size() {
        Node runningElement = head;
        int elementCount = 0;

        while (runningElement != null) {
            runningElement = runningElement.next;
            ++elementCount;
        }

        return elementCount;
    }

    /**
     * возвращает элемент по его позиции в списке
     * @param position позиция элемента
     * @return возвращает значение элемента стоящего в заданной позиции
     * @throws ListOutOfBoundException бросается в случае если позиция элемента
     * больше чем количество элементов в списке
     */
    public T retrieve(int position) throws ListOutOfBoundException {
        Node element = getElement(position);

        if (element == null)
            throw new ListOutOfBoundException();

        return element.value;
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
        if (!isEmpty() && (head.value.compareTo(element) == 0 || getPreviousElement(element).next != null))
            throw new ReAddValueException();

        if (position == 0)
            head = new Node(element, head);
        else {
            Node previousElement = getElement(position - 1);

            if (previousElement == null)
                throw new ListOutOfBoundException();

            previousElement.next = new Node(element, previousElement.next);
        }
    }

    /**
     * удаляет элемент стоящий на заданной позиции в списке
     * @param position позиция удаляемого элемента
     * @throws ListIsEmptyException бросается в случае если список пуст
     * @throws ListOutOfBoundException бросается в случае если позция элемента
     * больше чем количество элементов в списке
     */
    public void removeFromPosition(int position) throws ListIsEmptyException, ListOutOfBoundException {
        if (isEmpty())
            throw new ListIsEmptyException();

        if (position == 0)
            head = head.next;
        else {
            Node previousElement = getElement(position - 1);

            if (previousElement == null || previousElement.next == null)
                throw new ListOutOfBoundException();

            previousElement.next = previousElement.next.next;
        }
    }

    /**
     * удаляет элемент из списка с заданным значением
     * @param element значение удаляемого элемента
     * @throws ListIsEmptyException бросается в случае если список пуст
     * @throws ElementNotFoundException бросается в случае если в списке нет элемента с заданным значением
     */
    public void remove(T element) throws ListIsEmptyException, ElementNotFoundException {
        if (isEmpty())
            throw new ListIsEmptyException();

        if (head.value.compareTo(element) == 0)
            head = head.next;
        else {
            Node previousElement = getPreviousElement(element);

            if (previousElement.next == null)
                throw new ElementNotFoundException();

            previousElement.next = previousElement.next.next;
        }
    }

    private boolean isEmpty() {
        return head == null;
    }

    private Node getElement(int position) {
        Node element = head;

        while (position > 0 && element != null) {
            element = element.next;
            --position;
        }

        return element;
    }

    private Node getPreviousElement(T element) {
        Node previousNode = head;

        while (previousNode.next != null && previousNode.next.value.compareTo(element) != 0)
            previousNode = previousNode.next;

        return previousNode;
    }

    /** бросается при попытке получить следущий элемент или удалить элемент из пустого списка */
    public static class ListIsEmptyException extends Exception {

    }

    /** бросается при попытке добавить или удалить элемент из несуществующего места в списке */
    public static class ListOutOfBoundException extends Exception {

    }

    /** бросается при попытке добавить существующий элемент в список */
    public static class ReAddValueException extends Exception {

    }

    /** бросается при попытке удалить несуществующий элемент из списка */
    public static class ElementNotFoundException extends Exception {

    }
}
