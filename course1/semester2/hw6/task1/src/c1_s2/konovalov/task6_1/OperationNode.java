package c1_s2.konovalov.task6_1;

/** узел операция дерва разбора арифмитического выражения */
public class OperationNode implements ExpressionTree {
    private ExpressionTree leftChild;
    private ExpressionTree rightChild;
    private char operation;

    @Override
    public int getResult() {
        return Executor.executeOperation(operation, leftChild.getResult(), rightChild.getResult());
    }

    @Override
    public void printTree() {
        System.out.print("(" + operation + " ");
        leftChild.printTree();
        System.out.print(" ");
        rightChild.printTree();
        System.out.print(")");
    }

    /**
     * создает узел операцию
     * @param operation операция может быть '+', '-', '*', '/' другие операции не подделживаются
     * @param leftChild левый операнд
     * @param rightChild правый операнд
     */
    public OperationNode(char operation, ExpressionTree leftChild, ExpressionTree rightChild) {
        this.operation = operation;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
