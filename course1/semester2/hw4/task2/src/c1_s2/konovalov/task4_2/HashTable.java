package c1_s2.konovalov.task4_2;

/** хэш таблица */
public class HashTable {
    private int elementCount = 0;
    private HashFunction<String> hashFunction = new FirstHash();
    private UniqueList<StringPair>[] table;

    HashTable() {
        table = new UniqueList[16];
        initializeTable(table);
    }

    HashTable(int initialTableSize) {
        table = new UniqueList[initialTableSize];
        initializeTable(table);
    }

    /**
     * добавляет элемент в хэш таблицу
     * @param key ключ элемента
     * @param value значение элемента
     * @throws ReAddKeyException бросается в случае если в таблице уже присутсвует элемент с заданным ключем
     */
    public void add(String key, String value) throws ReAddKeyException {
        if (elementCount / (double)table.length >= 2)
            reSizeTable(table.length * 2);

        try {
            table[hashFunction.getHash(key, table.length)].push(new StringPair(key, value));
            ++elementCount;
        } catch (UniqueList.ReAddValueException e) {
            throw new ReAddKeyException(key);
        }
    }

    /**
     * возвращает значение элемента по ключу
     * @param key ключ элемента
     * @return возвращает значение элемента
     * @throws KeyNotFoundException бросается если ключ не был найден
     */
    public String get(String key) throws KeyNotFoundException {
        StringPair element = new StringPair(key, "");

        try {
            element = table[hashFunction.getHash(key, table.length)].getElement(element);
        } catch (UniqueList.ElementNotFoundException e) {
            throw new KeyNotFoundException(key);
        }

        return element.SecondString;
    }

    /**
     * удаляет элемент с заданным ключем
     * @param key ключ удаляемого элемента
     * @throws KeyNotFoundException бросается если элемент не был найден
     */
    public void delete(String key) throws KeyNotFoundException {
        try {
            table[hashFunction.getHash(key, table.length)].remove(new StringPair(key, ""));
            --elementCount;
        } catch (UniqueList.ElementNotFoundException e) {
            throw new KeyNotFoundException(key);
        }
    }

    /**
     * меняет значение хэш таблицы
     * @param newHashFunction новая хеш функция
     */
    public void setHashFunction(HashFunction<String> newHashFunction) {
        hashFunction = newHashFunction;
        reSizeTable(table.length);
    }

    /**
     * подсчитывает статистику таблицы
     * @return возвращает статистику таблицы
     */
    public Statistic getTableStatistic() {
        Statistic statistic = new Statistic();

        statistic.tableSize = table.length;
        statistic.loadFactor = (double)elementCount / table.length;
        statistic.maxConflictChainLength = 0;

        for (UniqueList<StringPair> chain: table) {
            int chainSize = chain.size();
            if (chainSize > 0)
                ++statistic.chainCount;
            if (chainSize > 1) {
                statistic.conflictCount += chainSize;
                if (chainSize > statistic.maxConflictChainLength)
                    statistic.maxConflictChainLength = chainSize;
            }
        }

        return statistic;
    }

    private void initializeTable(UniqueList<StringPair>[] table) {
        for (int i = 0; i < table.length; ++i)
            table[i] = new UniqueList<>();
    }

    private void reSizeTable(int newSize) {
        UniqueList<StringPair>[] newTable = new UniqueList[newSize];
        initializeTable(newTable);

        try {
            for (UniqueList<StringPair> chain : table) {
                StringPair element = chain.pop();
                while (element != null) {
                    newTable[hashFunction.getHash(element.FirstString, newSize)].push(element);
                    element = chain.pop();
                }
            }
            table = newTable;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /** статистика таблицы */
    public class Statistic {
        /** размер таблицы*/
        int tableSize;
        /** количество непустых цепочек в таблице */
        int chainCount;
        /** коэффициент нагрузки таблицы */
        double loadFactor;
        /** количество конфликтующих элементов в таблице */
        int conflictCount;
        /** максимальная длина цепочки конфликтующих элементов */
        int maxConflictChainLength;
    }

    private class StringPair implements Comparable<StringPair> {
        private String FirstString;
        private String SecondString;

        StringPair(String InitialFirstString, String InitialSecondString) {
            FirstString = InitialFirstString;
            SecondString = InitialSecondString;
        }

        @Override
        public int compareTo(StringPair o) {
            return FirstString.compareTo(o.FirstString);
        }
    }

    public class ReAddKeyException extends Exception {
        ReAddKeyException(String key) {
            super("key " + key + " already added");
        }
    }

    public class KeyNotFoundException extends Exception {
        KeyNotFoundException(String key) {
            super("key " + key + " not found");
        }
    }
}
