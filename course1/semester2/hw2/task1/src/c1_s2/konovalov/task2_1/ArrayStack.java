package c1_s2.konovalov.task2_1;

public class ArrayStack<T> implements Stack<T> {
    int head = 0;
    int size = 32;
    T[] elements = (T[])new Object[size];

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
            throw new StackIsEmptyException("error: stack is empty");
        --head;
        return elements[head];
    }

    @Override
    public T top() throws StackIsEmptyException {
        if (isEmpty())
            throw new StackIsEmptyException("error: stack is empty");
        return elements[head - 1];
    }

    @Override
    public boolean isEmpty() {
        return head == 0;
    }

    private void extendSize() {
        T[] templateArray = (T[])new Object[size * 2];
        for (int i = 0; i < size; ++i)
            templateArray[i] = elements[i];
        elements = templateArray;
        size *= 2;
    }
}
