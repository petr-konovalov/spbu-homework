package c1_s2.konovalov.task6_2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static junit.framework.TestCase.fail;

public class ArraySetTester {
    private final int elementsCount = 1024;
    private ArraySet<Integer> numbers;
    private Random generator = new Random();

    @Before
    public void initializeNumbers() {
        numbers = new ArraySet<>();
    }

    @Test
    public void testIsEmptyMethod() {
        if (!numbers.isEmpty())
            fail();
    }

    @Test
    public void testSizeMethod() {
        if (numbers.size() != 0)
            fail();

        fillSet(numbers, elementsCount);
        if (elementsCount != numbers.size())
            fail();
    }

    @Test
    public void testContainsMethod() {
        final int testCount = 128;

        fillSet(numbers, elementsCount);
        for (int i = 0; i < testCount; ++i) {
            if (!numbers.contains(generator.nextInt(elementsCount)))
                fail();
            if (numbers.contains(generator.nextInt() + elementsCount))
                fail();
        }
    }

    @Test
    public void testIterator() {
        boolean[] usedNumbers = new boolean[elementsCount];
        int usedNumbersCount = 0;
        fillSet(numbers, elementsCount);

        for (int number: numbers) {
            if (usedNumbers[number])
                fail();
            else {
                usedNumbers[number] = true;
                ++usedNumbersCount;
            }
        }

        if (usedNumbersCount != elementsCount)
            fail();
    }

    @Test
    public void testToArrayMethod() {
        boolean[] usedNumbers = new boolean[elementsCount];
        int usedNumbersCount = 0;

        fillSet(numbers, elementsCount);
        for (Object number: numbers.toArray()) {
            if (usedNumbers[(Integer)number])
                fail();
            else {
                usedNumbers[(Integer)number] = true;
                ++usedNumbersCount;
            }
        }

        if (usedNumbersCount != elementsCount)
            fail();
    }

    @Test
    public void testRemoveMethod() {
        final int testCount = 128;
        final int range = elementsCount / testCount;

        fillSet(numbers, elementsCount);
        for (int i = 0; i < testCount; ++i) {
            int number = generator.nextInt(range) + i * range;

            if (!numbers.remove(number))
                fail();
            if (numbers.contains(number))
                fail();
            if (numbers.remove(number + elementsCount))
                fail();
        }
    }

    @Test
    public void testAddAllMethod() {
        List<Integer> extraNumbers = new ArrayList<>();

        fillList(extraNumbers, elementsCount / 8, elementsCount, elementsCount);
        fillSet(numbers, elementsCount);
        if (!numbers.addAll(extraNumbers))
            fail();

        for (int number: extraNumbers)
            if (!numbers.contains(number))
                fail();
    }

    @Test
    public void testRetainAllMethod() {
        List<Integer> retainNumbers = new ArrayList<>();

        fillList(retainNumbers, elementsCount / 2, 0, 2 * elementsCount);
        fillSet(numbers, elementsCount);
        numbers.retainAll(retainNumbers);
        numbers.removeAll(retainNumbers);

        if (!numbers.isEmpty())
            fail();
    }

    @Test
    public void testRemoveAllMethod() {
        List<Integer> removeNumbers = new ArrayList<>();
        fillList(removeNumbers, elementsCount / 8, 0, elementsCount);

        numbers.removeAll(removeNumbers);
        for (int number: removeNumbers)
            if (numbers.contains(number))
                fail();
    }

    @Test
    public void testClearMethod() {
        fillSet(numbers, elementsCount);

        numbers.clear();
        if(!numbers.isEmpty())
            fail();
    }

    private void fillList(List<Integer> list, int elementsCount, int minimumNumber, int range) {
        for (int i = 0; i < elementsCount; ++i)
            list.add(generator.nextInt(range) + minimumNumber);
    }

    private void swapArray(int[] array, int firstIndex, int secondIndex) {
        int firstValue = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = firstValue;
    }

    private void mixArray(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            int swapValue = generator.nextInt(i + 1);
            swapArray(array, i, swapValue);
        }
    }

    private int[] createMixArray(int length) {
        int[] array = new int[length];

        for (int i = 0; i < length; ++i)
            array[i] = i;
        mixArray(array);

        return array;
    }

    private void fillSet(Set<Integer> numbers, int elementsCount) {
        int[] elements = createMixArray(elementsCount);

        for (int i = 0; i < elementsCount; ++i)
            numbers.add(elements[i]);
    }
}
