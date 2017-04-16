package c1_s2.konovalov.task4_2;

/** интерфейс хэш-функции */
public interface HashFunction<T> {
    /**
     * возвращает значение хэша для заданного ключа в заданном диапазоне
     * @param key ключ
     * @param tableSize размер хеш таблици
     * @return возвращает значение хэша в диапазоне от 0 до tableSize-1
     */
    int getHash(T key, int tableSize);
}
