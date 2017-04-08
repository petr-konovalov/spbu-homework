package c1_s2.konovalov.test1;

import org.junit.Test;
import static junit.framework.TestCase.fail;
import java.util.Random;

public class PriorityQueueTester {
    @Test(expected = QueueIsEmptyException.class)
    public void testQueueIsEmptyException () throws QueueIsEmptyException {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        generateNumbers(queue, 16);
        retrieveNumbers(queue, 16);
        queue.dequeue();
    }

    @Test
    public void testQueue() {
        final int minSize = 128;
        final int maxSize = 1024;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int size = minSize; size <= maxSize; size *= 2) {
            generateNumbers(queue, size);
            retrieveNumbers(queue, size);
        }

    }

    private void generateNumbers(PriorityQueue<Integer> queue, int count) {
        Random generator = new Random();

        for (int i = 0; i < count; ++i) {
            int nextNumber = generator.nextInt();
            queue.enqueue(nextNumber, nextNumber);
        }
    }

    private void retrieveNumbers(PriorityQueue<Integer> queue, int count) {
        try {
            int previousNumber = queue.dequeue();
            for (int i = 0; i < count - 1; ++i) {
                int nextNumber = queue.dequeue();

                if (previousNumber < nextNumber)
                    fail();

                previousNumber = nextNumber;
            }
        } catch (QueueIsEmptyException e) {
            fail();
        }
    }
}
