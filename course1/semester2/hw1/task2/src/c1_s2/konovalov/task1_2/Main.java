package c1_s2.konovalov.task1_2;

public class Main {
    public static void main(String[] argc)
    {
        List numbers = new List();
        for (int i = 0; i < 10; ++i)
            numbers.insertElement(i, i);
        numbers.printList();
    }
}
