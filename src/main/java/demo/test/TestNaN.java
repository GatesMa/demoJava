package test;

public class TestNaN {

    public static void main(String[] args) {

        Double f1 = new Double(1.0 / 0.0);

        System.out.println(f1.doubleValue());
        System.out.println(f1.isNaN());


    }

}
