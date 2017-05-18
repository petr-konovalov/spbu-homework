package c1_s2.konovalov.task4_2;

/** список неповторяющихся элементов */
public class UniqueList<T extends Comparable<T>> {
    private Node head = null;

    private class Node {
        private T value;
        private Node next;

        Node(T initialValue, Node initialNext) {
            value = initialValue;
            next = initialNext;
        }
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
     * вставляет элемент в начало
     * @param element значение элемента
     * @throws ReAddValueException бросается если добавляемый элемент уже присутствует в списке
     */
    public void push(T element) throws ReAddValueException {
        if (!isEmpty() && (head.value.compareTo(element) == 0 || getPreviousElement(element).next != null))
            throw new ReAddValueException();

        head = new Node(element, head);
    }

    /**
     * возвращает и удаляет первый элемент в списке
     * @return возвращает первый элемент или null если список пуст
     */
    public T pop() {
        if (isEmpty())
            return null;

        T element = head.value;
        head = head.next;
        return element;
    }

    /**
     * возвращает элемент по его значению
     * @param element значение элемента
     * @return возвращает элемент
     * @throws ElementNotFoundException бросается если элемент не был найден
     */
    public T getElement(T element) throws ElementNotFoundException {
        Node searchElement = head;

        while (searchElement != null && searchElement.value.compareTo(element) != 0)
            searchElement = searchElement.next;

        if (searchElement == null)
            throw new ElementNotFoundException();

        return searchElement.value;
    }

    /**
     * удаляет элемент из списка с заданным значением
     * @param element значение удаляемого элемента
     * @throws ElementNotFoundException бросается в случае если в списке нет элемента с заданным значением
     */
    public void remove(T element) throws ElementNotFoundException {
        if (isEmpty())
            throw new ElementNotFoundException();

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

    private Node getPreviousElement(T element) {
        Node previousNode = head;

        while (previousNode.next != null && previousNode.next.value.compareTo(element) != 0)
            previousNode = previousNode.next;

        return previousNode;
    }

    /** бросается при попытке добавить существующий элемент в список */
    public static class ReAddValueException extends Exception {

    }

    /** бросается при попытке удалить несуществующий элемент из списка */
    public static class ElementNotFoundException extends Exception {

    }
}
