package c1_s2.konovalov.task2_2;

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
            throw new ListIsEmptyException("linked list is empty");

        if (!hasNext())
            current = head;
        else
            current = current.next;

        return current.value;
    }

    @Override
    public T getPrevious() throws ListIsEmptyException {
        if (isEmpty())
            throw new ListIsEmptyException("linked list is empty");

        if (!hasPrevious())
            current = null;

        Node searchNode = head;

        while (searchNode.next != current)
            searchNode = searchNode.next;
        current = searchNode;

        return searchNode.value;
    }

    @Override
    public T getFirst() throws ListIsEmptyException {
        if (isEmpty())
            throw new ListIsEmptyException("liked list is empty");

        current = head;
        return head.value;
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
        return current != head;
    }

    @Override
    public void insert(int position, T element) throws ListOutOfBoundException {
        if (position == 0)
            head.next = new Node(element, head);

        Node previousElement = getPreviousElement(position);

        if (previousElement == null || previousElement.next == null)
            throw new ListOutOfBoundException("out of the linked list");

        previousElement.next = new Node(element, previousElement.next);
    }

    @Override
    public void remove(int position) throws ListOutOfBoundException {
        if (position == 0)
            head = head.next;

        Node previousElement = getPreviousElement(position);

        if (previousElement == null || previousElement.next == null)
            throw new ListOutOfBoundException("out of the linked list");

        previousElement.next = previousElement.next.next;
    }

    private Node getPreviousElement(int position) {
        Node previousElement = head;

        while (position > 1 && previousElement != null) {
            previousElement = previousElement.next;
            --position;
        }

        return previousElement;
    }
}
