package c1_s2.konovalov.task6_1;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter file name: ");
        String fileName = input.nextLine();

        try {
            File file = new File(fileName);
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            String debugRecord = in.readLine();
            ExpressionTree tree = new CreatorTree(debugRecord);

            System.out.println("Result: " + tree.getResult());
            System.out.print("Tree: ");
            tree.printTree();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
}
