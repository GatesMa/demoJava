package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * TestSet
 *
 * @author by gatesma.
 */
public class TestSet {

    public static void main(String[] args) {

        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");

        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("3");

        System.out.println(Stream.of("1", "2").anyMatch(set::contains));
        System.out.println(Collections.disjoint(set, list));

    }

}
