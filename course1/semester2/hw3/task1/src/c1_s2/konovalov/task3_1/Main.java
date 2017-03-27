package c1_s2.konovalov.task3_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] argc) {
        Integer[] numbers = new Integer[5];
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 5; ++i)
            numbers[i] = input.nextInt();

        Sorter<Integer> sorter = new QuickSorter<>();

        sorter.sort(numbers);
        for (int i = 0; i < 5; ++i)
            System.out.print(numbers[i] + " ");
    }
}
