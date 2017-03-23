package c1_s2.konovalov.task2_2;

public class DoublyLinkedList<T> implements List<T> {
    private Node head = null;
    private Node current = null;

    private class Node {
        private T value;
        private Node next;
        private Node previous;

        public Node(T initialValue, Node initialNext, Node initialPrevious) {
            value = initialValue;
            next = initialNext;
            previous = initialPrevious;
        }
    }

    @Override
    public T getNext() throws ListIsEmptyException {
        if (isEmpty())
            throw new ListIsEmptyException("doubly linked list is empty");

        if (!hasNext())
            current = head;
        else
            current = current.next;

        return current.value;
    }

    @Override
    public T getPrevious() throws ListIsEmptyException {
        if (isEmpty())
            throw new ListIsEmptyException("doubly linked list is empty");

        if (!hasPrevious())
            current = getEnd();
        else
            current = current.previous;

        return current.value;
    }

    @Override
    public T getFirst() throws ListIsEmptyException {
        if (isEmpty())
            throw new ListIsEmptyException("doubly linked list is empty");

        current = head;
        return current.value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean hasNext() {
        return current != null && current.next != null;
    }

    @Override
    public boolean hasPrevious() {
        return current != null && current.previous != null;
    }

    @Override
    public void insert(int position, T element) throws ListOutOfBoundException {
        if (position == 0)
            head = new Node(element, head, null);

        Node currentElement = getElement(position);
        if (currentElement == null)
            throw new ListOutOfBoundException("out of the doubly linked list");

        currentElement.previous.next = new Node(element, currentElement.next, currentElement.previous);
    }

    @Override
    public void remove(int position) throws ListOutOfBoundException {
        if (position == 0)
            head = head.next;

        Node currentElement = getElement(position);
        if (currentElement == null)
            throw new ListOutOfBoundException("out of the doubly linked list");

        currentElement.previous.next = currentElement.next;
    }

    private Node getEnd() {
        Node searchNode = head;
        while (searchNode.next != null)
            searchNode = searchNode.next;

        return searchNode;
    }

    private Node getElement(int position) {
        Node previousElement = head;

        while (position > 0 && previousElement != null) {
            previousElement = previousElement.next;
            --position;
        }

        return previousElement;
    }
}
