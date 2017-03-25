package c1_s2.konovalov.task2_1;

public class ArrayStack<T> implements Stack<T> {
    private int head = 0;
    private int size = 32;
    private T[] elements = (T[])new Object[size];

    @Override
    public void push(T value) {
        elements[head] = value;
        ++head;

        if (head == size)
            extendSize();
    }

    @Override
    public T pop() throws StackIsEmptyException {
        if (isEmpty())
            throw new StackIsEmptyException("method pop can not be evoked");

        --head;
        return elements[head];
    }

    @Override
    public T top() throws StackIsEmptyException {
        if (isEmpty())
            throw new StackIsEmptyException("method top can not be evoked");

        return elements[head - 1];
    }

    @Override
    public void clear() {
        head = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == 0;
    }

    private void extendSize() {
        T[] templateArray = (T[])new Object[size * 2];

        System.arraycopy(elements, 0, templateArray, 0, size);
        elements = templateArray;
        size *= 2;
    }
}
