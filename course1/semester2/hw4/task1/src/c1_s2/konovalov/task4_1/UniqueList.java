package c1_s2.konovalov.task4_1;

/** список неповторяющихся элементов */
public class UniqueList<T> extends LinkedList<T> {
    @Override
    public void insert(int position, T element) throws ListOutOfBoundException {
        if (searchElement(element) != -1)
            throw new ReAddValueException("the value " + element + " already added");

        super.insert(position, element);
    }

    private int searchElement(T element) {
        int position = 0;
        boolean isSearch = false;

        try {
            T runningElement = getNext();

            while (runningElement != null) {
                if (element.equals(runningElement))
                    isSearch = true;
                if (!isSearch)
                    ++position;
                runningElement = getNext();
            }
        } catch(ListIsEmptyException e) {
            isSearch = false;
        }

        return isSearch ? position : -1;
    }

    /** бросается при попытке добавить существующий элемент в список */
    public static class ReAddValueException extends RuntimeException {
        ReAddValueException(String message) {
            super(message);
        }
    }
}
