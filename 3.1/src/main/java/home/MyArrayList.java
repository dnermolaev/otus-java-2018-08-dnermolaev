package home;

import java.util.*;

public class MyArrayList <T> implements List <T> {

    public int size;
    public Object[] elementData;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int COPY_THRESHOLD           =   10;

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public MyArrayList(Collection<? extends T> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    public boolean isEmpty() throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return false;
    }

    public boolean contains(Object o) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return false;
    }

    public Iterator<T> iterator() throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return null;
    }

   /* public Iterator<T> iterator() {
        return new MyArrayIterator<T>();
    }*/

    public Object[] toArray() {
            return Arrays.copyOf(elementData, size);
    }


    public <T> T[] toArray(T[] a)  {
        if (a.length < size)

            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    private void add(T t, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = growCapacity();
        elementData[s] = t;
        size = s + 1;
    }

    public boolean add(T t) {
            //modCount++;
            add(t, elementData, size);
            return true;
        }

    T elementData(int index) {
        return (T) elementData[index];
    }

    static <T> T elementAt(Object[] es, int index) {
        return (T) es[index];
    }

    public T get(int index) {
        Objects.checkIndex(index,size);
        return elementData(index);
    }

    public T set(int index, Object element) {
        Objects.checkIndex(index, size);
        T oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    public int size() {
        return size;
    }

    public boolean remove(Object o) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return false;
    }

    public boolean containsAll(Collection<?> c) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        Object [] a=c.toArray();
        int numNew = c.size();
        if (numNew == 0)
            return false;
        Object[] elementData;
        int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = growCapacity(s + numNew);
        System.arraycopy(a, 0, elementData, s, numNew);
        size+=numNew;
        return true;
    }

    private Object[] growCapacity(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return minCapacity;
    }

    private Object[] growCapacity() {
        return growCapacity(size + 1);
    }

   /*public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        int srcSize = src.size();
        if (srcSize > dest.size())
            throw new IndexOutOfBoundsException("Source does not fit in dest");

        for (int i=0; i<srcSize; i++)
            dest.set(i, src.get(i));
        }*/

    /* public static <T> void sort(List<T> list, Comparator<? super T> c) {
       Object [] a= list.toArray();
       Arrays.sort(a,(Comparator) c);
       for (int i=0; i<a.length;i++)
           list.set(i, (T) a[i]);
    }

   public static <T extends Comparable<? super T>> void sort(List<T> list) {
       MyArrayList.sort(list, null);
   }*/


    public boolean addAll(int index, Collection<? extends T> c)throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return false;
    }

    public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return false;
    }

    public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return false;
    }

    public void clear() throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
    }

   /*public T set(int index, T element) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return null;
    }*/

    /*public T get(int index) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return null;
    }*/

    public void add(int index, T element) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
    }

    public T remove(int index) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return null;
    }

    public int indexOf(Object o) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return 0;
    }

    public int lastIndexOf(Object o) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return 0;
    }

    /*public ListIterator<T> listIterator()  throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return null;
    }*/

    public ListIterator<T> listIterator()   {
        return new MyArrayListIterator(0);
    }

    public ListIterator<T> listIterator(int index) {
        return new MyArrayListIterator(index);
    }
    /*public ListIterator<T> listIterator(int index) {
        rangeCheckForAdd(index);
        return new ArrayList.ListItr(index);
    }*/

    public List<T> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
        new UnsupportedOperationException("not implemented");
        return null;
    }

    /*private class MyArrayIterator <T> implements Iterator<T> {
        int currentPos=0;
        T[] values;

        MyArrayIterator() {
            this.values=values;
        }

        @Override
        public boolean hasNext() {
            return currentPos < values.length && values[currentPos] != null;
        }

       @Override
        public T next() {

            int i = currentPos;
            if (i >= size)
                throw new NoSuchElementException();
            return values[currentPos++];
        }

        public Iterator<T> iterator() {
            return this;
        }
    }*/

    private class MyArrayListIterator <T> implements ListIterator<T> {
        int index;
        int lastRet = -1;

        MyArrayListIterator(int index) {
            index=this.index;
        }

        @Override
        public boolean hasNext() {
            return index != size;
        }

        @Override
        public T next() {

            int i = index;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = MyArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            index = i + 1;
            return (T) elementData[lastRet = i];
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object t) {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                MyArrayList.this.set(lastRet, t);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(T t) {

        }


    }
}
