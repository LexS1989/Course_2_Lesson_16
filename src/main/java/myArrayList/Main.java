package myArrayList;

import myArrayList.server.IntegerList;
import myArrayList.server.IntegerListImpl;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();


        IntegerList testGrow = new IntegerListImpl();
        System.out.println(testGrow);
        for (int i = 0; i < 24; i++) {
            int a = random.nextInt(100);
            testGrow.add(a);
        }
        System.out.println(testGrow);
        System.out.println("testGrow.size() = " + testGrow.size());
        System.out.println("testGrow.contains(88) = " + testGrow.contains(88));
        System.out.println(testGrow);
    }
}
