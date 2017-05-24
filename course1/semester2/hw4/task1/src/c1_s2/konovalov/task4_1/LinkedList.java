package c1_s2.konovalov.task4_1;

public class LinkedList<T> implements List<T> {
    private Node head = null;
    private Node current = null;

    private class Node {
        private T value;
        private Node next;

        public Node(T initialValue, Node initialNext) {
            value = initialValue;
            next = initialNext;
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
        if (position == 0)
            head = new Node(element, head);
        else {
            Node previousElement = getElement(position - 1);

            if (previousElement == null)
                throw new ListOutOfBoundException();

            previousElement.next = new Node(element, previousElement.next);
        }
    }

    @Override
    public void remove(int position) throws ListOutOfBoundException {
        if (head != null && position == 0)
            head = head.next;
        else {
            Node previousElement = getElement(position - 1);

            if (previousElement == null || previousElement.next == null)
                throw new ListOutOfBoundException();

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
}