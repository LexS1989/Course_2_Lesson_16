package myArrayList.server;

import myArrayList.exception.ValueNotFoundException;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntPredicate;

public class StringListImpl implements StringList {
    private String[] array;

    private static final int LIMIT = 10;
    private int capacity;

    public StringListImpl() {
        array = new String[LIMIT];
        capacity = 0;
    }

    public StringListImpl(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Значение должно быть больше 0!");
        }
        array = new String[n];
        capacity = 0;
    }

    private static String[] myGrow(String[] array) {
        String[] arrayNew = Arrays.copyOf(array, array.length + 1);
        return arrayNew;
    }

    @Override
    public String add(String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Значение не должно быть null!");
        }
        if (capacity == array.length) {
            array = myGrow(array);
        }
        array[capacity] = item;
        capacity++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Значение не должно быть null!");
        }
        if (capacity == array.length) {
            array = myGrow(array);
        }
        if (index > capacity) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + capacity);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не должен быть отрицательным!");
        }
        for (int i = capacity; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = item;
        capacity++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Значение не должно быть null!");
        }
        if (index >= capacity) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + capacity);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не должен быть отрицательным!");
        }
        array[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Значение не должно быть null!");
        }
        int indexRemove = -1;
        for (int i = 0; i < capacity; i++) {
            if (array[i] == item) {
                indexRemove = i;
                break;
            }
        }
        if (indexRemove == -1) {
            throw new ValueNotFoundException("Значение не найдено!");
        }
        capacity--;
        for (int i = indexRemove; i < capacity; i++) {
            array[i] = array[i + 1];
        }
        array = Arrays.copyOf(array, capacity);
        return item;
    }

    @Override
    public String remove(int index) {
        if (index >= capacity) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + capacity);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не должен быть отрицательным!");
        }
        String item = array[index];
        capacity--;
        for (int i = index; i < capacity; i++) {
            array[i] = array[i + 1];
        }
        array = Arrays.copyOf(array, capacity);
        return item;
    }

    @Override
    public boolean contains(String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Значение не должно быть null!");
        }
        for (int i = 0; i < capacity; i++) {
            if (array[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Значение не должно быть null!");
        }
        int index = -1;
        for (int i = 0; i < capacity; i++) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Значение не должно быть null!");
        }
        int index = -1;
        for (int i = capacity - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public String get(int index) {
        if (index >= capacity) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + capacity);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не должен быть отрицательным!");
        }
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (Objects.isNull(otherList)) {
            throw new IllegalArgumentException("Значение не должно быть null!");
        }
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!array[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        if (size() != 0) {
            return false;
        }
        return true;
    }

    @Override
    public IntPredicate clear() {
        array = new String[LIMIT];
        capacity = 0;
        return null;
    }

    @Override
    public String[] toArray() {
        String[] result = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            result[i] = array[i];
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StringListImpl that = (StringListImpl) o;
        return capacity == that.capacity && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
