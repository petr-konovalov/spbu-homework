package c1_s2.konovalov.task2_1;

public interface Stack<T> {
    void push(T value);
    T pop() throws StackIsEmptyException;
    T top() throws StackIsEmptyException;
    void clear();
    boolean isEmpty();

    class StackIsEmptyException extends Exception {
        StackIsEmptyException (String message) {
            super(message);
        }
    }
}
