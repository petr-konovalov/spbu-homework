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
            throw new ListIsEmptyException();

        if (!hasNext())
            current = head;
        else
            current = current.next;

        if (current == null)
            return null;

        return current.value;
    }

    @Override
    public T getPrevious() throws ListIsEmptyException {
        if (isEmpty())
            throw new ListIsEmptyException();

        Node searchNode = head;

        while (searchNode != null && searchNode.next != current)
            searchNode = searchNode.next;
        current = searchNode;

        if (current == null)
            return null;
        return searchNode.value;
    }

    @Override
    public T getFirst() throws ListIsEmptyException {
        if (isEmpty())
            throw new ListIsEmptyException();

        current = head;
        return head.value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public boolean hasPrevious() {
        return current != null;
    }

    @Override
    public void insert(int position, T element) throws ListOutOfBoundException {
        if (position == 0)
            head = new Node(element, head);
        else {
            Node previousElement = getPreviousElement(position);

            if (previousElement == null)
                throw new ListOutOfBoundException();

            previousElement.next = new Node(element, previousElement.next);
        }
    }

    @Override
    public void remove(int position) throws ListOutOfBoundException {
        if (position == 0)
            head = head.next;
        else {
            Node previousElement = getPreviousElement(position);

            if (previousElement == null || previousElement.next == null)
                throw new ListOutOfBoundException();

            previousElement.next = previousElement.next.next;
        }
    }

    @Override
    public void insertCurrentPosition(T element) {
        if (!hasPrevious() || current == head)
        {
            head = new Node(element, head);
            current = head;
        } else {
            Node previousElement = getPreviousElement(current);

            previousElement.next = new Node(element, current);
            current = previousElement.next;
        }
    }

    @Override
    public void removeCurrentPosition() throws ListIsEmptyException {
        if (!hasPrevious() || current == head)
        {
            head = head.next;
            current = head;
        } else {
            if (isEmpty())
                throw new ListIsEmptyException();

            Node previousElement = getPreviousElement(current);

            current = current.next;
            previousElement.next = current;
        }
    }

    private Node getPreviousElement(int position) {
        Node previousElement = head;

        while (position > 1 && previousElement != null) {
            previousElement = previousElement.next;
            --position;
        }

        return previousElement;
    }

    private Node getPreviousElement(Node currentElement) {
        Node previousElement = head;

        while (previousElement.next != currentElement)
            previousElement = previousElement.next;

        return previousElement;
    }
}
