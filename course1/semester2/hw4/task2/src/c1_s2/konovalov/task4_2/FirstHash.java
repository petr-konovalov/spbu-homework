package c1_s2.konovalov.task4_2;

public class FirstHash implements HashFunction<String> {
    @Override
    public int getHash(String key, int tableSize) {
        int hash = 5381;

        for (int symbol: key.toCharArray())
            hash = hash * 33 + symbol;

        return (hash % tableSize + tableSize) % tableSize;
    }
}
