## Pass By Value

Java is a pass-by-value language. It means that is not possible to change the value from a method parameters, but it's not just that. Follow to understand more.

### Pass-by-value vs Pass-by-reference

**Pass-by-Value:**  The caller and the called method will operate on two different parameters which are copies of each other. Most programming languages use pass-by-value.

**Pass-by-reference:** The caller and the called method will operate on the same object. 

### Java example

Let's consider this example:

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
        int age;
    }

In this case, the output is 5,5.

### Object attributes keep the pointers to the real reference

Why is 5,5 and not 10,10 ?

Whenever an object is passed as an argument, an exact copy of the reference variable is created which points to the same location of the object in heap memory as the original reference variable. This means that we have the pointers to the real object, but if we try to point to a new instance we are just changing the pointers, and not the real object.

Let's get deep on it.

In the case of changing age of the person, we have something like:

![resources/](resources/PassByValue-Change%20Age.drawio.png)

This means that the method `changeAge(Person p)` will point to the real object, and with that, we can change the attributes of this object.

In the case of changing the person and age, we have something like:

![resources/](resources/PassByValue-Change%20Age%20and%20Person.drawio.png)

This means that the method `changeAgeAndPerson(Person p)` will point to the real object, but when we use `p = new Person();` we are pointing to a new instance in the memory, and because of it, we cannot change the real object age, just this new instance.



### Conclusion

Java is a pass-by-value language, which means that using the final for the parameters does not bring much value. Using final for the parameters means that you cannot attribute a new instance of the object, but it doesn't change the real object.


### References

* https://www.baeldung.com/java-pass-by-value-or-pass-by-reference
* https://www.youtube.com/watch?v=HXvKLn5RkRQ
* https://dzone.com/articles/pass-by-value-vs-reference-in-java