package server;

import myArrayList.exception.ValueNotFoundException;
import myArrayList.server.StringList;
import myArrayList.server.StringListImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringListImplTest {

    private final StringList out = new StringListImpl();

    @Test
    public void addForItemPositiveTest() {
        StringList expected = new StringListImpl();
        expected.add("1");
        out.add("1");
        assertEquals(expected, out);
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addWithArrayExpansionPositiveTest(StringList out) {
        out.add("11");
        int expected = 11;
        assertThat(out.size())
                .isEqualTo(expected);
    }

    @Test
    public void addNullForItemNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.add(null));
    }

    @Test
    public void addByIndexAndItemAndWithArrayExpansionPositiveTest() {
        StringList expected = new StringListImpl(3);
        expected.add("1");
        expected.add("3");
        expected.add("2");
        StringList out = new StringListImpl(2);
        out.add(0, "1");
        out.add(1, "2");
        out.add(1, "3");
        assertEquals(expected, out);
    }

    @Test
    public void addItemNullAndIndexMoreCapacityAndLessNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.add(0, null));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.add(1, "1"));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.add(-1, "1"));
    }

    @Test
    public void setPositiveTest() {
        StringList expected = new StringListImpl();
        expected.add(0, "1");
        expected.add(1, "3");
        StringList out = new StringListImpl();
        out.add(0, "1");
        out.add(1, "2");
        out.set(1, "3");
        assertEquals(expected, out);
    }

    @Test
    public void setItemNullAndIndexMoreCapacityAndLessNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.set(0, null));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.set(0, "1"));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.set(-1, "1"));
    }

    @Test
    public void removeByItemPositiveTest() {
        StringList expected = new StringListImpl(2);
        expected.add(0, "1");
        expected.add(1, "3");
        StringList out = new StringListImpl(3);
        out.add(0, "1");
        out.add(1, "2");
        out.add(2, "3");
        out.remove("2");
        assertEquals(expected, out);
    }

    @Test
    public void removeByItemNullAndNotFoundValueNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.remove(null));

        assertThatExceptionOfType(ValueNotFoundException.class)
                .isThrownBy(() -> out.remove("0"));
    }

    @Test
    public void removeByIndexPositiveTest() {
        StringList expected = new StringListImpl(2);
        expected.add(0, "1");
        expected.add(1, "3");
        StringList out = new StringListImpl(3);
        out.add(0, "1");
        out.add(1, "2");
        out.add(2, "3");
        out.remove(1);
        assertEquals(expected, out);
    }

    @Test
    public void removeForIndexMoreOrEqualsCapacityAndLessNullNegativeTest() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.remove(0));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.remove(-1));
    }

    @Test
    public void containsTest() {
        boolean expected = true;
        boolean expected1 = false;
        out.add("1");
        out.add("2");
        out.add("3");
        assertThat(out.contains("3"))
                .isEqualTo(expected);

        assertThat(out.contains("0"))
                .isEqualTo(expected1);
    }

    @Test
    public void containsItemNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.contains(null));
    }

    @Test
    public void indexOfPositiveTest() {
        int expected = 1;
        int expected1 = -1;
        out.add(0, "1");
        out.add(1, "2");
        out.add(2, "3");
        assertThat(out.indexOf("2"))
                .isEqualTo(expected);

        assertThat(out.indexOf("0"))
                .isEqualTo(expected1);
    }

    @Test
    public void indexOfItemNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.indexOf(null));
    }

    @Test
    public void lastIndexOfPositiveTest() {
        int expected = 0;
        int expected1 = -1;
        out.add(0, "1");
        out.add(1, "2");
        out.add(2, "3");
        assertThat(out.lastIndexOf("1"))
                .isEqualTo(expected);

        assertThat(out.lastIndexOf("0"))
                .isEqualTo(expected1);
    }

    @Test
    public void lastIndexOfItemNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.lastIndexOf(null));
    }

    @Test
    public void getIndexPositiveTest() {
        String expected = "3";
        out.add(0, "1");
        out.add(1, "2");
        out.add(2, "3");
        assertThat(out.get(2))
                .isEqualTo(expected);
    }

    @Test
    public void getForIndexMoreOrEqualsCapacityAndLessNullNegativeTest() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.get(0));

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> out.get(-1));
    }

    @Test
    public void equalsReturnTruePositiveTest() {
        StringList exp = new StringListImpl(2);
        exp.add(0, "1");
        exp.add(1, "2");
        boolean expected = true;
        out.add(0, "1");
        out.add(1, "2");
        assertThat(out.equals(exp))
                .isEqualTo(expected);
    }

    @Test
    public void equalsReturnFalsePositiveTest() {
        StringList exp = new StringListImpl(2);
        exp.add(0, "1");
        exp.add(1, "2");
        exp.add(2, "3");
        boolean expected = false;
        out.add(0, "1");
        out.add(1, "2");
        assertThat(out.equals(exp))
                .isEqualTo(expected);

        out.add(2, "5");
        assertThat(out.equals(exp))
                .isEqualTo(expected);
    }

    @Test
    public void equalsNullNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> out.equals(null));
    }

    @Test
    public void sizeTest() {
        int expected = 3;

        out.add(0, "1");
        out.add(1, "2");
        out.add(2, "3");
        assertThat(out.size())
                .isEqualTo(expected);
    }

    @Test
    public void isEmptyTruePositiveTest() {
        boolean expected = true;
        assertThat(out.isEmpty())
                .isEqualTo(expected);
    }

    @Test
    public void isEmptyFalsePositiveTest() {
        boolean expected = false;
        out.add("1");
        assertThat(out.isEmpty())
                .isEqualTo(expected);
    }

    @Test
    public void clearPositiveTest() {
        String expected = null;
        out.add("1");
        out.add("2");
        out.add("3");
        assertThat(out.clear())
                .isEqualTo(expected);
    }

    @Test
    public void toArrayPositiveTest() {
        String[] expected = {"1", "2", "3"};
        out.add(0, "1");
        out.add(1, "2");
        out.add(2, "3");
        String[] array = out.toArray();
        assertEquals(Arrays.toString(expected), Arrays.toString(array));
    }

    public static Stream<Arguments> params() {
        StringList arrayParams = new StringListImpl();
        arrayParams.add("1");
        arrayParams.add("2");
        arrayParams.add("3");
        arrayParams.add("4");
        arrayParams.add("5");
        arrayParams.add("6");
        arrayParams.add("7");
        arrayParams.add("8");
        arrayParams.add("9");
        arrayParams.add("10");
        return Stream.of(
                Arguments.of(arrayParams
                )
        );
    }
}