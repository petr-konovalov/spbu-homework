package c1_s2.konovalov.task6_1;

/** интерфейс дерева разбора арифмитического выражения */
public interface ExpressionTree {
    /**
     * считает значение выражения
     * @return возвращает значение выражения
     */
    int getResult();

    /**
     * печатает дерево
     */
    void printTree();
}
