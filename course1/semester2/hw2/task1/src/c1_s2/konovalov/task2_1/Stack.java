package c1_s2.konovalov.task2_1;

public interface Stack<T> {
    void push(T value);
    T pop() throws StackIsEmptyException;
    T top() throws StackIsEmptyException;
    boolean isEmpty();
}
