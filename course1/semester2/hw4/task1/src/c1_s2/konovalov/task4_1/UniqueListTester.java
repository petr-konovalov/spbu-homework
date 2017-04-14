package c1_s2.konovalov.task4_1;

import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static junit.framework.TestCase.fail;

public class UniqueListTester {
    final private int elementCount = 1024;
    private UniqueList<Integer> numbers;

    @Before
    public void initializeNumbers() {
        numbers = new UniqueList<>();
        initializeList(elementCount, numbers);
    }

    @Test(expected = UniqueList.ListIsEmptyException.class)
    public void testListIsEmptyException()
            throws UniqueList.ListIsEmptyException, UniqueList.ListOutOfBoundException {
        for (int i = 0; i <= elementCount; ++i)
            numbers.removeFromPosition(0);
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
            boolean[] numberSet = new boolean[elementCount];
            Integer number = -1;

            for (int i = 0; i < elementCount; ++i)
                numberSet[i] = false;
            while (number != null) {
                number = numbers.getNext();
                if (number != null) {
                    if (numberSet[number])
                        fail();
                    numberSet[number] = true;
                }
            }
            checkNumberSet(numberSet);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testMethodRetrieve() {
        try {
            boolean[] numberSet = new boolean[elementCount];
            Integer[] positions = new Integer[elementCount];

            for (int i = 0; i < elementCount; ++i) {
                numberSet[i] = false;
                positions[i] = i;
            }
            mixArray(positions);
            for (int i = 0; i < elementCount; ++i)
                numberSet[numbers.retrieve(positions[i])] = true;

            checkNumberSet(numberSet);
        } catch (Exception e) {
            fail();
        }
    }

    private void checkNumberSet (boolean[] numberSet) {
        for (boolean item: numberSet)
            if (!item)
                fail();
    }

    private void initializeList(int elementCount, UniqueList<Integer> numbers) {
        Integer[] array = new Integer[elementCount];

        try {
            for (int i = 0; i < elementCount; ++i)
                array[i] = i;

            mixArray(array);

            for (int i = 0; i < elementCount; ++i)
                numbers.insert(i, array[i]);
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
