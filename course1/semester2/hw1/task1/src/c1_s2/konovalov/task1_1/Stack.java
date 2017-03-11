package c1_s2.konovalov.task1_1;

public class Stack {
    public void push(int value) {
        if (isEmpty()) {
            head = new Node();
            head.value = value;
            head.next = null;
        } else {
            Node newNode = new Node();
            newNode.value = value;
            newNode.next = head;
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
        private int value;
        private Node next;
    }

    private Node head = null;
}
