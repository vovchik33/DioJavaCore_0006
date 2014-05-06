package edu.dio.lesson5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by flashconsult on 06.05.2014.
 */
public class Main {
    public static void main(String[] args) {
        Person[] sourceArray1 = new Person[]{
                new Person.Builder()
                        .setFirstName("Vladimir")
                        .setLastName("Kravchenko")
                        .setAge(32)
                        .setMail("vovchik33@gmail.com")
                        .build(),
                new Person.Builder()
                        .setFirstName("Vladimir")
                        .setLastName("Jabchenko")
                        .setAge(32)
                        .setMail("vovchik33@gmail.com")
                        .build(),
                new Person.Builder()
                        .setFirstName("Vladimir")
                        .setLastName("Savchenko")
                        .setAge(32)
                        .setMail("flashconsult@gmail.com")
                        .build()
        };

        Person[] sourceArray2 = new Person[]{
                new Person.Builder()
                        .setFirstName("Sergey")
                        .setLastName("Kravchenko")
                        .setAge(32)
                        .setMail("vovchik33@gmail.com")
                        .build(),
                new Person.Builder()
                        .setFirstName("Alexey")
                        .setLastName("Kravchenko")
                        .setAge(32)
                        .setMail("flashconsult@gmail.com")
                        .build()
        };

        ArrayWrapper arrayWrapper = new ArrayWrapper();

        arrayWrapper.setSourceData(sourceArray1);
        arrayWrapper.setAdditionalData(sourceArray2);

        System.out.println("Source Data" + Arrays.toString(arrayWrapper.getSourceData()));
        System.out.println("Additional Data " + Arrays.toString(arrayWrapper.getAdditionalData()));

        System.out.println("Merge Result " + Arrays.toString(arrayWrapper.getMerge()));
        System.out.println("Inner Join Result " + Arrays.toString(arrayWrapper.getInnerJoin()));
        System.out.println("Outer Join Result " + Arrays.toString(arrayWrapper.getOuterJoin()));

        // Using copmarator for sorting and binary searching
        class LastNameComparator implements Comparator<Person> {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        }
        Arrays.sort(sourceArray1, new LastNameComparator());
        System.out.println(Arrays.binarySearch(sourceArray1,
                new Person.Builder()
                        .setFirstName("Vladimir")
                        .setLastName("Kravchenko")
                        .setAge(32)
                        .setMail("vovchik33@gmail.com")
                        .build(),
                new LastNameComparator()
        ));

    }
}
