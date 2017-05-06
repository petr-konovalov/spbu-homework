package c1_s2.konovalov.task4_1;

public interface List<T> {
    T getNext() throws ListIsEmptyException;
    int size();
    T retrieve(int position) throws ListOutOfBoundException;
    void insert(int position, T element) throws ListOutOfBoundException;
    void remove(int position) throws ListOutOfBoundException;

    class ListIsEmptyException extends Exception {

    }

    class ListOutOfBoundException extends Exception {

    }
}
