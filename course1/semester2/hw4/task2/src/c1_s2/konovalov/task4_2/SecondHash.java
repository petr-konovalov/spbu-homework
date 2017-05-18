package c1_s2.konovalov.task4_2;

public class SecondHash implements HashFunction<String> {
    @Override
    public int getHash(String key, int tableSize) {
        int hash = 0;

        for (int symbol: key.toCharArray())
            hash = hash * 65599 + symbol;

        return (hash % tableSize + tableSize) % tableSize;
    }
}
