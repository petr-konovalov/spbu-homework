package c1_s2.konovalov.task1_2;

public class List {
    public void List() {
        head = null;
    }

    public void insertElementTop(int value) {
        ListElement newElement = new ListElement();
        newElement.setNextElement(head);
        newElement.setValue(value);
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

        ListElement newElement = new ListElement();
        newElement.setValue(value);
        newElement.setNextElement(previousElement.getNextElement());
        previousElement.setNextElement(newElement);
        return true;
    }

    public void deleteTopElement() {
        head = head.getNextElement();
    }

    public boolean deleteElement(int position) {
        if (position == 0) {
            deleteTopElement();
            return true;
        }

        ListElement previousElement = getPreviousElement(position);
        if (previousElement.getNextElement() == null)
            return false;

        ListElement deletingElement = previousElement.getNextElement();
        previousElement.setNextElement(deletingElement.getNextElement());
        return true;
    }

    public int locateElement(int value) {
        ListElement currentElement = head;
        int position = 0;

        while (currentElement != null && currentElement.getValue() != value) {
            currentElement = currentElement.getNextElement();
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
            currentElement = currentElement.getNextElement();
            --position;
        }

        if (currentElement == null)
            return 0;
        else
            return currentElement.getValue();
    }

    public void printList() {
        ListElement currentElement = head;

        while (currentElement != null) {
            System.out.print(currentElement.getValue() + " ");
            currentElement = currentElement.getNextElement();
        }

        System.out.println();
    }

    private ListElement getPreviousElement(int position) {
        ListElement previousElement = head;

        while (position > 1 && previousElement != null) {
            previousElement = previousElement.getNextElement();
            --position;
        }

        return previousElement;
    }

    private class ListElement {
        public int getValue() {
           return value;
        }

        public void setValue(int newValue) {
            value = newValue;
        }

        public ListElement getNextElement() {
            return nextElement;
        }

        public void setNextElement(ListElement newNextElement) {
            nextElement = newNextElement;
        }

        private int value;
        private ListElement nextElement;
    }

    private ListElement head;
}
