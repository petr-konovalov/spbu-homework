package c1_s2.konovalov.task6_1;

/** узел операнд дерева разбора арифмитического выражения */
public class OperandNode implements ExpressionTree {
    private int value;

    @Override
    public int getResult() {
        return value;
    }

    @Override
    public void printTree() {
        System.out.print(value);
    }

    /**
     * создает узел операнд
     * @param value значение узла операнда
     */
    public OperandNode(int value) {
        this.value = value;
    }
}
