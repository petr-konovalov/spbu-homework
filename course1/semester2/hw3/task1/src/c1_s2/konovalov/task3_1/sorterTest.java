package c1_s2.konovalov.task3_1;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.Random;

public class sorterTest {
    private Random generator;

    @Before
    public void initializeGenerator() {
        generator = new Random();
    }

    private Integer[] getRandomArray(int size) {
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; ++i)
            array[i] = generator.nextInt();

        return array;
    }

    private boolean isSorted(Integer[] array) {
        boolean isIncreases = true;
        boolean isDecreases = true;

        for (int i = 1; i < array.length && (isIncreases || isDecreases); ++i) {
            if (array[i - 1] > array[i])
                isIncreases = false;

            if (array[i - 1] < array[i])
                isDecreases = false;
        }

        return isIncreases || isDecreases;
    }

    private void sortTest(Sorter<Integer> sorter, int maxArraySize) {
        for (int size = 128; size <= maxArraySize; size *= 2) {
            Integer[] array = getRandomArray(size);

            sorter.sort(array);
            Assert.assertTrue(isSorted(array));
        }
    }

    @Test
    public void bubbleSortTest() {
        Sorter<Integer> sorter = new BubbleSorter<>();
        sortTest(sorter, 128 * 128);
    }

    @Test
    public void quickSortTest() {
        Sorter<Integer> sorter = new QuickSorter<>();
        sortTest(sorter, 1024 * 1024);
    }

    @After
    public void destroyGenerator() {
        generator = null;
    }
}
