package com.bomfim.passbyvalue;

public class PassByValue {

    public static void main(String[] args) {

        var p = new Person();
        p.age = 10;
        changeAge(p);
        System.out.println(p.age); //5

        changeAgeAndPerson(p);
        System.out.println(p.age); //5
    }

    private static void changeAge(Person p) {
        p.age = 5;
    }

    private static void changeAgeAndPerson(Person p) {
        p = new Person();
        p.age = 2;
    }

    public static class Person {
        Integer age;
    }
}
