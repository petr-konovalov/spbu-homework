package c1_s2.konovalov.task1_1;

public class Stack {
    public void Stack() {
        head = null;
    }

    public void push (int value) {
        if (isEmpty()){
            head = new Node();
            head.setValue(value);
            head.setNext(null);
        } else {
            Node newNode = new Node();
            newNode.setValue(value);
            newNode.setNext(head);
            head = newNode;
        }
    }

    public int pop () {
        int value = head.getValue();
        head = head.getNext();
        return value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private class Node {
        public void setValue(int newValue) {
            value = newValue;
        }

        public void setNext(Node newNext) {
            next = newNext;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        private int value;
        private Node next;
    }

    private Node head;
}
