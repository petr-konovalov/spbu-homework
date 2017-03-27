package c1_s2.konovalov.task2_2;

public class DoublyLinkedList<T> implements List<T> {
    private Node head = null;
    private Node current = null;

    private class Node {
        private T value;
        private Node next;
        private Node previous;

        private Node(T initialValue, Node initialNext, Node initialPrevious) {
            value = initialValue;
            next = initialNext;
            previous = initialPrevious;
        }
    }

    @Override
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

    @Override
    public int size() {
        Node runningElement = head;
        int elementCount = 0;

        while (runningElement != null) {
            runningElement = runningElement.next;
            ++elementCount;
        }

        return elementCount;
    }

    @Override
    public T retrieve(int position) throws ListOutOfBoundException {
        Node element = getElement(position);

        if (element == null)
            throw new ListOutOfBoundException();

        return element.value;
    }

    @Override
    public void insert(int position, T element) throws ListOutOfBoundException {
        if (position == 0) {
            if (head == null)
                head = new Node(element, null, null);
            else {
                head.previous = new Node(element, head, null);
                head = head.previous;
            }
        }
        else {
            Node previousElement = getElement(position - 1);

            if (previousElement == null)
                throw new ListOutOfBoundException();

            previousElement.next = new Node(element, previousElement.next, previousElement);
            if (previousElement.next.next != null)
                previousElement.next.next.previous = previousElement.next;
        }
    }

    @Override
    public void remove(int position) throws ListOutOfBoundException {
        if (head != null && position == 0) {
            head = head.next;
            if (head != null)
                head.previous = null;
        }
        else {
            Node previousElement = getElement(position - 1);

            if (previousElement == null || previousElement.next == null)
                throw new ListOutOfBoundException();

            previousElement.next = previousElement.next.next;
            if (previousElement.next != null)
                previousElement.next.previous = previousElement;
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
}
