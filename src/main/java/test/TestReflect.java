package test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import model.Person;

public class TestReflect {


    public static void main(String[] args)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Person person = new Person();
        person.setAge(12);
        person.setName("mike");



        Class<? extends Person> personClass = person.getClass();


        Field field = personClass.getDeclaredField("age");

        field.setAccessible(true);

        Object age = field.get(person);

        System.out.println(age);

        Method getAge = personClass.getDeclaredMethod("getAge");

        Object ageInvoke = getAge.invoke(person);

        System.out.println(ageInvoke);


    }


}
