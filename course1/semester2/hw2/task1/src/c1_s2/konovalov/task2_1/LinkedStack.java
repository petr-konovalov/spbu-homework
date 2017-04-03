package c1_s2.konovalov.task2_1;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> head = null;

    @Override
    public void push(T value) {
        head = new Node<T>(value, head);
    }

    @Override
    public T pop() throws StackIsEmptyException {
        if (isEmpty())
            throw new StackIsEmptyException("method pop can not be evoked");

        T value = head.value;
        head = head.next;
        return value;
    }

    @Override
    public T top() throws StackIsEmptyException {
        if (isEmpty())
            throw new StackIsEmptyException("method top can not be evoked");

        return head.value;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    private class Node<T> {
        private Node(T initialValue, Node<T> initialNext) {
            value = initialValue;
            next = initialNext;
        }

        private T value;
        private Node<T> next;
    }
}
