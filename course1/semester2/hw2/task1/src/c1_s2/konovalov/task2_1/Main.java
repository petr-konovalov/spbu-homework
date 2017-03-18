package c1_s2.konovalov.task2_1;

public class Main {
    public static void main(String[] argc) {
        Stack<Integer> numbers = new LinkedStack();
        String str = "12345";
        for (int i = 0; i < 32; ++i)
            numbers.push(i);
        try {
            while (!numbers.isEmpty())
                System.out.print(numbers.pop() + " ");
        } catch (StackIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
}
