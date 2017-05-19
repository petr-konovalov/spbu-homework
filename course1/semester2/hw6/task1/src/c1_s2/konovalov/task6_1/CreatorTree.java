package c1_s2.konovalov.task6_1;

/** создает дерево */
public class CreatorTree implements ExpressionTree {
    private ExpressionTree tree;
    private int currentSymbol = -1;

    @Override
    public int getResult() {
        return tree.getResult();
    }

    @Override
    public void printTree() {
        tree.printTree();
    }

    /**
     * создает дерево разбора арифмитического выражения по отладочной записи
     * @param debugRecord отладочная запись
     */
    public CreatorTree(String debugRecord) {
        tree = createTree(debugRecord);
    }

    private ExpressionTree createTree(String tree) {
        ++currentSymbol;
        if (isDigit(tree.charAt(currentSymbol)))
            return new OperandNode(getNextNumber(tree));
        else if (isSign(tree.charAt(currentSymbol)))
            return new OperationNode(tree.charAt(currentSymbol), createTree(tree), createTree(tree));
        else
            return createTree(tree);
    }

    private boolean isDigit(char symbol) {
        return '0' <= symbol && symbol <= '9';
    }

    private boolean isSign(char symbol) {
        return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/';
    }

    private int getNextNumber(String tree) {
        int Number = 0;

        while ('0' <= tree.charAt(currentSymbol) && tree.charAt(currentSymbol) <= '9') {
            Number = Number * 10 + (tree.charAt(currentSymbol) - '0');
            ++currentSymbol;
        }

        return Number;
    }
}
