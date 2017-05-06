package c1_s2.konovalov.task4_1;

import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static junit.framework.TestCase.fail;

public class UniqueListTester {
    final private int elementCount = 1024;
    private UniqueList<Integer> numbers;
    private Integer elements[] = new Integer[elementCount];

    @Before
    public void initializeNumbers() {
        numbers = new UniqueList<>();
        initializeList(elementCount, numbers);
    }

    @Test(expected = UniqueList.ReAddValueException.class)
    public void testReAddValueException() throws UniqueList.ListOutOfBoundException, UniqueList.ReAddValueException {
        Random generator = new Random();

        numbers.insert(generator.nextInt(elementCount), generator.nextInt(elementCount));
    }

    @Test(expected = UniqueList.ElementNotFoundException.class)
    public void testElementNotFoundException ()
            throws UniqueList.ListIsEmptyException, UniqueList.ElementNotFoundException {
        numbers.remove(elementCount);
    }

    @Test
    public void testMethodSize () {
        if (numbers.size() != elementCount)
            fail();
    }

    @Test
    public void testMethodGetNext () {
        try {
            Integer nextElement = numbers.getNext();
            for (int i = 0; i < elementCount; ++i) {
                if (!nextElement.equals(elements[i]))
                    fail();
                nextElement = numbers.getNext();
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testMethodRetrieve() {
        try {
            for (int i = 0; i < elementCount; ++i) {
                if (!numbers.retrieve(i).equals(elements[i]))
                    fail();
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testMethodLocate() {
        try {
            for (int i = 0; i < elementCount; ++i)
                if (numbers.locate(elements[i]) != i)
                    fail();
        } catch(Exception e) {
            fail();
        }
    }

    private void initializeList(int elementCount, UniqueList<Integer> numbers) {
        try {
            for (int i = 0; i < elementCount; ++i)
                elements[i] = i;

            mixArray(elements);

            for (int i = 0; i < elementCount; ++i)
                numbers.insert(i, elements[i]);
        } catch (Exception e) {
            fail();
        }
    }

    private void mixArray(Integer[] array) {
        Random generator = new Random();

        for (int i = array.length - 1; i >= 0; --i) {
            int index = generator.nextInt(i + 1);
            Integer temporaryValue = array[i];
            array[i] = array[index];
            array[index] = temporaryValue;
        }
    }
}
