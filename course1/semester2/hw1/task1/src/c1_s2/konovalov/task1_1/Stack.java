package c1_s2.konovalov.task1_1;

public class Stack {
    public void push(int value) {
        if (isEmpty())
            head = new Node(value, null);
        else {
            Node newNode = new Node(value, head);
            head = newNode;
        }
    }

    public int pop() {
        int value = head.value;
        head = head.next;
        return value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private class Node {
        public Node(int initalValue, Node initalNext) {
            value = initalValue;
            next = initalNext;
        }

        private int value;
        private Node next;
    }

    private Node head = null;
}
