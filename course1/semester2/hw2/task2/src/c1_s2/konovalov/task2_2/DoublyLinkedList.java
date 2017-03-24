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

        if (!hasPrevious())
            current = getEnd();
        else
            current = current.previous;

        if (current == null)
            return null;

        return current.value;
    }

    @Override
    public T getFirst() throws ListIsEmptyException {
        if (isEmpty())
            throw new ListIsEmptyException();

        current = head;
        return current.value;
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
        if (position == 0) {
            if (head == null)
                head = new Node(element, head, null);
            else {
                head.previous = new Node(element, head, null);
                head = head.previous;
            }
        }
        else {
            Node previousElement = getPreviousElement(position);

            if (previousElement == null)
                throw new ListOutOfBoundException();

            previousElement.next = new Node(element, previousElement.next, previousElement);
            if (previousElement.next.next != null)
                previousElement.next.next.previous = previousElement.next;
        }
    }

    @Override
    public void remove(int position) throws ListOutOfBoundException {
        if (position == 0) {
            head = head.next;
            if (head != null)
                head.previous = null;
        }
        else {
            Node previousElement = getPreviousElement(position);

            if (previousElement == null || previousElement.next == null)
                throw new ListOutOfBoundException();

            previousElement.next = previousElement.next.next;
            if (previousElement.next != null)
                previousElement.next.previous = previousElement;
        }
    }

    @Override
    public void insertCurrentPosition(T element) {
        if (hasPrevious() && current != head)
        {
            current.previous = new Node(element, current, current.previous);
            if (current.previous.previous != null)
                current.previous.previous.next = current.previous;
            current = current.previous;
        }
        else {
            if (head == null)
                head = new Node(element, head, null);
            else {
                head. previous = new Node(element, head, null);
                head = head.previous;
            }
            current = head;
        }
    }

    @Override
    public void removeCurrentPosition() throws ListIsEmptyException {
        if (hasPrevious() && current != head) {
            current = current.previous;
            current.next = current.next.next;
            if (current.next != null)
                current.next.previous = current;
        }
        else {
            if (isEmpty())
                throw new ListIsEmptyException();

            head = head.next;
            if (head != null)
                head.previous = null;
            current = head;
        }
    }

    private Node getEnd() {
        Node searchNode = head;
        while (searchNode.next != null)
            searchNode = searchNode.next;

        return searchNode;
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
