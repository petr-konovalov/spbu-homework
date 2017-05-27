package c1_s2.konovalov.task6_2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class ArraySet<E> implements Set<E> {
    private Vector<E> elements = new Vector<>();

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return elements.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return elements.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return !elements.contains(e) && elements.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return elements.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;

        for(E element: c)
            if(add(element))
                changed = true;

        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return elements.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return elements.removeAll(c);
    }

    @Override
    public void clear() {
        elements.clear();
    }
}
