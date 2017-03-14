package c1_s2.konovalov.task1_2;

public class List {
    public void insertElementTop(int value) {
        ListElement newElement = new ListElement(value, head);
        head = newElement;
    }

    public boolean insertElement(int value, int position) {
        if (position == 0) {
            insertElementTop(value);
            return true;
        }

        ListElement previousElement = getPreviousElement(position);
        if (previousElement == null)
            return false;

        ListElement newElement = new ListElement(value, previousElement.nextElement);
        previousElement.nextElement = newElement;
        return true;
    }

    public boolean deleteTopElement() {
        if (isEmpty())
            return false;
        head = head.nextElement;
        return true;
    }

    public boolean deleteElement(int position) {
        if (position == 0)
            return deleteTopElement();

        ListElement previousElement = getPreviousElement(position);
        if (previousElement.nextElement == null)
            return false;

        ListElement deletingElement = previousElement.nextElement;
        previousElement.nextElement = deletingElement.nextElement;
        return true;
    }

    public int locateElement(int value) {
        ListElement currentElement = head;
        int position = 0;

        while (currentElement != null && currentElement.value != value) {
            currentElement = currentElement.nextElement;
            ++position;
        }

        if (currentElement == null)
            return -1;
        else
            return position;
    }

    public int retrieveElement(int position) {
        ListElement currentElement = head;

        while (position > 0 && currentElement != null) {
            currentElement = currentElement.nextElement;
            --position;
        }

        if (currentElement == null)
            return 0;
        else
            return currentElement.value;
    }

    public int getFirstElement() {
        currentElement = head.nextElement;
        return head.value;
    }

    public int getNextElement() {
        int nextValue = currentElement.value;
        currentElement = currentElement.nextElement;
        return nextValue;
    }

    public boolean hasReachedLastElement() {
        return currentElement == null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private ListElement getPreviousElement(int position) {
        ListElement previousElement = head;

        while (position > 1 && previousElement != null) {
            previousElement = previousElement.nextElement;
            --position;
        }

        return previousElement;
    }

    private class ListElement {
        public ListElement(int initialValue, ListElement initialNextElement) {
            value = initialValue;
            nextElement = initialNextElement;
        }

        private int value;
        private ListElement nextElement;
    }

    private ListElement head = null;
    private ListElement currentElement = null;
}
