package c1_s2.konovalov.task2_2;

public interface List<T> {
    T getNext() throws ListIsEmptyException;
    T getPrevious() throws ListIsEmptyException;
    T getFirst() throws ListIsEmptyException;
    boolean isEmpty();
    boolean hasNext();
    boolean hasPrevious();
    void insert(int position, T element) throws ListOutOfBoundException;
    void remove(int position) throws ListOutOfBoundException;
    void insertCurrentPosition(T element);
    void removeCurrentPosition() throws ListIsEmptyException;

    class ListIsEmptyException extends Exception {
        ListIsEmptyException() {

        }
    }

    class ListOutOfBoundException extends Exception {
        ListOutOfBoundException() {

        }
    }
}
