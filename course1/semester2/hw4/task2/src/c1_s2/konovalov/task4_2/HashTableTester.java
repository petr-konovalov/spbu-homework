package c1_s2.konovalov.task4_2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static junit.framework.TestCase.fail;

public class HashTableTester {
    private final int elementCount = 1024 * 128;
    private String[] keys = new String[elementCount];
    private String[] values = new String[elementCount];
    private HashTable table;

    @Before
    public void initializeTest() throws HashTable.ReAddKeyException {
        table = new HashTable();
        generateKeys();
        generateStringArray(values);
        initializeTestHashTable();
    }

    @Test(timeout = 10000)
    public void testHashTable() throws HashTable.ReAddKeyException, HashTable.KeyNotFoundException {
        checkKeysValues();
        HashFunction<String> newHash = new SecondHash();
        table.setHashFunction(newHash);
        checkKeysValues();
    }

    @Test(timeout = 10000, expected = HashTable.KeyNotFoundException.class)
    public void testKeyNotFoundException() throws HashTable.KeyNotFoundException {
        table.get(getNextKey(keys[elementCount - 1]));
    }

    @Test(timeout = 10000, expected = HashTable.ReAddKeyException.class)
    public void testReAddKeyException() throws HashTable.ReAddKeyException {
        Random generator = new Random();
        table.add(keys[generator.nextInt(elementCount)], values[generator.nextInt(elementCount)]);
    }

    @After
    public void removeHashTableElement() throws HashTable.KeyNotFoundException {
        for (String key: keys)
            table.delete(key);
    }

    private void initializeTestHashTable ()
            throws HashTable.ReAddKeyException {
        generateKeys();
        generateStringArray(values);
        for (int i = 0; i < elementCount; ++i)
            table.add(keys[i], values[i]);
    }

    private void checkKeysValues ()
            throws HashTable.KeyNotFoundException {
        for (int i = 0; i < elementCount; ++i) {
            if (table.get(keys[i]).compareTo(values[i]) != 0)
                fail();
        }
    }

    private void generateKeys() {
        keys[0] = "AAAAAAAAAA";
        for (int i = 1; i < elementCount; ++i)
            keys[i] = getNextKey(keys[i - 1]);
    }

    private String getNextKey(String key) {
        StringBuilder newKey = new StringBuilder(key);
        int index = 0;

        while (newKey.charAt(index) == 'Z') {
            newKey.setCharAt(index, 'A');
            ++index;
        }
        newKey.setCharAt(index, (char)((int)newKey.charAt(index) + 1));

        return newKey.toString();
    }

    private void generateStringArray(String[] strings) {
        for (int i = 0; i < strings.length; ++i)
            strings[i] = generateString();
    }

    private String generateString() {
        Random generator = new Random();
        int length = generator.nextInt(32) + 1;
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; ++i)
            randomString.append((char)(generator.nextInt(26) + 'A'));

        return randomString.toString();
    }
}
