package c1_s2.konovalov.test1;

/** Приоритетная очередь */
public class PriorityQueue<T> {
    private Node head;

    /**
     * Добавляет в очередь элемент со значением value и приоритетом priority
     * @param value значение
     * @param priority приоритет
     */
    public void enqueue(T value, int priority) {
        if (head == null || head.priority < priority)
            head = new Node(value, priority, head);
        else {
            Node searchNode = head;

            while (searchNode.next != null && searchNode.next.priority > priority)
                searchNode = searchNode.next;

            searchNode.next = new Node(value, priority, searchNode.next);
        }
    }

    /**
     * Извлекает из очереди элемент с наименьшим приоритетом
     * @return элемент с наименьшим приоритетом
     * @throws QueueIsEmptyException возникает если пытаемся извлечь элемент из пустой очереди
     */
    public T dequeue() throws QueueIsEmptyException {
        if (isEmpty())
            throw new QueueIsEmptyException();

        T value = head.value;
        head = head.next;

        return value;
    }

    /**
     * Проверяет пуста ли очередь
     * @return возвратит true если очередь пуста
     */
    public boolean isEmpty() {
        return head == null;
    }

    private class Node {
        T value;
        int priority;
        Node next;

        Node(T initialValue, int initialPriority, Node initialNext) {
            value = initialValue;
            priority = initialPriority;
            next = initialNext;
        }
    }
}