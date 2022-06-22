package test;

/**
 * TestInt
 *
 * @author by gatesma.
 */
public class TestInt {

    public static void test(Integer a) {
        a = 10;
    }

    public static void main(String[] args) {

        Integer a = 1;
        test(a);
        System.out.println(a);

    }

}
