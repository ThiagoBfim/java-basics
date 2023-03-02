package com.bomfim.collections;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class CollectionsPerformance {

    public static final String MILLIS = " millis";

    private static HashSet<Integer> hashSet = new HashSet<>();
    private static TreeSet<Integer> treeSet = new TreeSet<>();
    private static LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
    private static ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
    private static Stack<Integer> stack = new Stack<>();
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private static LinkedList<Integer> linkedList = new LinkedList<>();

    public static void main(String[] args) {
        var count = 1_000_000;
        Collection<Integer> elements = IntStream.rangeClosed(0, count).boxed().toList();

        System.out.println("Add operation");
        System.out.println(addElementsToCollection(elements, Instant.now(), hashSet) + MILLIS); //156 millis
        System.out.println(addElementsToCollection(elements, Instant.now(), treeSet) + MILLIS); //432 millis
        System.out.println(addElementsToCollection(elements, Instant.now(), linkedHashSet) + MILLIS); //115 millis
        System.out.println(addElementsToCollection(elements, Instant.now(), arrayDeque) + MILLIS); //55 millis
        System.out.println(addElementsToCollection(elements, Instant.now(), stack) + MILLIS); //94 millis
        System.out.println(addElementsToCollection(elements, Instant.now(), arrayList) + MILLIS); //36 millis
        System.out.println(addElementsToCollection(elements, Instant.now(), linkedList) + MILLIS); //160 millis

        System.out.println(" ------------------------------ ");
        System.out.println("Contains operation");

        System.out.println(containsElement(Instant.now(), hashSet, 546_124) + MILLIS); //0 millis
        System.out.println(containsElement(Instant.now(), treeSet, 546_124) + MILLIS); //0 millis
        System.out.println(containsElement(Instant.now(), linkedHashSet, 546_124) + MILLIS); //0 millis
        System.out.println(containsElement(Instant.now(), arrayDeque, 546_124) + MILLIS); //16 millis
        System.out.println(containsElement(Instant.now(), stack, 546_124) + MILLIS); //17 millis
        System.out.println(containsElement(Instant.now(), arrayList, 546_124) + MILLIS); //18 millis
        System.out.println(containsElement(Instant.now(), linkedList, 546_124) + MILLIS); //16 millis

        System.out.println(" ------------------------------ ");
        System.out.println("Remove operation");

        System.out.println(removeElement(Instant.now(), hashSet, 546_124) + MILLIS); //0 millis
        System.out.println(removeElement(Instant.now(), treeSet, 546_124) + MILLIS); //0 millis
        System.out.println(removeElement(Instant.now(), linkedHashSet, 546_124) + MILLIS); //0 millis
        System.out.println(removeElement(Instant.now(), arrayDeque, 546_124) + MILLIS); //12 millis
        System.out.println(removeElement(Instant.now(), stack, 546_124) + MILLIS); //2 millis
        System.out.println(removeElement(Instant.now(), arrayList, 546_124) + MILLIS); //11 millis
        System.out.println(removeElement(Instant.now(), linkedList, 546_124) + MILLIS); //9 millis

    }

    private static long addElementsToCollection(Collection<Integer> elements, Instant before, Collection<Integer> collection) {
        elements.forEach(collection::add);
        Instant after = Instant.now();
        return Duration.between(before, after).toMillis();
    }

    private static long containsElement(Instant before, Collection<Integer> collection, int element) {
        final boolean contains = collection.contains(element);
        Instant after = Instant.now();
        return Duration.between(before, after).toMillis();
    }

    private static long removeElement(Instant before, Collection<Integer> collection, int element) {
        final boolean remove = collection.remove(element);
        Instant after = Instant.now();
        return Duration.between(before, after).toMillis();
    }

}
