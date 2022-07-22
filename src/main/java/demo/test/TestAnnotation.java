package test;

import annotation.MyAnnotation;
import java.lang.reflect.Method;
import model.Person;

public class TestAnnotation {

    public static void main(String[] args) throws NoSuchMethodException {

        Person person = new Person();

        person.setName("Holk");
        person.setAge(20);

        Class<Person> personClass = Person.class;


        // 类
        MyAnnotation annotation = personClass.getAnnotation(MyAnnotation.class);
        String value = annotation.value();
        System.out.println(value);

        // 方法
        Method getAge = personClass.getMethod("getAge");
        MyAnnotation annotation1 = getAge.getAnnotation(MyAnnotation.class);
        System.out.println(annotation1.value());;


    }

}
