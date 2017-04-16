package c1_s2.konovalov.task4_2;

public class ThirdHash implements HashFunction<String> {
    @Override
    public int getHash(String key, int tableSize) {
        int hash = 0;

        for (int symbol: key.toCharArray())
            hash = hash + symbol;

        return (hash % tableSize + tableSize) % tableSize;
    }
}
