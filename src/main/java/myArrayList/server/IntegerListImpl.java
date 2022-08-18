package myArrayList.server;

import myArrayList.exception.ValueNotFoundException;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntPredicate;

public class IntegerListImpl implements IntegerList {
    private Integer[] array;

    private static final int LIMIT = 10;
    private int capacity;

    public IntegerListImpl() {
        array = new Integer[LIMIT];
        capacity = 0;
    }

    public IntegerListImpl(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Значение должно быть больше 0!");
        }
        array = new Integer[n];
        capacity = 0;
    }

    private static Integer[] myGrow(Integer[] array) {
        Integer[] arrayNew = Arrays.copyOf(array, array.length + 1);
        return arrayNew;
    }

    @Override
    public Integer add(Integer item) {
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
    public Integer add(int index, Integer item) {
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
    public Integer set(int index, Integer item) {
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
    public Integer remove(Integer item) {
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
    public Integer remove(int index) {
        if (index >= capacity) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + capacity);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не должен быть отрицательным!");
        }
        Integer item = array[index];
        capacity--;
        for (int i = index; i < capacity; i++) {
            array[i] = array[i + 1];
        }
        array = Arrays.copyOf(array, capacity);
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Значение не должно быть null!");
        }
        sort();
        int min = 0;
        int max = capacity - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(array[mid])) {
                return true;
            }
            if (item < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
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
    public int lastIndexOf(Integer item) {
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
    public Integer get(int index) {
        if (index >= capacity) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + capacity);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не должен быть отрицательным!");
        }
        return array[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
        array = new Integer[LIMIT];
        capacity = 0;
        return null;
    }

    @Override
    public Integer[] toArray() {
        Integer[] result = new Integer[capacity];
        for (int i = 0; i < capacity; i++) {
            result[i] = array[i];
        }
        return result;
    }

    private void sort() {
        for (int i = 1; i < capacity; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
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
        IntegerListImpl that = (IntegerListImpl) o;
        return capacity == that.capacity && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
